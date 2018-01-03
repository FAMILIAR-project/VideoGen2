package fr.pagetpetit.videogentools

import org.eclipse.emf.common.util.URI
import org.junit.Test

import static org.junit.Assert.*
import java.util.concurrent.TimeUnit
import java.util.ArrayList

class VideoGenTest1 {
	
	@Test
	def void testLoadModel() {

		val videos = new ArrayList
		videos.add("jaunatan.mp4")
		videos.add("sheep.mp4")
		videos.add("wave.mp4")
		//videos.add("stan.mp4")
		
		VideoGenUtils.generatePlaylist(videos)
		
	}
	
	
}
