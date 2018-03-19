package lebonmaheu

import java.util.Scanner
import java.util.List
import java.io.InputStreamReader
import java.io.BufferedReader
import java.util.stream.Collectors

class FFMPEG {
	/**
	 * Compute the duration of the video in seconds.
	 * 
	 * @param locationVideo the video's location
	 * @return the video duration in seconds
	 */
	def static ffmpegComputeDuration(String locationVideo) {
    	val cmd = '''/usr/bin/ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 «locationVideo»'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor
    	
    	val scanner = new Scanner(p.inputStream) 
    	return Double.valueOf(scanner.next)
    }
    
    /**
     * Concatenate the given medias using FFMPEG.
     * 
     * @param locationList list of the locations of the medias
     * @param outputPath path of the output video
     */
    def static ffmpegConcatenateCommand(List<String> locationList, String outputPath) {
    	val inputs = locationList.map [ loc | "-i " + loc + " " ].join()
    	val inputCount = Integer.toString(locationList.size)
    	var setsar = ""
    	var segments = ""
    	
    	for (i : 0 ..< locationList.size) {
    		val is = Integer.toString(i)
    		setsar += "[" + is + "]setdar=16/9[v" + is + "];"
    		segments += "[v" + is + "][" + is + ":a]"
    	}
    	
    	val filter = "-filter_complex " + setsar + segments + "concat=n=" + inputCount + ":v=1:a=1[outv][outa]"
    	 
    	val cmd = '''/usr/bin/ffmpeg -y «inputs» «filter» -map [outv] -map [outa] «outputPath»'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor()
    }
    
    /**
     * Convert the video to a GIF.
     * 
     * @param input the input video, the output will be ${input}.gif
     */
    def static ffmpegConvertToGIF(String input) {
    	val cmd = '''ffmpeg -y -i «input» -r 15 «input».gif'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor
    	println(new BufferedReader(new InputStreamReader(p.errorStream)).lines().collect(Collectors.joining("\n")))
    }
}