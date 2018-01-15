package fr.istic.m2il.idm.videogenapp.service;

import fr.istic.m2il.idm.videogenapp.domain.*;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xtext.example.mydsl.videoGen.*;

import java.io.File;
import java.net.URISyntaxException;
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

    public VideoGeneratorModelWrapper wrap(VideoGeneratorModel model){
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
                // Videos not mixed input with images
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

    public String getRandomVideoGenSpecification() throws URISyntaxException {

        File videoGenFolder = new File("data/input/videogen");

        String[] specifications = videoGenFolder.list(
            (dir,name)-> name.endsWith(".videogen")
        );


        int randomIndex = (int) Math.random() * specifications.length ;
        File file = new File(specifications[randomIndex]);

        String[] files = file.getAbsolutePath().replace("\\", "/").split("/");

        return "data/input/videogen/" + files[files.length -1];
    }

    public String getRealName(File file){
        String[] files = file.getAbsolutePath().replace("\\", "/").split("/");

        return "data/input/videogen/" + files[files.length -1];
    }
}
