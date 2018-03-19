package videoGen;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import videoGenStudy.Variante;

import static videoGen.VideoGenCheck.*;
import static videoGenStudy.VarianteUtils.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		//generateAll();
		generateOne();
	}
	
	private static void generateAll() throws IOException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("videogenFile/example4.videogen"));
		List<Variante> variantes = evalVar(videoGen.getMedias());
	}
	
	private static void generateOne() throws IOException {

		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("videogenFile/example3.videogen"));
		
		//Le check n'est pas a jour par rapport au generator (images non gérées)
		//if(isCorrectVideoGen(videoGen.getMedias())) {
		
		VideoGenGenerator generator = new VideoGenGenerator();

		generator.genPlaylist(videoGen);
		generator.genVideo();
	}

}
