import org.eclipse.emf.common.util.URI
import org.junit.Test

import static org.junit.Assert.*
import java.util.concurrent.TimeUnit
import java.util.ArrayList

class VideoGenTest1 {
	
	@Test
	def void testLoadModel() {
//		var files = new ArrayList
//		files.add("jaunatan_o.mp4")
//		files.add("sheep_o.mp4")
//		VideoGenUtils.genPlaylist(files, "playlist.mp4")

		val res = new ArrayList
		val videos = new ArrayList
		videos.add("jaunatan.mp4")
		videos.add("sheep.mp4")
		videos.add("wave.mp4")
		videos.add("stan.mp4")
		for(video : videos){
			res.add(VideoGenUtils.getResolution(video))
		}
		
		var output_width = 0
		var output_height = 0
		
		for(r : res){
			if(r.get(0) > output_width){
				output_width = r.get(0)
			}
			if(r.get(1) > output_height){
				output_height = r.get(1)
			}
		}
		var i = 0;
		val playlist = new ArrayList
		for(video : videos){
			val r = res.get(i++)
			playlist.add(VideoGenUtils.resize(video, r.get(0), r.get(1), output_width, output_height))
		}
		
		VideoGenUtils.genPlaylist(playlist, "playlist.mp4")
		
		
	}
	
	
}