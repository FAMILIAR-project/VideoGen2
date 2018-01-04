package fr.istic.idm.model.mediasequence.visitors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public class FFMPEGMediaSequenceVisitor extends MediaSequenceVisitor {
	
	private static Logger log = LoggerFactory.getLogger(FFMPEGMediaSequenceVisitor.class);
	
	@Override
	public void visit(AlternativeMediaSequence sequence) {
		log.debug("Visiting AlternativeMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
	}

	@Override
	public void visit(OptionalMediaSequence sequence) {
		log.debug("Visiting OptionalMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
	}

	@Override
	public void visit(MandatoryMediaSequence sequence) {
		log.debug("Visiting MandatoryMediaSequence located at {}", sequence.getDescription().get().getLocation());
	}

	@Override
	public Object build() {
		log.debug("Build something");
		return null;
	}

}
