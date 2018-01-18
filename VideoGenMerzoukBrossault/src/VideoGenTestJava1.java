import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class VideoGenTestJava1 {
	
	private VideoGeneratorModel videoGen;
	private Medias medias;
	private VideoGenToolBox vgtb;
	
	@Before
	public void generateModel() {
//		this.videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
//		this.vgtb = new VideoGenToolBox();
//		this.medias = vgtb.getAllMedias(videoGen);
	}
	
	@Test
	public void testVideoGen() throws IOException, InterruptedException {
		assertNotNull(videoGen);		
	}
	
	@Test
	public void testMedias() throws IOException, InterruptedException {
		assertNotNull(medias);	
	}
	
	@Test
	public void testNbVariantes() {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
		Medias medias = this.vgtb.getAllMedias(videoGen);
		List<Medias> variantes = this.vgtb.getAllVariantes(videoGen);
		assertEquals(variantes.size(), medias.getNbVariantes());
	}
}