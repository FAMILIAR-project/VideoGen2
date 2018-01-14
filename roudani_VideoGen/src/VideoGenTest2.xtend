import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.*;

import static org.junit.Assert.*;
import java.io.*;
import transformation.Ffmpeg
import transformation.LongestVariant
import transformation.Html
import util.Utils
import transformation.VariantSizes

class VideoGenTest2 {
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));

	@Test
	def void testVariantFfmpeg() {

		// supprimer le fichier output.mp4 avant de relancer!!
		val variant = Ffmpeg.toFfmpeg(videoGen)
		assertTrue(Utils.validator(videoGen))
		//Utils.toFile(variant, "variant.txt")
		//Utils.concat_and_play("variant.txt", "variant")

	}

	@Test
	def void testLongestFfmpeg() {

		val logestVariant = LongestVariant.toLongestVar(videoGen)
		Utils.toFile(logestVariant, "logestVariant.txt");

	}

	@Test
	def void testHtmlPage() {

		val htmlPage = Html.toHtml(videoGen)
		Utils.toFile(htmlPage, "vignette.html");

	}
	
	@Test
	def void testVariantSizes(){
		val variantSizes = VariantSizes.getAllVariants(videoGen)
	}
	
	@Test
	def void testVideoToGif(){
		Utils.videoToGif("output.mp4", "gifFromVideo", 50, 50);
	}
}
