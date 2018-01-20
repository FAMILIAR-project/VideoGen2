package fr.istic.idm.model.mediasequence.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;

import fr.istic.idm.ffmpeg.FFMPEGCommand;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

/**
 * Ce visiteur répond à la problématique de récupérer plusieurs informations importantes des différents éléments de la variante de vidéo tels que:
 * La résolution (hauteur, largeur...)
 * 
 * Il n'est pas possible de récupérer ces informations lors de la visite de {@link FFMPEGMediaSequenceVisitor}, car il as besoin de la résolution de toutes les vidéos pour effectuer son travail.
 * @author Antoine Charpentier
 *
 */
public class VarianteInformationsVisitor extends MediaSequenceVisitor {
	private static Logger log = LoggerFactory.getLogger(VarianteInformationsVisitor.class);
	
	private Map<MediaDescription, Integer> widths;
	private Map<MediaDescription, Integer> heights;
	private int minWidth, maxWidth, minHeight, maxHeight;
	private long aggregatedSize;
	private Double aggregatedDuration;
	
	public VarianteInformationsVisitor() {
		this.widths = new HashMap<>();
		this.heights = new HashMap<>();
		this.aggregatedDuration = 0.00;
		this.aggregatedSize = 0;
		
		//On met des valeurs absurdement trop grandes pour calculer plus facilement la largeur et hauteur minimum
		this.minHeight = 9999999;
		this.minWidth = 9999999;
	}
	
	
	@Override
	public void visit(AlternativeMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
		
		workOnMediaDescription(sequence.getMedia(), sequence.getDescription().get());
	}

	@Override
	public void visit(OptionalMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
		workOnMediaDescription(sequence.getMedia(), sequence.getDescription().get());
	}

	@Override
	public void visit(MandatoryMediaSequence sequence) throws FileNotFoundException {
		workOnMediaDescription(sequence.getMedia(), sequence.getDescription().get());
	}
	
	

	private void workOnMediaDescription(Media media, MediaDescription description) throws FileNotFoundException {
		
		File file = null;
		if(!(file = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + file.getAbsolutePath() + "' is nowhere to be found");
		}
		
		//Ask FFMPEG for informations about files
		getWidth(file, description);
		getHeight(file, description);
		getSize(file, description);
		
		if(description instanceof ImageDescription) {
			this.aggregatedDuration += 3;
		} else {
			getDuration(file, description);
		}
	}
	
	private void getHeight(File file, MediaDescription description) {
		FFMPEGCommand command = new FFMPEGCommand("ffprobe -v error -show_entries stream=height -of default=noprint_wrappers=1:nokey=1 " + file.getAbsolutePath());
		
		int height = Integer.parseInt(command.getResult());
		
		this.heights.put(description, height);
		
		if(height < minHeight)
			minHeight = height;
		
		if(height > maxHeight)
			maxHeight = height;
		
		log.info("Height of {} : {}", file.getName(), height);
	}
	
	private void getDuration(File file, MediaDescription description) {
		FFMPEGCommand command = new FFMPEGCommand("ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 " + file.getAbsolutePath());
		
		double duration = Double.parseDouble(command.getResult());
		this.aggregatedDuration += duration;
		
		log.info("Duration of {} : {}", file.getName(), duration);
	}
	
	private void getSize(File file, MediaDescription description) {
		FFMPEGCommand command = new FFMPEGCommand("ffprobe -v error -show_entries format=size -of default=noprint_wrappers=1:nokey=1 " + file.getAbsolutePath());
		
		int size = Integer.parseInt(command.getResult());
		this.aggregatedSize += size;
		
		log.info("Size of {} : {}", file.getName(), size);
	}
	
	
	private void getWidth(File file, MediaDescription description) {
		FFMPEGCommand command = new FFMPEGCommand("ffprobe -v error -show_entries stream=width -of default=noprint_wrappers=1:nokey=1 " + file.getAbsolutePath());
		
		int width = Integer.parseInt(command.getResult());
		
		this.widths.put(description, width);
		
		if(width < minWidth)
			minWidth = width;
		
		if(width > maxWidth)
			maxWidth = width;
		
		log.info("Width of {} : {}", file.getName(), width);
	}


	public Map<MediaDescription, Integer> getWidths() {
		return widths;
	}


	public void setWidths(Map<MediaDescription, Integer> widths) {
		this.widths = widths;
	}


	public Map<MediaDescription, Integer> getHeights() {
		return heights;
	}


	public void setHeights(Map<MediaDescription, Integer> heights) {
		this.heights = heights;
	}

	public int getMinWidth() {
		return minWidth;
	}


	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}


	public int getMaxWidth() {
		return maxWidth;
	}


	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}


	public int getMinHeight() {
		return minHeight;
	}


	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}


	public int getMaxHeight() {
		return maxHeight;
	}


	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}


	public long getSize() {
		// TODO: compute size
		return this.aggregatedSize;
	}
	
	public Double getDuration() {
		// TODO: compute duration
		return this.aggregatedDuration;
	}

	
}
