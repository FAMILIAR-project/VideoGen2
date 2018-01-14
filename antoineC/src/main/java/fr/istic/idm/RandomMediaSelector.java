package fr.istic.idm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.eclipse.emf.ecore.EAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;

import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;


public class RandomMediaSelector {
	private static final Logger log = LoggerFactory.getLogger(RandomMediaSelector.class);
	private final static Random rand = new Random();
	
	private static boolean isProbabilitySet(VideoDescription description) {
		for(EAttribute attribute : description.eClass().getEAttributes()) {
			if(attribute.getName().equals("probability"))
				return description.eIsSet(attribute); 
		}
		return false;
	}
	
	private static Optional<Integer> getProbability(VideoDescription description) throws InvalidVideoGenGrammarException {
		if(isProbabilitySet(description)) {
			int probability = description.getProbability();
			
			if(probability < 0 || probability > 100)
				throw new InvalidVideoGenGrammarException("Probability of an optional or alternatives video must be set between 0 and 100 !");
			return Optional.ofNullable(probability);
		}
		return Optional.ofNullable(null);
	}
	
	public static MediaSequence selectOptionalMedia(OptionalMedia optionalMedia) throws InvalidVideoGenGrammarException {
		int random = rand.nextInt(101);
		
		if(optionalMedia.getDescription() instanceof VideoDescription) {
			Optional<Integer> probability = getProbability((VideoDescription) optionalMedia.getDescription());
			
			if(probability.isPresent()) {
				
				if(random <= probability.get()) {
					return new OptionalMediaSequence(optionalMedia, Optional.ofNullable(optionalMedia.getDescription()));
				} else {
					return new OptionalMediaSequence(optionalMedia, null);
				}
			}
			
		}
		
		
		return random > 50 ? new OptionalMediaSequence(optionalMedia, Optional.ofNullable(null)) : new OptionalMediaSequence(optionalMedia, Optional.ofNullable(optionalMedia.getDescription()));
	}
	
	public static Optional<MediaSequence> selectAlternativesMedia(AlternativesMedia alternatives) throws InvalidVideoGenGrammarException {
		
		if(alternatives.getMedias().size() == 0)
			return Optional.ofNullable(null);
			
		if(alternatives.getMedias().get(0) instanceof VideoDescription) {
			Map<Optional<Integer>, MediaDescription> probabilities = new HashMap<>();
			int nullableProbabilitiesCounter = 0;
			for(MediaDescription description : alternatives.getMedias()) {
				Optional<Integer> probability = getProbability((VideoDescription) description); 
				
				if(!probability.isPresent())
					nullableProbabilitiesCounter++;
				
				probabilities.put(probability, description);
			}
			
			
			if(nullableProbabilitiesCounter != alternatives.getMedias().size()) {
				int aggregatedProbabilities = 0;
				
				for(Optional<Integer> probability : probabilities.keySet()) {
					if(probability.isPresent())
						aggregatedProbabilities += probability.get();
				}
				
				if(aggregatedProbabilities > 100) {
					throw new InvalidVideoGenGrammarException("Aggregated Probabilities of alternatives video sequence must be lower or equal to 100");
				}
				
				
				
				//Trouve la part équiprobable des alternatives qui n'ont pas de probabilités (c'est un calcul sur des entiers donc imprécis)
				int equitablePartOfNullableProbabilities = nullableProbabilitiesCounter == 0 ? 0 : (100 - aggregatedProbabilities) / nullableProbabilitiesCounter;
				
				int random = rand.nextInt(101);
				int probabilityCounter = 0;
				for(Optional<Integer> probability : probabilities.keySet()) {
					if(probability.isPresent())
						probabilityCounter += probability.get();
					else {
						probabilityCounter += equitablePartOfNullableProbabilities;
					}
					
					if(probabilityCounter > random)
						return Optional.ofNullable(new AlternativeMediaSequence(alternatives, Optional.ofNullable(probabilities.get(probability))));
					
				}
			}	
		}
		
		//Pour quand c'est entièrement équiprobable, on tire juste un index de la liste au hasard.
		return Optional.ofNullable(
				new AlternativeMediaSequence(
						alternatives, 
						Optional.ofNullable(alternatives.getMedias().get(
								rand.nextInt(
										alternatives.getMedias().size())))));
	}
}
