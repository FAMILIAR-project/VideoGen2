package fr.istic.m2il.idm.videogenapp.domain;

import org.xtext.example.mydsl.videoGen.VideoGenInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ismael
 */

public class VideoGeneratorModelWrapper{
    public VideoGenInformation information;
    public List<MediaWrapper> medias = new ArrayList<>();
}
