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
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter
import org.xtext.example.mydsl.videoGen.NegateFilter
import org.xtext.example.mydsl.videoGen.FlipFilter

class VideoGenPlayTransformations {
	
	static def String generateRandomPlayList(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null ){
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
							if(alternativeMedia instanceof VideoDescription)
								thumbs.add(FFMPEGHelper.generateThumbnail(alternativeMedia.location))
							if(alternativeMedia instanceof ImageDescription)
								thumbs.add(alternativeMedia.location)		
						}
				   }
					if(media instanceof MandatoryMedia){
						if(media.description instanceof VideoDescription){
							thumbs.add(FFMPEGHelper.generateThumbnail(media.description.location))
						}
						if(media.description instanceof ImageDescription){
							thumbs.add(media.description.location)
						}
							
					}
					if(media instanceof OptionalMedia){
						if(media.description instanceof VideoDescription){
								thumbs.add(FFMPEGHelper.generateThumbnail(media.description.location))
						}
						if(media.description instanceof ImageDescription){
							thumbs.add(media.description.location)
						}
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
		
		if(VideoGenConfigs.outPutFoulder !== null  && VideoGenConfigs.getGifResolutions.get(0) > 0 && VideoGenConfigs.getGifResolutions.get(1) > 0){
			return FFMPEGHelper.videoToGif(
								VideoGenUtils.makePlaylist(
															playlist, 
															CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4")
														  ),
								VideoGenConfigs.getGifResolutions().get(0),
								VideoGenConfigs.getGifResolutions().get(1)
								)
		}
		else return null
	}
	
	static def String videoGenApplyFilters(VideoGeneratorModel videoGen){
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(VideoGenConfigs.outPutFoulder !== null ){
				for(media:videoGen.medias){
					if(media instanceof AlternativesMedia){
						for(m:media.medias){
							if(m instanceof VideoDescription){
								val vdescription = media as VideoDescription
								if(vdescription.filter instanceof BlackWhiteFilter){
									vdescription.location = FFMPEGHelper.applyFilter("format=gray", vdescription.location)
								}
								if(vdescription.filter instanceof NegateFilter){
									vdescription.location = FFMPEGHelper.applyFilter("negate", vdescription.location)
								}
				
								if(vdescription.filter instanceof FlipFilter){
									val flipFilter = vdescription.filter as FlipFilter
									var flipOrientation = flipFilter.orientation
									if(flipOrientation.equals("horizontal"))
										flipOrientation = "h"
									if(flipOrientation.equals("vertical"))
										flipOrientation = "v"	
						
									vdescription.location = FFMPEGHelper.applyFilter(flipOrientation+"flip", vdescription.location)
								}
							}
						}
					}
					else{
						if(media instanceof VideoDescription){
							val vdescription = media as VideoDescription
							if(vdescription.filter instanceof BlackWhiteFilter){
								vdescription.location = FFMPEGHelper.applyFilter("format=gray", vdescription.location)
							}
							if(vdescription.filter instanceof NegateFilter){
								vdescription.location = FFMPEGHelper.applyFilter("negate", vdescription.location)
							}
				
							if(vdescription.filter instanceof FlipFilter){
								val flipFilter = vdescription.filter as FlipFilter
								var flipOrientation = flipFilter.orientation
								if(flipOrientation.equals("horizontal"))
									flipOrientation = "h"
								if(flipOrientation.equals("vertical"))
									flipOrientation = "v"	
						
								vdescription.location = FFMPEGHelper.applyFilter(flipOrientation+"flip", vdescription.location)
							}
						}
					}
				}
			}
		}
	}
	
}