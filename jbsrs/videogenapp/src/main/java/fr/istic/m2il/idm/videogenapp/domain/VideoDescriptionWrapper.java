package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoText;

/**
 * @author ismael
 */

public class VideoDescriptionWrapper extends MediaDescriptionWrapper{

    public FilterWrapper filterWrapper;
    public VideoTextWrapper videoTextWrapper;

    public VideoTextWrapper getVideoTextWrapper() {
        return videoTextWrapper;
    }



    public void setVideoTextWrapper(VideoTextWrapper videoTextWrapper) {
        this.videoTextWrapper = videoTextWrapper;
    }

    public FilterWrapper getFilterWrapper() {
        return filterWrapper;
    }

    public void setFilterWrapper(FilterWrapper filterWrapper) {
        this.filterWrapper = filterWrapper;
    }

    public VideoDescriptionWrapper(String thumb_url, MediaDescription description, boolean selected, FilterWrapper filterWrapper, VideoTextWrapper videoTextWrapper) {
        super();
        this.description = description;
        this.selected = selected;
        this.thumb_url = thumb_url;
        this.filterWrapper = filterWrapper;
        this.videoTextWrapper = videoTextWrapper;
    }

    public VideoDescriptionWrapper() {
    }
}
