package fr.istic.m2il.idm.videogenapp.service;

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing VideoGen.
 */
@Service
@Transactional
public class VideoGenService {

    private final Logger log = LoggerFactory.getLogger(VideoGenService.class);

    private final VideoGenRepository videoGenRepository;


    public VideoGenService(VideoGenRepository videoGenRepository) {
        this.videoGenRepository = videoGenRepository;
    }

    /**
     * Save a videoGen.
     *
     * @param videoGen the entity to save
     * @return the persisted entity
     */
    public VideoGen save(VideoGen videoGen) {
        log.debug("Request to save VideoGen : {}", videoGen);
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
}
