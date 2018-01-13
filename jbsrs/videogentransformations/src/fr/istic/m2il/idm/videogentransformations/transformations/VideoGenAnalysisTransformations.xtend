package fr.istic.m2il.idm.videogentransformations.transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenChekerHelper

class VideoGenAnalysisTransformations {
	
	
	static def int getMaxDuration(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			var maxDuration = 0;
			for(media: videoGen.medias){
				if(media instanceof AlternativesMedia){
					var alternativeMedia = media as AlternativesMedia
					var maxAlternativeDuration = 0;
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

			maxDuration
		}
	}
	
	static def double getMinimalSize(VideoGeneratorModel videoGen){
		
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		
		var minimalSize = getPlayListSize(playlists.get(0))
		
		for(playlist: playlists){
			var playlistSize = getPlayListSize(playlist)
			if(playlistSize < minimalSize)
			minimalSize = playlistSize
		}
		minimalSize
	}
	
	static def double getMaximalSize(VideoGeneratorModel videoGen){
		var maximalSize = 0.0
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		
		for(playlist: playlists){
			var playlistSize = getPlayListSize(playlist)
			
			if(playlistSize > maximalSize)
			maximalSize = playlistSize
		}
		maximalSize
	}
	
	private static def double getPlayListSize(List<MediaDescription> playlist){
		var playlistSize = 0.0
		for(video: playlist){
				val videoDescription = video as VideoDescription
				playlistSize += VideoGenUtils.getVideoSize(videoDescription)
			}
		playlistSize
	}
	
	static def double getAverageSize(VideoGeneratorModel videoGen){
		var averageSize = 0.0
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		
		for(playlist: playlists){
			var playlistSize = getPlayListSize(playlist)
			averageSize += playlistSize
		}
		averageSize / playlists.size
	}
	
	static def double getStandardDeviation(VideoGeneratorModel videoGen){
		var standardDeviation = 0.0
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		
		for(playlist: playlists){
			standardDeviation += Math.pow(getPlayListSize(playlist) - getAverageSize(videoGen),2)
		}
		
		Math.sqrt(standardDeviation/playlists.size);
	}
	
	static def double getRealZise(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null){
				var realSize = 0.0
				var playlists = VideoGenUtils.generatePlaylists(videoGen)
				var playlistIndex = 0;
				for(playlist: playlists){
					realSize += getRealSize(playlist)
					playlistIndex++
				}
				realSize
			}
		}
	}
	
	static def double getRealSize(List<MediaDescription> playlist){
		VideoGenUtils.getVideoSize(VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4")))
	}
	
	static def double getRealSize(String playlistLocation){
		VideoGenUtils.getVideoSize(playlistLocation)
	}

}