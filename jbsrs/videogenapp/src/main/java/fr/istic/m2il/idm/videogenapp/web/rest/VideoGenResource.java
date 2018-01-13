package fr.istic.m2il.idm.videogenapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.m2il.idm.videogenapp.domain.User;
import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogenapp.repository.VideoGenRepository;
import fr.istic.m2il.idm.videogenapp.security.AuthoritiesConstants;
import fr.istic.m2il.idm.videogenapp.service.dto.UserDTO;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.BadRequestAlertException;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.EmailAlreadyUsedException;
import fr.istic.m2il.idm.videogenapp.web.rest.errors.LoginAlreadyUsedException;
import fr.istic.m2il.idm.videogenapp.web.rest.util.HeaderUtil;
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * @author ismael
 */

@RestController
@RequestMapping("/api")
public class VideoGenResource {

    private final Logger log = LoggerFactory.getLogger(VideoGenResource.class);

    private final VideoGenRepository videoGenRepository;

    public VideoGenResource(VideoGenRepository videoGenRepository) {
        this.videoGenRepository = videoGenRepository;
    }

    @PostMapping("/videos")
    @Timed
    public void createUser(){

    }
}
