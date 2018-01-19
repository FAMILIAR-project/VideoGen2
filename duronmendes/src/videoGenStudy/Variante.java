package videoGenStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import videoGen.VideoGenHelper;

import static videoGen.VideoGenCheck.*;
import static videoGenStudy.VarianteUtils.*;

public class Variante {
	private String name;
	private List<MediaDescription> content;
	private float length;
	private int size;

	public Variante() {
		this.content = new ArrayList<>();
	}

	public Variante(List<MediaDescription> content) {
		this.content = new ArrayList<>(content);
		;
	}

	public Variante(String name, List<MediaDescription> content) {
		super();
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMedia(MediaDescription media) {
		this.content.add(media);
	}

	public List<MediaDescription> getContentDeep() {
		List<MediaDescription> res = new ArrayList<>(this.content);
		return res;
	}

	public List<MediaDescription> getContentShallow() {
		return this.content;
	}

	public void setContent(List<MediaDescription> content) {
		this.content = content;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		String name = "";
		for (MediaDescription mediaDescription : content) {
			if (mediaDescription instanceof VideoDescription) {
				name += ((VideoDescription) mediaDescription).getVideoid();
			}
		}
		return name;
	}

	public static void main(String[] args) {

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
		EList<Media> medias = videoGen.getMedias();
		List<Variante> tmp = null;
		HashMap<Variante, Float> tmp2 = null;
		if (medias != null) {
			tmp = evalVar(medias);
		} else {
			System.out.println("ntm");
		}

		setVariantesLengthAndSize(tmp, medias);
		try {
			exportToCsv("/home/amendes/Bureau/report.csv", tmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// tmp2 = setVariantesLength(tmp, medias);
		// for (Map.Entry<Variante, Float> entry : tmp2.entrySet()) {
		// System.out.println("Variante : "+entry.getKey().toString() + " : " +
		// entry.getValue() + "\n");
		// }
		//
		// String output = "";
		// for (Variante variante : tmp) {
		// output += variante.toString() + "\n" + "Length : " + variante.getLength() + "
		// Size : "
		// + variante.getSize() / 1000 + " Mo" + "\n";
		// }
		// System.out.print(output);

		// try {
		// getMediaLength("/home/amendes/Documents/M2/IDM/projetIdm/duronmendes/videos/rgb.mp4");
		// } catch (IOException | InterruptedException e) {
		// e.printStackTrace();
		// }

	}
}
