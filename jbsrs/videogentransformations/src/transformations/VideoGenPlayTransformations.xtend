package transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import utils.VideoGenUtils
import helpers.FFMPEGHelper
import utils.CommonUtils
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.List

class VideoGenPlayTransformations {
	
	static def String generateRandomPlayList(VideoGeneratorModel videoGen){
		
		var playlist = new ArrayList<MediaDescription>
		
		for(media: videoGen.medias){
			if(media instanceof MandatoryMedia){
				
				if(media.description instanceof ImageDescription){
					playlist.add(media.description)
				}
				
				if(media.description instanceof VideoDescription){

					playlist.add(media.description)
				}
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof ImageDescription){
					if(Math.random() * 2 < 1){
						playlist.add(media.description)
					}
				}
				
				if(media.description instanceof VideoDescription){
					val vdescription = (media.description as VideoDescription)
					if(Math.random() * 2 < 1){
						playlist.add(media.description)
					}
				}				
			}
			if(media instanceof AlternativesMedia){
				var isImageDescription = false
				if(media.medias.get(0) instanceof ImageDescription)
					isImageDescription = true
				//if(isImageDescription){
					var alternativesIndex = (Math.random() * media.medias.size) as int
					val mdescription = (media.medias.get(alternativesIndex)) as MediaDescription
					playlist.add(mdescription)
				//}
				
			}
		}
		
		VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName("output/playlists/playlist.mp4"))	
	}
	
		static def String getMaxDuration(VideoGeneratorModel videoGen){
		
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
	
	
	static def List<String> makeThumbnails(VideoGeneratorModel videoGen){
		val playlists = VideoGenUtils.generatePlaylists(videoGen)
		var thumbs = new ArrayList
		for(playlist: playlists){
			thumbs.add(FFMPEGHelper.generateThumbnail(VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName("output/playlists/playlist.mp4"))))
		}
		thumbs
	}
	
	static def void makeWebPage(VideoGeneratorModel videoGen){
		
	}
	
	static def void videoGenToGif(VideoGeneratorModel videoGen){
		
	}
	
}