package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.impl.MediaDescriptionImpl;

/**
 * @author ismael
 */

public class MediaDescriptionWrapper {
    public String thumb_url;
    public MediaDescription description;
    public boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public MediaDescription getDescription() {
        return description;
    }

    public void setDescription(MediaDescription description) {
        this.description = description;
    }

    public MediaDescriptionWrapper() {

    }
}

