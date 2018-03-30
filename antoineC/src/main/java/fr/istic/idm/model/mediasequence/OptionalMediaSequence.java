package fr.istic.idm.model.mediasequence;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;

import fr.istic.idm.model.mediasequence.visitors.MediaSequenceVisitor;

public class OptionalMediaSequence extends MediaSequence {

	public OptionalMediaSequence(OptionalMedia media, Optional<MediaDescription> description) {
		super(media, description);
	}

	@Override
	public void accept(MediaSequenceVisitor visitor) throws FileNotFoundException {
		visitor.visit(this);
	}

	public OptionalMedia getMedia() {
		return (OptionalMedia) super.getMedia();
	}
}
