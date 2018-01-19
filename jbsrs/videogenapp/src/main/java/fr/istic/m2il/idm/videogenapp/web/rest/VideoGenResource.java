package fr.istic.m2il.idm.videogenapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.istic.m2il.idm.videogenapp.VideogenappApp;
import fr.istic.m2il.idm.videogenapp.config.DefaultProfileUtil;
import fr.istic.m2il.idm.videogenapp.domain.MediaDescriptionWrapper;
import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.domain.VideoGeneratorModelWrapper;
import fr.istic.m2il.idm.videogenapp.service.VideoGenService;
import fr.istic.m2il.idm.videogenapp.service.util.DevUtils;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.BadRequestAlertException;
import fr.istic.m2il.idm.videogenapp.web.rest.util.HeaderUtil;
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.zip.InflaterInputStream;

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

    @GetMapping("/video-gens/playlist/gifs/data/output/playlists/{playlist}")
    public String getPlaylistGifs(@PathVariable String playlist) throws IOException {
        String gifs = FFMPEGHelper.videoToGif("data/output/playlists/" + playlist + ".mp4", 200, 200);
        log.debug("getPlaylistGifs : " + playlist);
        File devToTarget = new File("target/www/data/output/gifs");



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
                FileUtils.copyFileToDirectory(new File(gifs), devToTarget);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*ClassPathResource cl = new ClassPathResource(gifs);
        System.out.println(" HASH "+cl.exists());

        File initialFile = new File(gifs);
        InputStream in = FileUtils.openInputStream(initialFile.getAbsoluteFile());


        //System. out.println("Document Pojo:" + document);
        //String pdfBase64 = IOUtils.toByteArray( new FileInputStream( initialFile.getAbsolutePath() ));
        //JSONResult result = new JSONResult();
        //result.setData( "data:application/pdf;base64," +pdfBase64);


        //response.setContentType("application/pdf");
        //response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
        /*InputStream inputStream = new FileInputStream(new File("C:\\demo-file.pdf"));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
        };*/



        /*return ResponseEntity.ok().contentLength(initialFile.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
            .body());*/
        return gifs;
    }


    @PostMapping("/video-gens/playlist/configure/")
   // @ApiImplicitParam(name = "num", value = "?????", required = true, dataType = "Long", paramType="path")
    public String generateConfigurePlaylist(@Valid @RequestBody String[] mediaDescriptionWrappers){

        List<String> medias = new ArrayList<>();
        for(String string: mediaDescriptionWrappers){
            medias.add(string);
        }
        String playlist = VideoGenUtils.makePlaylist(medias, CommonUtils.getOutPutFileName("data/output/playlists/playlist.mp4"));


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

    @GetMapping("/video-gens/playlist/random")
    //@ApiImplicitParam(name = "num", value = "?????", required = true, dataType = "Long", paramType="path")
    public String generateRandomPlaylist() throws URISyntaxException {

        String [] profiles = this.env.getActiveProfiles();

        VideoGenConfigs.setOutPutFolder(new File("data/output").getPath());
        VideoGenConfigs.setServerIP("http://localhost:8080/");
        VideoGenConfigs.setGifResolutions(190, 60);
        VideoGenConfigs.initSubOutPutFolders();

        String specificationpath = this.videoGenService.getRandomVideoGenSpecification(this.env);
        VideoGeneratorModel videoGeneratorModel =
           new VideoGenHelper().loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(new File(specificationpath).getAbsolutePath()));
        String playlist = VideoGenPlayTransformations.generateRandomPlayList(videoGeneratorModel);

        File devToTarget = new File("target/www/data/output/playlists");



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

    @GetMapping("/video-gens/model/random")
    //@ApiImplicitParam(name = "num", value = "?????", required = true, dataType = "Long", paramType="path")
    public String getRandomModel() throws URISyntaxException {
        VideoGenConfigs.setOutPutFolder(new File("data/output").getPath());
        VideoGenConfigs.setServerIP("http://localhost:8080/");
        VideoGenConfigs.setGifResolutions(200, 200);
        VideoGenConfigs.initSubOutPutFolders();

        String [] profiles = this.env.getActiveProfiles();

        String specificationpath = this.videoGenService.getRandomVideoGenSpecification(this.env);

        VideoGeneratorModel videoGeneratorModel =
            new VideoGenHelper().loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI(new File(specificationpath).getAbsolutePath()));

        List<String> thumbs = VideoGenPlayTransformations.makeThumbnails(videoGeneratorModel);

        File devToTarget = new File("target/www/data/output/thumbs");

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


        VideoGeneratorModelWrapper videoGeneratorModelWrapper = this.videoGenService.wrap(videoGeneratorModel, thumbs);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String someJsonString = null;
        try {
            someJsonString = mapper.writeValueAsString(videoGeneratorModelWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return someJsonString;
    }


}
