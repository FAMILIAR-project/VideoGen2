package videoGenStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;

public class VarianteUtils {

	private static final String COMMAND_SIZE = "ffprobe -v error -show_entries format=size -of default=noprint_wrappers=1:nokey=1 ";
	private static final String COMMAND_LENGTH = "ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 ";
	private static final String COMMA = ",";
	private static final String CSV_COLUMNS = "Name, Length, Size, Real size\n";

	public static List<Variante> evalVar(EList<Media> medias) {
		List<Variante> variantes = new ArrayList<>();
		List<Variante> variantesToAdd = new ArrayList<>();
		MediaDescription currentMediaDesc;
		Variante currentVar;

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				currentMediaDesc = ((MandatoryMedia) media).getDescription();

				if (variantes.size() != 0) {
					for (Variante variante : variantes) {
						variante.addMedia(currentMediaDesc);
					}
				} else {
					currentVar = new Variante();
					currentVar.addMedia(currentMediaDesc);
					variantes.add(currentVar);
				}
			} else if (media instanceof OptionalMedia) {
				currentMediaDesc = ((OptionalMedia) media).getDescription();
				variantesToAdd.clear();
				if (variantes.size() != 0) {
					for (Variante variante : variantes) {
						currentVar = new Variante(variante.getContentDeep());
						currentVar.addMedia(currentMediaDesc);
						variantesToAdd.add(currentVar);
					}
					variantes.addAll(variantesToAdd);
				} else {
					currentVar = new Variante();
					currentVar.addMedia(currentMediaDesc);

					variantes.add(new Variante());
					variantes.add(currentVar);
				}
			} else if (media instanceof AlternativesMedia) {
				variantesToAdd.clear();
				if (variantes.size() != 0) {
					for (MediaDescription alternativeDesc : ((AlternativesMedia) media).getMedias()) {
						for (Variante variante : variantes) {
							currentVar = new Variante(variante.getContentDeep());

							currentVar.addMedia(alternativeDesc);
							variantesToAdd.add(currentVar);
						}
					}
					variantes.clear();
					variantes.addAll(variantesToAdd);
				} else {
					for (MediaDescription alternativeDesc : ((AlternativesMedia) media).getMedias()) {
						currentVar = new Variante();
						currentVar.addMedia(alternativeDesc);
						variantes.add(currentVar);
					}
				}
			}
		}
		return variantes;
	}

	public static void setVariantesLengthAndSize(List<Variante> variantes, EList<Media> medias) {
		SimpleEntry<HashMap<String, Float>, HashMap<String, Integer>> dataMaps = null;
		HashMap<String, Float> mediaLength = null;
		HashMap<String, Integer> mediaSize = null;
		String videoId = "";
		Float tmpLength;
		Integer tmpSize;
		try {
			dataMaps = getMediaLengthAndSizeMap(medias);
			mediaLength = dataMaps.getKey();
			mediaSize = dataMaps.getValue();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		for (Variante variante : variantes) {
			tmpLength = 0.0f;
			tmpSize = 0;
			for (MediaDescription mediaDescription : variante.getContentDeep()) {
				if (mediaDescription instanceof VideoDescription) {
					videoId = ((VideoDescription) mediaDescription).getVideoid();
					tmpLength += mediaLength.get(videoId);
					tmpSize += mediaSize.get(videoId);
				}
			}
			variante.setLength(tmpLength);
			variante.setSize(tmpSize);
		}
	}

	private static SimpleEntry<HashMap<String, Float>, HashMap<String, Integer>> getMediaLengthAndSizeMap(
			EList<Media> medias) throws IOException, InterruptedException {
		String currentMediaLocation = "";
		MediaDescription currentMediaDesc;
		String videoId = "";
		SimpleEntry<HashMap<String, Float>, HashMap<String, Integer>> res;
		HashMap<String, Float> mediaLength = new HashMap<>();
		HashMap<String, Integer> mediaSize = new HashMap<>();

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				currentMediaDesc = ((MandatoryMedia) media).getDescription();
				currentMediaLocation = currentMediaDesc.getLocation();
				videoId = ((VideoDescription) currentMediaDesc).getVideoid();

				mediaLength.put(videoId, getMediaLength(currentMediaLocation));
				mediaSize.put(videoId, getMediaSize(currentMediaLocation));
			}
			if (media instanceof OptionalMedia) {
				currentMediaDesc = ((OptionalMedia) media).getDescription();
				currentMediaLocation = currentMediaDesc.getLocation();
				videoId = ((VideoDescription) currentMediaDesc).getVideoid();

				mediaLength.put(videoId, getMediaLength(currentMediaLocation));
				mediaSize.put(videoId, getMediaSize(currentMediaLocation));
			}
			if (media instanceof AlternativesMedia) {
				for (MediaDescription alternativeDesc : ((AlternativesMedia) media).getMedias()) {
					currentMediaLocation = alternativeDesc.getLocation();
					videoId = ((VideoDescription) alternativeDesc).getVideoid();

					mediaLength.put(videoId, getMediaLength(currentMediaLocation));
					mediaSize.put(videoId, getMediaSize(currentMediaLocation));
				}
			}
		}
		res = new SimpleEntry(mediaLength, mediaSize);
		return res;
	}

	private static float getMediaLength(String location) throws IOException, InterruptedException {
		return Float.parseFloat(getMediaData(location, 0));
	}

	private static int getMediaSize(String location) throws IOException, InterruptedException {
		return Integer.parseInt(getMediaData(location, 1));
	}

	private static String getMediaData(String location, int commandType) throws IOException, InterruptedException {
		Process p;
		String commandToRun = null;
		String s = null;
		String result = new String("");
		int mediaLength;
		if (commandType == 0) {
			commandToRun = COMMAND_LENGTH;
		} else if (commandType == 1) {
			commandToRun = COMMAND_SIZE;
		}

		p = Runtime.getRuntime().exec(commandToRun + location);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while ((s = stdInput.readLine()) != null) {
			result += s;
		}
		p.waitFor();
		return result;
	}
	
	public static void getOutputVideoSize(String outputPath) {
		List<String> outputVideoPath = new ArrayList<>();
		outputVideoPath = getOutputVideoPath(outputPath);
		
		for(String videoPath : outputVideoPath) {
			try {
				getMediaSize(outputPath);
				//Déterminer le type de retour
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<String> getOutputVideoPath(String outputPath) {
		File folder = new File(outputPath);
		File[] listOfFiles = folder.listFiles();
		List<String> outputVideoPath = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				outputVideoPath.add(listOfFiles[i].getAbsolutePath());
			}
		}
		return outputVideoPath;
	}

	public static void exportToCsv(String csvLocation, List<Variante> variantes) throws IOException {
		String line = "";
		BufferedWriter bw = null;
		FileWriter fw = null;
		fw = new FileWriter(csvLocation);
		bw = new BufferedWriter(fw);
		bw.write(CSV_COLUMNS);
		for (Variante variante : variantes) {
			line = "";
			line += variante.toString() + COMMA + variante.getLength() + COMMA + variante.getSize() + COMMA + "\n";
			bw.write(line);
		}

		if (bw != null)
			bw.close();

		if (fw != null)
			fw.close();
	}
}
