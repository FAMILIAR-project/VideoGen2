package fr.istic.idm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import fr.istic.idm.model.MediaSequence;

public class VideoGenCompiler {

	private static Logger logger = LoggerFactory.getLogger(VideoGenCompiler.class);

	private VideoGeneratorModel model;
	private List<MediaSequence> variantes;
	
	public VideoGenCompiler(VideoGeneratorModel model) {
		this.model = model;
		this.variantes = new ArrayList<MediaSequence>();
	}
	
	public static void main(String[] args) {
		if(args.length != 1) {
			logger.error("Un argument doit être fournit à ce programme correspondant à la grammaire *.videogen qui sera compilé");	
			return;
		}
		
		File videoGenFile = new File(args[0]);
		
		if(!videoGenFile.exists() || !videoGenFile.isFile()) {
			logger.error("Le fichier {} n'existe pas", args[0]);
			return;
		}
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(videoGenFile.getPath()));
		VideoGenCompiler compiler = new VideoGenCompiler(videoGen);
		logger.info("File loaded successfully");
		
		
		compiler.generateModel();
	}
	
	public void generateModel() {
//		for(int i = 0 ; i < this.model.getMedias() ; i++) {
//			
//		}
		
		try {

			combinations(Arrays.asList(2, 1, 3, 2));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void combinations(List<Integer> integers) {
		List<List<Integer>> variantes = new ArrayList<>();
		for(int i = 0 ; i < integers.size() ; i++) {
			int current = integers.get(i);
			
			if(current == 1) {
				
				if(variantes.size() == 0) {
					variantes.add(new ArrayList<>(Arrays.asList(current)));
				} else {
					for(int varianteIndex = 0 ; varianteIndex < variantes.size() ; varianteIndex++) {
						variantes.get(varianteIndex).add(current);
					}
				}
				
			} else {
				
				if(variantes.size() == 0) {
					for(int j = 0 ; j < current ; j++) {
						variantes.add(new ArrayList<>(Arrays.asList(j+1)));
					}
					
					continue;
				}
				List<List<Integer>> clone = new ArrayList<>(variantes);
				List<List<Integer>> variantesToCompute = new ArrayList<>();
				for(int j = 0 ; j < current ; j++) {
					
					int currentIndex = j;
					
					
					clone.forEach((variante) -> {
						List<Integer> varianteClone = new ArrayList<>(variante);
						varianteClone.add(currentIndex+1);
						variantesToCompute.add(varianteClone);
					});
				}
				
				variantes = new ArrayList<>(variantesToCompute);
			}
		}
		
		logger.info("Variantes computed: {}", variantes.size());
		logger.info("{}", variantes);
	}
	
	
}
