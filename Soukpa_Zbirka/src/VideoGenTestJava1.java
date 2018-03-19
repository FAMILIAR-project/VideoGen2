import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class VideoGenTestJava1 {

	private static final String VIGNETTES_DIRECTORY = "vignettes";
	private static final String INPUT_VIDEOGEN = "example2.videogen";
	private static final String OUTPUT_VIDEOGEN_CSV = INPUT_VIDEOGEN + ".csv";
	private static final String FFMPEG_DURATION_COMMAND = "ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 ";
	private static final String FFMPEG_CONCAT_PREFIX_FILE = "file ";
	private static final String VLC_BASE_COMMAND = "vlc --play-and-exit ";
	private static final String OUTPUT = "ff.txt";
	private static final String COMPILATION_NAME = "compilation.mp4";
	
	// Detect if video has sound ==> prefix + videoFileName + suffix
	private static final String FFMPEG_PREFIX_NOSOUND_CMD = "ffmpeg -i ";
	private static final String FFMPEG_SUFFIX_NOSOUND_CMD = " -hide_banner 2>&1 | grep Audio | awk '{print $0}' | tr -d ,";

	private static final String FFMPEG_SUFFIX_APPEND_NULL_SOUND_CMD = " -f lavfi -i anullsrc -c:v copy -c:a aac -strict -2 -shortest ";
	
	private static final String GIF_OUTPUT_FOLDER = "gif/";
	private static final String GIF_INPUT = "output_video.mp4";
	
	
	
	
//	private Process runCommandInterpretedMode(String command) {
//		Process p = null;
//		try {
//			p = Runtime.getRuntime().exec(command);
//			p.waitFor();
//
//		} catch (IOException e) {
//			System.err.println("Echec au lancement de la commande : " + command);
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return p;
//	}
	
	private Process runCommandInterpretedMode(String command) {
		return runCommandInterpretedMode(command, false);
	}
	
	/**
	 * 
	 * @param command
	 * @param printResult TRUE for printing result, FALSE = no output
	 * @return
	 */
	private Process runCommandInterpretedMode(String command, boolean printResult) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			
			if (printResult) {
				BufferedReader stdInput = new BufferedReader(new 
					     InputStreamReader(p.getInputStream()));

					BufferedReader stdError = new BufferedReader(new 
					     InputStreamReader(p.getErrorStream()));

					// read the the command
					System.out.println("Here is the executed command:\n" + command +"\n\n");
					
					// read the output from the command
					System.out.println("Here is the standard output of the command:\n");
					String s = null;
					while ((s = stdInput.readLine()) != null) {
					    System.out.println(s);
					}

					// read any errors from the attempted command
					System.out.println("Here is the standard error of the command (if any):\n");
					while ((s = stdError.readLine()) != null) {
					    System.err.println(s);
					}
			}
			
		} catch (IOException e) {
			System.err.println("Echec au lancement de la commande : " + command);
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return p;
	}

	private int nbVariante(VideoGeneratorModel videogen) {
		EList<Media> medias = videogen.getMedias();

		int variantes = 1;

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				// NOTHING TO DO HERE
			} else if (media instanceof OptionalMedia) {
				variantes *= 2;
			} else if (media instanceof AlternativesMedia) {
				variantes *= ((AlternativesMedia) media).getMedias().size();
			}
		}

		return variantes;
	}
	
	
	//@Test
	public void testNbVariante() {
		int max = 7;
		for (int i = 1; i <= max; i++) {
			VideoGeneratorModel videoGen = new VideoGenHelper()
					.loadVideoGenerator(URI.createURI("example" + i + ".videogen"));
			assertNotNull(videoGen);
			System.out.println("example" + i + "::nbVariantes = " + nbVariante(videoGen));
		}
	}

	
	//@Test
	public void testNbVarianteCSV() {
		// CSV
		String csvName = "CSV-videogen-sample - Sheet1.csv"; // CSV for example2
		Path csvPath = new File(OUTPUT_VIDEOGEN_CSV).toPath();

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);

		try (Stream<String> lines = Files.lines(csvPath, Charset.defaultCharset())) {
			long numOfLines = lines.count();
			assertEquals("It should be equal", numOfLines, nbVariante(videoGen) + 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
	}

	
	//@Test
	public void testInterpretedModeWithVLC() throws InterruptedException, IOException {

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);
		nbVariante(videoGen);

		EList<Media> medias = videoGen.getMedias();

		String videoLocation;

		// Mode interprété
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();

					// Tape ta commande
					String command = VLC_BASE_COMMAND + videoLocation;
					runCommandInterpretedMode(command);
				}

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();

				Random rand = new Random();
				int decision = rand.nextInt(2); // 0 ou 1

				// decision = 1 on prend sinon O donc on laisse
				if (decision == 1) {
					// On prend cette video
					if (description instanceof VideoDescription) {
						videoLocation = description.getLocation();

						// Tape ta commande
						String command = VLC_BASE_COMMAND + videoLocation;
						runCommandInterpretedMode(command);
					}
				}
			} else if (media instanceof AlternativesMedia) {
				// On récupère toutes les possibilités des videos alternatives
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();

				// On sélectionne la vidéo qui sera jouée
				Random rand = new Random();
				int selectedVideo = rand.nextInt(alternatives.size()); // index de la video = [0, medias.size[
				MediaDescription selectedMedia = alternatives.get(selectedVideo);

				// On récupère les informations de la video et on applique les traitements
				if (selectedMedia instanceof VideoDescription) {
					videoLocation = selectedMedia.getLocation();

					// Tape ta commande
					String command = VLC_BASE_COMMAND + videoLocation;
					runCommandInterpretedMode(command);
				}
			}
		}
	}

	
	//@Test
	public void testCompiledModeWithFFMPEG() throws InterruptedException, IOException {

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);
//		nbVariante(videoGen);

		EList<Media> medias = videoGen.getMedias();
		String videoLocation = "";

		// To use with ffmpeg filter-complex
		int videoIndex = 0;
		String cmdPlaylistString = "";
		String cmdFilterComplexMapping = " -filter_complex ";

		// Mode interprété
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoLocation = checkHasNoSound(description.getLocation());
					cmdPlaylistString += " -i " + videoLocation;
					cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
					videoIndex++;
				}

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();

				Random rand = new Random();
				int decision = rand.nextInt(2); // 0 ou 1

				// decision = 1 on prend sinon O donc on laisse
				if (decision == 1) {
					// On prend cette video
					if (description instanceof VideoDescription) {
						videoLocation = checkHasNoSound(description.getLocation());
						cmdPlaylistString += " -i " + videoLocation;
						cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
						videoIndex++;
					}
				}
			} else if (media instanceof AlternativesMedia) {
				// On récupère toutes les possibilités des videos alternatives
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();

				// On sélectionne la vidéo qui sera jouée
				Random rand = new Random();
				int selectedVideo = rand.nextInt(alternatives.size()); // index de la video = [0, medias.size[
				MediaDescription selectedMedia = alternatives.get(selectedVideo);

				// On récupère les informations de la video et on applique les traitements
				if (selectedMedia instanceof VideoDescription) {
					videoLocation = checkHasNoSound(selectedMedia.getLocation());
					cmdPlaylistString += " -i " + videoLocation;
					cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
					videoIndex++;
				}
			}
		}
		
		// Création de la commande à exécuter
		// On considère que les vidéos ont toutes la même extension.
		String extension = videoLocation.substring(videoLocation.lastIndexOf("."));
		String outputName = "output_video" + extension;
		String cmdFilterComplexOptions = "concat=n="+videoIndex+":v=1:a=1[outv][outa]";
		String cmd = "ffmpeg" + cmdPlaylistString 
				+ cmdFilterComplexMapping + cmdFilterComplexOptions
				+ " -map [outv] -map [outa] -strict -2 "+outputName +" -y";

		// String ffmpeg = "ffmpeg -f concat -safe 0 -i " + OUTPUT + " -c -y copy " + COMPILATION_NAME;
		// String ffmpeg = "ffmpeg -f concat -i " + OUTPUT + " -preset ultrafast -c -y copy " + COMPILATION_NAME;
		
		// On lance la concaténation des vidéos
		runCommandInterpretedMode(cmd, true);
		
		// On lance la commande vlc pour jouer la vidéo
		runCommandInterpretedMode(VLC_BASE_COMMAND + outputName);
	}

	
	//@Test
	public void testVarianteDePlusLongueDuree() throws InterruptedException, IOException {

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);
		nbVariante(videoGen);

		EList<Media> medias = videoGen.getMedias();

		double videoDuration = 0.0;
		String videoLocation;
		
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();

					// On execute la commande FFMPEG et on recupère la durée
					String command = FFMPEG_DURATION_COMMAND + videoLocation;
					Process p = runCommandInterpretedMode(command);
					double test = getVideoDescriptionDurationWithFFMPEG(p);
					System.out.println("[Mandatory] " + videoLocation + " duration ffmpeg=" + test);
					videoDuration += test;
				}

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();

				// On prend systématiquement la video
				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();

					// On execute la commande FFMPEG et on recupère la durée
					String command = FFMPEG_DURATION_COMMAND + videoLocation;
					Process p = runCommandInterpretedMode(command);
					double test = getVideoDescriptionDurationWithFFMPEG(p);
					System.out.println("[Optional] " + videoLocation + " duration ffmpeg=" + test);
					videoDuration += test;
				}
			} else if (media instanceof AlternativesMedia) {
				// On récupère toutes les possibilités des videos alternatives
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();
				MediaDescription mostLongAlternative = null;

				double max = 0.0;
				for (MediaDescription possibility : alternatives) {

					if (possibility instanceof VideoDescription) {
						videoLocation = possibility.getLocation();

						// On execute la commande FFMPEG et on recupère la durée
						String command = FFMPEG_DURATION_COMMAND + videoLocation;
						Process p = runCommandInterpretedMode(command);

						// On considère cette vidéo
						double test = getVideoDescriptionDurationWithFFMPEG(p);
						if (max < test) {
							mostLongAlternative = possibility;
							max = test;
						}
						System.out.println("[alternative] " + videoLocation + " duration ffmpeg=" + test);
					}
				}

				// On récupère les informations de la video et on applique les traitements
				if (mostLongAlternative != null && mostLongAlternative instanceof VideoDescription) {
					videoLocation = mostLongAlternative.getLocation();

					// String command = FFMPEG_DURATION_COMMAND + videoLocation;
					// Process p = runCommandInterpretedMode(command);
					// System.out.println("[alternative] selected " + videoLocation + " duration :
					// max=" + max + " ffmpeg="
					// + getVideoDescriptionDurationWithFFMPEG(p));
					videoDuration += max;
				}
			}
		}
		System.out.println("Videogen Most Long Variante: " + videoDuration);
	}
	
	
	//@Test
	public void testFfmpegCompileVariantes() throws IOException {
		compileVariantes(listeDeToutesLesVariantes());
	}
	
	private void compileVariantes(List<List<MediaDescription>> variantes) throws IOException {
		int index = 0;
		for (List<MediaDescription> variante : variantes) {
			// To use with ffmpeg filter-complex
			int videoIndex = 0;
			String cmdPlaylistString = "";
			String cmdFilterComplexMapping = " -filter_complex ";
			String videoLocation = "";
			
			for (MediaDescription description : variante) {
				if (description instanceof VideoDescription) {
					videoLocation = checkHasNoSound(description.getLocation());
					cmdPlaylistString += " -i " + videoLocation;
					cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
					videoIndex++;
				}
			}
			
			// Création de la commande à exécuter
			// On considère que les vidéos ont toutes la même extension.
			index++;
			String extension = videoLocation.substring(videoLocation.lastIndexOf("."));
			String outputName = INPUT_VIDEOGEN + "_variante" + index + extension;
			String cmdFilterComplexOptions = "concat=n="+videoIndex+":v=1:a=1[outv][outa]";
			String cmd = "ffmpeg" + cmdPlaylistString 
					+ cmdFilterComplexMapping + cmdFilterComplexOptions
					+ " -map [outv] -map [outa] -strict -2 "+outputName +" -y";
			// On lance la concaténation des vidéos
			runCommandInterpretedMode(cmd, true);
			System.out.println(cmd + "\n");
		}
	}
	
	
	//@Test
	public void testFfmpegDureesVariantes() throws IOException {
		dureesVariantes(listeDeToutesLesVariantes());
	}
	
	private void dureesVariantes(List<List<MediaDescription>> variantes) throws IOException {
		int index = 0;
		for (List<MediaDescription> variante : variantes) {
			// To use with ffmpeg filter-complex
			int videoIndex = 0;
			String cmdPlaylistString = "";
			String cmdFilterComplexMapping = " -filter_complex ";
			String videoLocation = "";
			
			for (MediaDescription description : variante) {
				if (description instanceof VideoDescription) {
					// Construction de la commande pour concatener les videos
					videoLocation = checkHasNoSound(description.getLocation());
					cmdPlaylistString += " -i " + videoLocation;
					cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
					videoIndex++;
				}
			}
			
			// Création de la commande à exécuter
			// On considère que les vidéos ont toutes la même extension.
			index++;
			String extension = videoLocation.substring(videoLocation.lastIndexOf("."));
			String outputName = INPUT_VIDEOGEN + "_variante" + index + extension;
			String cmdFilterComplexOptions = "concat=n="+videoIndex+":v=1:a=1[outv][outa]";
			String cmd = "ffmpeg" + cmdPlaylistString 
					+ cmdFilterComplexMapping + cmdFilterComplexOptions
					+ " -map [outv] -map [outa] -strict -2 "+outputName +" -y";
			// On lance la concaténation des vidéos
			runCommandInterpretedMode(cmd, true);
			
			// On lance le calcul de la durée
			String command = FFMPEG_DURATION_COMMAND + outputName;
			Process p = runCommandInterpretedMode(command);
			double videoDuration = getVideoDescriptionDurationWithFFMPEG(p);
			System.out.println("variante["+videoIndex+"] duration ffmpeg=" + videoDuration);
			runCommandInterpretedMode(command, true);
		}
	}
	
	
	//@Test
	public void calculerTaillesVariantes() throws IOException {
		
		// CSV File
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);
		EList<Media> medias = videoGen.getMedias();
		List<String> csvFile= new ArrayList<>();
		String separatorCsvFile = ",", lineCsvFile = "id";

		// On récupère la liste des variantes
		List<List<MediaDescription>> variantes = listeDeToutesLesVariantes();
		String videoLocation, videoId;
		Long moy = 0l;
		
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoId = ((VideoDescription) description).getVideoid();
					lineCsvFile += separatorCsvFile + videoId;
				}

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoId = ((VideoDescription) description).getVideoid();
					lineCsvFile += separatorCsvFile + videoId;
				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();

				for (MediaDescription possibility : alternatives) {
					if (possibility instanceof VideoDescription) {
						videoId = ((VideoDescription) possibility).getVideoid();
						lineCsvFile += separatorCsvFile + videoId;
					}
				}
			}
		}
		// Ajout de notre ligne d'entête
		lineCsvFile += separatorCsvFile + "size";
		lineCsvFile += separatorCsvFile + "realSize";
		csvFile.add(lineCsvFile);
		
		// Afin de récupérer les ID des vidéos
		String [] referentiel = lineCsvFile.split(separatorCsvFile);
		
		// Pour chaque variante possible
		for (int i = 0; i < variantes.size(); i++) {
			List<MediaDescription> uneVariante = variantes.get(i);
			
			// id, size, realsize sont à exclure du référentiel
			int nbVideos = 1, fin = referentiel.length - 3;
			long uneVarianteSize = 0l; 
			int j = 0; 
			
			lineCsvFile = (i + 1) + "";
			while (nbVideos <= fin) {
				MediaDescription video = uneVariante.get(j);
				
				if (video  instanceof VideoDescription) {
					String v = ((VideoDescription) video).getVideoid();
					if (v.equals(referentiel[nbVideos])) {
						lineCsvFile += separatorCsvFile + "TRUE";
						
						// On fait la somme des tailles des vidéos qui la composent
						videoLocation = video.getLocation();
						uneVarianteSize += new File(videoLocation).length();

						j++; // On passe à la vidéo suivante
					} else {
						lineCsvFile += separatorCsvFile + "FALSE";
					}
					nbVideos++;
				}
			}
			
			// calcul de la valeur de realSize
			// To use with ffmpeg filter-complex
			int videoIndex = 0;
			String cmdPlaylistString = "";
			String cmdFilterComplexMapping = " -filter_complex ";
			String location = "";
			for (MediaDescription description : uneVariante) {
				
				if (description instanceof VideoDescription) {
					location = checkHasNoSound(description.getLocation());
					cmdPlaylistString += " -i " + location;
					cmdFilterComplexMapping += "["+videoIndex+":v]["+videoIndex+":a:0]";
					videoIndex++;
				}
			}
			
			// Création de la commande à exécuter
			// On considère que les vidéos ont toutes la même extension.
			String extension = location.substring(location.lastIndexOf("."));
			String outputName = INPUT_VIDEOGEN + "_variante" + (i+1) + extension;
			String cmdFilterComplexOptions = "concat=n="+videoIndex+":v=1:a=1[outv][outa]";
			String cmd = "ffmpeg" + cmdPlaylistString 
					+ cmdFilterComplexMapping + cmdFilterComplexOptions
					+ " -map [outv] -map [outa] -strict -2 "+outputName +" -y";
			// On lance la concaténation des vidéos
			runCommandInterpretedMode(cmd, true);
			
			// System.out.println(cmd + "\n");
			
			// On ajoute la valeur de la colonne size
			lineCsvFile += separatorCsvFile + uneVarianteSize;
			
			// On ajoute la valeur de la colonne realSize
			lineCsvFile += separatorCsvFile + new File(outputName).length();
			
			// On ajoute la ligne correspondant à la variante
			csvFile.add(lineCsvFile);
		}
		
		// On crée le csv
		Path csv = new File(OUTPUT_VIDEOGEN_CSV).toPath();
		Files.write(csv, csvFile,Charset.defaultCharset());
		
		// Print le fichier CSV
		System.out.println("Impression du fichier CSV\n");
		for (String line : csvFile) {
			System.out.println(line);
		}
		
		/*
		List<Long> varianteSize = new ArrayList<>();
		// Pour chaque variante possible
		for (List<MediaDescription> variante : variantes) {
			long size = 0l; 
			for (MediaDescription description : variante) {
				if (description instanceof VideoDescription) {
					
					// On fait la somme des tailles des vidéos qui la composent
					videoLocation = description.getLocation();
					size += new File(videoLocation).length();
				}
			}
			varianteSize.add(Long.valueOf(size));
			System.out.println(size);
			// Total
			moy += size;
		}
		
		// On calcule la moyenne
		moy = moy / varianteSize.size();

		Long min = Long.MAX_VALUE;
		Long max = Long.MIN_VALUE;
		Long variance = 0l;
		
		for (Long size : varianteSize) {
			
			// Min
			if (min > size) {
				min = size;
			}
			
			// Max
			if (max < size) {
				max = size;
			}
			
			// Variance
			variance += (size - moy) * (size - moy);
		}
		
		variance = variance / variantes.size();
		
		// Ecart - Type
		double ecartType = Math.sqrt(variance);
		*/
	}
	
	private List<List<MediaDescription>> listeDeToutesLesVariantes () {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);

		EList<Media> medias = videoGen.getMedias();
		
		// On a au moins une variante
		List<List<MediaDescription>> variantes = new ArrayList<>();
		variantes.add(new ArrayList<>());

		String videoLocation;

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				// On l'ajoute à toutes les variantes
				for (List<MediaDescription> variante : variantes) {
					variante.add(description);
				}
//				varianteCourante.add(description);

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();
				List<List<MediaDescription>> variantesClone = new ArrayList<>(variantes);
				
				// Pour chaque occurrence
				for (List<MediaDescription> varianteSans : variantesClone) {

					// Dupliquer la liste
					List<MediaDescription> varianteAvec = new ArrayList<>(varianteSans);
					
					// Dans une copie, ajouter le media, dans l'autre ne rien faire :: copie inchangée
					varianteAvec.add(description);
					
					// On répertorie cette nouvelle variante
					variantes.add(varianteAvec);
				}
				
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();
				List<List<MediaDescription>> variantesClone = new ArrayList<>(variantes);
				int index = 0;
				int nbVariantes = variantes.size();
				int nbChoix = alternatives.size();
				
				// Pour chaque choix alternatif
				for (MediaDescription description : alternatives) {
					index++;
					// Ajouter le choix courant 
					for (List<MediaDescription> varianteSans : variantesClone) {

						if (index == nbChoix) {
							// Dernière itération, pas besoin de cloner
							varianteSans.add(description);
						} else {
							// Creation d'une variante avec le choix courant
							List<MediaDescription> varianteAvec = new ArrayList<>(varianteSans);						
							varianteAvec.add(description);
							
							// On répertorie cette nouvelle variante
							variantes.add(varianteAvec);
						}
						
					}
				}
				
			}
		}
		
		for (List<MediaDescription> list : variantes) {
			for (MediaDescription description : list) {
				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();
					System.out.print(((VideoDescription) description).getVideoid() + " ");
				}
			}
			System.out.println();
		}
		return variantes;
	}
	
	
	//@Test
	public void testPagePourAfficherVignettes() throws IOException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);

		EList<Media> medias = videoGen.getMedias();
		List<String> pageHTML = new ArrayList<>();
		String videoLocation;

		pageHTML.add("<!DOCTYPE HTML>");
		pageHTML.add("<html>");
		pageHTML.add("<head>");
		pageHTML.add("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		pageHTML.add("<title>Your Website</title>");
		pageHTML.add("</head>");
		pageHTML.add("<body>");
		pageHTML.add("<header>");
		pageHTML.add("<nav>");
		pageHTML.add("<ul>");
		pageHTML.add("<li>Your menu</li>");
		pageHTML.add("</ul>");
		pageHTML.add("</nav>");
		pageHTML.add("</header>");
		pageHTML.add("<section>");
		pageHTML.add("<h1>Vignettes</h1>");
		pageHTML.add("<div>");
		// Mode interprété
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();
					
					String vignette = videoLocation +".png";
					String command = "ffmpeg -y -i "+ videoLocation +" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "+ VIGNETTES_DIRECTORY +"/" + vignette;
					runCommandInterpretedMode(command, true);

					pageHTML.add("<div>");
					pageHTML.add("<h2>Mandatory</h2>");
					pageHTML.add("<img src=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\" alt=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\">");
					pageHTML.add("</div>");
				}

			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();

				if (description instanceof VideoDescription) {
					videoLocation = description.getLocation();

					String vignette = videoLocation +".png";
					String command = "ffmpeg -y -i "+ videoLocation +" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "+ VIGNETTES_DIRECTORY +"/" + vignette;
					runCommandInterpretedMode(command, true);

					pageHTML.add("<div>");
					pageHTML.add("<h2>Optional</h2>");
					pageHTML.add("<img src=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\" alt=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\">");
					pageHTML.add("</div>");
				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();
				
				pageHTML.add("<div>");
				pageHTML.add("<h2>Alternatives</h2>");
				pageHTML.add("<ul>");

				for (MediaDescription selectedMedia : alternatives) {
					// On récupère les informations de la video et on applique les traitements
					if (selectedMedia instanceof VideoDescription) {
						videoLocation = selectedMedia.getLocation();

						String vignette = videoLocation +".png";
						String command = "ffmpeg -y -i "+ videoLocation +" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "+ VIGNETTES_DIRECTORY +"/" + vignette;
						runCommandInterpretedMode(command, true);

						pageHTML.add("<li><img src=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\" alt=\""+ VIGNETTES_DIRECTORY +"/"+ vignette +"\"></li>");
					}
				}
				
				pageHTML.add("</ul>");
				pageHTML.add("</div>");
			}
		}
		
		pageHTML.add("</div>");
		pageHTML.add("</section>");
		pageHTML.add("<footer>");
		pageHTML.add("<p>Copyright 2018 SOUKPA Adou</p>");
		pageHTML.add("</footer>");
		pageHTML.add("</body>");
		pageHTML.add("</html>");
		
		for (String string : pageHTML) {
			System.out.println(string);
		}
		
		Path html = new File("index.html").toPath();
		Files.write(html,pageHTML,Charset.defaultCharset());
	}
	
	
	//@Test
	public void testGenerateurDeVignette() {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);

		// Création du répertoire pour les vignettes
		File vignetteDir = new File(VIGNETTES_DIRECTORY);
		vignetteDir.mkdir();
		
		EList<Media> medias = videoGen.getMedias();
		String videoLocation;

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();

				if (description instanceof VideoDescription || 
						media instanceof OptionalMedia) {
					videoLocation = description.getLocation();

					// Tape ta commande
					String command = "ffmpeg -y -i "+ videoLocation +" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "+ vignetteDir +"/" + videoLocation +".png";
					runCommandInterpretedMode(command, true);
				}
			} else if (media instanceof AlternativesMedia) {
				// On récupère toutes les possibilités des videos alternatives
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();

				for (MediaDescription selectedMedia : alternatives) {
					// On récupère les informations de la video et on applique les traitements
					if (selectedMedia instanceof VideoDescription) {
						videoLocation = selectedMedia.getLocation();

						// Tape ta commande
						String command = "ffmpeg -y -i "+ videoLocation +" -r 1 -t 00:00:01 -ss 00:00:02 -f image2 "+ vignetteDir +"/" + videoLocation +".png";
						runCommandInterpretedMode(command, true);
					}
				}
			}
		}
	}

	private double getVideoDescriptionDurationWithFFMPEG(Process p) throws IOException {
		double videoDuration = 0;
		if (p != null) {
			BufferedReader output = getOutput(p);

			String ligne = "";
			ligne = output.readLine();
			if (ligne != null) {
				videoDuration += Double.parseDouble(ligne);
			}
		}
		return videoDuration;
	}
	
	/**
	 * If video has no sound, create video with sound and return its location.
	 * @param videoLocation video location
	 * @return videoLocation to use if video has no sound, otherwise return param value
	 * @throws IOException
	 */
	private String  checkHasNoSound (String videoLocation) throws IOException {
		String newVideoName = videoLocation;
		
		// On vérifie que la video n'a pas de son
		String cmdCheckSound = FFMPEG_PREFIX_NOSOUND_CMD + videoLocation + FFMPEG_SUFFIX_NOSOUND_CMD;
		Process p = runCommandInterpretedMode(cmdCheckSound);
		boolean hasSound = videoHasSound(p);
		
		// Si elle n'a pas de son, on crée une nouvel vidéo à laquelle on ajoute du son.
		if (!hasSound) {
			
			// Nouveau Nom pour la vidéo créée
			int nameWithoutExtension = videoLocation.lastIndexOf(".");
			
			newVideoName = videoLocation.substring(0, nameWithoutExtension)
					+ "-wsound" + videoLocation.substring(nameWithoutExtension);
			
			// Commande à executer pour ajouter le son "null"
			String cmdOverwriteFFMPEG = " -y";
			String cmdAppendNullSound = FFMPEG_PREFIX_NOSOUND_CMD 
					+ videoLocation + FFMPEG_SUFFIX_APPEND_NULL_SOUND_CMD
					+ newVideoName + cmdOverwriteFFMPEG;
			
			runCommandInterpretedMode(cmdAppendNullSound);
		}
		
		return newVideoName;
	}

	/**
	 * Checks if a video has a sound or not
	 * @param p process where command-line has been carrying out
	 * @return TRUE if video has sound, FALSE otherwise
	 * @throws IOException
	 */
	private boolean videoHasSound(Process p) throws IOException {
		// Par défaut, on suppose que la vidéo n'a pas de son
		boolean hasSound = false;
		if (p != null) {
			BufferedReader output = getOutput(p);

			String ligne = "";
			ligne = output.readLine();

			// vrai si la ligne n'est pas vide - présence de son
			return ligne != null && !"".equals(ligne);
		}
		return hasSound;
	}

	private BufferedReader getOutput(Process p) {
		BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
		return output;
	}
	
	
	
	//@Test
	public void testErreursVideoGen() throws IOException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(INPUT_VIDEOGEN));
		assertNotNull(videoGen);

		EList<Media> medias = videoGen.getMedias();
		Map<String, String> listeVideosId = new HashMap<>();
		
		for(Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MediaDescription description = ((MandatoryMedia) media).getDescription();
				if (description instanceof VideoDescription) {
					if (listeVideosId.get(((VideoDescription) description).getVideoid()) == null) {
					
						listeVideosId.put(((VideoDescription) description).getVideoid(), 
								description.getLocation());
						System.out.println(((VideoDescription) description).getVideoid()+" - "+description.getLocation());
					}
					else if (listeVideosId.get(((VideoDescription) description).getVideoid()) 
							!= description.getLocation()) {
						System.out.println("L'identifieur " + ((VideoDescription) description).getVideoid()
									+ " est déjà utilisé !!");
						assertTrue(false);
					}
				}
			} else if (media instanceof OptionalMedia) {
				MediaDescription description = ((OptionalMedia) media).getDescription();
				if (description instanceof VideoDescription) {
					if (listeVideosId.get(((VideoDescription) description).getVideoid()) == null) {
						listeVideosId.put(((VideoDescription) description).getVideoid(), description.getLocation());
						System.out.println(((VideoDescription) description).getVideoid()+" - "+description.getLocation());
					}
					else if (listeVideosId.get(((VideoDescription) description).getVideoid()) 
							!= description.getLocation()) {
						System.out.println("L'identifieur " + ((VideoDescription) description).getVideoid()
									+ " est déjà utilisé !!");
						assertTrue(false);
					}
				}
			} else if (media instanceof AlternativesMedia) {
				EList<MediaDescription> alternatives = ((AlternativesMedia) media).getMedias();
				for (MediaDescription selectedMedia : alternatives) {
					if (selectedMedia instanceof VideoDescription) {
						if (listeVideosId.get(((VideoDescription) selectedMedia).getVideoid()) == null) {
							listeVideosId.put(((VideoDescription) selectedMedia).getVideoid(), selectedMedia.getLocation());
							System.out.println(((VideoDescription) selectedMedia).getVideoid()+" - "+selectedMedia.getLocation());
						}
						else if (listeVideosId.get(((VideoDescription) selectedMedia).getVideoid()) 
								!= selectedMedia.getLocation()) {
							System.out.println("L'identifieur " + ((VideoDescription) selectedMedia).getVideoid()
										+ " est déjà utilisé !!");
							assertTrue(false);
						}
					}

				}
				
			}
		}
		
	}
	
	//Génère un fichier gif à partir d'une video
	//scale : largeur en pixels du gif. Le rapport hauteur/largeur est préservé
	//fps : file per second. Fréquence de prélèvement des images dans la video pour réaliser le gif.
	@Test
	public void gifWithFfmpeg(int scale, int fps) throws IOException {
		
		String cmd1 = "ffmpeg -i " + GIF_INPUT + " -vf scale=" + scale + ":-1:flags=lanczos,fps=" + fps + " " + GIF_OUTPUT_FOLDER + "ffout%03d.png";
		String cmd2 = "convert -loop 0 " + GIF_OUTPUT_FOLDER + "ffout*.png " + GIF_OUTPUT_FOLDER + "output.gif";
		
		runCommandInterpretedMode(cmd1, true);
		runCommandInterpretedMode(cmd2, true);
		
	}
	
}
