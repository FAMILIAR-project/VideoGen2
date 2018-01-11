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
import configs.VideoGenConfigs
import java.util.HashMap

class VideoGenPlayTransformations {
	
	static def String generateRandomPlayList(VideoGeneratorModel videoGen){
		
		var playlist = new ArrayList<MediaDescription>
		
		for(media: videoGen.medias){
			if(media instanceof MandatoryMedia){
				
					playlist.add(media.description)

			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof ImageDescription){
					if(Math.random() * 2 < 1){
						playlist.add(media.description)
					}
				}
				
				if(media.description instanceof VideoDescription){
					
					var list = new ArrayList
					val optionalVideo = (media.description as VideoDescription)
					list.add(optionalVideo)

					val video = VideoGenUtils.getRandom(list)
					if(video !== null)
						playlist.add(video)
				}				
			}
			if(media instanceof AlternativesMedia){
				var isImageDescription = false
				if(media.medias.get(0) instanceof ImageDescription)
					isImageDescription = true
				if(isImageDescription){
					var alternativesIndex = (Math.random() * media.medias.size) as int
					val mdescription = (media.medias.get(alternativesIndex)) as MediaDescription
					playlist.add(mdescription)
				}
				else{
					var list = new ArrayList
					for(alternative: media.medias){
						val alternaiveVideo = alternative as VideoDescription
						list.add(alternaiveVideo)
					}
					playlist.add(VideoGenUtils.getRandom(list))
				}
				
			}
		}
		
		VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4"))	
	}
	

	
	
	static def HashMap<String,List<String>> makeThumbnails(VideoGeneratorModel videoGen){
		var thumbs = new HashMap<String,List<String>>
		for(media: videoGen.medias){
			if(media instanceof AlternativesMedia){
				for(alternativeMedia: media.medias){
					if(alternativeMedia instanceof VideoDescription)
						if(thumbs.get("Alternatives") !== null){
							var list = thumbs.get("Alternatives") as List<String>
							list.add(FFMPEGHelper.generateThumbnail(alternativeMedia.location))
						}
						else{
							var list = new ArrayList
							list.add(FFMPEGHelper.generateThumbnail(alternativeMedia.location))
							thumbs.put("Alternatives", list)
						}
				}
			}
			if(media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					if(thumbs.get("Mandatory") !== null){
						var list = thumbs.get("Mandatory") as List<String>
						list.add(FFMPEGHelper.generateThumbnail(media.description.location))
					}
					else{
						var list = new ArrayList
						list.add(FFMPEGHelper.generateThumbnail(media.description.location))
						thumbs.put("Mandatory", list)
					}
				}
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					if(thumbs.get("Optional") !== null){
						var list = thumbs.get("Optional") as List<String>
						list.add(FFMPEGHelper.generateThumbnail(media.description.location))
					}
					else{
						var list = new ArrayList
						list.add(FFMPEGHelper.generateThumbnail(media.description.location))
						thumbs.put("Optional", list)
					}
				}
			}
		}
		
		thumbs
	}
	
	static def List<String> makeWebPage(VideoGeneratorModel videoGen){
		var html = new ArrayList
		html.add("<div id=\"gallery\">")
		for(media: videoGen.medias){
			if(media instanceof AlternativesMedia){
				html.add("  <div class=\"row alternatives\">")
				for(alternativeMedia: media.medias){
					if(alternativeMedia instanceof VideoDescription)
						html.add("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + FFMPEGHelper.generateThumbnail(alternativeMedia.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
					if(alternativeMedia instanceof ImageDescription)
						html.add("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + alternativeMedia.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
					html.add("    </div>")
				}
				html.add("  </div>")
			}
			if(media instanceof MandatoryMedia){
				
				if(media.description instanceof VideoDescription)
					html.add("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + FFMPEGHelper.generateThumbnail(media.description.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
				if(media.description instanceof ImageDescription)
					html.add("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + media.description.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
				html.add("  </div>")
				
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription)
					html.add("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + FFMPEGHelper.generateThumbnail(media.description.location) + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">")
				if(media.description instanceof ImageDescription)
					html.add("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + VideoGenConfigs.getServerIP() + media.description.location + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">") 
				html.add("  </div>")
			}
		}
		html.add("</div>")
		html	
	}
	
	static def List<String> videoGensToGifs(VideoGeneratorModel videoGen){
		var gifs = new ArrayList
		val playlists = VideoGenUtils.generatePlaylists(videoGen)
		
		for(playlist: playlists){
			gifs.add(videoGenToGif(playlist))
		}
		
		gifs
	}
	
	static def String videoGenToGif(List<MediaDescription> playlist){
		
		FFMPEGHelper.videoToGif(
								VideoGenUtils.makePlaylist(
															playlist, 
															CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4")
														  ),
								VideoGenConfigs.getGifResolutions().get(0),
								VideoGenConfigs.getGifResolutions().get(1)
								)
	}
	
}