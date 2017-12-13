package lebonmaheu

import static org.junit.Assert.*
import org.eclipse.emf.common.util.URI
import java.util.List
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import java.util.concurrent.ThreadLocalRandom
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import java.io.File
import java.io.FileWriter

class Test {
	def public static void main(String[] args) {
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"))
		assertNotNull(videoGen)
		println(videoGen.information.authorName)
		
        //println(videoGen.information.authorName)        
        // and then visit the model
        // eg access video sequences: videoGen.videoseqs
        
        var List<MediaDescription> playlist = new ArrayList
                
        for (media : videoGen.medias) {
            if(media instanceof MandatoryMedia){
                playlist.add(media.description)
            }
            
            if(media instanceof OptionalMedia){
                if(ThreadLocalRandom.current.nextInt(0,1)==1){
                    playlist.add(media.description)
                }
            }
        
            if(media instanceof AlternativesMedia)
            {
                var rand = ThreadLocalRandom.current.nextInt(0,media.medias.size)
                playlist.add(media.medias.get(rand))
            }
        }
        
        
        val file = new File("playlist.txt")
        file.createNewFile
        
        val writer = new FileWriter(file)
        
        for(item : playlist){
            writer.write("file '"+item.location+"'\n")
        }
        
        writer.flush()
        writer.close()
        
        var command = ffmpegConcatenateCommand("playlist.txt",  "o.mp4")
        var p = Runtime.runtime.exec(command.toString)
        p.waitFor    
    }
    
    def public static longestVariant() {
    	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"))
		assertNotNull(videoGen)
		var List<MediaDescription> playlist = new ArrayList
		
		for(media : videoGen.medias) {
			if(media instanceof MandatoryMedia) {
				playlist.add(media.description)
			}
			
			else if(media instanceof OptionalMedia) {
				playlist.add(media.description)
			}
			
			else if(media instanceof AlternativesMedia) {
				var durations = media.medias.map [ m |
					Integer.parseInt(ffmpegComputeDuration(m.location).toString)
				]
				
				var max = 0
				
				for(var i = 0; i < durations.size; i++) {
					if(durations.get(max) < durations.get(i)) {
						max = i
					}
				}
				
				playlist.add(media.medias.get(max))
			}
		}
		
		return playlist
    }
    
    def static ffmpegComputeDuration(String locationVideo)
    '''/usr/local/bin/ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 «locationVideo»'''
    
    def static ffmpegConcatenateCommand(String mpegPlaylistFile, String outputPath) 
    '''/usr/bin/ffmpeg -y -f concat -safe 0 -i �mpegPlaylistFile� -c copy �outputPath�'''
}
