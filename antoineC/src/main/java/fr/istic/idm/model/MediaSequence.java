package fr.istic.idm.model;

import java.util.Optional;

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
	private Optional<MediaDescription> description;
	
	public MediaSequence(Media media, Optional<MediaDescription> description) {
		if(media == null || description == null)
			throw new RuntimeException("There Shoudn't be null values here");
		this.media = media;
		this.description = description;
	}
	
	
	public Media getMedia() {
		return media;
	}

	/**
	 * Return MediaDescription according to given Media above.
	 * @warnings can return NULL value so it is wrapped in Optional
	 * @return
	 */
	public Optional<MediaDescription> getDescription() {
		return description;
	}


	/**
	 * Update equals alongs with hashcode methods to say to the jvm 
	 * that two object or identical in term of reference when they have the same Media and MediaDescription
	 */
	@Override
	public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof MediaSequence)) {
            return false;
        }

        MediaSequence mediaSequence = (MediaSequence) obj;
        
        return mediaSequence.media.equals(media) && description.equals(mediaSequence.description);
	}
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + media.hashCode();
		result = 31 * result + description.hashCode();
		return result;
	}
}
