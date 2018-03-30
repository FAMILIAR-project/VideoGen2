package fr.istic.idm.model.mediasequence.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.istic.idm.model.Variante;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public abstract class VarianteVisitor extends VideoGenCompilerVisitor {

	public abstract void startVisiting(Variante variante);
	public abstract void endVisiting(Variante variante);
	
	@Override
	public abstract void visit(AlternativeMediaSequence sequence) throws FileNotFoundException;

	@Override
	public abstract void visit(OptionalMediaSequence sequence) throws FileNotFoundException;

	@Override
	public abstract void visit(MandatoryMediaSequence sequence) throws FileNotFoundException;

	@Override
	public abstract File build() throws IOException;

}
