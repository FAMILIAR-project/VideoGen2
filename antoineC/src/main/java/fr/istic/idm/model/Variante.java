package fr.istic.idm.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.OptionalMedia;

import fr.istic.idm.RandomMediaSelector;
import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.MediaSequence;
import fr.istic.idm.model.mediasequence.visitors.MediaSequenceVisitor;
import fr.istic.idm.model.mediasequence.visitors.VarianteInformationsVisitor;
import fr.istic.idm.model.mediasequence.visitors.VideoGenCompilerVisitor;
import fr.istic.idm.model.mediasequence.visitors.VisitorFactory;

/**
 * 
 * @author Antoine Charpentier
 *
 */
public class Variante {
	private static Logger log = LoggerFactory.getLogger(Variante.class);
	private List<MediaSequence> variante;

	public Variante(Variante variante) {
		this.variante = new ArrayList<>(variante.get());
	}
	public Variante() {
		this.variante = new ArrayList<>();
	}
	
	public Variante(List<MediaSequence> sequences) {
		this.variante = new ArrayList<>(sequences);
	}
	
	public List<MediaSequence> get() {
		return this.variante;
	}
	
	public void add(MediaSequence sequence) {
		this.variante.add(sequence);
	}
	
	
	public VarianteInformationsVisitor getVarianteInformations() {
		VarianteInformationsVisitor visitor = VisitorFactory.createInformationsVisitor();
		
		Iterator<MediaSequence> iterator = this.variante.iterator();
		
		try {
			while(iterator.hasNext())
				iterator.next().accept(visitor);
		} catch(FileNotFoundException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled())
				e.printStackTrace();
			
			throw new RuntimeException("Compiler aborted due to: " + e.getMessage());
		}
		
		return visitor;
	}
	
	/**
	 * This method compile a video file using a tool like ffmpeg.
	 */
	public File compile() {
		VideoGenCompilerVisitor visitor = VisitorFactory.createCompilerVisitor(getVarianteInformations());
		
		Iterator<MediaSequence> iterator = this.variante.iterator();
		
		try {
			while(iterator.hasNext())
				iterator.next().accept(visitor);
		} catch(FileNotFoundException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled())
				e.printStackTrace();
			
			throw new RuntimeException("Compiler aborted due to: " + e.getMessage());
		}
		
		try {
			return visitor.build();
		} catch(IOException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled())
				e.printStackTrace();
			
			throw new RuntimeException("Aborted due to previous logged exception");
		}
	}
	
	
	public static Variante generate(List<Media> medias) throws InvalidVideoGenGrammarException {
		Variante variante = new Variante();
		
		for(Media media : medias) {
			if(media instanceof MandatoryMedia) {
				variante.add(new MandatoryMediaSequence((MandatoryMedia) media, Optional.ofNullable(((MandatoryMedia) media).getDescription())));
			} else if(media instanceof OptionalMedia) {
				
				MediaSequence optionalMedia = RandomMediaSelector.selectOptionalMedia((OptionalMedia) media);
				
				variante.add(optionalMedia);
				
			} else if(media instanceof AlternativesMedia) {
				Optional<MediaSequence> sequence = RandomMediaSelector.selectAlternativesMedia((AlternativesMedia) media);
				if(sequence.isPresent())
					variante.add(sequence.get());
			}
		}
		
		return variante;
	}
	
	/**
	 * Update equals alongs with hashcode methods to say to the jvm 
	 * that two Variante or identical in term of reference when they have the same List
	 */
	@Override
	public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Variante)) {
            return false;
        }

        Variante v = (Variante) obj;
        
        return v.variante.equals(this.variante);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + this.variante.hashCode();
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(System.identityHashCode(this) + " [");
		
		for(MediaSequence sequence : variante) {
			if(!sequence.getDescription().isPresent()) {
				builder.append("EMPTY, ");
			} else
				builder.append(sequence.getDescription().get().getLocation() + ", ");
		}
		builder.append("]");
		return builder.toString();
	}
}
