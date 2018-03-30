package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.Filter;

/**
 * @author ismael
 */

public class NegateFilterWrapper extends FilterWrapper{

    public NegateFilterWrapper(Filter filter) {
        super(filter);
    }

    public NegateFilterWrapper() {
    }
}
