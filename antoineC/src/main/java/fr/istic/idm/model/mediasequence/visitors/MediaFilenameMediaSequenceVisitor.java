package fr.istic.idm.model.mediasequence.visitors;

import fr.istic.idm.model.Variante;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaFilenameMediaSequenceVisitor extends VarianteVisitor {
    private List<String> filenames;

    public MediaFilenameMediaSequenceVisitor() {
        this.filenames = new ArrayList<>();
    }

    @Override
    public void visit(AlternativeMediaSequence sequence) throws FileNotFoundException {
        if(!sequence.getDescription().isPresent())
            return;

        if(!filenames.contains(sequence.getDescription().get().getLocation()))
            filenames.add(sequence.getDescription().get().getLocation());

    }

    @Override
    public void visit(OptionalMediaSequence sequence) throws FileNotFoundException {
        if(!sequence.getDescription().isPresent())
            return;

        if(!filenames.contains(sequence.getDescription().get().getLocation()))
            filenames.add(sequence.getDescription().get().getLocation());
    }

    @Override
    public void visit(MandatoryMediaSequence sequence) throws FileNotFoundException {
        if(!filenames.contains(sequence.getDescription().get().getLocation()))
            filenames.add(sequence.getDescription().get().getLocation());
    }

    public List<String> getFilenames() {
        return filenames;
    }

	@Override
	public void startVisiting(Variante variante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endVisiting(Variante variante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File build() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
