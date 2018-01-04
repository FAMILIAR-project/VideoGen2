package fr.istic.idm;

import java.io.File;

import javax.swing.text.html.HTMLDocument.RunElement;

import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import fr.istic.idm.exception.InvalidVarianteGeneration;
import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.Variante;
import fr.istic.idm.model.Variantes;

public class VideoGenCompiler {

	private static Logger log = LoggerFactory.getLogger(VideoGenCompiler.class);
	
	private VideoGeneratorModel model;
	private Variantes variantes;
	
	public VideoGenCompiler(VideoGeneratorModel model) {
		this.model = model;
	}
	
	public static void main(String[] args) {
		if(args.length != 1) {
			log.error("Un argument doit être fournit à ce programme correspondant à la grammaire *.videogen qui sera compilé");	
			return;
		}
		
		File videoGenFile = new File(args[0]);
		
		if(!videoGenFile.exists() || !videoGenFile.isFile()) {
			log.error("Le fichier {} n'existe pas", args[0]);
			return;
		}
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(videoGenFile.getPath()));
		VideoGenCompiler compiler = new VideoGenCompiler(videoGen);
		log.info("File loaded successfully");
		
		try {
			compiler.compile();
		} catch(RuntimeException e) {
			log.error(e.getMessage());
		}
	}
	
	private void compile() {
		long startTime, endTime, duration;
		
		
		startTime = System.currentTimeMillis();
		variantes = new Variantes();
		variantes.generate(this.model.getMedias());
		endTime = System.currentTimeMillis();
		
		duration = (endTime - startTime);
		log.info("Generate {} variantes in {} ms", variantes.size(), duration);
		
		
			try {
				Variante variante = Variante.generate(this.model.getMedias());
				log.info("Generated Variante: {}", variante);
				
				if(!variantes.contains(variante))
					throw new InvalidVarianteGeneration("Try to generate a variant that doesn't exist");
				
				variante.makeVideo();
			} catch (InvalidVideoGenGrammarException | InvalidVarianteGeneration e) {
				log.error(e.getMessage());
				
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
//				throw new RuntimeException("The compiler cannot compile the grammar: " + e.getMessage());
			}
			
		
	}
	
}
