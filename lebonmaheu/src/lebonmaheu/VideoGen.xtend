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
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.LinkedList

class VideoGen {
	def public static void main(String[] args) {
		var input = ""
		var output = ""
		
		/* This program needs 2 arguments: the .videogen file and the output file */
		if (args.length != 2) {
			return
		}
		
		input = args.get(0)
		output = args.get(1)
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(input))
		assertNotNull(videoGen)
		
        //println(videoGen.information.authorName)        
        // and then visit the model
        // eg access video sequences: videoGen.videoseqs
        
        var List<MediaDescription> playlist = new ArrayList
        
        /* Generate the playlist */
        for (media : videoGen.medias) {
            if(media instanceof MandatoryMedia){
                playlist.add(media.description)
            }
            
            else if(media instanceof OptionalMedia){
            	val probability = (media.description as VideoDescription).probability
            	val prob = if (probability == 0) 50 else probability
            	
                if(ThreadLocalRandom.current.nextInt(0,100) < prob){
                    playlist.add(media.description)
                }
            }
        
            else if(media instanceof AlternativesMedia)
            {
            	if (! Utils.probabilitiesAreCorrect(media.medias)) {
            		/* 
            		 * if the probability-related laws are not respected,
            		 * then every media has an equal chance to be picked.
            		 */
                	val rand = ThreadLocalRandom.current.nextInt(0,media.medias.size)
                	playlist.add(media.medias.get(rand))
                } else {
                	val medias = Utils.fillProbabilities(media.medias)
                	val rand = ThreadLocalRandom.current.nextInt(0, 100)
                	
                	val m = medias.findFirst [ m | (m as VideoDescription).probability > rand ]
                	playlist.add(m)
                }
            }
        }
        
        
        /* Generate the playlist file */
        val file = new File("playlist.txt")
        file.createNewFile
        
        val writer = new FileWriter(file)
        val locationList = new LinkedList<String>
        
        /* Write the playlist in its file */
        for(item : playlist){
            writer.write("file '"+item.location+"'\n")
            locationList.add(item.location)
        }
        
        writer.flush()
        writer.close()
        
        /* Generate the video */
        FFMPEG.ffmpegConcatenateCommand(locationList,  output)
        FFMPEG.ffmpegConvertToGIF(output)
    }
    
    
}
