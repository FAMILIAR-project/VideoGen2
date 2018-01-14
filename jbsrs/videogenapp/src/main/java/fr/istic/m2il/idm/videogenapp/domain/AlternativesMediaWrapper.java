package fr.istic.m2il.idm.videogenapp.domain;


import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.MediaDescription;

/**
 * @author ismael
 */

public class AlternativesMediaWrapper extends MediaWrapper{
    public int id;
    public EList<MediaDescription> medias;
}
