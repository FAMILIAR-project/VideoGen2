package fr.istic.m2il.idm.videogentransformations.transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.List
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenChekerHelper

/**
 * @author Ramadan Soumaila
 * Class for apply transformations related to played a videoGen specification
 */
class VideoGenPlayTransformations {
	
	static def String generateRandomPlayList(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null ){
				var playlist = VideoGenUtils.getRandomPlaylist(videoGen)
				VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4"))	
			}
		}
		else
			return null
	}
	
	
	static def List<String> getRandomPlayList(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null ){
				var playlist = VideoGenUtils.getRandomPlaylist(videoGen)
				VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4"))
				playlist	
			}
		}
		else
			return null	
	}
	

	
	
	static def List<String> makeThumbnails(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null){
				var thumbs = new ArrayList
				for(media: videoGen.medias){
					if(media instanceof AlternativesMedia){
						for(alternativeMedia: media.medias){
							thumbs.add(FFMPEGHelper.generateThumbnail(alternativeMedia.location))	
						}
				   }
					if(media instanceof MandatoryMedia){
						thumbs.add(FFMPEGHelper.generateThumbnail(media.description.location))		
					}
					if(media instanceof OptionalMedia){
						thumbs.add(FFMPEGHelper.generateThumbnail(media.description.location))
					}
				}
		
				thumbs
			}
		}
	}
	
	static def List<String> makeWebPage(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null ){
				var html = new ArrayList
				html.add("<div class=\"row\" id=\"gallery\">")
				for(media: videoGen.medias){
					if(media instanceof AlternativesMedia){
						html.add("  <div class=\"row alternatives\">")
						for(alternativeMedia: media.medias){
							if(alternativeMedia instanceof VideoDescription)
								html.add("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + FFMPEGHelper.generateThumbnail(alternativeMedia.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
							if(alternativeMedia instanceof ImageDescription)
								html.add("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + alternativeMedia.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
							html.add("    </div>")
						}
						html.add("  </div>")
					}
					if(media instanceof MandatoryMedia){
						if(media.description instanceof VideoDescription)
							html.add("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + FFMPEGHelper.generateThumbnail(media.description.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
						if(media.description instanceof ImageDescription)
							html.add("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + media.description.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
						html.add("  </div>")
				
					}
					if(media instanceof OptionalMedia){
						if(media.description instanceof VideoDescription)
							html.add("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + FFMPEGHelper.generateThumbnail(media.description.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
						if(media.description instanceof ImageDescription)
							html.add("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + media.description.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
						html.add("  </div>")
					}
				}
				html.add("</div>")
				return html	
			}
		}
		else{
			return null
		}
	}
		
	static def List<String> videoGensToGifs(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null  && VideoGenConfigs.getGifResolutions.get(0) > 0 && VideoGenConfigs.getGifResolutions.get(1) > 0){
				var gifs = new ArrayList
				val playlists = VideoGenUtils.generatePlaylists(videoGen)
		
				for(playlist: playlists){
					gifs.add(videoGenToGif(playlist))
				}
				return gifs
			} 
		}
		else{
			return null
		}
	}
	
	static def String videoGenToGif(List<MediaDescription> playlist){
		
		var filterizedList = applyFilters(playlist);
		
		if(VideoGenConfigs.outPutFoulder !== null  && VideoGenConfigs.getGifResolutions.get(0) > 0 && VideoGenConfigs.getGifResolutions.get(1) > 0){
			return FFMPEGHelper.videoToGif(
								VideoGenUtils.makePlaylist(
															filterizedList, 
															CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4")
														  ),
								VideoGenConfigs.getGifResolutions().get(0),
								VideoGenConfigs.getGifResolutions().get(1)
								)
		}
		else return null
	}
	
	static def String videosToGif(List<String> videos){
		var video = VideoGenUtils.makePlaylist(videos, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4"))
		FFMPEGHelper.videoToGif(video, VideoGenConfigs.getGifResolutions().get(0),VideoGenConfigs.getGifResolutions().get(1))
	}
	
	
	static def List<String> applyFilters(List<MediaDescription> mediaDescriptions){
		var medias = newArrayList
		
		for(media: mediaDescriptions){
			if(media instanceof VideoDescription){
				if(media !== null ){
					var String location = media.location
					if(media.filter !== null){
						medias.add(FFMPEGHelper.applyFilter(VideoGenUtils.getFilter(media), media.location))
					}
					else{
						medias.add(media.location)
					}
					
				}
			}
			else{
				if( (media as ImageDescription) !== null){
					medias.add(media.location)
				}
			}
		}
		medias
	}
	
}