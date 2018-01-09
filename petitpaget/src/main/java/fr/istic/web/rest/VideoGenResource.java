package fr.istic.web.rest;

import fr.pagetpetit.videogentools.VideoGenHelper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xtext.example.mydsl.videoGen.*;

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

    @GetMapping("/videogen/{filepath}")
    public VideoGeneratorModelWrapper getVideoGenModel(@PathVariable String filepath){
        filepath = "data/input/videogen/" + filepath + ".videogen";
        log.debug("getVideoGenModel : " + filepath);
        //return addClues(videogenHelper.loadVideoGenerator(URI.createURI(filepath)));
        return wrap(videogenHelper.loadVideoGenerator(URI.createURI(filepath)));
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
            MediaWrapper mediaWrapper;
            if(media instanceof MandatoryMedia){
                mediaWrapper = new MandatoryMediaWrapper();
                MandatoryMedia mandatoryMedia = (MandatoryMedia) media;
                ((MandatoryMediaWrapper) mediaWrapper).description = mandatoryMedia.getDescription();
                String type;
                if(mandatoryMedia.getDescription() instanceof VideoDescription){
                    type = "mv";
                }else{
                    type = "mi";
                }
                mediaWrapper.type = type;
                wrapper.medias.add(mediaWrapper);
            }else if(media instanceof OptionalMedia){
                mediaWrapper = new OptionalMediaWrapper();
                OptionalMedia optionalMedia = (OptionalMedia) media;
                ((OptionalMediaWrapper) mediaWrapper).description = optionalMedia.getDescription();
                String type;
                if(optionalMedia.getDescription() instanceof VideoDescription){
                    type = "ov";
                }else{
                    type = "oi";
                }
                mediaWrapper.type = type;
                wrapper.medias.add(mediaWrapper);
            }else{
                mediaWrapper = new AlternativesMediaWrapper();
                AlternativesMedia alternativesMedia = (AlternativesMedia) media;
                ((AlternativesMediaWrapper) mediaWrapper).medias = alternativesMedia.getMedias();
                String type;
                // Videos not mixed in with images
                if(alternativesMedia.getMedias().get(0) instanceof VideoDescription){
                    type = "av";
                }else{
                    type = "ai";
                }
                mediaWrapper.type = type;
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
        public MediaDescription description;
    }

    private class OptionalMediaWrapper extends MediaWrapper{
        public MediaDescription description;
    }

    private class AlternativesMediaWrapper extends MediaWrapper{
        public int id;
        public EList<MediaDescription> medias;
    }

}
