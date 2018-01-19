package main;

import java.util.Scanner;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import other.VideoGenHelper;
import transformation.Etude;
import transformation.Html;
import transformation.LongestVariant;
import transformation.PlayList;
import transformation.VideoToGif;
import util.Utils;

public class Main {

	public static void random(String pathWebGen) {
		VideoGeneratorModel videoGenWeb = new VideoGenHelper()
				.loadVideoGenerator(URI.createURI(pathWebGen + "variante.videogen"));
		String out = PlayList.generate(videoGenWeb, pathWebGen);
		Utils.toFile(out, pathWebGen + "textFiles/variante.txt");
		Utils.concat(pathWebGen + "textFiles/variante", pathWebGen + "generatedVideos/variante");
		System.out.println("done!");
	}

	public static void export(String pathWebGen) {
		VideoToGif exGifWeb = new VideoToGif();
		exGifWeb.videoToGif(pathWebGen + "generatedVideos/variante.mp4", pathWebGen + "gifs/variante.gif", 150, 200);
		System.out.println("done!");
	}

	public static void clean(String pathWebGen) {
		Utils.cleanTestFiles(pathWebGen.toString());
	}

	public static void main(String[] args) {
		System.out.println("Ne pas oublier de nettoyer les dossiers (Commande CLEAN)"
				+ " avant de lancer de nouvelles generations de videos");

		System.out.println("\nSupported commands {\n");

		System.out.println("GENERATE_PLAYLIST videogen_file_without_prfx");
		System.out.println("CONCAT_PLAYLIST generated_txt_playlist");
		System.out.println("EX:	EXPORT_GIF videos/1.mp4");
		System.out.println("GENERATE_LONGEST_PLAYLIST videogen_file_without_prfx");
		System.out.println("GENERATE_HTML videogen_file_without_prfx");
		System.out.println("GENERATE_CSV_FILE videogen_file_without_prfx");
		System.out.println("VALIDATOR videogen_file_without_prfx");
		System.out.println("VALIDATOR videogen_file_without_prfx");
		System.out.println("CLEAN");
		System.out.println("EXIT for exit");
		System.out.println("\n} \n"+"Enter your command :\n");

		boolean finish = false;
		while (!finish) {
			Scanner sc = new Scanner(System.in);
			String command = sc.next();
			String arg;
			switch (command) {

			case "GENERATE_PLAYLIST":// ex: GENERATE_PLAYLIST example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					String variante = PlayList.generate(videoGen, "");
					Utils.toFile(variante, "textFiles/" + arg + ".txt");
				}
				System.out.println("done!");
				break;
			case "CONCAT_PLAYLIST": // ex: CONCAT_PLAYLIST example1
				arg = sc.next();
				Utils.concat("textFiles/" + arg, "generatedVideos/" + arg);
				System.out.println("done!");
				break;
			case "EXPORT_GIF": // ex: EXPORT_GIF videos.mp4
				arg = sc.next();
				VideoToGif exGif = new VideoToGif();
				String output = "gifs/output.gif";
				exGif.videoToGif(arg, output, 150, 200);
				System.out.println("done!");
				break;
			case "GENERATE_GIFS": // ex: GENERATE_GIFS example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					VideoToGif genGif = new VideoToGif();
					genGif.modelToGifs(videoGen, 150, 200);
				}
				System.out.println("done!");
				break;
			case "GENERATE_LONGEST_PLAYLIST":// ex: GENERATE_LONGEST_PLAYLIST example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					String logestVariant = LongestVariant.toLongestPlayList(videoGen);
					Utils.toFile(logestVariant, "textFiles/logestVariant.txt");
				}
				System.out.println("done!");
				break;
			case "GENERATE_HTML": // ex: GENERATE_HTML example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					Utils.toFile(Html.toHtml(videoGen), "textFiles/vignette.html");
				}
				System.out.println("done!");
				break;
			case "GENERATE_CSV_FILE": // ex: GENERATE_CSV_FILE example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					// generer toutes les variantes poussibles et integrer les resultats dans le csv
					Etude.getAllVariants(videoGen);

				}
				System.out.println("done!");
				break;
			case "CLEAN": // ex: CLEAN
				Utils.cleanTestFiles();
				System.out.println("done!");
				break;
			case "VALIDATOR": // ex: VALIDATOR example2
				arg = sc.next();
				System.out.println(arg + ".videogen");
				if (!Utils.isFileExist(arg + ".videogen")) {
					System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
				} else {
					VideoGeneratorModel videoGen = new VideoGenHelper()
							.loadVideoGenerator(URI.createURI(arg + ".videogen"));
					Utils.validator(videoGen);
				}
				System.out.println("done!");
				break;

			default:
				System.out.println("Commande incorrecte!");
				finish = true;
				break;
			}
		}

	}

}
