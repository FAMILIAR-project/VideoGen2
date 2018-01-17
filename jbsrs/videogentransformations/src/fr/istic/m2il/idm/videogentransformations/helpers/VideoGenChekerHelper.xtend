package fr.istic.m2il.idm.videogentransformations.helpers

import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.util.List
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import java.io.File

/**
 * @author Ramadan Soumaila
 * A helper class to check the validity of a videoGen specification
 */
class VideoGenChekerHelper {
	
	/**
	 * Checks if a optional video description's probability is valid
	 * the probability must less than or equal to 1 or 100%
	 * 
	 * @param video the video description to check
	 * @return a boolean value which is true if the probability is valid , else otherwise
	 */
	static def boolean isGoodOptionalVideoProbality(VideoDescription video){
		isGoodVideoProbability(video)
	}
	
	/**
	 * Checks if the probabilities of a alternatives medias are valid
	 * the sum of his medias's probabilities can't over 100% 
	 * 
	 * @param videos the list of alternattives's video description to check
	 * @return a boolean value which is true if the sum of probabilities is valid,or else otherwise
	 */
	static def boolean isGoodAlternativesVideosProbabilties(List<VideoDescription> videos){
		var isGood = true
		var probabities = 0
		for(video:videos){
			if(!isGoodVideoProbability(video))
				return false
			probabities += video.probability
		}
		if(probabities <0 || probabities > 100)
			isGood = false
		isGood
	}
	/**
	 * Checks the validity of probabilities in a videoGen specification
	 * for alternatives media the sum of probabilities must less than or equal to 100%
	 * for optional media the probability must less or equal to 1 or 100% 
	 * 
	 * @param videoGen the VideoGeneratorModel to check
	 * @return a boolean value, which is true if all medias's probabilities are valid
	 */
	static def boolean isGoodMediasProbabilities(VideoGeneratorModel videoGen){
		var isGoodMediasProbabilities = true
		for(media:videoGen.medias){
			if(media instanceof AlternativesMedia){
				var list = new ArrayList
				for(m:media.medias){
					if(m instanceof VideoDescription){
						list.add(m)
					}
				}
				if(!isGoodAlternativesVideosProbabilties(list))
					return false
			}
			if(media instanceof OptionalMedia){
				if(media instanceof VideoDescription){
					if(!isGoodOptionalVideoProbality(media))
						return false
				}
			}
		}
		isGoodMediasProbabilities
	}
	/**
	 * Checks if the videoGen specification is valid :
	 * All probabilities must be coherent :
	 * a optional media's probability can't over 1 or 100%
	 * the sum of alternatives media's probabilities must less than or equal 100%
	 * All medias's identifier must be unique
	 * The media's files (images or videos) specified must existed
	 * @param videoGen the videoGenerator specification to check
	 * @retun a boolean value which is true if the specification is valid, or else otherwise
	 */
	static def boolean isGoodVideoGenSpecification(VideoGeneratorModel videoGen){
		var isGood = true
		if(isNotEmptyVideoGen(videoGen))
			return false
		if(!isGoodMediasProbabilities(videoGen))
			return false
		if(!isAllMediasIdIsUnique(videoGen))
			return false
		if(!isAllMediasFilesExist(videoGen))
			return false
		isGood
	}
	
	/**
	 * Checks if a VideoDescription has a valid probability
	 * 
	 * @param video the videoDescription to check
	 * @return the boolean value, which is true if the specification is valid, or else otherwise
	 */
	static def boolean isGoodVideoProbability(VideoDescription video){
		var isGood = true
		if(video.probability < 0 || video.probability > 100)
			isGood = false
		isGood
	}
	
	/**
	 * Checks the uniqueness of medias's id in a VideoGen specification.
	 * 
	 * @param videoGen the videoGenerator specification to check
	 * @return a boolean value, which is true if all medias's id are unique , or false otherwise	
	 */
	def static boolean isAllMediasIdIsUnique(VideoGeneratorModel videoGen){
		var isAllIdUnique = true
		var idList = new ArrayList
		for(media:videoGen.medias){
			if(media instanceof AlternativesMedia){
				idList.add(media.id.toString)
				for(m: media.medias){
					if(m instanceof VideoDescription)
						idList.add(m.videoid.toString)
					if(m instanceof ImageDescription)
						idList.add(m.imageid.toString)
				}
			}
			if(media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription)
					idList.add((media.description as VideoDescription).videoid.toString)
				if(media.description instanceof ImageDescription)
					idList.add((media.description as ImageDescription).imageid.toString)
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription)
					idList.add((media.description as VideoDescription).videoid.toString)
				if(media.description instanceof ImageDescription)
					idList.add((media.description as ImageDescription).imageid.toString)
			}
						
		}
	
		val set = newHashSet
		set.addAll(idList)
		if(set.size < idList.size){
    		isAllIdUnique = false
		}
		isAllIdUnique
	}
	
	/**
	 * Checks the if videoGen medias's files existed
	 * 
	 * @param videoGen the videoGen specification to check
	 * @return a boolean value which is true if all medias's file exist, or else otherwise
	 */
	static def boolean isAllMediasFilesExist(VideoGeneratorModel videoGen){
		var isallMediaFilesExist = true
		for(media:videoGen.medias){
			if(media instanceof AlternativesMedia){
				for(m: media.medias){
					if(m instanceof VideoDescription)
						if(!new File(m.location).exists)
							return false
					if(m instanceof ImageDescription)
						if(!new File(m.location).exists)
							return false
				}
			}
			if(media instanceof MandatoryMedia){
				if(media instanceof VideoDescription)
					if(!new File(media.location).exists)
							return false
				if(media instanceof ImageDescription)
					if(!new File(media.location).exists)
							return false
			}
			if(media instanceof OptionalMedia){
				if(media instanceof VideoDescription)
					if(!new File(media.location).exists)
							return false
				if(media instanceof ImageDescription)
					if(!new File(media.location).exists)
							return false
			}
		}
		isallMediaFilesExist
	}
	
	/**
	 * Checks if a videoGen specification is not empty
	 * 
	 * @param videoGen the videoGen specification to check
	 * @return a boolean value which is true if is not empty, or else otherwise
	 */
	static def boolean isNotEmptyVideoGen(VideoGeneratorModel videoGen){
		videoGen.medias.empty
	}
}