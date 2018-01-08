package helper

import java.util.List
import java.util.ArrayList

class FFMPEGHelper {
	
	def void runPlayList(List<String> playlistFile, String outputFile){
		//val command = new ArrayList<String>
		
		var process = Runtime.runtime.exec("/usr/bin/ffmpeg -y -f concat -safe 0 -i " + playlistFile + " -c copy " + outputFile)
		process.waitFor
	}
	
	def void generateThumbnail(String videoLocation, String thumbFolder, String thumbName){
		
		val command = new ArrayList<String>
		command.add("ffmpeg")
		command.add("-y")
		command.add("-i")
		command.add(videoLocation)
		command.add(" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 " + thumbFolder + "/" + thumbName + ".png")
		ProcessHelper.execute(command)
	}
	
	
	static def String concatVideos(String[] filenames, String outputfilename){
		val command = new ArrayList<String>
		var filter = ""
		command.add("ffmpeg")
		command.add("-y")
		var i = 0
		for(file : filenames){
			command.add("-i")
			command.add(file)
			filter += "[" + i + ":v:0][" + i + ":a:0]"
			i++
		}
		command.add("-filter_complex")
		filter += "concat=n=" + i + ":v=1:a=1[outv][outa]"
		command.add(filter)
		command.add("-map")
		command.add("[outv]")
		command.add("-map")
		command.add("[outa]")
		command.add(outputfilename)
		
		for(arg : command){
			println(arg)
		}
		
		ProcessHelper.execute(command)
		println(outputfilename + " created")
		
		return outputfilename
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
	
}