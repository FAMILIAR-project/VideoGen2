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
	
	static def String makeWebPage(VideoGeneratorModel videoGen){
		var html = "<div>"
		for(media: videoGen.medias){
			if(media instanceof AlternativesMedia){
				for(alternativeMedia: media.medias){
					if(alternativeMedia instanceof VideoDescription)
					
				}
			}
			if(media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					
				}
			}
			if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					
				}
			}
		}
		html += "</div>"
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