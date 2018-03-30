package fr.istic.m2il.idm.videogenapp.domain;


import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.MediaDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ismael
 */

public class AlternativesMediaWrapper extends MediaWrapper{
    public int id;
    public List<MediaDescriptionWrapper> descriptionWrappers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MediaDescriptionWrapper> getDescriptionWrappers() {
        return descriptionWrappers;
    }

    public void setDescriptionWrappers(EList<MediaDescriptionWrapper> descriptionWrappers) {
        this.descriptionWrappers = descriptionWrappers;
    }

    public AlternativesMediaWrapper(String type, int id, List<MediaDescriptionWrapper> descriptionWrappers) {
        super(type);
        this.id = id;
        this.descriptionWrappers = descriptionWrappers;
    }

    public AlternativesMediaWrapper(String type) {
        super(type);
    }

    public AlternativesMediaWrapper() {
        super();
    }
}
