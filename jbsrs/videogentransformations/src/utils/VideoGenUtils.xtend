package utils

import java.util.List
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

class VideoGenUtils {
	
	
	static def List<List<MediaDescription>> makePlaylists(VideoGeneratorModel videoGen){
		
		var playlists = new ArrayList<List<MediaDescription>>
		playlists.add(new ArrayList<MediaDescription>())
		for(media : videoGen.medias){
			if (media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					for(playlist : playlists){
						playlist.add(media.description);
					}
				}
			}else if(media instanceof AlternativesMedia){
					playlists = populatePlaylists(playlists, media)
				
			}else if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					playlists = populatePlaylists(playlists, media)
				}		
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
	
	static def int getMinimalSize(VideoGeneratorModel videoGen){
		return 0;
	}
	
	static def int getMaximalSize(VideoGeneratorModel videoGen){
		return 0;
	}
	
	static def int getAverageSize(VideoGeneratorModel videoGen){
		return 0;
	}
	
	static def int getStandardDeviation(VideoGeneratorModel videoGen){
		return 0
	}
}