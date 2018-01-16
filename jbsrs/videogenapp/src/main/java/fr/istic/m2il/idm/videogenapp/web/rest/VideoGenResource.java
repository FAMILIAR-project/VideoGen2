package fr.istic.m2il.idm.videogenapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.m2il.idm.videogenapp.VideogenappApp;
import fr.istic.m2il.idm.videogenapp.config.DefaultProfileUtil;
import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.domain.VideoGeneratorModelWrapper;
import fr.istic.m2il.idm.videogenapp.service.VideoGenService;
import fr.istic.m2il.idm.videogenapp.service.util.DevUtils;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.BadRequestAlertException;
import fr.istic.m2il.idm.videogenapp.web.rest.util.HeaderUtil;
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
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

    private final Environment env;

    private final VideoGenService videoGenService;
    private VideoGenHelper videogenHelper = new VideoGenHelper();
    private VideoGenConfigs videoGenConfigs = new VideoGenConfigs();

    public VideoGenResource(VideoGenService videoGenService, Environment env) {
        this.videoGenService = videoGenService;
        this.env = env;

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
     * @return the ResponseEntity with status 200 (OK) and the list of videoGens input body
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

    /*@GetMapping("/video-gens/{filename}")
    public VideoGeneratorModelWrapper getVideoGenModel(@PathVariable String filename){
        filename = "data/videogen/" + filename + ".videogen";
        log.debug("getVideoGenModel : " + filename);
        return videoGenService.wrap(videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename)));
    }*/

   /* @GetMapping("/video-gens/random/{filename}")
    public String generateRandomVariant(@PathVariable String filename){
        filename = "data/videogen/" + filename + ".videogen";
        log.debug("getRandomVariant : " + filename);
        VideoGeneratorModel model = videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename));
        String location = VideoGenPlayTransformations.generateRandomPlayList(model);
        return location;
    }*/

    /*@GetMapping("/video-gens/files")
    public String[] getVideoGenFiles(){
        File videoGenFolder = new File("data/videogen");
        return videoGenFolder.list(
            (dir,name)-> name.endsWith(".videogen")
        );
    }*/

    @GetMapping(value = "/video-gens/file/{filename}")
    public String getVideoFile(@PathVariable String filename)throws IOException {
        File videoGenFolder = new File("data/videogen");
        return videoGenFolder.getName();
    }

    @GetMapping("/video-gens/playlist/gif/{filename}")
    public VideoGeneratorModelWrapper getPlaylistGifs(@PathVariable String filename){
        filename = "data/videogen/" + filename + ".videogen";
        log.debug("getVideoGenModel : " + filename);
        return videoGenService.wrap(videogenHelper.loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(filename)));
    }

    @PostMapping("/video-gens/playlist/configure")
    public ResponseEntity generateConfigurePlaylist(@Valid @RequestBody List<String> videos){

        String location = VideoGenUtils.makePlaylist(videos, CommonUtils.getOutPutFileName("data/output/playlists/playlist.mp4"));
        return ResponseEntity.accepted()
            .headers(HeaderUtil.createAlert( "Generated playlist location ", location))
            .body(location);
    }

    @GetMapping("/video-gens/playlist/random")
    public String generateRandomPlaylist() throws URISyntaxException {

        VideoGenConfigs.setOutPutFoulder(new File("data/output").getPath());
        VideoGenConfigs.setServerIP("http://localhost:8080/");
        VideoGenConfigs.setGifResolutions(190, 60);
        VideoGenConfigs.initSubOutPutFolders();

        String specificationpath = this.videoGenService.getRandomVideoGenSpecification();
        System.out.println(new File(specificationpath).getAbsolutePath());
        VideoGeneratorModel videoGeneratorModel =
           new VideoGenHelper().loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(new File(specificationpath).getAbsolutePath()));
        String playlist = VideoGenPlayTransformations.generateRandomPlayList(videoGeneratorModel);

        File devToTarget = new File("target/www/data/output/playlists");

        String [] profiles = this.env.getActiveProfiles();

        boolean isDev = false;
        for(String p:profiles){
            if(p.equals("dev")){
                isDev = true;
                break;
            }
        }

        if(isDev){
            try {
                FileUtils.copyFileToDirectory(new File(playlist), devToTarget);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return playlist;

    }

    @GetMapping("/video-gens/variant/random")
    public List<String> getRandomVariant() throws URISyntaxException {
        VideoGenConfigs.setOutPutFoulder(new File("data/output").getPath());
        VideoGenConfigs.setServerIP("http://localhost:8080/");
        VideoGenConfigs.setGifResolutions(190, 60);
        VideoGenConfigs.initSubOutPutFolders();

        String specificationpath = this.videoGenService.getRandomVideoGenSpecification();

        VideoGeneratorModel videoGeneratorModel =
            new VideoGenHelper().loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(new File(specificationpath).getAbsolutePath()));

        List<String> thumbs = VideoGenPlayTransformations.makeThumbnails(videoGeneratorModel);

        System.out.println("Thumbs size " + thumbs.size());
        System.out.println("Videogen medias size " + videoGeneratorModel.getMedias().size());

        File devToTarget = new File("target/www/data/output/thumbs");

        String [] profiles = this.env.getActiveProfiles();

        boolean isDev = false;
        for(String p:profiles){
            if(p.equals("dev")){
                isDev = true;
                break;
            }
        }

        if(isDev){
            for(String thumb: thumbs){
                try {
                    FileUtils.copyFileToDirectory(new File(thumb), devToTarget);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        int i = 0;

        for(Media mediad: videoGeneratorModel.getMedias()){

        }


        return thumbs;
    }

}
