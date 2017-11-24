import org.eclipse.emf.common.util.URI
import org.junit.Test

import static org.junit.Assert.*

class VideoGenTest1 {
	
	@Test
	def void testLoadModel() {
		var videosFiles = newArrayList("example1.videogen", "example2.videogen")
		
		for(file : videosFiles){
			val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(file))
			var csv = VideoGenUtils.createCSV(videoGen)
			var variants = VideoGenUtils.genNbVariant(videoGen)
			println(file + ' : csv size -> ' + (csv.length - 1) + ' nbVariants -> ' + variants)
			assertTrue(csv.length - 1 == variants)
		}
	}
	
	
}