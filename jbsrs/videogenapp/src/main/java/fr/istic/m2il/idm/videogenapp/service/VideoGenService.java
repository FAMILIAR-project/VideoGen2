package fr.istic.m2il.idm.videogenapp.service;

import fr.istic.m2il.idm.videogenapp.domain.*;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xtext.example.mydsl.videoGen.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing VideoGen.
 */
@Service
@Transactional
public class VideoGenService {

    private final Logger log = LoggerFactory.getLogger(VideoGenService.class);

    private final VideoGenRepository videoGenRepository;
    private VideoGenHelper videogenHelper;

    public VideoGenService(VideoGenRepository videoGenRepository) {
        this.videoGenRepository = videoGenRepository;
        this.videogenHelper = new VideoGenHelper();
    }

    /**
     * Save a videoGen.
     *
     * @param videoGen the entity to save
     * @return the persisted entity
     */
    public VideoGen save(VideoGen videoGen) {
        log.debug("Request to save VideoGen : {}", videoGen);
        videoGen.getVideosUrls().add("list1.mp4");
        return videoGenRepository.save(videoGen);
    }

    /**
     * Get all the videoGens.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<VideoGen> findAll() {
        log.debug("Request to get all VideoGens");
        return videoGenRepository.findAll();
    }

    /**
     * Get one videoGen by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public VideoGen findOne(Long id) {
        log.debug("Request to get VideoGen : {}", id);
        return videoGenRepository.findOne(id);
    }

    /**
     * Delete the videoGen by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete VideoGen : {}", id);
        videoGenRepository.delete(id);
    }

    public VideoGeneratorModelWrapper wrap(VideoGeneratorModel model, List<String> thumbsUrls){
        VideoGeneratorModelWrapper wrapper = new VideoGeneratorModelWrapper();
        wrapper.information = model.getInformation();
        int thumbsUrlsIndex = 0;
        for(Media media : model.getMedias()){
            MediaWrapper mediaWrapper;
            if(media instanceof MandatoryMedia){
                mediaWrapper = new MandatoryMediaWrapper();
                MandatoryMedia mandatoryMedia = (MandatoryMedia) media;

                if(mandatoryMedia.getDescription() instanceof VideoDescription){
                    ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper = new VideoDescriptionWrapper();
                    ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper.selected = true;
                    ((VideoDescriptionWrapper)((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper).filterWrapper = getFilter((VideoDescription) mandatoryMedia.getDescription());
                }
                else {
                    ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper = new ImageDescriptionWrapper();
                    ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper.selected = true;
                }
                ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper.setDescription(mandatoryMedia.getDescription());
                ((MandatoryMediaWrapper)mediaWrapper).descriptionWrapper.thumb_url = thumbsUrls.get(thumbsUrlsIndex);
                thumbsUrlsIndex++;
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

                if(optionalMedia.getDescription() instanceof VideoDescription){
                    ((OptionalMediaWrapper)mediaWrapper).descriptionWrapper = new VideoDescriptionWrapper();
                    if(((VideoDescription) optionalMedia.getDescription()).getFilter() != null){
                        ((VideoDescriptionWrapper)((OptionalMediaWrapper)mediaWrapper).descriptionWrapper).filterWrapper = getFilter((VideoDescription) optionalMedia.getDescription());
                    }
                }
                else {
                    ((OptionalMediaWrapper)mediaWrapper).descriptionWrapper = new ImageDescriptionWrapper();
                    ((OptionalMediaWrapper)mediaWrapper).descriptionWrapper.selected = true;
                }

                ((OptionalMediaWrapper)mediaWrapper).descriptionWrapper.setDescription(optionalMedia.getDescription());
                ((OptionalMediaWrapper)mediaWrapper).descriptionWrapper.thumb_url = thumbsUrls.get(thumbsUrlsIndex);
                thumbsUrlsIndex++;
                String type;
                if(optionalMedia.getDescription() instanceof VideoDescription){
                    type = "ov";
                }else{
                    type = "oi";
                }
                mediaWrapper.type = type;
                wrapper.medias.add(mediaWrapper);
            }else{
                mediaWrapper= new AlternativesMediaWrapper();
                AlternativesMedia alternativesMedia = (AlternativesMedia) media;
                for(MediaDescription alt : alternativesMedia.getMedias()){
                    MediaDescriptionWrapper mediaDescriptionWrapper;

                    if(alt instanceof VideoDescription){
                        mediaDescriptionWrapper = new VideoDescriptionWrapper();
                        if(((VideoDescription)alt).getFilter() != null){
                            ((VideoDescriptionWrapper)mediaDescriptionWrapper).filterWrapper = getFilter((VideoDescription) alt);
                        }
                    }
                    else {
                        mediaDescriptionWrapper = new ImageDescriptionWrapper();
                    }

                    mediaDescriptionWrapper.description = alt;
                    mediaDescriptionWrapper.thumb_url = thumbsUrls.get(thumbsUrlsIndex);
                    ((AlternativesMediaWrapper)mediaWrapper).descriptionWrappers.add(mediaDescriptionWrapper);
                    thumbsUrlsIndex++;
                }
                String type;
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

    public String getRandomVideoGenSpecification(Environment env) throws URISyntaxException {

        File videoGenFolder ;

        String [] profiles = env.getActiveProfiles();

        boolean isDev = false;
        for(String p:profiles){
            if(p.equals("dev")){
                isDev = true;
                break;
            }
        }

        if(isDev){
            videoGenFolder = new File("data/input/videogen");
        }
        else {
            videoGenFolder = new File("/data/input/videogen");
        }



        String[] specifications = videoGenFolder.list(
            (dir,name)-> name.endsWith(".videogen")
        );


        int randomIndex = (int) Math.random() * specifications.length ;
        File file = new File(specifications[randomIndex]);

        String[] files = file.getAbsolutePath().replace("\\", "/").split("/");

        return "data/input/videogen/" + files[files.length -1];
    }

    private FilterWrapper getFilter(VideoDescription videoDescription){
        FilterWrapper filterWrapper = null;
        if(videoDescription.getFilter() != null){
            if(videoDescription.getFilter() instanceof BlackWhiteFilter){
                filterWrapper = new BlackWhiteFilterWrapper();
                filterWrapper.filter = videoDescription.getFilter();
            }
            if(videoDescription.getFilter() instanceof NegateFilter){
                filterWrapper = new NegateFilterWrapper();
                filterWrapper.filter = videoDescription.getFilter();
            }
            if(videoDescription.getFilter() instanceof FlipFilter){
                filterWrapper = new FlipFilterWrapper();
                filterWrapper.filter = videoDescription.getFilter();
            }
        }
        return  filterWrapper;
    }
}
