package fr.istic.idm.model.mediasequence.visitors;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.VideoDescription;

import com.google.common.collect.ImmutableMap;

import fr.istic.idm.VideoGenCompiler;
import fr.istic.idm.ffmpeg.FFMPEGCommand;
import fr.istic.idm.ffmpeg.StreamHandler;
import fr.istic.idm.model.mediasequence.AlternativeMediaSequence;
import fr.istic.idm.model.mediasequence.MandatoryMediaSequence;
import fr.istic.idm.model.mediasequence.OptionalMediaSequence;

public class FFMPEGMediaSequenceVisitor extends VideoGenCompilerVisitor {
	private static Logger log = LoggerFactory.getLogger(FFMPEGMediaSequenceVisitor.class);
	
	private LinkedList<File> videoParts;
	private File videoConcatenationInstructionsFile;
	private FileWriter concatenationInstructionsWriter;
	
	
	public FFMPEGMediaSequenceVisitor() {
		this.videoParts = new LinkedList<>();
		this.videoConcatenationInstructionsFile = FileUtils.getFile(VideoGenCompiler.TEMP_DIR_PATH + "concatenate.txt");
		
		try {
			this.videoConcatenationInstructionsFile.createNewFile();
			this.concatenationInstructionsWriter = new FileWriter(this.videoConcatenationInstructionsFile);
		} catch (IOException e) {
			log.error(e.getMessage());
			
			if(log.isDebugEnabled())
				e.printStackTrace();
		}
		
	}
	
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
		
		File output = new File(VideoGenCompiler.TEMP_DIR_PATH + UUID.randomUUID() + ".jpg");
		commandBuilder.append(filtersBuilder.toString()).append(" -y ").append(output.getAbsolutePath());
		
		// TODO: WARNING test in a unix system because development is made in windows, and it must work in unix.
		// Bottom: ffmpeg -i image.png -vf drawtext=fontfile=src/main/resources/arial.ttf:fontcolor=white:fontsize=74:x="(w-text_w)/2":y="(h-text_h)":text="Bottom Text" -y bottom.png
		// TP: ffmpeg -i image.png -vf drawtext=fontfile=src/main/resources/arial.ttf:fontcolor=white:fontsize=74:x="(w-text_w)/2":y=0:text="Top Text" -y top.png
		
		if(!new FFMPEGCommand(commandBuilder.toString()).execute()) {
			throw new RuntimeException("Cannot generate image with text from '" + image.getAbsolutePath() + "'");
		}
		
		File videoOutput = new File(VideoGenCompiler.TEMP_DIR_PATH + UUID.randomUUID() + ".mp4");
		videoParts.add(videoOutput);
		
		String command = "ffmpeg -loop 1 -i " + output.getAbsolutePath() + " -f lavfi -i anullsrc=r=48000:cl=stereo -vf scale=\"trunc(iw/2)*2:trunc(ih/2)*2\" -t 3 -y " + videoOutput.getAbsolutePath();
		
		if(!new FFMPEGCommand(command).execute()) {
			throw new RuntimeException("Cannot generate video from image '" + image.getAbsolutePath() + "'");
		}
	}
	
	private void workOnVideoDescription(Media media, VideoDescription description) throws FileNotFoundException {
		log.info("Video with this details: {}, {}, {}, {}, {}", description.getVideoid(), description.getLocation(), description.getProbability(), description.getDuration(), description.getDescription());
		
		File video = null;
		if(!(video = FileUtils.getFile(description.getLocation())).exists()) {
			throw new FileNotFoundException("The file '" + video.getAbsolutePath() + "' is nowhere to be found");
		}
		
		
		File output = new File(VideoGenCompiler.TEMP_DIR_PATH + UUID.randomUUID() + ".mp4");
		this.videoParts.add(output);
		String command = "ffmpeg -i " + video.getAbsolutePath() + " " + output.getAbsolutePath() + " -y -hide_banner";
		
		
		if(!new FFMPEGCommand(command).execute()) {
			throw new RuntimeException("Cannot transcode '" + video.getAbsolutePath() + "' into mp4");
		}
		
//		description.getVideoid() nullable, 
//		description.getLocation() always defined, 
//		description.getProbability() zero, 
//		description.getDuration() 0, 
//		description.getDescription() nullable
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
	
	@Override
	public File build() throws IOException {
		
		this.concatenationInstructionsWriter.append("# VideoGen Compiler Concatenation File\n");
		
		while(!this.videoParts.isEmpty()){
			File videoPart = this.videoParts.removeFirst();
			this.concatenationInstructionsWriter.append("file '" + videoPart.getAbsolutePath() + "'\n");
		}
		
		this.concatenationInstructionsWriter.flush();
		log.info("Concatenation file is served '{}'", videoConcatenationInstructionsFile.getAbsolutePath()) ;
		log.info("Start to generate Video File, this can take severall minutes based on generated video file size.");
		
		String command = "ffmpeg -f concat -safe 0 -i " + this.videoConcatenationInstructionsFile.getAbsolutePath() + " -c copy " + VideoGenCompiler.TEMP_DIR_PATH + "concatenated.mp4";
		
		if(!new FFMPEGCommand(command).execute())
			throw new RuntimeException("Cannot create video file");
		
		this.concatenationInstructionsWriter.close();
		
		
		log.info("Video File successfully generated");
		return FileUtils.getFile(VideoGenCompiler.TEMP_DIR_PATH + "concatenated.mp4");
	}
}
