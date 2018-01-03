package fr.istic.idm.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.OptionalMedia;

/**
 * @author Antoine Charpentier
 * Variantes object that holds all variantes of current videogen file being compiled
 * This is where the logic to generate all variantes is written.
 */
public class Variantes {
	private static Logger log = LoggerFactory.getLogger(Variantes.class);
	
	private List<Variante> variantes;
	
	public Variantes(Variantes variantes) {
		this.variantes = new ArrayList<>(variantes.variantes);
	}
	
	public Variantes(List<Variante> variantes) {
		this.variantes = new ArrayList<Variante>(variantes);
	}
	
	public Variantes() {
		this.variantes = new ArrayList<Variante>();
	}
	
	public void generate(List<Media> medias) {
		for(int i = 0 ; i < medias.size() ; i++) {
			Media current = medias.get(i);
			
			if(current instanceof MandatoryMedia) {
				
				if(variantes.size() == 0) {
					variantes.add(new Variante(Arrays.asList(new MediaSequence(current, Optional.ofNullable(((MandatoryMedia) current).getDescription())))));
				} else {
					for(int varianteIndex = 0 ; varianteIndex < variantes.size() ; varianteIndex++) {
						variantes.get(varianteIndex).add(new MediaSequence(current, Optional.ofNullable(((MandatoryMedia) current).getDescription())));;
					}
				}
				
			} else if(current instanceof OptionalMedia) {
				OptionalMedia optional = (OptionalMedia) current;
				if(variantes.size() == 0) {
					variantes.add(new Variante(Arrays.asList(new MediaSequence(optional, Optional.ofNullable(optional.getDescription())))));
					variantes.add(new Variante(Arrays.asList(new MediaSequence(optional, Optional.ofNullable(null)))));
					continue;
				}
				
				Variantes clone = new Variantes(this);
				Variantes variantesToCompute = new Variantes();
				
				for(int j = 0 ; j < 2 ; j++) {
					
					int currentIndex = j;
					
					
					clone.variantes.forEach((variante) -> {
						Variante varianteClone = new Variante(variante);
						
						if(currentIndex == 1)
							varianteClone.add(new MediaSequence(optional, Optional.ofNullable(optional.getDescription())));
						else
							varianteClone.add(new MediaSequence(optional, Optional.ofNullable(null)));
						variantesToCompute.variantes.add(varianteClone);
					});
				}
				
				variantes = new Variantes(variantesToCompute).variantes;
			} else if(current instanceof AlternativesMedia) { 
				AlternativesMedia alternative = (AlternativesMedia) current;
				if(variantes.size() == 0) {
					
					for(int j = 0 ; j < alternative.getMedias().size() ; j++) {
						variantes.add(new Variante(Arrays.asList(new MediaSequence(alternative, Optional.ofNullable(alternative.getMedias().get(j))))));
					}
					
					continue;
				}
				
				Variantes clone = new Variantes(variantes);
				Variantes variantesToCompute = new Variantes();
				for(int j = 0 ; j < alternative.getMedias().size() ; j++) {
					
					int currentIndex = j;
					
					
					clone.variantes.forEach((variante) -> {
						Variante varianteClone = new Variante(variante);
						varianteClone.add(new MediaSequence(alternative, Optional.ofNullable(alternative.getMedias().get(currentIndex))));
						variantesToCompute.variantes.add(varianteClone);
					});
				}
				
				variantes = new Variantes(variantesToCompute).variantes;
			}
		}
		
		log.info("Variantes computed: {}", variantes.size());
		log.info("{}", variantes);
	}

	public boolean contains(Variante variante) {
		return this.variantes.contains(variante);
	}
	
	public int size() {
		return this.variantes.size();
	}
}
