package fr.istic.m2il.idm.videogentransformations.helpers

import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.util.List
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import java.util.HashSet
import java.util.Set
import java.io.File

class VideoGenChekerHelper {
	
	
	static def boolean isGoodOptionalVideoProbality(VideoDescription video){
		isGoodVideoProbability(video)
	}
	
	static def boolean isGoodAlternativesVideosProbabilties(List<VideoDescription> videos){
		var isGood = true
		var probabities = 0
		for(video:videos){
			if(!isGoodVideoProbability(video))
				return false
			probabities += video.probability
		}
		if(probabities <0 ||probabities > 100)
			isGood = false
		isGood
	}
	
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
	
	static def boolean isGoodVideoProbability(VideoDescription video){
		var isGood = true
		if(video.probability < 0 || video.probability > 100)
			isGood = false
		isGood
	}
	
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
	
	static def boolean isNotEmptyVideoGen(VideoGeneratorModel videoGen){
		videoGen.medias.empty
	}
}