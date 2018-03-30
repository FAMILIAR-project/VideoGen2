package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.ImageDescription;

/**
 * @author ismael
 */

public class ImageDescriptionWrapper extends MediaDescriptionWrapper{
    public ImageDescription imageDescription;

    public ImageDescription getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(ImageDescription imageDescription) {
        this.imageDescription = imageDescription;
    }

    public ImageDescriptionWrapper(ImageDescription imageDescription) {
        super();
        this.imageDescription = imageDescription;
    }

    public ImageDescriptionWrapper() {
    }
}
