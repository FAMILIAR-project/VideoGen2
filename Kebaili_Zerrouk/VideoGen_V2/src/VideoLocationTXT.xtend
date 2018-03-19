import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import java.util.Random
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.Media

class VideoLocationTXT {
	
	static def String fromModelToTXTFile (VideoGeneratorModel videoGen) {		
		
		// txt file that will contain the location of the videos, in the correct format for FFMPEG	
		var filelist = ""
		
		// Visit videogen file
		if (videoGen instanceof VideoGeneratorModel){
			for (Media media : videoGen.getMedias()) {
				// if Mandatory video
				if (media instanceof MandatoryMedia) {
					if (media.getDescription() instanceof MediaDescription){
						filelist = filelist+"file '"+media.getDescription().location+"'\n"			
					}				
				}
				// if optionnal video, add 1/2 time
				else if (media instanceof OptionalMedia) {
					if (media.getDescription() instanceof MediaDescription){
						// random 50% to add the video
						if (new Random().nextInt(100) > 50 ){
							filelist = filelist+"file '"+media.getDescription().location+"'\n"
						}
					}		
				}
				//choose randomly between the alternative videos
				else if (media instanceof AlternativesMedia) {
					var alternativesize = media.getMedias().size
					val random_alt = media.getMedias().get(new Random().nextInt(alternativesize))
					if (random_alt instanceof MediaDescription){
					    filelist = filelist+"file '"+random_alt.location+"'\n"
					}
				}
			}
		}
				
	return filelist;
	}
}