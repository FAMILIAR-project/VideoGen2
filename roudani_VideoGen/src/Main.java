import java.util.Scanner;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import transformation.Etude;
import transformation.Ffmpeg;
import transformation.Html;
import transformation.LongestVariant;
import transformation.VideoToGif;
import util.Utils;

public class Main {

	public static void main(String[] args) {
		System.out.println(
				"Ne pas oublier de nettoyer les dossiers" + " avant de lancer de nouvelles generations de videos");

		Scanner sc = new Scanner(System.in);
		String command = sc.next();
		String arg;
		switch (command) {
		case "GENERATE_PLAYLIST"://ex: GENERATE_PLAYLIST example2
			arg = sc.next();
			System.out.println(arg + ".videogen");
			if (!Utils.isFileExist(arg + ".videogen")) {
				System.out.println("le fichier " + arg + ".videogen" + " n'existe pas");
			} else {
				VideoGeneratorModel videoGen = new VideoGenHelper()
						.loadVideoGenerator(URI.createURI(arg + ".videogen"));
				String variante = Ffmpeg.generatePlayList(videoGen);
				Utils.toFile(variante, "textFiles/" + arg + ".txt");
			}
			System.out.println("done!");
			break;
		case "CONCAT_PLAYLIST": //ex: CONCAT_PLAYLIST example1.txt
			arg = sc.next();
			Utils.concat_and_play("textFiles/" + arg, "generatedVideos/" + arg);
			System.out.println("done!");
			break;
		case "EXPORT_GIF": //ex: EXPORT_GIF generatedVideos/example1.mp4
			arg = sc.next();
			VideoToGif exGif = new VideoToGif();
			String output = "gifs/output.gif";
			exGif.videoToGif(arg, output, 150, 200);
			System.out.println("done!");
			break;
		case "GENERATE_GIFS": //ex: GENERATE_GIFS example2
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
		case "GENERATE_LONGEST_PLAYLIST"://ex: GENERATE_LONGEST_PLAYLIST example2
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
		case "GENERATE_HTML": //ex: GENERATE_HTML example2
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
		case "CLEAN": //ex: CLEAN
			Utils.cleanTestFiles();
			System.out.println("done!");
			break;
		case "VALIDATOR": //ex: VALIDATOR example2
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
			break;
		}

	}

}
