package fr.istic.idm.model.mediasequence.visitors;

import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public abstract class MediaSequenceVisitor {
	public abstract void visit(AlternativeMediaSequence sequence);
	public abstract void visit(OptionalMediaSequence sequence);
	public abstract void visit(MandatoryMediaSequence sequence);
	
	/**
	 * Build an object based on precedings visited MediaSequence
	 * @return
	 */
	public abstract Object build();
}
