package fr.istic.idm.model.mediasequence.visitors;

import java.io.FileNotFoundException;

import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public abstract class MediaSequenceVisitor {
	public abstract void visit(AlternativeMediaSequence sequence) throws FileNotFoundException;
	public abstract void visit(OptionalMediaSequence sequence) throws FileNotFoundException;
	public abstract void visit(MandatoryMediaSequence sequence) throws FileNotFoundException;
	
	/**
	 * Build an object based on precedings visited MediaSequence
	 * @return
	 */
	public abstract Object build();
}
