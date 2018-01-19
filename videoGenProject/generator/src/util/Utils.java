package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

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
		System.out.println(playListName);
			concat(playListName,output);
			try {
				Runtime.getRuntime().exec("vlc " + output + ".mp4");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	
	public static void concat(String playListName, String output) {
		System.out.println(playListName);
		try {
			
			String cmd = "ffmpeg -f concat -safe 0 -i " + playListName+".txt" + " -c copy " + output + ".mp4";
			Runtime.getRuntime().exec(cmd)
					.waitFor();
			System.out.println(cmd);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * creer une vignette png pour le fichier a l'adresse path
	 * @param path
	 * @param folder
	 * @param inputExt
	 */
	public static void createVignette(String path, String folder,String inputExt) {
		String output = rename(path, folder, "images", inputExt, "png");
		try {
			String cmd = "ffmpeg -y -i " + path + " -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "
					+ output;
			String[] b = new String[] {"/bin/sh", "-c", cmd};
			Runtime.getRuntime().exec(b);
			System.out.println(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// permet de verifier quelques incoherences dans la specification :
	// Les probabilités doivent être cohérentes :
	// une probabilité d’une séquence vidéo “optionnelle” ne peut dépasser 1 ou 100%
	// la somme des probabilités ne doit pas dépasser 100% pour les séquences video
	// “alternatif”
	// Les identifieurs doivent être uniques
	// Les fichiers spécifiés (images ou vidéos) n’existent pas

	public static boolean validator(VideoGeneratorModel videoGen) {
		ArrayList<String> identifs = new ArrayList<>();
		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				VideoDescription mDesc = (VideoDescription) ((MandatoryMedia) media).getDescription();
				if (!isFileExist(mDesc.getLocation())) {
					//
					System.err.println("Le fichier " + mDesc.getLocation() + " n'existe pas");
					return false;
				}
				if (isIdExist(mDesc.getVideoid(), identifs)) {
					//
					System.err.println("l'identifieur " + mDesc.getVideoid() + " n'est pas unique");
					return false;
				}
				identifs.add(mDesc.getVideoid());

			} else if (media instanceof OptionalMedia) {
				VideoDescription oDesc = (VideoDescription) ((OptionalMedia) media).getDescription();

				if (!isFileExist(oDesc.getLocation())) {
					//
					System.err.println("Le fichier " + oDesc.getLocation() + " n'existe pas");
					return false;

				}

				if ((isIdExist(oDesc.getVideoid(), identifs))) {
					//
					System.err.println("l'identifieur " + oDesc.getVideoid() + " n'est pas unique");
					return false;

				}
				identifs.add(oDesc.getVideoid());

				if (oDesc.getProbability() > 100 || oDesc.getProbability() < 0) {
					//
					System.err.println("La probabilité doit appartenir à l'interval [1,100]!");
					return false;
				}

			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> altMedia = ((AlternativesMedia) media).getMedias();
				int probability = 1;

				if ((isIdExist(((AlternativesMedia) media).getId(), identifs))) {
					//
					System.err.println("l'identifieur " + ((AlternativesMedia) media).getId() + " n'est pas unique");
					return false;

				}
				identifs.add(((AlternativesMedia) media).getId());

				for (MediaDescription m : altMedia) {
					probability += ((VideoDescription) m).getProbability();
					if (!isFileExist(m.getLocation())) {
						System.err.println("Le fichier " + m.getLocation() + " n'existe pas");
						return false;

					}
					if (isIdExist(((VideoDescription) m).getVideoid(), identifs)) {
						System.err
								.println("l'identifieur " + ((VideoDescription) m).getVideoid() + " n'est pas unique");
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
		return file.exists() && (file.length() > 0);
	}

	public static boolean isIdExist(String id, ArrayList<String> l) {
		for (String s : l) {
			if (s.equals(id))
				return true;
		}
		return false;
	}

	/**
	 * renomer un fichier au format extOutput
	 * 
	 * @param input
	 * @param extInput
	 * @param extOutput
	 * @return
	 */
	public static String rename(String input,String folderIn,String folderOut ,String extInput, String extOutput) {
		return input.replace(extInput, extOutput).replace(folderIn, folderOut);
	}

	/**
	 * cette fonction procede au choix d'un media parmis une liste donnée (gestion
	 * des probabilités)
	 * 
	 * @param l
	 * @return
	 */
	public static MediaDescription choose(EList<MediaDescription> l) {
		for (MediaDescription m : l) {
			if(m instanceof VideoDescription) {
			double prob = 0.01 * ((VideoDescription) m).getProbability();
			// 0.5 est la valeur par defaut
			if (prob == 0.)
				prob = 0.5;
			if (Math.random() < prob)
				return m;
			}
		}
		// si aucun choix à la fin prendre un au hazard
		Random rn = new Random();
		return l.get(rn.nextInt(l.size()));
	}
	
	/**
	 * permet de netoyer tous les fichier generes
	 */
	public static void cleanTestFiles() {
		try {
			String[] b = new String[] {"/bin/sh", "-c", "rm generatedVideos/*; rm gifs/*; rm images/*; rm textFiles/*"};
			Runtime.getRuntime().exec(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * permet de netoyer tous les fichier generes en specifiant le dossier source
	 */
	public static void cleanTestFiles(String path) {
		try {
			String[] b = new String[] {"/bin/sh", "-c", "rm "+ path +"generatedVideos/*; rm "+path +"gifs/*; rm "+path+"images/*; rm "+path +"textFiles/*"};
			Runtime.getRuntime().exec(b);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
