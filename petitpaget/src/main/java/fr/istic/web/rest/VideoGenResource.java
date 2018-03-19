package fr.istic.web.rest;

import fr.istic.web.rest.util.HeaderUtil;
import fr.pagetpetit.videogentools.VideoGenHelper;
import fr.pagetpetit.videogentools.VideoGenUtils;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xtext.example.mydsl.videoGen.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);
    private final VideoGenHelper videogenHelper;

    public VideoGenResource(){
        this.videogenHelper = new VideoGenHelper();
    }

    @GetMapping("/videogen/{filename}")
    public VideoGeneratorModelWrapper getVideoGenModel(@PathVariable String filename){
        filename = "data/input/videogen/" + filename + ".videogen";
        log.debug("getVideoGenModel : " + filename);
        VideoGeneratorModel model = videogenHelper.loadVideoGenerator(URI.createURI(filename));
        VideoGenUtils.generateThumbnails(model, "target/www/data/output/thumbnails");
        return wrap(model);
    }

    @PostMapping("/videogen")
    public ResponseEntity generatePlaylist(@Valid @RequestBody List<String> videos){
        String location = VideoGenUtils.generatePlaylist(videos.toArray(new String[0]), "target/www/data/output");
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert( "A playlist was generated at ", location))
            .body(location.replace("target/www/", ""));
    }

    @GetMapping("videogen/random/{filename}")
    public ResponseEntity generateRandomVariant(@PathVariable String filename){
        filename = "data/input/videogen/" + filename + ".videogen";
        log.debug("getRandomVariant : " + filename);
        VideoGeneratorModel model = videogenHelper.loadVideoGenerator(URI.createURI(filename));
        String[] variant = VideoGenUtils.getRandomVariant(model);
        String location = VideoGenUtils.generatePlaylist(variant, "target/www/data/output");
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert( "A Random Variant was generated at ", location))
            .body(location.replace("target/www/", ""));
    }

    @GetMapping("videogen/files")
    public String[] getVideoGenFiles(){
        File videoGenFolder = new File("data/input/videogen");
        String[] res = videoGenFolder.list(
            (dir,name)-> name.endsWith(".videogen")
        );
        String[] output = new String[res.length];
        for(int i = 0; i < res.length; i++){
            output[i] = res[i].replace(".videogen", "");
        }
        return output;
    }

    @Deprecated
    private VideoGeneratorModel addClues(VideoGeneratorModel model){
        for(Media media : model.getMedias()){
            if(media instanceof MandatoryMedia){
                MandatoryMedia mandatoryMedia = (MandatoryMedia) media;
                String location = (mandatoryMedia.getDescription() instanceof VideoDescription ? "mv" : "mi" )
                    + mandatoryMedia.getDescription().getLocation();
                mandatoryMedia.getDescription().setLocation(location);
            }else if(media instanceof OptionalMedia){
                OptionalMedia optionalMedia = (OptionalMedia) media;
                String location = (optionalMedia.getDescription() instanceof VideoDescription ? "ov" : "oi" )
                    + optionalMedia.getDescription().getLocation();
                optionalMedia.getDescription().setLocation(location);
            }else if(media instanceof AlternativesMedia){
                for(MediaDescription mediaDescription : ((AlternativesMedia) media).getMedias()){
                    String location = (mediaDescription instanceof VideoDescription ? "av" : "ai" )
                        + mediaDescription.getLocation();
                    mediaDescription.setLocation(location);
                }
            }
        }
        return model;
    }

    private VideoGeneratorModelWrapper wrap(VideoGeneratorModel model){
        VideoGeneratorModelWrapper wrapper = new VideoGeneratorModelWrapper();
        wrapper.information = model.getInformation();
        for(Media media : model.getMedias()){
            if(media instanceof MandatoryMedia){
                MandatoryMediaWrapper mediaWrapper = new MandatoryMediaWrapper();
                MandatoryMedia mandatoryMedia = (MandatoryMedia) media;

                if(mandatoryMedia.getDescription() instanceof VideoDescription){
                    mediaWrapper.type = "mv";
                    mediaWrapper.description = new VideoDescriptionWrapper((VideoDescription) mandatoryMedia.getDescription(), true);
                }else{
                    mediaWrapper.type = "mi";
                }
                wrapper.medias.add(mediaWrapper);
            }else if(media instanceof OptionalMedia){
                OptionalMediaWrapper mediaWrapper = new OptionalMediaWrapper();
                OptionalMedia optionalMedia = (OptionalMedia) media;

                if(optionalMedia.getDescription() instanceof VideoDescription){
                    mediaWrapper.type = "ov";
                    mediaWrapper.description = new VideoDescriptionWrapper((VideoDescription) optionalMedia.getDescription(), false);
                }else{
                    mediaWrapper.type = "oi";
                }
                wrapper.medias.add(mediaWrapper);
            }else{
                AlternativesMediaWrapper mediaWrapper = new AlternativesMediaWrapper();
                AlternativesMedia alternativesMedia = (AlternativesMedia) media;
                mediaWrapper.id = alternativesMedia.getId();
                // Videos not mixed in with images
                if(alternativesMedia.getMedias().get(0) instanceof VideoDescription){
                    mediaWrapper.type = "av";
                    boolean selected = true;
                    for(MediaDescription desc : alternativesMedia.getMedias()){
                        mediaWrapper.medias.add(new VideoDescriptionWrapper((VideoDescription) desc, selected));
                        selected = false;
                    }
                }else{
                    mediaWrapper.type = "ai";
                }
                wrapper.medias.add(mediaWrapper);
            }
        }

        return wrapper;
    }

    private class VideoGeneratorModelWrapper{
        public VideoGenInformation information;
        public List<MediaWrapper> medias = new ArrayList<>();
    }

    private abstract class MediaWrapper {
        public String type;
    }

    private class MandatoryMediaWrapper extends MediaWrapper{
        public MediaDescriptionWrapper description;
    }

    private class OptionalMediaWrapper extends MediaWrapper{
        public MediaDescriptionWrapper description;
    }

    private class AlternativesMediaWrapper extends MediaWrapper{
        public String id;
        public List<MediaDescriptionWrapper> medias = new ArrayList<>();
    }

    private class MediaDescriptionWrapper{
        public String location;
    }

    private class VideoDescriptionWrapper extends MediaDescriptionWrapper{
        public String videoId;
        public int duration;
        public int probability;
        public String description;
        public Filter filter;
        public VideoText videoText;
        public String thumbnail;
        public boolean selected;

        public VideoDescriptionWrapper(VideoDescription videoDescription, boolean selected) {
            super();
            location = videoDescription.getLocation();
            videoId = videoDescription.getVideoid();
            duration = videoDescription.getDuration();
            probability = videoDescription.getProbability();
            description = videoDescription.getDescription();
            filter = videoDescription.getFilter();
            videoText = videoDescription.getText();
            String[] file = location.replace(".mp4", ".png").split("/");
            thumbnail = "data/output/thumbnails/" + file[file.length - 1];
            this.selected = selected;
        }
    }

    private class ImageDescriptionWrapper extends MediaDescriptionWrapper{
        public String imageId;
        public String top;
        public String bottom;
    }

}
