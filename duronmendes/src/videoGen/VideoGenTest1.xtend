package videoGen

import org.junit.Test
import org.eclipse.emf.common.util.URI

import static org.junit.Assert.*
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.concurrent.ThreadLocalRandom
import java.io.File
import java.io.FileWriter
import java.util.ArrayList

class VideoGenTest1 {
	
	def ffmpegConcatenateCommand(String mpegPlaylistFile, String outputPath) 
	'''/usr/bin/ffmpeg -y -f concat -safe 0 -i «mpegPlaylistFile» -c copy «outputPath»'''
	
	@Test
	def void testLoadModel() {
			
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
		assertNotNull(videoGen)
		//println(videoGen.information.authorName)		
		// and then visit the model
		// eg access video sequences: videoGen.videoseqs
		
		var List<MediaDescription> playlist = new ArrayList
				
		for (media : videoGen.medias) {
			if(media instanceof MandatoryMedia){
				playlist.add(media.description)
			}
			
			if(media instanceof OptionalMedia){
				val rand = ThreadLocalRandom.current.nextInt(1+1)
				if(rand==1){
					playlist.add(media.description)
				}
			}
		
			if(media instanceof AlternativesMedia)
			{
				var rand = ThreadLocalRandom.current.nextInt(media.medias.size+1)
				playlist.add(media.medias.get(rand))
			}
		}
		
		val file = new File("out/playlist.txt")
		file.createNewFile
		
		val writer = new FileWriter(file)
		
		for(item : playlist){
			writer.write("file '"+item.location+"'\n")
		}
		
		writer.flush()
		writer.close()
		
		var command = ffmpegConcatenateCommand("out/playlist.txt",  "out/o.mp4")
		var p = Runtime.runtime.exec(command.toString)
		p.waitFor	
	}
}

