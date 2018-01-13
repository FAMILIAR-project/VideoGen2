package fr.istic.m2il.idm.videogenapp.service;

/**
 * @author ismael
 */

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

/**
 * Service class for managing videogen.
 */
@Service
@Transactional
public class VideoGenService {

    private final Logger log = LoggerFactory.getLogger(VideoGenService.class);

    private final VideoGenRepository videoGenRepository;

    public VideoGenService(VideoGenRepository videoGenRepository) {
        this.videoGenRepository = videoGenRepository;
    }

    public void save(VideoGen videoGen){
        videoGenRepository.save(videoGen);
    }

    public VideoGen wrapVideoGen(VideoGeneratorModel videoGeneratorModel){
        VideoGen videogen = new VideoGen();
        videogen.setAuthor(videoGeneratorModel.getInformation().getAuthorName());
        videogen.setDate(videoGeneratorModel.getInformation().getCreationDate());
        videogen.setVersion(videoGeneratorModel.getInformation().getVersion());
        videoGeneratorModel.getMedias().stream().forEach(media -> {
            if(media instanceof MandatoryMedia){
                videogen.getVideosUrls().add(((MandatoryMedia) media).getDescription().getLocation());
            }
            if(media instanceof OptionalMedia){
                videogen.getVideosUrls().add(((OptionalMedia) media).getDescription().getLocation());
            }
            if(media instanceof AlternativesMedia){
                ((AlternativesMedia) media).getMedias().stream().forEach(alt ->{
                    videogen.getVideosUrls().add(alt.getLocation());
                });
            }
        });
        return videogen;
    }
}
