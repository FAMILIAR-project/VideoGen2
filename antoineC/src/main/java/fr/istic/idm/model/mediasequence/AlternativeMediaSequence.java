package fr.istic.idm.model.mediasequence;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MediaDescription;

import fr.istic.idm.model.mediasequence.visitors.MediaSequenceVisitor;

public class AlternativeMediaSequence extends MediaSequence{
	public AlternativeMediaSequence(AlternativesMedia media, Optional<MediaDescription> description) {
		super(media, description);
	}

	@Override
	public void accept(MediaSequenceVisitor visitor) throws FileNotFoundException {
		visitor.visit(this);
	}
	

	public AlternativesMedia getMedia() {
		return (AlternativesMedia) super.getMedia();
	}

}
