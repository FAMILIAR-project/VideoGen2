import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
		VideoGenToolBox vgtb = new VideoGenToolBox();
		
		//Generate all variantes
		List<Medias> variantes = vgtb.getAllVariantes(videoGen);
//		Medias variante = vgtb.getMoreLongerVideo(variantes);
		
		//Generate report
//		CSVReporter reporter = new CSVReporter(variantes, vgtb.getAllMedias(videoGen));
//		reporter.report();
		
//		Choose a random video
		int choosenVersion = (int) (1+Math.random()*(variantes.size()));
//		Generate the corresponding mp4 file
		String videoName = "destination"+choosenVersion;
		vgtb.generateMp4FileForOneVariante(variantes.get(choosenVersion-1), videoName);
		
//		run result
		vgtb.runVideo(videoName);
	}

}
