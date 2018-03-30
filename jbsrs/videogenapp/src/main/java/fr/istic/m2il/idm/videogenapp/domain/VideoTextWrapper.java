package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.VideoText;

/**
 * @author ismael
 */

public class VideoTextWrapper {
    public VideoText videoText;

    public VideoText getVideoText() {
        return videoText;
    }

    public void setVideoText(VideoText videoText) {
        this.videoText = videoText;
    }

    public VideoTextWrapper(VideoText videoText) {
        this.videoText = videoText;
    }

    public VideoTextWrapper() {
    }
}
