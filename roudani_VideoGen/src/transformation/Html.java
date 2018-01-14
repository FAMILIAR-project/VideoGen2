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

public class Html {
	public static String toHtml(VideoGeneratorModel videoGen) {
		String res = "<!-- #######  THIS IS A COMMENT #########-->";
		for (Media media : videoGen.getMedias()) {
			String name;
			String pecture;
			if (media instanceof MandatoryMedia) {
				if (((MandatoryMedia) media).getDescription() instanceof MediaDescription) {
					File file = new File(((MandatoryMedia) media).getDescription().getLocation());
					Utils.createApercu(file.getPath());
					name = "<h3>MandatoryMedia : " + file.getPath() + "</h3>";
					pecture = '"' + "images/" + file.getPath().replace('/', '_') + ".png" + '"';
					res += '\n' + name + '\n' + "<div> <img src=" + pecture + "/></div>";
				}
			} else if (media instanceof OptionalMedia) {
				if (((OptionalMedia) media).getDescription() instanceof MediaDescription) {
					File file = new File(((OptionalMedia) media).getDescription().getLocation());
					Utils.createApercu(file.getPath());

					name = "<h3>OptionalMedia : " + file.getPath() + "</h3>";
					pecture = '"' + "images/" + file.getPath().replace('/', '_') + ".png" + '"';
					res += '\n' + name + '\n' + "<div> <img src=" + pecture + "/></div>";

				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();
				if (altMedia.size() > 0) {
					String block = "<div>";
					for (MediaDescription m : altMedia) {
						Utils.createApercu(m.getLocation());
						pecture = "<img src=" + '"' + "images/" + m.getLocation().replace('/', '_') + ".png" + '"'
								+ "/>";
						block += pecture;
					}
					block += "</div>";
					name = "<h3>AlternativesMedia : </h3>";
					res += '\n' + name + '\n' + block;
				}
			}
		}
		return res;

	}
}
