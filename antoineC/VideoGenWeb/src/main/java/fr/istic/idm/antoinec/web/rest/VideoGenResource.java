package fr.istic.idm.antoinec.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.idm.antoinec.domain.VideoGen;

import fr.istic.idm.antoinec.repository.VideoGenRepository;
import fr.istic.idm.antoinec.service.videogenServices.VideoGenService;
import fr.istic.idm.antoinec.service.videogenServices.VideogenCompilerService;
import fr.istic.idm.antoinec.web.rest.errors.BadRequestAlertException;
import fr.istic.idm.antoinec.web.rest.errors.InternalServerErrorException;
import fr.istic.idm.antoinec.web.rest.util.HeaderUtil;
import fr.istic.idm.antoinec.web.rest.util.StreamHandler;
import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;
import java.util.function.Consumer;

/**
 * REST controller for managing VideoGen.
 */
@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);

    private static final String ENTITY_NAME = "videoGen";

    private final VideoGenRepository videoGenRepository;
    private final VideoGenService videoGenService;
    private final VideogenCompilerService videogenCompilerService;


    public VideoGenResource(VideoGenRepository videoGenRepository, VideogenCompilerService videoGenCompilerService, VideoGenService videoGenService) {
        this.videoGenRepository = videoGenRepository;
        this.videoGenService = videoGenService;
        this.videogenCompilerService = videoGenCompilerService;

    }

    /**
     * POST  /video-gens : Create a new videoGen.
     *
     * @param videoGen the videoGen to create
     * @param file the videogen source code File to store into server
     * @param assets the videogen medias Files to store into server
     * @return the ResponseEntity with status 201 (Created) and with body the new videoGen, or with status 400 (Bad Request) if the videoGen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(method = RequestMethod.POST, value = "/video-gens", headers = {"content-type=multipart/mixed", "content-type=multipart/form-data"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Timed
    public ResponseEntity<VideoGen> createVideoGen(@RequestPart("videogen") final VideoGen videoGen, @RequestPart("file") final MultipartFile file, @RequestPart("assets") final MultipartFile[] assets) throws URISyntaxException, IOException, InterruptedException {
        log.debug("REST request to create VideoGen : {}, Videogen size: {}, Assets: {}", videoGen, file.getSize(), assets.length);
        if (videoGen.getId() != null) {
            throw new BadRequestAlertException("A new videoGen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VideoGen result = videoGenRepository.save(videoGen);
        videoGenService.persistChanges(result, file, assets);
        videoGenService.hydrateWithFileHierarchy(result);

//        Process p = Runtime.getRuntime().exec("java -jar ../../videogen_compiler.jar INFOS " + FileUtils.getFile(VideoGenService.UPLOAD_DIR, result.getId() + "/" + result.getId() + ".videogen").getAbsolutePath(), null, FileUtils.getFile(VideoGenService.UPLOAD_DIR, result.getId().toString()));
//
//        StreamHandler info = new StreamHandler(p.getInputStream(), "INFO");
//        StreamHandler err = new StreamHandler(p.getErrorStream(), "ERROR");
//
//        Thread t1 = new Thread(info);
//        Thread t2 = new Thread(err);
//        t1.start();
//        t2.start();
//
//        p.waitFor();

        return ResponseEntity.created(new URI("/api/video-gens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /video-gens : Updates an existing videoGen.
     *
     * @param videoGen the videoGen to update
     * @param file the videogen source code File to store into server
     * @param assets the videogen medias Files to store into server
     * @return the ResponseEntity with status 200 (OK) and with body the updated videoGen,
     * or with status 400 (Bad Request) if the videoGen is not valid,
     * or with status 500 (Internal Server Error) if the videoGen couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/video-gens", headers = {"content-type=multipart/mixed", "content-type=multipart/form-data"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Timed
    public ResponseEntity<VideoGen> updateVideoGen(@RequestPart("videogen") final VideoGen videoGen, @RequestPart("file") final MultipartFile file, @RequestPart("assets") final MultipartFile[] assets) throws URISyntaxException, IOException, InterruptedException {
        log.debug("REST request to update VideoGen : {}, Videogen size: {}, Assets: {}", videoGen, file.getSize(), assets.length);
        if (videoGen.getId() == null) {
            return createVideoGen(videoGen, file, assets);
        }
        VideoGen result = videoGenRepository.save(videoGen);

        videoGenService.persistChanges(result, file, assets);
        videoGenService.hydrateWithFileHierarchy(result);

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


        List<VideoGen> videogens = videoGenRepository.findAll();
        videogens.forEach(new Consumer<VideoGen>() {
            @Override
            public void accept(VideoGen videoGen) {
                videoGenService.hydrateWithFileHierarchy(videoGen);
            }
        });
        return videogens;
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
        videoGenService.hydrateWithFileHierarchy(videoGen);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(videoGen));
    }


    /**
     * GET  /video-gens/:id/variante : download a video variante
     *
     * @param id the id of the videoGen to retrieve
     * @return void
     */
    @GetMapping("/video-gens/{id}/download/variante")
    @Timed
    public void downloadVariante(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to download VideoGen : {}", id);

        try {
            File variante = videogenCompilerService.getVariante(videoGenRepository.findOne(id));

            // get your file as InputStream
            InputStream is = new FileInputStream(variante);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        } catch (InvalidVideoGenGrammarException e) {
            throw new InternalServerErrorException("Error generating a video variante");
        }

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

        videoGenService.delete(id);
        videoGenRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
