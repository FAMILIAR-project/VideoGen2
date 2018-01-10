package transformations

import org.junit.Test
import org.eclipse.emf.common.util.URI

import static org.junit.Assert.*
import org.junit.Before
import org.junit.After
import helpers.VideoGenHelper

class VideoGenPlayTransformationsTest {

	

	@Before
	def void setUp(){
		
	}
	
	@After
	def void tearDown(){
		
	}
	
	@Test
	def void generateRandomPlayList(){
		//val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
		//VideoGenPlayTransformations.generateRandomPlayList(videoGen)
	}
	
	@Test
	def void makeThumbnails(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
		VideoGenPlayTransformations.makeThumbnails(videoGen)
	}
	
}