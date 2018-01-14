package fr.istic.m2il.idm.videogenapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.domain.VideoGeneratorModelWrapper;
import fr.istic.m2il.idm.videogenapp.service.VideoGenService;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.BadRequestAlertException;
import fr.istic.m2il.idm.videogenapp.web.rest.util.HeaderUtil;
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VideoGen.
 */
@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);

    private static final String ENTITY_NAME = "videoGen";

    private final VideoGenService videoGenService;
    private VideoGenHelper videogenHelper = new VideoGenHelper();
    private VideoGenConfigs videoGenConfigs = new VideoGenConfigs();

    public VideoGenResource(VideoGenService videoGenService) {
        this.videoGenService = videoGenService;
        this.videoGenConfigs.setOutPutFoulder("data/output");
        this.videoGenConfigs.setServerIP("http://localhost:8080/");
        this.videoGenConfigs.setGifResolutions(190, 60);
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
        if (videoGen.getId() != null) {
            throw new BadRequestAlertException("A new videoGen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VideoGen result = videoGenService.save(videoGen);
        return ResponseEntity.created(new URI("/api/video-gens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
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
        VideoGen result = videoGenService.save(videoGen);
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
        return videoGenService.findAll();
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
        VideoGen videoGen = videoGenService.findOne(id);
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
        videoGenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/videogen/{filename}")
    public VideoGeneratorModelWrapper getVideoGenModel(@PathVariable String filename){
        filename = "data/input/videogen/" + filename + ".videogen";
        log.debug("getVideoGenModel : " + filename);
        return videoGenService.wrap(videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename)));
    }

    @GetMapping("/videogen/gifs/{filename}")
    public VideoGeneratorModelWrapper getGifs(@PathVariable String filename){
        filename = "data/input/videogen/" + filename + ".videogen";
        log.debug("getVideoGenModel : " + filename);
        return videoGenService.wrap(videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename)));
    }

    @PostMapping("/videogen")
    public ResponseEntity generatePlaylist(@Valid @RequestBody List<String> videos){

        String location = VideoGenUtils.makePlaylist(videos, CommonUtils.getOutPutFileName("data/output/playlists/playlist.mp4"));
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert( "Generated playlist location ", location))
            .body(location);
    }

    @GetMapping("videogen/random/{filename}")
    public String generateRandomVariant(@PathVariable String filename){
        filename = "data/input/videogen/" + filename + ".videogen";
        log.debug("getRandomVariant : " + filename);
        VideoGeneratorModel model = videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename));
        String location = VideoGenPlayTransformations.generateRandomPlayList(model);
        return location;
    }

    @GetMapping("videogen/files")
    public String[] getVideoGenFiles(){
        File videoGenFolder = new File("data/input/videogen");
        return videoGenFolder.list(
            (dir,name)-> name.endsWith(".videogen")
        );
    }


}
