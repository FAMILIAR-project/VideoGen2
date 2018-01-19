package fr.istic.m2il.idm.videogentransformations.helpers

import java.util.List
import java.util.ArrayList
import java.io.File
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils
import java.util.regex.Pattern
import org.xtext.example.mydsl.videoGen.MediaDescription

/**
 * @author Ramadan Soumaila
 * A helper class to execute ffmpeg commands
 */
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
/**
 * @author Ramadan Soumaila
 * A helper class to execute ffmeg commands
 */
class FFMPEGHelper {
	
	/**
	 * Creates the thumb of a video/image
	 * @param location the location of video/image 
	 * @return a string which represent the location of generated thumbs if execution is successful, or "" otherwise
	 */
	static def String generateThumbnail(String location){
		
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-y")
		command.add("-i")
		command.add(location)
		command.add("-r")
		command.add("1")
		command.add("-t") 
		command.add("00:00:01")
		command.add("-ss")
		
		command.add("00:00:0" + Math.random() * Math.round(Float.parseFloat(getVideoDurationString(location).split(":").get(2))))
		command.add("-f")
		command.add("image2")
		var outputFile = CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/thumbs/thumb.png")
		command.add(outputFile)
		ProcessHelper.execute(command)
		outputFile
	}
	
	
	/**
	 * Creates a video by concatenate several 
	 * @param outputfilename the location of generated video
	 * @param files the list of videos's locations 
	 * @return a string which represent the location of generated video if execution is successful, or "" otherwise
	 */
	static def String concatVideos(List<String> files, String outputfilename){
		val command = new ArrayList<String>
		var filter = ""
		command.add("ffmpeg")
		command.add("-y")
		
		println("fi "+files.size)
		
		var i = 0
		for(file : files){
			command.add("-i")
			println("fi "+file)
			command.add(file)
			if(
				file.replace(".","@").split("@").get(1).equals("jpg") ||
			    file.replace(".","@").split("@").get(1).equals("png") ||
			    file.replace(".","@").split("@").get(1).equals("gif") ||
			    file.replace(".","@").split("@").get(1).equals("bpm") ||
			    file.replace(".","@").split("@").get(1).equals("tiff")
			)
				filter += "[" + i + ":v:0][0]"
			else{
				filter += "[" + i + ":v:0][" + i + ":a:0]"
				
				}
			i++
		}
		command.add("-filter_complex")
		filter += "concat=n=" + i + ":v=1:a=1[outv][outa]"
		command.add(filter)
		command.add("-map")
		command.add("[outv]")
		command.add("-map")
		command.add("[outa]")
		command.add("-strict")
		command.add("-2")
		command.add(outputfilename)
		
		ProcessHelper.execute(command)
		println("FFMHelper outputfilename " + outputfilename)
		return outputfilename
	}
	
	/**
	 * Gets a video/image resolution 
	 * @param videoFile the location of video/image 
	 * @return a array of int which contains the width at first index, 
	 * and height at second index if execution is successful, or null otherwise
	 */
	static def int[] getVideoResolution(String videoFile){
		val command = new ArrayList
		command.add("ffmpeg")
		command.add("-i")
		command.add(videoFile)
		val IO = ProcessHelper.executeAndGetIOStream(command)
		val resolution = new ArrayList
		for(line : IO){
			if(line.contains("Stream #0:0")){
				var pattern = Pattern.compile(",\\s[0-9]+x[0-9]+");
				var matcher = pattern.matcher(line);
				if (matcher.find)
				{
    				val res = matcher.group(0).replace(", ", "").split("x");
    				resolution.add(Integer.parseInt(res.get(0)))
					resolution.add(Integer.parseInt(res.get(1)))
				}
			}
		}
		
		return resolution
	}
	
	/**
	 * Changes the resolution of a video/image if the input resolution over the output resolution
	 * @param filename location of video/image
	 * @param inputWidth the input width resolution to set 
	 * @param inputHeight the input height resolution to set 
	 * @param outWidthWidth the output width resolution to set 
	 * @param outputHeight the output width resolution to set 
	 * @return a string which represent the location of generated thumbs if execution is successful, or "" otherwise
	 */
	static def String homogenizeMediaResolution(String filename, int inputWidth, int inputHeight, int outputWidth, int outputHeight){
		if(inputWidth == outputWidth && inputHeight == outputHeight){
			return filename
		}
		var file = filename.replace(".","@#").split("@#")
		var command = new ArrayList
		command.add("ffmpeg")
        command.add("-i")
        command.add(filename)
        command.add("-strict")
        command.add("-2")
        command.add("-vf")
        
        if(file.get(1).equals("jpg") || file.get(1).equals("png") || file.get(1).equals("bpm") || file.get(1).equals("tiff") || file.get(1).equals("gif")){
        	command.add("scale=" + outputWidth + ":" + outputHeight)
        }else{
        	command.add("pad=" + outputWidth + ":" + outputHeight + ":" + (outputWidth - inputWidth) / 2 + ":" + (outputHeight - inputHeight) / 2)
        }
        
        
        val outputFile =  CommonUtils.getOutPutFileName(
        VideoGenConfigs.outPutFoulder + "/resizes/" + new File(filename).absolutePath.replace("\\", "/").split("/").last.replace(".", "@").split("@").get(0) + "_o." + file.get(1)
        )

        command.add(outputFile)
        ProcessHelper.execute(command)
        return outputFile
	}
	
	/**
	 * Gets a video's duration of a playlist
	 * 
	 * @param playlist a list of media description
	 * @return a int value of the playlist duration if the command is successful, or 0 otherwise
	 */
	static def int getVideoDuration(List<MediaDescription> playlist){
		var duration = 0
		
		var i = 0;
		for(media: playlist){
			if(media !== null){
				println("I " + i)
				duration += getVideoDuration(media.location)
			}
			i++
		}
		println("Duration " + duration)
		duration
	}
	
	/**
	 * Gets a video's duration 
	 * 
	 * @param videolocation the location of video
	 * @return a int value of the video duration if the command is successful, or 0 otherwise
	 */
	static def int getVideoDuration(String videoLocation){
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-i")
		command.add(videoLocation) 
					 
		val IO = ProcessHelper.executeAndGetIOStream(command)
		
		var duration = 0
		
		for(line : IO){
			if(line.contains("Duration:")){
				val stringduration = line.trim.split(",").get(0).split(" ").get(1)
				duration = 3600 * Math.round(Float.parseFloat(stringduration.split(":").get(0)))  + 
						   60 * Math.round(Float.parseFloat(stringduration.split(":").get(1)))  + 
						   Math.round(Float.parseFloat(stringduration.split(":").get(2)))
			}
		}
		duration
	}
	
	/**
	 * Gets a video's duration of a video in string(formated)
	 * 
	 * @param videoLocation the location of video
	 * @return a formated string value of the video of duration if the command is successful, or 0 otherwise
	 */
	static def String getVideoDurationString(String videoLocation){
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-i")
		command.add(videoLocation) 
					 
		val IO = ProcessHelper.executeAndGetIOStream(command)
		
		var duration = ""
		
		for(line : IO){
			if(line.contains("Duration:")){
				duration = line.trim.split(",").get(0).split(" ").get(1)
			
			}
		}
		duration
	}
	
	/**
	 * Gets the gif of a video 
	 * 
	 * @param videoLocation the location of video
	 * @return a string value of the gif generated if the command is successful, or 0 otherwise
	 */
	static def String videoToGif(String videoLocation, int width, int height){
		val command = new ArrayList<String>
		var file = videoLocation.replace(".","@#").split("@#")
		command.add("ffmpeg")
		command.add("-i")

		command.add(videoLocation)
		
		val outputFile =  CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/gifs/" + new File(videoLocation).absolutePath.replace("\\", "/").split("/").last.replace(".", "@").split("@").get(0).split("_").get(0) + ".gif")
		
		command.add("-vf")
		command.add("scale=" + width + ":" + height)
		command.add(outputFile)
		
		command.add("-hide_banner")
		
		ProcessHelper.execute(command)
		
		outputFile
	}
	
	/**
	 * Applies a filter on a video
	 * 
	 * @param fileter the filter to apply
	 * @return a string value of the video generated with filter if the command is successful, or "" otherwise
	 */
	static def String applyFilter(String filter, String location){
		val filtercommand = new ArrayList<String>
					filtercommand.add("ffmpeg")
					filtercommand.add("-i")
					filtercommand.add(location)
							filtercommand.add("-strict")
		filtercommand.add("-2")
					filtercommand.add("-vf")
					filtercommand.add(filter)
					var outputFile = CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/filtered/" + new File(location).absolutePath.replace("\\", "/").split("/").last)
					filtercommand.add(outputFile)
					ProcessHelper.execute(filtercommand)

					outputFile
	}
	
	/**
	 * Adds some text on a video
	 * @param location the video location
	 * @param content the content to write
	 * @param size the font size of the content
	 * @param color the color of the content
	 * @param position the position of content
	 * @return a string value of the output location if is successful, or "" otherwise
	 */
	static def String addTextToVideo(String location, String content, int size, String color, String position){
		var commands = newArrayList
		var color_ = color
		if (color_ === "")
			color_ = "white"
		var size_ = size
		if(size_ == 0)
			size_ = 20
		var position_ = position
		if(position === "")
			position_ = "BOTTOM"
		var String y
		switch(position_){
			case "TOP":
				y = "5"
			case "BOTTOM":
				y = "(h-text_h-line_h)"
	
			case "CENTER":
				y = "(h-text_h-line_h)/2"
		
		}
	
		commands.add("ffmpeg")
		commands.add("-i")
		commands.add(location)
		commands.add("-strict")
        commands.add("-2")
		commands.add("-vf")
		commands.add("drawtext=fontsize=" + size_ + ":fontcolor=" + color_ + ":fontfile=FreeSerif.ttf" + ":text=" + "'" + content + "'" + ":x=(w-text_w)/2:y=" + y + "'")
		var output = CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/filtered/" + new File(location).absolutePath.replace("\\", "/").split("/").last)
		commands.add(output)
		
		ProcessHelper.execute(commands)
		
		output
	}
}