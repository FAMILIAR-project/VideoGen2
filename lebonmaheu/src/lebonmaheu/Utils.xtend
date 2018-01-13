package lebonmaheu

import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.ImageDescription
import java.util.List
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import java.io.File
import java.io.FileWriter
import java.util.LinkedList

class Utils {
	/**
	 * Get the media's ID regardless of the media's nature.
	 * 
	 * @param m the media description
	 * @return the media ID 
	 */
	def public static getMediaId(MediaDescription m) {
		if(m instanceof VideoDescription)
			return m.videoid
		else if(m instanceof ImageDescription)
			return m.imageid
		
		throw new NullPointerException
	}
	
	def private static copyVariantList(List<List<MediaDescription>> all) {
    	val List<List<MediaDescription>> newAll = new ArrayList<List<MediaDescription>>
    	
    	all.forEach [ variant |
    		newAll.add(new ArrayList<MediaDescription>(variant))
    	]
    	
    	return newAll
    }
    
    def public static getAllVariants(VideoGeneratorModel videoGen, String inputFile) {
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
    
    def public static getAllIds(VideoGeneratorModel videoGen, String inputFile) {
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
    
    def public static makeCSV(VideoGeneratorModel videoGen, String inputFile) {
    	val all = getAllVariants(videoGen, inputFile)
    	val seqs = getAllIds(videoGen, inputFile)
    	
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
        	writer.write('''«variantSize(variant)»'''.toString + "\n")
        }
        
        writer.flush
        writer.close
    }
    
    def public static variantDuration(List<MediaDescription> variant) {
    	var duration = 0
    	
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
    
    def public static longestVariant(VideoGeneratorModel videoGen, String inputFile) {
		var List<MediaDescription> playlist = new ArrayList
		
		for(media : videoGen.medias) {
			if(media instanceof MandatoryMedia) {
				playlist.add(media.description)
			}
			
			else if(media instanceof OptionalMedia) {
				playlist.add(media.description)
			}
			
			else if(media instanceof AlternativesMedia) {
				var durations = media.medias.map [ m | FFMPEG.ffmpegComputeDuration(m.location) ]
				
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
    
    def public static probabilitiesAreCorrect(List<MediaDescription> medias) {
    	var hasFallback = false
    	var amount = 0
    	
    	for(media : medias) {
    		if(media instanceof VideoDescription) {
    			val prob = media.probability
    			if (prob == 0)
    				hasFallback = true
    			else
    				amount += prob
    		}
    	}
    	
    	return (amount == 100) || (amount < 100 && hasFallback)
    }
    
    def public static fillProbabilities(List<MediaDescription> medias) {
    	var empty = new LinkedList<MediaDescription>
    	var done = new LinkedList<MediaDescription>
    	
    	var amount = 0
    	
    	for (media : medias) {
    		if(media instanceof VideoDescription) {
    			if(media.probability == 0)
    				empty.add(media)
    			else {
    				amount += media.probability
    				media.probability = amount
    				done.add(media)
    			}
    		}
    	}
    	
    	val part = (100 - amount) / empty.size
    	
    	for (media : empty) {
    		if (media instanceof VideoDescription) {
    			amount += part
    			media.probability = amount
    		}
    	}
    	
    	done.addAll(empty)
    	return done
    }
}