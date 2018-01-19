package other

import java.io.File
import org.eclipse.emf.common.util.URI
import org.junit.Before
import org.junit.Test
import transformation.Etude
import transformation.Html
import transformation.LongestVariant
import transformation.PlayList
import transformation.VideoToGif
import util.Filters
import util.Utils

import static org.junit.Assert.*

class Tests {
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("variante.videogen"))
	
	
	//IMPORTANT!! la fonction isFileExit test aussi si le fichier n'est pas vide
	
	@Before
	def void befor(){
		println("Ne pas oublier de nettoyer les dossiers avant de lancer de nouvelles generations de videos")
	}
	

	@Test
	def void testPlayListGENERATOR() {
		//faite un clean avant de relancer
		Utils.cleanTestFiles()
		val variant = PlayList.generate(videoGen, "")
		Utils.toFile(variant, "textFiles/variante.txt")
		Utils.concat("textFiles/variante", "generatedVideos/variante")
		assertTrue(Utils.isFileExist("generatedVideos/variante.mp4"));

	}

	@Test
	def void testLongestFfmpeg() {

		val logestVariant = LongestVariant.toLongestPlayList(videoGen)
		Utils.toFile(logestVariant, "logestVariante.txt")
		assertTrue(Utils.isFileExist("logestVariante.txt"));
	}

	@Test
	def void testHtmlPage() {

		val htmlPage = Html.toHtml(videoGen)
		Utils.toFile(htmlPage, "textFiles/vignette.html")
		assertTrue(Utils.isFileExist("textFiles/vignette.html"))

	}
	
	@Test
	def void testVariantSizes(){
		Etude.getAllVariants(videoGen)
		Utils.isFileExist("textFiles/tab.csv")
	}
	
	@Test
	def void testGifExporter(){
		val toGif = new VideoToGif
		toGif.videoToGif("videos/1.mp4", "gifs/gif_exported.gif" ,200,200)
		Utils.isFileExist("gifs/gif_exported.gif")
	}
	
//	@Test
//	def void testModelToGif(){
//		val toGif = new VideoToGif
//		assertTrue(toGif.modelToGifs(videoGen,200,200))	
//	}
	
	@Test
	def void testFilterBlackWhite(){
		Filters.blackWhiteFilter("videos/1.mp4", false)
		assertTrue(Utils.isFileExist("generatedVideos/1_bw.mp4"))
	}
	
	@Test
	def void testFilterNegate(){
		Filters.negateFilter("videos/1.mp4", false)
		assertTrue(Utils.isFileExist("generatedVideos/1_neg.mp4"))
	}
	
	@Test
	def void testFilterFlip(){
		Filters.flipFilter("videos/1.mp4", "h",false)
		assertTrue(Utils.isFileExist("generatedVideos/1_hflip.mp4"))
	}
	
	@Test
	def void testClean(){
		//pour tester clean
		// rm gifs/* rm images/* rm textFiles/
		Utils.cleanTestFiles()
		var generatedVideos = new File("generatedVideos/")
		var images = new File("images/")
		var gifs = new File("gifs/")
		assertTrue(generatedVideos.list.empty)
		assertTrue(images.list.empty)
		assertTrue(gifs.list.empty)
	}
}
