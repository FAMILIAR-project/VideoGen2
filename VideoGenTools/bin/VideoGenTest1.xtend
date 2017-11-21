import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

import static org.junit.Assert.*

class VideoGenTest1 {
	
	def List<List<MediaDescription>> loadPlaylists(VideoGeneratorModel videoGen){
		assertNotNull(videoGen)
		
		var playlists = new ArrayList<List<MediaDescription>>
		var nbPlaylists = 1
		var nbVideos = 0
		playlists.add(new ArrayList<MediaDescription>())
		for(media : videoGen.medias){
			if (media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					nbVideos++
					for(playlist : playlists){
						playlist.add(media.description);
					}
				}
			}else if(media instanceof AlternativesMedia){
					nbPlaylists *= media.eContents.size
					nbVideos += media.eContents.size
					playlists = populatePlaylists(playlists, media)
				
			}else if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					nbPlaylists *= 2
					nbVideos++
					playlists = populatePlaylists(playlists, media)
				}		
			}
		}
		return playlists
	}
	
	@Test
	def void testLoadModel() {
		var videosFiles = newArrayList("example1.videogen", "example2.videogen")
		
		for(file : videosFiles){
			val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(file))
			val playlists = loadPlaylists(videoGen)
			var csv = createCSV(playlists)
			var variants = genNbVariant(videoGen)
			println(file + ' : csv size -> ' + (csv.length - 1) + ' nbVariants -> ' + variants)
			assertTrue(csv.length - 1 == variants)
		}
	}
	
	def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, OptionalMedia opt){
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
	
	def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, AlternativesMedia alt){
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
	
	def void printPlaylist(List<List<MediaDescription>> playlists){
		var i = 0;
		for(playlist : playlists){
			println('Playlist ' + i++)
			for(media : playlist){
				println(media)
			}
		}
	}
	
	def String createCSVLine(List<MediaDescription> playlist, int index){
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
	
	def String createCSVHeader(List<List<MediaDescription>> playlists){
		val headerList = new ArrayList<String>()
		for(mediaDesc : playlists.get(0)){
			headerList.add(null);
		}
		for(playlist : playlists){
			var index = 0
			for(mediaDesc : playlist){
				if(headerList.get(index) === null && mediaDesc !== null){
					headerList.set(index, (mediaDesc as VideoDescription).videoid)
				}
				index++;
			}
		}
		var header = 'id;'
		for(str : headerList){
			header += str + ';'
		}
		header += 'size;'
		return header
	}
	
	def List<String> createCSV(List<List<MediaDescription>> playlists){
		var csv = new ArrayList<String>()
		csv.add(createCSVHeader(playlists))
		var index = 0
		for(playlist : playlists){
			csv += createCSVLine(playlist, index++) + '\n'
		}
		return csv
	}
	
	def int genNbVariant(VideoGeneratorModel videoGen){
		var variant = 1
		for(media : videoGen.medias){
			if(media instanceof OptionalMedia){
				variant *= 2;
			}else if(media instanceof AlternativesMedia){
				variant *= media.medias.size
			}
		}
		return variant
	}
}