package fr.istic.m2il.idm.videogentransformations.utils

import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper
import java.io.File
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.Random
import org.xtext.example.mydsl.videoGen.ImageDescription

class VideoGenUtils {
	
	
	static def List<List<MediaDescription>> generatePlaylists(VideoGeneratorModel videoGen){
		
		var playlists = new ArrayList<List<MediaDescription>>
		playlists.add(new ArrayList<MediaDescription>())
		for(media : videoGen.medias){
			if (media instanceof MandatoryMedia){
				for(playlist : playlists){
						playlist.add(media.description);
				}
			}else if(media instanceof AlternativesMedia){
					playlists = populatePlaylists(playlists, media)
				
			}else if(media instanceof OptionalMedia){
				playlists = populatePlaylists(playlists, media)		
			}
		}
		writePlaylistsFile(playlists)
		return playlists
	}
	
	static private def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, OptionalMedia opt){
		val newPlaylists = new ArrayList<List<MediaDescription>>
		for(playlist : playlists){
			var list1 = new ArrayList<MediaDescription>(playlist)
			list1.add(opt.description)
			newPlaylists.add(list1)
			var list2 = new ArrayList<MediaDescription>(playlist)
			list2.add(null)
			newPlaylists.add(list2)
		}		
		return newPlaylists
	}
	
	static private def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, AlternativesMedia alt){
		val newPlaylists = new ArrayList<List<MediaDescription>>
		for(playlist : playlists){
			var size = alt.eContents.size - 1
			var index = 0
			for(desc : alt.eContents){
				var list = new ArrayList<MediaDescription>(playlist)
				for(var i = 0; i < index; i++){
					list.add(null);
				}
				list.add(desc as MediaDescription)
				for(var i = 0; i < size; i++){
					list.add(null);
				}
				newPlaylists.add(list)
				size--;
				index++;
			}
		}
		return newPlaylists
	}
	
	
	
	
	static def String makePlaylist(List<String> locations, String playlistName){
		val resolutions = new ArrayList
		
		for(location : locations){
			if(location !== null && 
				
				(
					!location.replace(".","@").split("@").get(1).equals("jpg") && 
					!location.replace(".","@").split("@").get(1).equals("png") &&
					!location.replace(".","@").split("@").get(1).equals("gif") &&
					!location.replace(".","@").split("@").get(1).equals("bpm") &&
					!location.replace(".","@").split("@").get(1).equals("tiff")
				)
			)
			resolutions.add(FFMPEGHelper.getVideoResolution(location))
		}
		
		var maxOutputWidth = 0
		var maxOutputHeight = 0
		
		for(resolution : resolutions){
			if(resolution.get(0) > maxOutputWidth){
				maxOutputWidth = resolution.get(0)
			}
			if(resolution.get(1) > maxOutputHeight){
				maxOutputHeight = resolution.get(1)
			}
		}
		var i = 0;
		val playlist = new ArrayList
		for(location : locations){
				if(location !== null){
					playlist.add(FFMPEGHelper.homogenizeMediaResolution(
															location, 
															FFMPEGHelper.getVideoResolution(location).get(0), 
															FFMPEGHelper.getVideoResolution(location).get(1), 
															maxOutputWidth, 
															maxOutputHeight
															)
					)
				}
			
		}
		var playlistWrite = newArrayList
		for(p : playlist){
			playlistWrite.add("file '" + p + "'" + '\n')
		}
		CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.txt"), playlistWrite)
		return FFMPEGHelper.concatVideos(playlist, playlistName)
		
	}
	
	static def double getVideoSize(MediaDescription media){
		VideoGenUtils.getVideoSize(media.location)
	}
	
	static def double getVideoSize(String location){
		new File(location).length
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
	
	static def List<String> getGif(VideoGeneratorModel videoGen, int width, int heigth){
		var playlistsGif = new ArrayList
		var playlists = generatePlaylists(videoGen)
		var playlistIndex = 0
		for(playlist: playlists){
			playlistsGif.add(getGif(playlist, VideoGenConfigs.outPutFoulder + "/gifs/playlist_" + playlistIndex, width, heigth))
		}
		playlistsGif
	}
	
	static def String getGif(List<MediaDescription> playlist, String playlistName, int width, int heigth){
		FFMPEGHelper.videoToGif(makePlaylist(getMediaDescriptionsLocation(playlist), playlistName), width, heigth)
	}
	
	static def VideoDescription getRandom(List<VideoDescription> videos){
		var random = new Random();
		var totalSum = 0
		for(video: videos){
			totalSum += video.probability
		}
		
		if(totalSum == 0){
        	if(videos.get(videos.size - 1) instanceof AlternativesMedia){
        		var alternativesIndex = (Math.random() * videos.size) as int
        		return videos.get(alternativesIndex)
        	}
        	else{
        		if(Math.random() * 2 < 1){
						return videos.get(0)
				}
				else
					return null
        	}
        	
        }
		
		var index = random.nextInt(totalSum)
        var sum = 0
        var i=0
        while(sum < index ) {
             sum = sum + videos.get(i++).probability
        }
        videos.get(Math.max(0,i-1))
	}
	
	static def int getVideoGenMediasNumber(VideoGeneratorModel videoGen){
		var mediasNumber = 0
		
		for(media: videoGen.medias){
			if(media instanceof MandatoryMedia){
					mediasNumber++
			}
			if(media instanceof OptionalMedia){
				mediasNumber++	
			}
			if(media instanceof AlternativesMedia){
				for(m:media.medias){
					mediasNumber ++
				}
			}
		}
		
		
		mediasNumber
	}
	
	static def writePlaylistsFile(List<List<MediaDescription>> playlists){
		val files = new ArrayList
		for(p : playlists){
			for(media: p){
				if(media !== null){
					files.add("file '" + media.location + "'" + '\n')
				}
			}
			CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.txt"), files)
		}
	}
	
	static def List<String> getMediaDescriptionsLocation(List<MediaDescription> playlist){
		var playlistlocations = newArrayList
		for(media: playlist){
			if( media instanceof AlternativesMedia){
				for(alt: media.medias){
					if(media !== null)
						playlistlocations.add(media.location)
				}
			}
			else
				if(media !== null)
					playlistlocations.add(media.location)
		}
		playlistlocations
	}
	
	static def List<String> getRandomPlaylist(VideoGeneratorModel videoGen){
		var playlist = newArrayList
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
		getMediaDescriptionsLocation(playlist)
	}
}