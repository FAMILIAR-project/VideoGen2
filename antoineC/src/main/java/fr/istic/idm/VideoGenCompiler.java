package fr.istic.idm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.MediaSequence;

public class VideoGenCompiler {

	private static Logger log = LoggerFactory.getLogger(VideoGenCompiler.class);
	private VideoGeneratorModel model;
	private List<List<MediaSequence>> variantes;
	
	public VideoGenCompiler(VideoGeneratorModel model) {
		this.model = model;
		this.variantes = new ArrayList<List<MediaSequence>>();
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
		
		
		compiler.generateModel();
	}
	
	public void generateModel() {
		
//		long startTime = System.currentTimeMillis();
		variantes = variantes(this.model.getMedias());
		
		try {
			List<MediaSequence> variante = generateVariante();
			log.info("Generated Variante: {}", variante);
			log.info("Variante previously found ? {}", variantes.contains(variante));
		} catch (InvalidVideoGenGrammarException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
//		
//		long endTime = System.currentTimeMillis();
//
//		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		
//		logger.info("Combination algorithm duration: {} ms, with {} variants", duration, variantes.size());
	}
	
	private List<MediaSequence> generateVariante() throws InvalidVideoGenGrammarException {
		List<MediaSequence> variante = new ArrayList<>();
		
		for(Media media : this.model.getMedias()) {
			if(media instanceof MandatoryMedia) {
				variante.add(new MediaSequence(media, ((MandatoryMedia) media).getDescription()));
			} else if(media instanceof OptionalMedia) {
				
				Optional<MediaSequence> optionalMedia = RandomMediaSelector.selectOptionalMedia((OptionalMedia) media);
				
				if(optionalMedia.isPresent()) {
					variante.add(optionalMedia.get());
				}
				
			} else if(media instanceof AlternativesMedia) {
				Optional<MediaSequence> description = RandomMediaSelector.selectAlternativesMedia((AlternativesMedia) media);
				if(description.isPresent())
					variante.add(description.get());
			}
		}
		
		return variante;
	}
	
	private List<List<MediaSequence>> variantes(List<Media> medias) {
		List<List<MediaSequence>> variantes = new ArrayList<>();
		for(int i = 0 ; i < medias.size() ; i++) {
			Media current = medias.get(i);
			
			if(current instanceof MandatoryMedia) {
				
				if(variantes.size() == 0) {
					variantes.add(new ArrayList<>(Arrays.asList(new MediaSequence(current, ((MandatoryMedia) current).getDescription()))));
				} else {
					for(int varianteIndex = 0 ; varianteIndex < variantes.size() ; varianteIndex++) {
						variantes.get(varianteIndex).add(new MediaSequence(current, ((MandatoryMedia) current).getDescription()));;
					}
				}
				
			} else if(current instanceof OptionalMedia) {
				OptionalMedia optional = (OptionalMedia) current;
				if(variantes.size() == 0) {
					variantes.add(new ArrayList<>(Arrays.asList(new MediaSequence(optional, optional.getDescription()))));
					variantes.add(new ArrayList<>(Arrays.asList(new MediaSequence(optional, null))));
					continue;
				}
				
				List<List<MediaSequence>> clone = new ArrayList<>(variantes);
				List<List<MediaSequence>> variantesToCompute = new ArrayList<>();
				
				for(int j = 0 ; j < 2 ; j++) {
					
					int currentIndex = j;
					
					
					clone.forEach((variante) -> {
						List<MediaSequence> varianteClone = new ArrayList<>(variante);
						
						if(currentIndex == 1)
							varianteClone.add(new MediaSequence(optional, optional.getDescription()));
						else
							varianteClone.add(new MediaSequence(optional, null));
						variantesToCompute.add(varianteClone);
					});
				}
				
				variantes = new ArrayList<>(variantesToCompute);
			} else if(current instanceof AlternativesMedia) { 
				AlternativesMedia alternative = (AlternativesMedia) current;
				if(variantes.size() == 0) {
					
					for(int j = 0 ; j < alternative.getMedias().size() ; j++) {
						variantes.add(new ArrayList<>(Arrays.asList(new MediaSequence(alternative, alternative.getMedias().get(j)))));
					}
					
					continue;
				}
				
				List<List<MediaSequence>> clone = new ArrayList<>(variantes);
				List<List<MediaSequence>> variantesToCompute = new ArrayList<>();
				for(int j = 0 ; j < alternative.getMedias().size() ; j++) {
					
					int currentIndex = j;
					
					
					clone.forEach((variante) -> {
						List<MediaSequence> varianteClone = new ArrayList<>(variante);
						varianteClone.add(new MediaSequence(alternative, alternative.getMedias().get(currentIndex)));
						variantesToCompute.add(varianteClone);
					});
				}
				
				variantes = new ArrayList<>(variantesToCompute);
			}
		}
		
		log.info("Variantes computed: {}", variantes.size());
		log.info("{}", variantes);
		return variantes;
	}
	
	
}
