package fr.istic.idm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import fr.istic.idm.exception.InvalidVarianteGeneration;
import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.Variante;
import fr.istic.idm.model.Variantes;
import fr.istic.idm.model.mediasequence.visitors.VariantesInformationsVisitor;
import fr.istic.idm.model.mediasequence.visitors.VideoGenCompilerVisitor;
import fr.istic.idm.model.mediasequence.visitors.VisitorFactory;

public class VideoGenCompiler {
	private static final String TEMP_DIR_NAME = "VideoGenTemp";
	public static File WORK_DIR;
	public static File TEMP_DIR;
	public static File OUTPUT_DIR;
	
	// TODO: Update this if linux failed to work
	public static String TEMP_DIR_PATH = "";
	
	private static Logger log = LoggerFactory.getLogger(VideoGenCompiler.class);
	
	private VideoGeneratorModel model;
	private Variantes variantes;
	
	
	
	public VideoGeneratorModel getModel() {
		return model;
	}

	public Variantes getVariantes() {
		return variantes;
	}

	public VideoGenCompiler(VideoGeneratorModel model) {
		if(!initializePaths(System.getProperty("user.dir"))) {
			throw new RuntimeException();
		}
		
		this.model = model;
	}
	
	/**
	 * Constructor qui permet de travailler dans un répertoire spécifique fournis en paramètre
	 * @param basePath
	 * @param videogenfile
	 */
	public VideoGenCompiler(String basePath, String videogenfile) {
		File videoGenFile = FileUtils.getFile(basePath, videogenfile);
		
		if(!videoGenFile.exists() || !videoGenFile.isFile()) {
			log.error("Le fichier {} n'existe pas", videogenfile);
			throw new RuntimeException();
		}
		
		this.model = new VideoGenHelper().loadVideoGenerator(URI.createURI(videoGenFile.getPath()));
		
		if(!initializePaths(basePath)) {
			throw new RuntimeException();
		}
	}
	
	private boolean initializePaths(String basePath) {
		
		WORK_DIR = FileUtils.getFile(basePath);
		
		if(!WORK_DIR.exists() || !WORK_DIR.isDirectory()) {
			log.error("{} devrait être un répertoire existant", WORK_DIR.getAbsolutePath());
			return false;
		}
		TEMP_DIR = FileUtils.getFile(FilenameUtils.normalize(basePath + "/" + TEMP_DIR_NAME));
		OUTPUT_DIR = FileUtils.getFile(FilenameUtils.normalize(basePath + "/" + "VideoGenOutput"));
		
		TEMP_DIR_PATH = FilenameUtils.normalize(TEMP_DIR.getAbsolutePath() + "/");
		
		if(!TEMP_DIR.exists()) {
			TEMP_DIR.mkdir();
		}
		
		if(!OUTPUT_DIR.exists()) {
			OUTPUT_DIR.mkdir();
		}
		
		try {
			FileUtils.cleanDirectory(TEMP_DIR);
		} catch(IOException e) {
			log.error(e.getMessage());
			if(log.isDebugEnabled())
				e.printStackTrace();
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		if(args.length != 2) {
			log.error("Usage 'java -jar VideogenCompiler.jar <COMPILE|INFOS>' <videogen filepath>");
			return;
		}
		
		File videoGenFile = new File(args[1]);
		
		if(!videoGenFile.exists() || !videoGenFile.isFile()) {
			log.error("Le fichier {} n'existe pas", args[0]);
			return;
		}
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(videoGenFile.getPath()));
		VideoGenCompiler compiler;
		
		try {
			compiler = new VideoGenCompiler(videoGen);
		} catch(RuntimeException e) {
			return;
		}
		log.info("VideoGen SourceCode loaded successfully");
		
		if(args[0].equalsIgnoreCase("COMPILE")) {
			try {
				compiler.compile();
			} catch(RuntimeException e) {
				log.error(e.getMessage());
			}
		} else if (args[0].equalsIgnoreCase("INFOS")) {
			compiler.extractInformations();
			
		}
	}
	
	public void generateVariantes() {
		variantes = new Variantes();
		variantes.generate(this.model.getMedias());
	}
	
	public Variante generateVariante() throws InvalidVideoGenGrammarException {
		return Variante.generate(this.model.getMedias());
	}
	
	private void extractInformations() {
		try {
			if(variantes == null)
				generateVariantes();
			
			VariantesInformationsVisitor visitor = VisitorFactory.createVariantesInformationsVisitor();
			
			this.variantes.visitAll(visitor);
			
			log.info("Informations file generated at {}", visitor.build().getAbsolutePath());
		} catch (IOException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private void compile() {
		long startTime, endTime, duration;
	    
	    
		try {
			startTime = System.currentTimeMillis(); 
			if(variantes == null)
				generateVariantes();
			
			endTime = System.currentTimeMillis(); 
		     
		    duration = (endTime - startTime); 
		    log.info("Generate {} all differents variantes metadatas in {} ms", variantes.size(), duration); 
		    
			Variante variante = generateVariante();
			log.info("Generated Variante: {}", variante);
			
				if(!variantes.contains(variante))
					throw new InvalidVarianteGeneration("Try to generate a variant that doesn't exist");
			
			File video = variante.compile();
			
			File concatenedFile = FileUtils.getFile(OUTPUT_DIR, System.identityHashCode(variante) + ".mp4");
			if(concatenedFile.exists()) {
				concatenedFile.delete();
			}
			
			FileUtils.moveFile(video, concatenedFile);
			
			log.info("Result available here {}", concatenedFile.getAbsolutePath());
		} catch (InvalidVideoGenGrammarException | InvalidVarianteGeneration | IOException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
//				throw new RuntimeException("The compiler cannot compile the grammar: " + e.getMessage());
		}
			
		
	}
	
}
