package helpers

import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.List
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import utils.VideoGenUtils
import transformations.VideoGenAnalysisTransformation

class CSVHelper {
	
	
	static private def String addLine(List<MediaDescription> playlist, int index, boolean isGif){
		var line = index + ';'
		var size = 0.0;
		for(mediaDescription : playlist){
			if(mediaDescription !== null){
				if((mediaDescription as VideoDescription).text !== null){
					size += VideoGenUtils.getVideoSize(mediaDescription)
				}
				line += 'TRUE' + ';'
			}else{
				line += 'FALSE' + ';'
			}
		}
		if(isGif)
			return line + size + ";" + VideoGenAnalysisTransformation.getRealSize(playlist, "playlist_" + index)
		else
			return line + size + ";" + VideoGenAnalysisTransformation.getRealSize(VideoGenUtils.getGif(playlist, "playlist_" + index, 190, 60))
	}
	
	static private def String addHeader(List<List<MediaDescription>> playlists){
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
	
	static def List<String> create(VideoGeneratorModel videoGen, boolean isGif){
		return create(VideoGenUtils.generatePlaylists(videoGen), isGif)
	}
	
	static def List<String> create(List<List<MediaDescription>> playlists, boolean isGif){
		var csvContent = new ArrayList<String>()
		csvContent.add(addHeader(playlists))
		var index = 0
		for(playlist : playlists){
			csvContent += addLine(playlist, index++, isGif) + '\n'
		}
		return csvContent
	}
	
}