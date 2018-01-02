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
	
	
	/**
	 * Update equals alongs with hashcode methods to say to the jvm that two object or identical in term of reference when they have the same Media and MediaDescription
	 */
	@Override
	public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof MediaSequence)) {
            return false;
        }

        MediaSequence mediaSequence = (MediaSequence) obj;
        
        if(description == null)
        	return mediaSequence.media.equals(media) && mediaSequence == description;
        
        return mediaSequence.media.equals(media) && description.equals(mediaSequence.description);
	}
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + media.hashCode();
		
		if(description != null)
			result = 31 * result + description.hashCode();
		return result;
	}
}
