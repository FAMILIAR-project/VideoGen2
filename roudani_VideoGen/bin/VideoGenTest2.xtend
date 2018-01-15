import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import static org.junit.Assert.*;
import transformation.Ffmpeg
import transformation.LongestVariant
import transformation.Html
import util.Utils
import util.Filters
import transformation.VideoToGif
import org.junit.Before
import transformation.Etude

class VideoGenTest2 {
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example4.videogen"));
	@Before
	def void befor(){
		println("Ne pas oublier de nettoyer les dossiers avant de lancer de nouvelles generations de videos")
	}
	

	@Test
	def void testVariantFfmpeg() {
		//supprimer le fichier output.mp4 avant de relancer!!
		val variant = Ffmpeg.generatePlayList(videoGen)
		Utils.toFile(variant, "textFiles/variant.txt")
		Utils.concat_and_play("textFiles/variant.txt", "generatedVideos/variante")

	}

	@Test
	def void testLongestFfmpeg() {

		val logestVariant = LongestVariant.toLongestPlayList(videoGen)
		Utils.toFile(logestVariant, "logestVariant.txt");

	}

	@Test
	def void testHtmlPage() {

		val htmlPage = Html.toHtml(videoGen)
		Utils.toFile(htmlPage, "textFiles/vignette.html");
		assertTrue(Utils.isFileExist("textFiles/vignette.html"))

	}
	
	@Test
	def void testVariantSizes(){
		val variantSizes = Etude.getAllVariants(videoGen)
	}
	
	@Test
	def void testVideoToGif(){
		val toGif = new VideoToGif
		assertTrue(toGif.modelToGifs(videoGen,200,200))	
	}
	
	@Test
	def void testFilterBlackWhite(){
		Filters.blackWhiteFilter("videos/1.mp4", false)
		assertTrue(Utils.isFileExist("generatedVideos/1_bw.avi"))
	}
	
		@Test
	def void testFilterNegate(){
		Filters.negateFilter("videos/1.mp4", false)
		assertTrue(Utils.isFileExist("generatedVideos/1_neg.avi"))
	}
			@Test
	def void testFilterFlip(){
		Filters.flipFilter("videos/1.mp4", "h",false)
		assertTrue(Utils.isFileExist("generatedVideos/1_hflip.avi"))
	}
	
	@Test
	def void testClean(){
		//decomenter pour tester clean
		//Utils.cleanTestFiles();
		assertTrue(true)
	}
	
	@Test 
		def void testAddText(){
		//Filters.addText("videos/1.mp4",)
	}
	
}
