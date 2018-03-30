package fr.istic.idm.model.mediasequence;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.MediaDescription;

import fr.istic.idm.model.mediasequence.visitors.MediaSequenceVisitor;

public class MandatoryMediaSequence extends MediaSequence {

	public MandatoryMediaSequence(MandatoryMedia media, Optional<MediaDescription> description) {
		super(media, description);
	}

	@Override
	public void accept(MediaSequenceVisitor visitor) throws FileNotFoundException {
		visitor.visit(this);
	}
	

	public MandatoryMedia getMedia() {
		return (MandatoryMedia) super.getMedia();
	}

}
