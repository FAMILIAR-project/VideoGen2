package transformation;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import util.Utils;

public class Html {
	
	/**
	 * genere une simple page html avec des apercus des videos de genVideo
	 * @param videoGen
	 * @return
	 */
	public static String toHtml(VideoGeneratorModel videoGen) {
		String res = "<!-- #######  THIS IS A COMMENT #########-->";
		for (Media media : videoGen.getMedias()) {
			String name;
			String pecture;
			if (media instanceof MandatoryMedia) {
				if (((MandatoryMedia) media).getDescription() instanceof MediaDescription) {
					VideoDescription file = (VideoDescription) ((MandatoryMedia) media).getDescription();
					Utils.createVignette(file.getLocation(), "videos", "mp4");
					name = "<h3>MandatoryMedia : " + file.getVideoid() + "</h3>";
					pecture = '"' + "../" + Utils.rename(file.getLocation(), "videos", "images", "mp4", "png") + '"';
					res += '\n' + name + '\n' + "<div> <img src=" + pecture + "width=\"200\" height=\"150\"/></div>";
				}
			} else if (media instanceof OptionalMedia) {
				if (((OptionalMedia) media).getDescription() instanceof MediaDescription) {
					VideoDescription file = (VideoDescription) ((OptionalMedia) media).getDescription();
					Utils.createVignette(file.getLocation(), "videos", "mp4");
					name = "<h3>OptionalMedia : " + file.getVideoid() + "</h3>";
					pecture = '"' + "../" + Utils.rename(file.getLocation(), "videos", "images", "mp4", "png") + '"';
					res += '\n' + name + '\n' + "<div> <img src=" + pecture +"width=\"200\" height=\"150\"/></div>";

				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();
				if (altMedia.size() > 0) {
					String block = "<div>";
					for (MediaDescription m : altMedia) {
						Utils.createVignette(m.getLocation(), "videos", "mp4");
						pecture = "<img src=" + '"' + "../" + Utils.rename(m.getLocation(), "videos", "images", "mp4", "png") + '"'
								+ "width=\"200\" height=\"150\"/>";
						block += pecture;
					}
					block += "</div>";
					name = "<h3>AlternativesMedia : "+((AlternativesMedia) media).getId()+"</h3>";
					res += '\n' + name + '\n' + block;
				}
			}
		}
		return res;

	}
}
