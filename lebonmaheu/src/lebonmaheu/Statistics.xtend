package lebonmaheu

import static org.junit.Assert.*
import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.io.File
import java.io.FileWriter
import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription

class Statistics {
	def public static main(String[] args) {
		if (args.length != 1)
			return
		
		val input = args.get(0)
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(input))
		assertNotNull(videoGen)
		
		val allVariants = Utils.getAllVariants(videoGen)
		
		allVariants.forEach [ variant |
			val output = variant.map [ m | Utils.getMediaId(m) ].join + ".mp4"
			val locations = variant.map [ m | m.location ]
			
			FFMPEG.ffmpegConcatenateCommand(locations, output)
		]
		
		makeCSV(videoGen, input)
	}
	
	def public static makeCSV(VideoGeneratorModel videoGen, String inputFile) {
    	val all = Utils.getAllVariants(videoGen)
    	val seqs = Utils.getAllIds(videoGen)
    	
    	val file = new File("playlist.csv")
        file.createNewFile
        
        val writer = new FileWriter(file)
        
        writer.write("id")
        seqs.forEach [ id | writer.write(";" + id) ]
        writer.write(";duration;size;realsize\n")
        
        for(var i = 0; i < all.size; i++) {
        	val List<MediaDescription> variant = all.get(i)
        	writer.write(Integer.toString(i + 1) + ";")
        	val ids = variant.map [ m | Utils.getMediaId(m) ]
        	seqs.forEach [ id |
        		if(ids.contains(id))
        			writer.write("TRUE")
        		else
        			writer.write("FALSE")
        		writer.write(";")
        	]
        	writer.write('''«variantDuration(variant)»;«variantSize(variant)»;«variantRealSize(variant)»'''.toString + "\n")
        }
        
        writer.flush
        writer.close
    }
    
    def public static variantDuration(List<MediaDescription> variant) {
    	var duration = 0.0
    	
    	for (media : variant) {
			duration += FFMPEG.ffmpegComputeDuration(media.location)
    	}
    	
    	return duration
    }
    
    def public static variantSize(List<MediaDescription> variant) {
    	var size = 0L
    	
    	for (media : variant) {
			val file = new File(media.location)
			size += file.length
    	}
    	
    	return size
    }
    
    def public static variantRealSize(List<MediaDescription> variant) {
    	val name = variant.map [ m | Utils.getMediaId(m) ].join + ".mp4"
    	val file = new File(name)
    	
    	return file.length
    }
}