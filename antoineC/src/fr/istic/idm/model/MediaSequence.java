package fr.istic.idm.model;

import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;

/**
 * 
 * @author tanaky
 * A MediaSequence object is a couple of two object, the Media and It's MediaDescription.
 * It is used during the computation of all Variantes of videos.
 */
public class MediaSequence {
	private Media media;
	private MediaDescription description;
	
	public MediaSequence(Media media, MediaDescription description) {
		this.media = media;
		this.description = description;
	}
}
