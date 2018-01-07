package fr.istic.web.rest;

import fr.pagetpetit.videogentools.VideoGenHelper;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);
    private final VideoGenHelper videogenHelper;

    public VideoGenResource(){
        this.videogenHelper = new VideoGenHelper();
    }

    @GetMapping("/videogen/{filepath}")
    public VideoGeneratorModel getVideoGenModel(@PathVariable String filepath){
        filepath = "data/input/videogen/" + filepath + ".videogen";
        log.debug("getVideoGenModel : " + filepath);
        return videogenHelper.loadVideoGenerator(URI.createURI(filepath));
    }
}
