package lebonmaheu

import java.util.Scanner

class FFMPEG {
	def static ffmpegComputeDuration(String locationVideo) {
    	val cmd = '''/usr/local/bin/ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 «locationVideo»'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor
    	
    	return new Scanner(p.inputStream).nextInt
    }
    
    def static ffmpegConcatenateCommand(String mpegPlaylistFile, String outputPath) { 
    	val cmd = '''/usr/bin/ffmpeg -y -f concat -safe 0 -i «mpegPlaylistFile» -c copy «outputPath»'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor
    }
    
    def static ffmpegConvertToGIF(String input) {
    	val cmd = '''ffmpeg -i «input» -vf scale=320:-1 -r 10 -f image2pipe -vcodec ppm - | convert -delay 5 -loop 0 - «input».gif'''
    	val p = Runtime.runtime.exec(cmd.toString)
    	p.waitFor
    }
}