package utils

import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import helpers.FFMPEGHelper
import java.io.File

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
				//newPlaylists.add(list)
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
	
	
	
	
	static def String makePlaylist(List<MediaDescription> medias, String playlistName){
		val resolutions = new ArrayList
		
		for(media : medias){
			if(media !== null && !media.location.replace(".","@").split("@").get(1).equals("jpg"))
			resolutions.add(FFMPEGHelper.getVideoResolution(media.location))
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
		val playlist = new ArrayList<MediaDescription>
		for(media : medias){
				if(media !== null){
					media.location = FFMPEGHelper.homogenizeMediaResolution(
															media.location, 
															FFMPEGHelper.getVideoResolution(media.location).get(0), 
															FFMPEGHelper.getVideoResolution(media.location).get(1), 
															maxOutputWidth, 
															maxOutputHeight
															)
				playlist.add(media
						)
				}
			
		}
		
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
			playlistsGif.add(getGif(playlist, "output/gifs/playlist_" + playlistIndex, width, heigth))
		}
		playlistsGif
	}
	
	static def String getGif(List<MediaDescription> playlist, String playlistName, int width, int heigth){
		FFMPEGHelper.videoToGif(makePlaylist(playlist, playlistName), width, heigth)
	}
}