package transformations

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.util.List
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import helper.FFMPEGHelper

class VideoGenPlayerTransformation {
	
	
	def void play(VideoGeneratorModel videoGen, String outPutFile){
		playWithFFMPEG(makePlayList(videoGen), outPutFile);
	}
	
	def List<String> makePlayList(VideoGeneratorModel videoGen){
		
		var playlist = new ArrayList<String>
		
		for(Media media: videoGen.medias){
			if(media instanceof MandatoryMedia){
				val vdescription = (media.description as VideoDescription)
				if(media.description instanceof VideoDescription){

					playlist.add(vdescription.location)
				}
			}
			if(media instanceof OptionalMedia){
				val vdescription = (media.description as VideoDescription)
				if(media.description instanceof VideoDescription){
					if(Math.random() * 2 < 1){
						playlist.add(vdescription.location)
					}
				}
			}
			if(media instanceof AlternativesMedia){
				
				var alternativeIndex = (Math.random() * media.medias.size) as int				
				val vdescription = (media.medias.get(alternativeIndex)) as VideoDescription
				playlist.add(vdescription.location)
			}
		}
		
	}
	
	def void playWithFFMPEG(List<String> playListFile, String outPutFile){
		var helper = new FFMPEGHelper()
		//helper.run(playListFile, outPutFile);
	}
}