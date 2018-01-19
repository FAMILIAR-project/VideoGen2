package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.MediaDescription;

/**
 * @author ismael
 */

public class MandatoryMediaWrapper extends MediaWrapper{
    public MediaDescriptionWrapper descriptionWrapper;

    public MandatoryMediaWrapper(String type) {
        super(type);
    }

    public MediaDescriptionWrapper getDescriptionWrapper() {
        return descriptionWrapper;
    }

    public void setDescriptionWrapper(MediaDescriptionWrapper descriptionWrapper) {
        this.descriptionWrapper = descriptionWrapper;
    }

    public MandatoryMediaWrapper(String type, MediaDescriptionWrapper descriptionWrapper) {
        super(type);
        this.descriptionWrapper = descriptionWrapper;
    }

    public MandatoryMediaWrapper() {
        super();
    }
}
