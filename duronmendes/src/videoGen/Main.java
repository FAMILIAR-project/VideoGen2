package videoGen;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class Main {

	public static void main(String[] args) {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
	}

}
