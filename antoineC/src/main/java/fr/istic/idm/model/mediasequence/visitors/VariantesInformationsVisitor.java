package fr.istic.idm.model.mediasequence.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.istic.idm.VideoGenCompiler;
import fr.istic.idm.model.Variante;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

/**
 * Visiteur qui visite plusieurs variante successivement pour en généré la liste
 * sous forme de fichier csv
 * 
 * @author Antoine Charpentier
 *
 */
public class VariantesInformationsVisitor extends VarianteVisitor {
	private List<String> variantesInformations;

	private String current;

	public VariantesInformationsVisitor() {
		this.variantesInformations = new ArrayList<String>();
	}

	@Override
	public void visit(AlternativeMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			current += ";";
		else
			current += sequence.getDescription().get().getLocation() + ";";
	}

	@Override
	public void visit(OptionalMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			current += ";";
		else
			current += sequence.getDescription().get().getLocation() + ";";
	}

	@Override
	public void visit(MandatoryMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			current += ";";
		else
			current += sequence.getDescription().get().getLocation() + ";";
	}

	@Override
	public File build() throws IOException {
		File variantes = FileUtils.getFile(VideoGenCompiler.WORK_DIR, "variantes.csv");
		
		variantes.createNewFile();
		
		FileWriter writer = new FileWriter(variantes);
		
		for(String line : variantesInformations) {
			writer.append(line + "\n");
		}
		
		writer.close();
		
		return variantes;
	}

	@Override
	public void startVisiting(Variante variante) {
		this.current = "Composition:;";
	}

	@Override
	public void endVisiting(Variante variante) {
		VarianteInformationsVisitor infos = variante.getVarianteInformations();
		this.current += "Taille:;" + infos.getSize() + ";Durée:;" + infos.getDuration() + ";\n";
		this.variantesInformations.add(this.current);
	}

}
