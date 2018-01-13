package fr.istic.m2il.idm.videogenapp;

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import org.xtext.example.mydsl.videoGen.*;
import org.eclipse.emf.common.util.URI;

import java.util.Date;

/**
 * @author ismael
 */

public class Test {
    public static void main(String[] args){

        //videoGenRepository.save(v);
        VideoGenConfigs c =  new VideoGenConfigs();
        c.setGifResolutions(160,90);
        c.setOutPutFoulder("output");
        c.initSubOutPutFolders();
        VideoGenHelper helper = new VideoGenHelper();

        VideoGen v = new VideoGen();
        VideoGeneratorModel videoGeneratorModel = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample9.videogen"));
        String playlistPath = VideoGenPlayTransformations.generateRandomPlayList(videoGeneratorModel);
        System.out.println("Playlist path " + playlistPath);

    }
}
