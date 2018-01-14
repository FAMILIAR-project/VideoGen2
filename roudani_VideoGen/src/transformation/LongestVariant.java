package transformation;

import java.io.File;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import util.Utils;

public class LongestVariant {

	// transformation de videoGen vers la variante de la plus longue durée
	public static String toLongestVar(VideoGeneratorModel videoGen) {
		String res = "#this is a comment";
		double totalDuree = 0.;
		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				if (((MandatoryMedia) media).getDescription() instanceof MediaDescription) {
					File file = new File(((MandatoryMedia) media).getDescription().getLocation());
					res += '\n' + "file " + "'" + file.getPath() + "'";
					totalDuree += Utils.getLength(file.getPath());
				}
			} else if (media instanceof OptionalMedia) {
				if (((OptionalMedia) media).getDescription() instanceof MediaDescription) {
					File file = new File(((OptionalMedia) media).getDescription().getLocation());
					res += '\n' + "file " + "'" + file.getPath() + "'";
					totalDuree += Utils.getLength(file.getPath());
				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();

				if (altMedia.size() > 0) {
					String longest = "";
					double length = 0.;
					for (MediaDescription m : altMedia) {
						double currLength = Utils.getLength(m.getLocation());
						if (currLength > length) {
							length = currLength;
							longest = m.getLocation();
						}
						;
					}
					res += '\n' + "file " + "'" + longest + "'";
					totalDuree += length;
					res += "\n# duree total : " + totalDuree;
				}
			}
		}
		return res;
	}
}
