package transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import utils.VideoGenUtils
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.ArrayList
import helper.FFMPEGHelper

class VideoGenAnalysisTransformation {
	
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
		var realSize = 0.0
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		var playlistIndex = 0;
		for(playlist: playlists){
			realSize += getRealSize(playlist, "playlist_" + playlistIndex)
			playlistIndex++
		}
		realSize
	}
	
	static def double getRealSize(List<MediaDescription> playlist, String playlistName){
		VideoGenUtils.getVideoSize(VideoGenUtils.generatePlaylist(playlist, playlistName))
	}
	
	static def List<String> getGif(VideoGeneratorModel videoGen, int width, int heigth){
		var playlistsGif = new ArrayList
		var playlists = VideoGenUtils.generatePlaylists(videoGen)
		var playlistIndex = 0
		for(playlist: playlists){
			playlistsGif.add(getGif(playlist, "output/gifs/playlist_" + playlistIndex, width, heigth))
		}
		playlistsGif
	}
	
	static def String getGif(List<MediaDescription> playlist, String playlistName, int width, int heigth){
		FFMPEGHelper.videoToGif(VideoGenUtils.generatePlaylist(playlist, playlistName), width, heigth)
	}

}