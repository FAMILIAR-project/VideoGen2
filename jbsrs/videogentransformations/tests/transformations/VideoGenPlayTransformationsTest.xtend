package transformations

import org.junit.Test
import org.eclipse.emf.common.util.URI

import static org.junit.Assert.*
import org.junit.Before
import org.junit.After
import helpers.VideoGenHelper
import configs.VideoGenConfigs

class VideoGenPlayTransformationsTest {

	

	@Before
	def void setUp(){
		
	}
	
	@After
	def void tearDown(){
		
	}
	
	@Test
	def void generateRandomPlayList(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample9.videogen"))
		VideoGenConfigs.setOutPutFoulder("output")
		VideoGenConfigs.setGifResolutions(190, 60)
		VideoGenPlayTransformations.generateRandomPlayList(videoGen)
	}
	
	@Test
	def void makeThumbnails(){
		/*val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample3.videogen"))
		VideoGenConfigs.setOutPutFoulder("output")
		VideoGenConfigs.setGifResolutions(190, 60)
		val thumbs = VideoGenPlayTransformations.makeThumbnails(videoGen)
		println(thumbs.size)
		println("Alt size" +thumbs.get("Alternatives").size)
		println("Man size" +thumbs.get("Mandatory").size)
		println("Op size" +thumbs.get("Optional").size)*/
	}
	
	@Test
	def void makeWebPage(){
		/*val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample3.videogen"))
		VideoGenConfigs.setOutPutFoulder("output")
		VideoGenConfigs.setGifResolutions(190, 60)
		val htmls = VideoGenPlayTransformations.makeWebPage(videoGen)
		for(html: htmls)
			println(html)*/
	}
	
	@Test
	def void videoMaxDuration(){
		//val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample3.videogen"))
		//println(VideoGenAnalysisTransformations.getMaxDuration(videoGen))
	}
	
	@Test
	def void videoToGif(){
		/*val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample3.videogen"))
		VideoGenConfigs.setOutPutFoulder("output")
		VideoGenConfigs.setGifResolutions(190, 60)
		VideoGenPlayTransformations.videoGensToGifs(videoGen)*/
	}
	
	@Test
	def void checksVariantsNumber(){
		
	}
	
	@Test
	def void checksCSVLinesNumber(){
		
	}
	
	@Test
	def void cheksThumbsNumber(){
		
	}
	
	@Test
	def void checksWebPageThums(){
		
	}
	
}