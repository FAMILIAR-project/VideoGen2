import java.util.ArrayList
import java.util.List
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

class VideoGenUtils {
	
	static def List<List<MediaDescription>> loadPlaylists(VideoGeneratorModel videoGen){
		
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
	
	static def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, OptionalMedia opt){
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
				newPlaylists.add(list)
				for(var i = 0; i < size; i++){
					list.add(null);
				}
				size--;
				index++;
			}
		}
		return newPlaylists
	}
	
	static def void printPlaylist(List<List<MediaDescription>> playlists){
		var i = 0;
		for(playlist : playlists){
			println('Playlist ' + i++)
			for(media : playlist){
				println(media)
			}
		}
	}
	
	static private def String createCSVLine(List<MediaDescription> playlist, int index){
		var line = index + ';'
		var size = 0;
		for(desc : playlist){
			if(desc !== null){
				if((desc as VideoDescription).text !== null){
					size += (desc as VideoDescription).text.size
				}
				line += 'TRUE' + ';'
			}else{
				line += 'FALSE' + ';'
			}
		}
		return line + size + ';'
	}
	
	static private def String createCSVHeader(List<List<MediaDescription>> playlists){
		val headerList = new ArrayList<String>()
		for(mediaDesc : playlists.get(0)){
			headerList.add(null)
		}
		for(playlist : playlists){
			var index = 0
			for(mediaDesc : playlist){
				if(headerList.get(index) === null && mediaDesc !== null){
					headerList.set(index, (mediaDesc as VideoDescription).videoid)
				}
				index++
			}
		}
		var header = 'id;'
		for(str : headerList){
			header += str + ';'
		}
		header += 'size;'
		return header
	}
	
	static def List<String> createCSV(VideoGeneratorModel videoGen){
		return createCSV(loadPlaylists(videoGen))
	}
	
	static private def List<String> createCSV(List<List<MediaDescription>> playlists){
		var csv = new ArrayList<String>()
		csv.add(createCSVHeader(playlists))
		var index = 0
		for(playlist : playlists){
			csv += createCSVLine(playlist, index++) + '\n'
		}
		return csv
	}
	
	static def int genNbVariant(VideoGeneratorModel videoGen){
		var variant = 1
		for(media : videoGen.medias){
			if(media instanceof OptionalMedia){
				variant *= 2
			}else if(media instanceof AlternativesMedia){
				variant *= media.medias.size
			}
		}
		return variant
	}
}