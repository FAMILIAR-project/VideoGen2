package transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import helper.FFMPEGHelper

class VideoGenInformationTransformation {
	
	def String getMaxDuration(VideoGeneratorModel videoGen){
		
		var maxDuration = 0f;
		for(media: videoGen.medias){
			if(media instanceof AlternativesMedia){
				var alternativeMedia = media as AlternativesMedia
				var maxAlternativeDuration = 0f;
				for(alternative: alternativeMedia.medias){
					if(alternative instanceof VideoDescription){
						var videoDescription = alternative as VideoDescription
						var alternativeDuration = FFMPEGHelper.getVideoDuration(videoDescription.location)
						if(maxAlternativeDuration < alternativeDuration)
							maxAlternativeDuration = alternativeDuration
					}
				}
				maxDuration += maxAlternativeDuration
			}
			
			if(media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					var videoDescription = media.description as VideoDescription
					maxDuration += FFMPEGHelper.getVideoDuration(videoDescription.location)
				}
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					var videoDescription = media.description as VideoDescription
					maxDuration += FFMPEGHelper.getVideoDuration(videoDescription.location)
				}
			}

		}
		var minutesDuration = Math.floor(maxDuration / 60);
		var secondeDuration = (maxDuration / 60) - minutesDuration
		
		var stringDuration = minutesDuration.toString + " mn " + secondeDuration.toString + " s"
		
		return stringDuration
	}
	
	static def int getVariantNumber(VideoGeneratorModel videoGen){
		var variantNumber = 1
		for(media : videoGen.medias){
			if(media instanceof OptionalMedia){
				variantNumber *= 2
			}else if(media instanceof AlternativesMedia){
				variantNumber *= media.medias.size
			}
		}
		return variantNumber
	}
	
	
}