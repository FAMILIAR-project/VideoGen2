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
	
	/**
	 * Deep copy of a list of variants.
	 * 
	 * @param all the list of variants
	 * @return the copy
	 */
	def private static copyVariantList(List<List<MediaDescription>> all) {
    	val List<List<MediaDescription>> newAll = new ArrayList<List<MediaDescription>>
    	
    	all.forEach [ variant |
    		newAll.add(new ArrayList<MediaDescription>(variant))
    	]
    	
    	return newAll
    }
    
    /**
     * Computes every possible variants of the videogen.
     * 
     * @param videoGen the videogen
     * @return the variants
     */
    def public static getAllVariants(VideoGeneratorModel videoGen) {
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
    
    /**
     * Get the ID of each media in the videogen.
     * 
     * @param videoGen the videogen
     * @return the ID of each media
     */
    def public static getAllIds(VideoGeneratorModel videoGen) {
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
    
    /**
     * Get the longest variant in terms of duration.
     * 
     * @param videoGen the videogen
     * @return the longest variant
     */
    def public static longestVariant(VideoGeneratorModel videoGen) {
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
    
    /**
     * Check if the probabilities given in the alternatives respect the given laws:
     *   - the addition of all does not exceed 100
     *   - medias without probabilities equally share the remaining probabilities
     * 
     * @param medias the list of alternatives
     * @return true if the laws are respected, false otherwise
     */
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
    
    /**
     * Write explicitly the probabilities of the medias that didn't have theirs user-specified.
     * 
     * @param medias the list of alternatives
     * @return the new list of alternatives
     */
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