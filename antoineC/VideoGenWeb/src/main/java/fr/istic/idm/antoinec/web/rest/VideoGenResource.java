package fr.istic.idm.antoinec.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.idm.antoinec.domain.VideoGen;

import fr.istic.idm.antoinec.repository.VideoGenRepository;
import fr.istic.idm.antoinec.service.FileUploader;
import fr.istic.idm.antoinec.web.rest.errors.BadRequestAlertException;
import fr.istic.idm.antoinec.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;

/**
 * REST controller for managing VideoGen.
 */
@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);

    private static final String ENTITY_NAME = "videoGen";

    private final VideoGenRepository videoGenRepository;

    @Autowired
    private FileUploader uploader;

    public VideoGenResource(VideoGenRepository videoGenRepository) {
        this.videoGenRepository = videoGenRepository;
    }

    /**
     * POST  /video-gens : Create a new videoGen.
     *
     * @param videoGen the videoGen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new videoGen, or with status 400 (Bad Request) if the videoGen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/video-gens")
    @Timed
    public ResponseEntity<VideoGen> createVideoGen(@RequestBody VideoGen videoGen) throws URISyntaxException {
        log.debug("REST request to save VideoGen : {}", videoGen);
        log.debug("REST request to save VideoGen file: {}", videoGen.getFile() != null ?  videoGen.getFile().substring(0, videoGen.getFile().length()> 20 ? 20 : videoGen.getFile().length()) : "NULL");

        if (videoGen.getId() != null) {
            throw new BadRequestAlertException("A new videoGen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VideoGen result = videoGenRepository.save(videoGen);


        if(videoGen.getFile() != null) {
            File videogenFile = FileUtils.getFile(FilenameUtils.normalize(videoGen.getName()) + ".videogen");

        }
        return ResponseEntity.created(new URI("/api/video-gens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

   /* @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/video-gens/upload", headers = {"content-type=multipart/mixed", "content-type=multipart/form-data"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Timed
    public ResponseEntity<String> uploadFile(@RequestPart("file") final MultipartFile[] files) throws ServletException, IOException {
        if(files == null)
            return ResponseEntity.badRequest().body("Error");

        String result = "";
        for(MultipartFile file : files) {
            result += file.getOriginalFilename() + "  ";
        }

        return ResponseEntity.ok("OK (" + result + ")");
    }

    /**
     * PUT  /video-gens : Updates an existing videoGen.
     *
     * @param videoGen the videoGen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated videoGen,
     * or with status 400 (Bad Request) if the videoGen is not valid,
     * or with status 500 (Internal Server Error) if the videoGen couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/video-gens")
    @Timed
    public ResponseEntity<VideoGen> updateVideoGen(@RequestBody VideoGen videoGen) throws URISyntaxException {
        log.debug("REST request to update VideoGen : {}", videoGen);
        if (videoGen.getId() == null) {
            return createVideoGen(videoGen);
        }
        VideoGen result = videoGenRepository.save(videoGen);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, videoGen.getId().toString()))
            .body(result);
    }

    /**
     * GET  /video-gens : get all the videoGens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of videoGens in body
     */
    @GetMapping("/video-gens")
    @Timed
    public List<VideoGen> getAllVideoGens() {
        log.debug("REST request to get all VideoGens");
        return videoGenRepository.findAll();
        }

    /**
     * GET  /video-gens/:id : get the "id" videoGen.
     *
     * @param id the id of the videoGen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the videoGen, or with status 404 (Not Found)
     */
    @GetMapping("/video-gens/{id}")
    @Timed
    public ResponseEntity<VideoGen> getVideoGen(@PathVariable Long id) {
        log.debug("REST request to get VideoGen : {}", id);
        VideoGen videoGen = videoGenRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(videoGen));
    }

    /**
     * DELETE  /video-gens/:id : delete the "id" videoGen.
     *
     * @param id the id of the videoGen to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/video-gens/{id}")
    @Timed
    public ResponseEntity<Void> deleteVideoGen(@PathVariable Long id) {
        log.debug("REST request to delete VideoGen : {}", id);
        videoGenRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
