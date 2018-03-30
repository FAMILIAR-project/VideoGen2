package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.Filter;

/**
 * @author ismael
 */

public class FilterWrapper {
    public Filter filter;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public FilterWrapper(Filter filter) {
        this.filter = filter;
    }

    public FilterWrapper(){

    }
}
