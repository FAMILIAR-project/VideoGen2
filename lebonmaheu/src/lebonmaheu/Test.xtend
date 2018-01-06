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
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.VideoDescription

class Test {
	def private static getMediaId(MediaDescription m) {
		if(m instanceof VideoDescription)
			return m.videoid
		else if(m instanceof ImageDescription)
			return m.imageid
		
		throw new NullPointerException
	}
	
	def public static void main(String[] args) {
		var input = ""
		var output = ""
		
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
                
        for (media : videoGen.medias) {
            if(media instanceof MandatoryMedia){
                playlist.add(media.description)
            }
            
            else if(media instanceof OptionalMedia){
                if(ThreadLocalRandom.current.nextInt(0,1)==1){
                    playlist.add(media.description)
                }
            }
        
            else if(media instanceof AlternativesMedia)
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
        
        var command = ffmpegConcatenateCommand("playlist.txt",  output)
        var p = Runtime.runtime.exec(command.toString)
        
        val all = getAllVariants(input)
        all.forEach [variant |
        	variant.forEach [ m | print(getMediaId(m) + " ") ]
        	println("")
        ]
        
        makeCSV(input)
        p.waitFor
        
        command = ffmpegConvertToGIF(output)
        p = Runtime.runtime.exec(command.toString)
        
        p.waitFor    
    }
    
    def private static copyVariantList(List<List<MediaDescription>> all) {
    	val List<List<MediaDescription>> newAll = new ArrayList<List<MediaDescription>>
    	
    	all.forEach [ variant |
    		newAll.add(new ArrayList<MediaDescription>(variant))
    	]
    	
    	return newAll
    }
    
    def public static getAllVariants(String inputFile) {
    	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(inputFile))
		assertNotNull(videoGen)
		val all = new ArrayList<List<MediaDescription>>
		all.add(new ArrayList<MediaDescription>)
		
		for(media : videoGen.medias) {
			if(media instanceof MandatoryMedia) {
				all.forEach[ variant |
					variant.add(media.description)
				]
			}
			
			else if(media instanceof OptionalMedia) {
				val newAll = copyVariantList(all)
				newAll.forEach [ variant | variant.add(media.description) ]
				all.addAll(newAll)
			}
			
			else if(media instanceof AlternativesMedia) {
				val oldAll = copyVariantList(all)
				all.clear
				
				media.medias.forEach [ m |
					val withM = copyVariantList(oldAll)
					withM.forEach [ variant | variant.add(m) ]
					all.addAll(withM)
				]
			}
		}
		
		return all
    }
    
    def public static getAllIds(String inputFile) {
    	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(inputFile))
		assertNotNull(videoGen)
		
		val ids = new ArrayList<String>
		
		videoGen.medias.forEach[ m |
			if(m instanceof MandatoryMedia)
				ids.add(getMediaId(m.description))
			else if(m instanceof OptionalMedia)
				ids.add(getMediaId(m.description))
			else if(m instanceof AlternativesMedia) {
				m.medias.forEach [ alt | ids.add(getMediaId(alt)) ]
			}
		]
		
		return ids
    }
    
    def public static makeCSV(String inputFile) {
    	val all = getAllVariants(inputFile)
    	val seqs = getAllIds(inputFile)
    	
    	val file = new File("playlist.csv")
        file.createNewFile
        
        val writer = new FileWriter(file)
        
        writer.write("id")
        seqs.forEach [ id | writer.write(";" + id) ]
        writer.write(";size\n")
        
        for(var i = 0; i < all.size; i++) {
        	val List<MediaDescription> variant = all.get(i)
        	writer.write(Integer.toString(i + 1) + ";")
        	val ids = variant.map [ m | getMediaId(m) ]
        	seqs.forEach [ id |
        		if(ids.contains(id))
        			writer.write("TRUE")
        		else
        			writer.write("FALSE")
        		writer.write(";")
        	]
        	writer.write("0\n")
        }
        
        writer.flush
        writer.close
    }
    
    def public static longestVariant(String inputFile) {
    	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(inputFile))
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
    '''/usr/bin/ffmpeg -y -f concat -safe 0 -i «mpegPlaylistFile» -c copy «outputPath»'''
    
    def static ffmpegConvertToGIF(String input)
    '''ffmpeg -i «input» -vf scale=320:-1 -r 10 -f image2pipe -vcodec ppm - | convert -delay 5 -loop 0 - «input».gif'''
}
