package fr.istic.idm.model.mediasequence.visitors;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.EClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.VideoDescription;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ExecutionError;

import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.MediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public class FFMPEGMediaSequenceVisitor extends MediaSequenceVisitor {
	
	private static Logger log = LoggerFactory.getLogger(FFMPEGMediaSequenceVisitor.class);
	
	@Override
	public void visit(AlternativeMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
		
//		log.debug("Visiting AlternativeMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}

	@Override
	public void visit(OptionalMediaSequence sequence) throws FileNotFoundException {
		if(!sequence.getDescription().isPresent())
			return;
//		log.debug("Visiting OptionalMediaSequence located at {}", sequence.getDescription().isPresent() ? sequence.getDescription().get().getLocation() : "EMPTY");
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}

	@Override
	public void visit(MandatoryMediaSequence sequence) throws FileNotFoundException {
//		log.debug("Visiting MandatoryMediaSequence located at {}", sequence.getDescription().get().getLocation());
		
		if(sequence.getDescription().get() instanceof VideoDescription) {
			workOnVideoDescription(sequence.getMedia(), (VideoDescription) sequence.getDescription().get());
		} else {
			workOnImageDescription(sequence.getMedia(), (ImageDescription) sequence.getDescription().get());
		}
	}
	
	
	private void workOnImageDescription(Media media, ImageDescription description) throws FileNotFoundException {
		log.info("Image with this details: {}, {}, {}, {}", description.getImageid(), description.getLocation(), description.getTop(), description.getBottom());
		File image = null;
		if(!(image = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + image.getAbsolutePath() + "' is nowhere to be found");
		}
		
		StringBuilder commandBuilder = new StringBuilder("ffmpeg -i " + image.getPath() + " -vf ");
		
		String drawTextTemplate = "drawtext=fontfile=${fontfile}:fontcolor=${fontcolor}:fontsize=${fontsize}:x=\"(w-text_w)/2\":y=\"${y}\":text=\"${text}\"";
		StringBuilder filtersBuilder = new StringBuilder();
		
		
		if(description.getBottom() != null && description.getBottom() != "") {
			addFFMPEGFilter(filtersBuilder, drawTextTemplate, ImmutableMap.of(
					"${fontfile}", "src/main/resources/arial.ttf",
					"${fontcolor}", "white",
					"${fontsize}", "72",
					"${y}", "h-text_h",
					"${text}", description.getBottom()
			));
		}
		
		if(description.getTop() != null && description.getTop() != "") {
			addFFMPEGFilter(filtersBuilder, drawTextTemplate, ImmutableMap.of(
					"${fontfile}", "src/main/resources/arial.ttf",
					"${fontcolor}", "white",
					"${fontsize}", "72",
					"${y}", "0",
					"${text}", description.getTop()
			));
		}
		
		commandBuilder.append(filtersBuilder.toString()).append(" -y output.jpg");
		
		// TODO: WARNING test in a unix system because development is made in windows, and it must work in unix.
		// Bottom: ffmpeg -i image.png -vf drawtext=fontfile=src/main/resources/arial.ttf:fontcolor=white:fontsize=74:x="(w-text_w)/2":y="(h-text_h)":text="Bottom Text" -y bottom.png
		// TP: ffmpeg -i image.png -vf drawtext=fontfile=src/main/resources/arial.ttf:fontcolor=white:fontsize=74:x="(w-text_w)/2":y=0:text="Top Text" -y top.png
		
		try {
			log.info("Command to be proccessed: {}", commandBuilder.toString());
			Process p = Runtime.getRuntime().exec(commandBuilder.toString());
			
			if(p.waitFor() != 0) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				
				String line = "";
				while((line = reader.readLine()) != null)  {
					log.error(line);
				}
				
				reader.close();
				
				throw new Exception("Unable to generate image");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		description.getImageid() nullable, 
//		description.getLocation() always set, 
//		description.getTop() nullable, 
//		description.getBottom() nullable
	}

	/**
	 * Build a FFMPEG filter, must be extracted in a specific class later, but for now it's good enough
	 * @param filters
	 * @param filterTemplate
	 * @param replacements
	 */
	private void addFFMPEGFilter(StringBuilder filters, String filterTemplate, Map<String, String> replacements) {
		if(filters.length() != 0)
			filters.append(",");
		
		String filter = filterTemplate;
		for(String templateVariable : replacements.keySet()) {
			filter = filter.replace(templateVariable, replacements.get(templateVariable));
		}
		
		filters.append(filter);
	}
	
	private void workOnVideoDescription(Media media, VideoDescription description) throws FileNotFoundException {
		log.info("Video with this details: {}, {}, {}, {}, {}", description.getVideoid(), description.getLocation(), description.getProbability(), description.getDuration(), description.getDescription());
		
		File video = null;
		if(!(video = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + video.getAbsolutePath() + "' is nowhere to be found");
		}
		
//		description.getVideoid() nullable, 
//		description.getLocation() always defined, 
//		description.getProbability() zero, 
//		description.getDuration() 0, 
//		description.getDescription() nullable
	}
	
	

	@Override
	public Object build() {
		log.debug("Build something");
		return null;
	}

}
