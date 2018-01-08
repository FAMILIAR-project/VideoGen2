package helper

import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.List
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import utils.VideoGenUtils

class CSVHelper {
	
	
	static private def String addLine(List<MediaDescription> playlist, int index){
		var line = index + ';'
		var size = 0;
		for(mediaDescription : playlist){
			if(mediaDescription !== null){
				if((mediaDescription as VideoDescription).text !== null){
					size += (mediaDescription as VideoDescription).text.size
				}
				line += 'TRUE' + ';'
			}else{
				line += 'FALSE' + ';'
			}
		}
		return line + size + ';'
	}
	
	static private def String makeHeader(List<List<MediaDescription>> playlists){
		val headers = new ArrayList<String>()
		for(mediaDescription : playlists.get(0)){
			headers.add(null)
		}
		for(playlist : playlists){
			var index = 0
			for(mediaDescription : playlist){
				if(headers.get(index) === null && mediaDescription !== null){
					headers.set(index, (mediaDescription as VideoDescription).videoid)
				}
				index++
			}
		}
		var header = 'id;'
		for(str : headers){
			header += str + ';'
		}
		header += 'size;'
		
		header += 'realSize'
		return header
	}
	
	static def List<String> create(VideoGeneratorModel videoGen){
		return create(VideoGenUtils.makePlaylists(videoGen))
	}
	
	static def List<String> create(List<List<MediaDescription>> playlists){
		var csvContent = new ArrayList<String>()
		csvContent.add(makeHeader(playlists))
		var index = 0
		for(playlist : playlists){
			csvContent += addLine(playlist, index++) + '\n'
		}
		return csvContent
	}
	
}