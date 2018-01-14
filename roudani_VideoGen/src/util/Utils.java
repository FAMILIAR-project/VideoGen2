package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class Utils {

	// retourn la duree de la video qui se trouve dans path
	public static double getLength(String path) {
		Process cmd;
		double duration = 0;
		try {
			cmd = Runtime.getRuntime().exec(
					"ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 " + path);
			String tmp;
			try {
				tmp = new BufferedReader(new InputStreamReader(cmd.getInputStream())).readLine();
				duration = Double.parseDouble(tmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return duration;

	}

	// retourn la taille du fichier qui se trouve à l'adresse path
	public static double getSize(String path) {
		File file = new File(path);
		return file.length();
	}

	// save input string into output file
	public static void toFile(String input, String output) {
		try (PrintWriter out = new PrintWriter(output)) {
			out.println(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// concat video from the ffmpeg file 'paleListName' and play the output with vlc
	public static void concat_and_play(String playListName, String output) {
		try {
			Runtime.getRuntime().exec("ffmpeg -f concat -safe 0 -i " + playListName + " -c copy " + "output" + ".mp4")
					.waitFor();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Runtime.getRuntime().exec("vlc " + "output" + ".mp4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// creer un apercu de la video se trouvant à 'path' dans le dossier images
	public static void createApercu(String path) {
		try {
			Runtime.getRuntime().exec("ffmpeg -y -i " + path + " -r 1 -t 00:00:01 -ss 00:00:02 -f image2 images/"
					+ path.replace('/', '_') + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// convertir une video (variante) en format gif
	public static void videoToGif(String path, String output, int height, int width) {
		String cmd = "ffmpeg -i " + path + " -vf " + "scale=" + height + ':' + width + ' ' + output
				+ ".gif -hide_banner";
		try {
			Process pros = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// permet de verifier quelques incoherences dans la specification :
	//Les probabilités doivent être cohérentes : 
		//une probabilité d’une séquence vidéo “optionnelle” ne peut dépasser 1 ou 100%
		//la somme des probabilités ne doit pas dépasser 100% pour les séquences video “alternatif”
	//Les identifieurs doivent être uniques
	//Les fichiers spécifiés (images ou vidéos) n’existent pas 

	public static boolean validator(VideoGeneratorModel videoGen) {
		ArrayList<String> identifs = new ArrayList<>();
		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				VideoDescription mDesc = (VideoDescription)((MandatoryMedia) media).getDescription();
				if (!isFileExist(mDesc.getLocation())) {
					//
					System.err.println("Le fichier "+mDesc.getLocation()+" n'existe pas");
					return false;
				}
				if (isIdExist(mDesc.getVideoid(), identifs)) {
					//
					System.err.println("l'identifieur "+mDesc.getVideoid()+" n'est pas unique");
					return false;
				}
				identifs.add(mDesc.getVideoid());

			} else if (media instanceof OptionalMedia) {
				VideoDescription oDesc = (VideoDescription)((OptionalMedia) media).getDescription();

				if (!isFileExist(oDesc.getLocation())) {
					//
					System.err.println("Le fichier "+oDesc.getLocation()+" n'existe pas");
					return false;

				}

				if ( (isIdExist(oDesc.getVideoid(), identifs))) {
					//
					System.err.println("l'identifieur "+oDesc.getVideoid()+" n'est pas unique");
					return false;

				}
				identifs.add(oDesc.getVideoid());
				
				if (oDesc.getProbability() > 100 || oDesc.getProbability() < 1) {
					//
					System.err.println("La probabilité doit appartenir à l'interval [1,100]!");
					return false;
				}

			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();
				int probability = 1;
				
				if ((isIdExist(((AlternativesMedia) media).getId(), identifs))) {
					//
					System.err.println("l'identifieur "+((AlternativesMedia) media).getId()+" n'est pas unique");
					return false;

				}
				identifs.add(((AlternativesMedia) media).getId());

				for (MediaDescription m : altMedia) {
					probability += ((VideoDescription) m).getProbability();
					if (!isFileExist(m.getLocation())) {
						System.err.println("Le fichier "+m.getLocation()+" n'existe pas");
						return false;

					}
					if (isIdExist(((VideoDescription) m).getVideoid(),identifs)) {
						System.err.println("l'identifieur "+((VideoDescription) m).getVideoid()+" n'est pas unique");
						return false;
					}
					identifs.add(((VideoDescription) m).getVideoid());
				}
				
				if (probability > 101 || probability < 1) {
					//
					System.err.println("La somme des probabilités doit appartenir à l'interval [1,100]!");
					return false;
				}

			}
		}
		return true;
	}
	
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
	
	public static boolean isIdExist(String id, ArrayList<String> l) {
		for(String s:l) {
			if (s.equals(id)) return true;
		}
		return false;
	}
	
}
