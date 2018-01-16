package fr.istic.m2il.idm.videogentransformations.helpers

import java.util.List
import java.util.ArrayList
import java.io.File
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils
import java.util.regex.Pattern
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter
import org.xtext.example.mydsl.videoGen.NegateFilter
import org.xtext.example.mydsl.videoGen.FlipFilter
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs

class FFMPEGHelper {
	
	
	static def String generateThumbnail(String videoLocation){
		
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-y")
		command.add("-i")
		command.add(videoLocation)
		command.add("-r")
		command.add("1")
		command.add("-t") 
		command.add("00:00:01")
		command.add("-ss")
		
		command.add("00:00:0" + Math.random() * Math.round(Float.parseFloat(getVideoDurationString(videoLocation).split(":").get(2))))
		command.add("-f")
		command.add("image2")
		var outputFile = CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/thumbs/thumb.png")
		command.add(outputFile)
		ProcessHelper.execute(command)
		outputFile
	}
	
	
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
		return outputfilename
	}
	
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
}