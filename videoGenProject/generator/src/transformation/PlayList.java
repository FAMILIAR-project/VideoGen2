package transformation;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.Filter;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;

import util.Filters;
import util.Utils;

public class PlayList {

	
	/**
	 * fonction principale pour la generation de playList
	 * @param videoGen
	 * @return
	 */
	public static String generate(VideoGeneratorModel videoGen, CharSequence pathRegulator) {
		//si la specif de videoGen n'est pas valide, retourner null
		if(!Utils.validator(videoGen)) {
			//return null;
		};
		//sinon
		String res = "# this is a comment";
		String suffix = '\n' + "file " + "'../" ;

		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				MandatoryMedia manMedia = (MandatoryMedia) media;
				MediaDescription manMediaDesc = manMedia.getDescription();
				if (manMediaDesc instanceof VideoDescription) {
					Filter filter = ((VideoDescription) manMediaDesc).getFilter();
					VideoText videoText = ((VideoDescription) manMediaDesc).getText();
					String path = Filters.addText(manMediaDesc.getLocation(), videoText,false);
					System.out.println("voila le path = " + path + "  location = " + manMediaDesc.getLocation());
					boolean locationChanged = (!path.equals(manMediaDesc.getLocation()));
					path = Filters.filter(filter, path, locationChanged);
					res += suffix + path.replace(pathRegulator, "") + "'";
				}
			} else if (media instanceof OptionalMedia) {
				OptionalMedia opMedia = (OptionalMedia) media;
				MediaDescription opMediaDesc = opMedia.getDescription();
				if (opMediaDesc instanceof VideoDescription) {
					VideoDescription opVideoDesc = (VideoDescription) opMediaDesc;
					double probability = 0.5;
					if (opVideoDesc.getProbability() > 0)
						probability = 0.01 * opVideoDesc.getProbability();
					if (Math.random() < probability) {
						Filter filter = opVideoDesc.getFilter();
						VideoText videoText = opVideoDesc.getText();
						String path = Filters.addText(opVideoDesc.getLocation(), videoText, false);
						System.out.println("voila le path = " + path + "  location = " + opVideoDesc.getLocation());
						boolean locationChanged = (!path.equals(opVideoDesc.getLocation()));
						path = Filters.filter(filter, path, locationChanged);
						res += suffix + path.replace(pathRegulator, "") + "'";
					}

				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();

				MediaDescription choice = Utils.choose(altMedia);
				Filter filter = ((VideoDescription) choice).getFilter();
				VideoText videoText = ((VideoDescription) choice).getText();
				String path = Filters.addText(choice.getLocation(), videoText, false);
				System.out.println("voila le path = " + path + "  location = " + choice.getLocation());
				boolean locationChanged = (!path.equals(choice.getLocation()));
				path = Filters.filter(filter, path, locationChanged);
				res += suffix + path.replace(pathRegulator, "") + "'";

			}
		}
		System.out.println(res);
		return res;
	}

}
