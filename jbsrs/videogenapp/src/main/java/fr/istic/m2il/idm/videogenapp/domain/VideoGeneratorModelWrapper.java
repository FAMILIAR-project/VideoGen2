package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.VideoGenInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ismael
 */

public class VideoGeneratorModelWrapper{
    public VideoGenInformation information;
    public List<MediaWrapper> medias = new ArrayList<>();

    public VideoGenInformation getInformation() {
        return information;
    }

    public void setInformation(VideoGenInformation information) {
        this.information = information;
    }

    public List<MediaWrapper> getMedias() {
        return medias;
    }

    public void setMedias(List<MediaWrapper> medias) {
        this.medias = medias;
    }

    public VideoGeneratorModelWrapper(VideoGenInformation information, List<MediaWrapper> medias) {
        this.information = information;
        this.medias = medias;
    }

    public VideoGeneratorModelWrapper(){

    }
}
