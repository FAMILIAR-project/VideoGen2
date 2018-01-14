package transformation;

import java.io.File;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class Ffmpeg {

	public static String toFfmpeg(VideoGeneratorModel videoGen) {
		String res = "# this is a comment";
		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				if (((MandatoryMedia) media).getDescription() instanceof MediaDescription) {
					File file = new File(((MandatoryMedia) media).getDescription().getLocation());
					res += '\n' + "file " + "'" + file.getPath() + "'";
				}
			} else if (media instanceof OptionalMedia) {
				OptionalMedia opMedia = (OptionalMedia) media;
				MediaDescription opMediaDesc = opMedia.getDescription();
				if (opMediaDesc instanceof VideoDescription) {
					File file = new File(((OptionalMedia) media).getDescription().getLocation());
					MediaDescription f = ((OptionalMedia) media).getDescription();
					VideoDescription opVideoDesc = (VideoDescription) opMediaDesc;
					double probability = 0.5;
					if (opVideoDesc.getProbability() > 0 ) probability = 0.01*opVideoDesc.getProbability();
					if (Math.random() < probability) {
						res += '\n' + "file " + "'" + file.getPath() + "'";
					}

				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();
				int numberElts = altMedia.size();
				if (numberElts > 0) {
					Random rn = new Random();
					MediaDescription choice = altMedia.get(rn.nextInt(numberElts));
					File file = new File(choice.getLocation());
					res += '\n' + "file " + "'" + file.getPath() + "'";
				}
			}
		}
		System.out.println(res);
		return res;
	}
	
	
}
