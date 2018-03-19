package eloisance

import org.junit.Test
import org.eclipse.emf.common.util.URI

import static org.junit.Assert.*
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MediaDescription
import java.util.ArrayList
import java.util.Random
import java.io.PrintWriter
import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader
import java.nio.file.Paths

class VideoGenTest1 {
	
	var playlist = new ArrayList<VideoDescription>();
	ArrayList<ArrayList<VideoDescription>> finalPlayList = new ArrayList<ArrayList<VideoDescription>>();
	
	@Test
	def void launch() {
		generateRandomVideo("example2.videogen", "./playlist.txt", "./../data/", "./video.mp4")
	}
	
	/* TODO */
	def void filter() {
		// black&white --> ffmpeg -y -i inputLocation -vf hue=s=0 -c:a ac3 outputLocation
	}
	
	/**
	 * Charge le fichier .videogen et génère
	 * une variante de vidéo.
	 */
	def void generateRandomVideo(String pathFileVideoGen, String pathPlaylist, String pathVideoLocation, String pathSaveVideo) {
		println("generateRandomVideo \r---")
		
		// clear & reset
		playlist.clear
		val file = new File("./video.mp4");
		file.delete()
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(pathFileVideoGen))
		for(Media media : videoGen.medias) {
			if(media instanceof MandatoryMedia) {
				mandatoryMedia(media)
			} else if (media instanceof OptionalMedia) {
				optionalMedia(media);
			} else if (media instanceof AlternativesMedia) {
				alternativesMedia(media)
			}
		}
		println("---\rplaylist size : " + playlist.size + " videos")
		
		// create playlist.txt file from playlist array information
		var writer = new PrintWriter(pathPlaylist, "UTF-8")
		for (var i = 0; i < playlist.size; i++) {
			val video = playlist.get(i)
			writer.println("file '" + pathVideoLocation + video.location + "'");
		}
		writer.close();

		// create final video
		var cmd = "ffmpeg -f concat -safe 0 -i " + pathPlaylist + " -c copy " + pathSaveVideo
		println("cmd ffmpeg: " + cmd)
		
		var process = Runtime.runtime.exec(cmd)
		process.waitFor
		
		/* START - FOR DEBUG */
		
		val stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		val  stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		var s = "";
		println("stdInput")
		while ((s = stdInput.readLine()) !== null) {
		    println(s)
		}
		
		println("stdError")
		while ((s = stdError.readLine()) !== null) {
		    println(s);
		}
		
		val currentRelativePath = Paths.get("");
		var currentPath = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + currentPath);
		
		/* END - FOR DEBUG */
		
		// play video
		// Runtime.runtime.exec("/usr/bin/vlc " + pathSaveVideo)
	}
	
	/**
	 * Only for dev test
	 */
	def void testLoadModel() {
		// clear lists
		playlist.clear
		finalPlayList.clear
		
		// load videogen
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"))
		assertNotNull(videoGen)
		
		for(Media media : videoGen.medias) {
			if(media instanceof MandatoryMedia) {
				mandatoryMedia(media)
			} else if (media instanceof OptionalMedia) {
				optionalMedia(media);
			} else if (media instanceof AlternativesMedia) {
				alternativesMedia(media)
			}
		}
		
		// print result
		println('-------')
		println('playlist: '+ playlist.size)
		println('-------')
		println('finalPlayList: ' + finalPlayList.size)
		println('-------')
		println()
		
		val p = Runtime.runtime.exec("/usr/bin/vlc ./../data/intro.mp4")
	}
	
	/**
	 * Mandatory
	 */
	def void mandatoryMedia(MandatoryMedia mandatory) {
		if (mandatory.description instanceof VideoDescription) {
			val video = (mandatory.description as VideoDescription)
			println("mandatory video: " + video.location)
			playlist.add(video)
			// tp 3 to count variante
			if (finalPlayList.isEmpty) {
				val ArrayList<VideoDescription> l1 = new ArrayList<VideoDescription>()
				l1.add(video);
				finalPlayList.add(l1);
			} else {
				var ArrayList<ArrayList<VideoDescription>> tmpFinalPlayList = new ArrayList<ArrayList<VideoDescription>>(finalPlayList)
				for(var i = 0; i < finalPlayList.size; i++) {
					tmpFinalPlayList.get(i).add(video)
				}
				finalPlayList = tmpFinalPlayList;
			}
			// end
		} else if (mandatory.description instanceof ImageDescription) {
			val image = (mandatory as ImageDescription)
			println("mandatory image: " + image.top)
		}
	}
	
	/**
	 * Optional
	 */
	def void optionalMedia(OptionalMedia optional) {
		if (optional.description instanceof VideoDescription) {
			val video = (optional.description as VideoDescription)
			println("optional video: " + video.location)
			val r = new Random();
			if (r.nextBoolean()) {
				// TODO: use probability
				playlist.add(video)
			}
			// tp 3 to count variante
			if (finalPlayList.isEmpty) {
				val ArrayList<VideoDescription> l1 = new ArrayList<VideoDescription>()
				l1.add(video);
				finalPlayList.add(l1);
				val ArrayList<VideoDescription> l2 = new ArrayList<VideoDescription>()
				l2.add(null);
				finalPlayList.add(l2);
			} else {
				var ArrayList<ArrayList<VideoDescription>> tmpFinalPlayList = new ArrayList<ArrayList<VideoDescription>>(finalPlayList)
				for(var i = 0; i < finalPlayList.size; i++) {
					tmpFinalPlayList.get(i).add(video)
				}
				finalPlayList = tmpFinalPlayList;
			}
			// end
		} else if (optional.description instanceof ImageDescription) {
			val image = (optional as ImageDescription)
			println("optional image: " + image.top)
		}
	}
	
	/**
	 * Alternatives
	 */
	def void alternativesMedia(AlternativesMedia alternatives) {
		var alternativesVideos = newArrayList()
		for(MediaDescription mediaAlter : alternatives.medias) {
			if (mediaAlter instanceof VideoDescription) {
				val video = (mediaAlter as VideoDescription)
				println("alternative video: " + video.location)
				alternativesVideos.add(video)
			} else if (mediaAlter instanceof ImageDescription) {
				val image = (mediaAlter as ImageDescription)
				println("alternative image: " + image.top)
			}
		}
		val r = new Random()
		val index = r.nextInt(alternativesVideos.size)
		playlist.add(alternativesVideos.get(index))
	}
}
