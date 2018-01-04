package fr.istic.idm.model.mediasequence.visitors;


import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.EClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.VideoDescription;

import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.MediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public class FFMPEGMediaSequenceVisitor extends MediaSequenceVisitor {
	
	private static Logger log = LoggerFactory.getLogger(FFMPEGMediaSequenceVisitor.class);
	
	@Override
	public void visit(AlternativeMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
		
//		log.debug("Visiting AlternativeMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}

	@Override
	public void visit(OptionalMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
//		log.debug("Visiting OptionalMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}

	@Override
	public void visit(MandatoryMediaSequence sequence) throws FileNotFoundException {
//		log.debug("Visiting MandatoryMediaSequence located at {}", sequence.getDescription().get().getLocation());
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}
	
	
	private void workOnImageDescription(Media media, ImageDescription description) throws FileNotFoundException {
		log.info("Image with this details: {}, {}, {}, {}", description.getImageid(), description.getLocation(), description.getTop(), description.getBottom());
		File image = null;
		if(!(image = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + image.getAbsolutePath() + "' is nowhere to be found");
		}
		
//		description.getImageid() nullable, 
//		description.getLocation() always set, 
//		description.getTop() nullable, 
//		description.getBottom() nullable
	}
	
	private void workOnVideoDescription(Media media, VideoDescription description) throws FileNotFoundException {
		log.info("Video with this details: {}, {}, {}, {}, {}", description.getVideoid(), description.getLocation(), description.getProbability(), description.getDuration(), description.getDescription());
		
		File video = null;
		if(!(video = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + video.getAbsolutePath() + "' is nowhere to be found");
		}
		
//		description.getVideoid() nullable, 
//		description.getLocation() always defined, 
//		description.getProbability() zero, 
//		description.getDuration() 0, 
//		description.getDescription() nullable
	}
	
	

	@Override
	public Object build() {
		log.debug("Build something");
		return null;
	}

}
