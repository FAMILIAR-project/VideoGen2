package videoGen;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;

public class VideoGenCheck {

	public static boolean isCorrectVideoGen(EList<Media> medias) {
		String currentMediaLocation = "";
		MediaDescription currentMediaDesc;
		String videoId = "";
		HashSet<String> videoIds = new HashSet<>();

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				currentMediaDesc = ((MandatoryMedia) media).getDescription();
				currentMediaLocation = currentMediaDesc.getLocation();
				videoId = ((VideoDescription) currentMediaDesc).getVideoid();
				
				if (!videoIds.add(videoId)) {
					return false;
				}
				
			}
			if (media instanceof OptionalMedia) {
				currentMediaDesc = ((OptionalMedia) media).getDescription();
				currentMediaLocation = currentMediaDesc.getLocation();
				videoId = ((VideoDescription) currentMediaDesc).getVideoid();
				
				if (!videoIds.add(videoId)) {
					return false;
				}

				if(!isGoodProbability((VideoDescription)currentMediaDesc)) {
					return false;
				}
			}
			if (media instanceof AlternativesMedia) {
				for (MediaDescription alternativeDesc : ((AlternativesMedia) media).getMedias()) {
					currentMediaLocation = alternativeDesc.getLocation();
					videoId = ((VideoDescription) alternativeDesc).getVideoid();
					
					if (!videoIds.add(videoId)) {
						return false;
					}
				}
				
				if(isGoodAlternativeProbability((AlternativesMedia) media) == false) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isGoodProbability(VideoDescription videoDescription) {
		int probability = videoDescription.getProbability();
		return (0 < probability && probability <= 100);
	}

	public static boolean isGoodAlternativeProbability(AlternativesMedia alternativesMedia) {
		MediaDescription currentMediaDesc;
		boolean res;
		int tmpCounter = 0;
		for (MediaDescription alternativeDesc : (alternativesMedia.getMedias())) {
			tmpCounter += ((VideoDescription) alternativeDesc).getProbability();
		}
		res = (0 < tmpCounter && tmpCounter <= 100);
		return res;
	}
}
