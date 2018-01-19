package fr.istic.m2il.idm.videogenapp.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author ismael
 */

public class MediaWrapper {
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MediaWrapper(String type) {
        this.type = type;
    }

    public MediaWrapper() {
    }
}
