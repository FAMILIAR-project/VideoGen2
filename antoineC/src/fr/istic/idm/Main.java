package fr.istic.idm;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class.toString());
	
	public static void main(String[] args) {
		if(args.length != 1) {
			logger.log(Level.SEVERE, "Un argument doit être fournit à ce programme correspondant à la grammaire *.videogen qui sera compilé");	
			return;
		}
		
		File videoGenFile = new File(args[0]);
		
		if(!videoGenFile.exists() || !videoGenFile.isFile()) {
			logger.log(Level.SEVERE, "Le fichier " + args[0] + " n'existe pas");
			return;
		}
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(videoGenFile.getPath()));
		logger.log(Level.INFO, "File loaded successfully");
	}
}
