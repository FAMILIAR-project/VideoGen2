package fr.istic.m2il.idm.videogentransformations.helpers

import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.List
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenAnalysisTransformations
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter
import org.xtext.example.mydsl.videoGen.NegateFilter

class CSVHelper {
	
	
	static private def String addLine(List<MediaDescription> playlist, int index, boolean isDuration, boolean isGif){
		var line = index + ';'
		var size = 0.0;
		var playlistTemp = playlist
		for(mediaDescription : playlistTemp){
			if(mediaDescription !== null){
				if(mediaDescription instanceof VideoDescription){
					if(isDuration){
						if((mediaDescription as VideoDescription).text !== null){
							size += (mediaDescription as VideoDescription).duration
						}
						else{
							if(mediaDescription.filter !== null){
								
								FFMPEGHelper.applyFilter(VideoGenUtils.getFilter(mediaDescription), mediaDescription.location)
								size += VideoGenUtils.getVideoDuration(FFMPEGHelper.applyFilter(VideoGenUtils.getFilter(mediaDescription), mediaDescription.location))
							}
							else{
								size += VideoGenUtils.getVideoDuration(mediaDescription.location)
							
							}
						}
					}
					else{
						if((mediaDescription as VideoDescription).text !== null){
							size += (mediaDescription as VideoDescription).text.size
						}
						else{
							
							if(mediaDescription.filter !== null){
							
								size += VideoGenUtils.getVideoSize(FFMPEGHelper.applyFilter(VideoGenUtils.getFilter(mediaDescription), mediaDescription.location))
							}
							else{
								size += VideoGenUtils.getVideoSize(mediaDescription)
						
							}
						}
					}
				}
				else{
					if(isDuration){
						size += VideoGenUtils.getVideoDuration(mediaDescription.location) as double
					}
					else{
						size += VideoGenUtils.getVideoSize(mediaDescription)
					}
				}
				line += 'TRUE' + ';'
			}else{
				line += 'FALSE' + ';'
			}
		}
		if(isDuration){
			return line + size + ";" + VideoGenAnalysisTransformations.getRealDuration(playlistTemp)
		}
		else{
			if(!isGif)
				return line + size + ";" + VideoGenAnalysisTransformations.getRealSize(playlistTemp)
			else
				return line + size + ";" + VideoGenAnalysisTransformations.getRealSize(VideoGenUtils.getGif(playlistTemp, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/playlists/playlist.mp4"), 190, 60))
		}
		
	}
	
	static private def String addHeader(List<List<MediaDescription>> playlists, boolean isDuration){
		val headers = new ArrayList<String>()
		for(mediaDescription : playlists.get(0)){
			headers.add(null)
		}
		for(playlist : playlists){
			var index = 0
			for(mediaDescription : playlist){
				if(headers.get(index) === null && mediaDescription !== null){
					if(mediaDescription instanceof VideoDescription)
						headers.set(index, (mediaDescription as VideoDescription).videoid)
					if(mediaDescription instanceof ImageDescription)
						headers.set(index, (mediaDescription as ImageDescription).imageid)
				}
				index++
			}
		}
		var header = 'id;'
		for(str : headers){
			header += str + ';'
		}
		if(isDuration){
			header += 'duration;'
		
			header += 'realDuration'
		}
		else{
			header += 'size;'
		
			header += 'realSize'
		}
		return header
	}
	
	static def List<String> create(VideoGeneratorModel videoGen, boolean isDuration, boolean isGif){
		return create(VideoGenUtils.generatePlaylists(videoGen), isDuration, isGif)
	}
	
	static def List<String> create(List<List<MediaDescription>> playlists, boolean isDuration, boolean isGif){
		var csvContent = newArrayList
		csvContent.add(addHeader(playlists, isDuration) + '\n')
		var index = 0
		for(playlist : playlists){
			csvContent += addLine(playlist, index++, isDuration, isGif) + '\n'
		}
		CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/analysis.csv"), csvContent)
		return csvContent
	}
	
}