package helpers

import java.util.List
import java.util.ArrayList
import java.io.File
import utils.CommonUtils
import java.util.regex.Pattern
import java.util.regex.Matcher
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter
import org.xtext.example.mydsl.videoGen.NegateFilter
import org.xtext.example.mydsl.videoGen.FlipFilter

class FFMPEGHelper {
	
	
	static def String generateThumbnail(String videoLocation){
		
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-y")
		command.add("-i")
		command.add(videoLocation)
		var outputFile = CommonUtils.getOutPutFileName("output/thumbs/thumb.png")
		command.add("-r 1")
		command.add("-t 00:00:01 -ss 00:00:02 -f image2")
		command.add(outputFile)
		ProcessHelper.execute(command)
		outputFile
	}
	
	
	static def String concatVideos(List<MediaDescription> medias, String outputfilename){
		val command = new ArrayList<String>
		var filter = ""
		command.add("ffmpeg")
		command.add("-y")
		
		var i = 0
		for(media : medias){
			command.add("-i")
			
			if(media instanceof VideoDescription){
				val vdescription = media as VideoDescription
				if(vdescription.filter instanceof BlackWhiteFilter){
					applyFilter("format=gray", vdescription)
					
				}

				if(vdescription.filter instanceof NegateFilter){
					applyFilter("negate", vdescription)
				}
				
				if(vdescription.filter instanceof FlipFilter){
					val flipFilter = vdescription.filter as FlipFilter
					var flipOrientation = flipFilter.orientation
					if(flipOrientation.equals("horizontal"))
						flipOrientation = "h"
					if(flipOrientation.equals("vertical"))
						flipOrientation = "v"	
						
					applyFilter(flipOrientation+"flip", vdescription)
				}
			}
			command.add(media.location)
			if(
				media.location.replace(".","@").split("@").get(1).equals("jpg") ||
			    media.location.replace(".","@").split("@").get(1).equals("png") ||
			    media.location.replace(".","@").split("@").get(1).equals("gif") ||
			    media.location.replace(".","@").split("@").get(1).equals("bpm") ||
			    media.location.replace(".","@").split("@").get(1).equals("tiff")
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
        "output/resizes/" + new File(filename).absolutePath.replace("\\", "/").split("/").last.replace(".", "@").split("@").get(0) + "_o." + file.get(1)
        )

        command.add(outputFile)
        ProcessHelper.execute(command)
        return outputFile
	}
	
	static def float getVideoDuration(String videoLocation){
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-i")
		command.add(videoLocation)
		command.add("2>&1 | grep Duration | cut -d ' ' -f 4 | sed s/,// |
					 sed 's@\\..*@@g' | awk '{ split($1, A, \":\"); split(A[3], B, \".\");
					 print 3600*A[1] + 60*A[2] + B[1] }'")
		val iostream = ProcessHelper.executeAndGetIOStream(command)
		return Float.valueOf(iostream.get(0))
	}
	
	static def String videoToGif(String videoLocation, int width, int height){
		val command = new ArrayList<String>
		var file = videoLocation.replace(".","@#").split("@#")
		command.add("ffmpeg")
		command.add("-i")

		command.add(videoLocation)
		
		val outputFile =  CommonUtils.getOutPutFileName("output/gifs/" + new File(videoLocation).absolutePath.replace("\\", "/").split("/").last.replace(".", "@").split("@").get(0) + ".gif")
		
		command.add("-vf")
		command.add("scale=" + width + ":" + height)
		command.add(outputFile)
		
		command.add("-hide_banner")
		
		ProcessHelper.execute(command)
		
		outputFile
	}
	
	static def String applyFilter(String filter, MediaDescription media){
		val filtercommand = new ArrayList<String>
					filtercommand.add("ffmpeg")
					filtercommand.add("-i")
					filtercommand.add(media.location)
							filtercommand.add("-strict")
		filtercommand.add("-2")
					filtercommand.add("-vf")
					filtercommand.add(filter)
					var outputFile = CommonUtils.getOutPutFileName("output/filtered/" + new File(media.location).absolutePath.replace("\\", "/").split("/").last)
					filtercommand.add(outputFile)
					ProcessHelper.execute(filtercommand)
					media.location = outputFile
					outputFile
	}
}