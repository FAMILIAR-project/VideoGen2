package fr.istic.m2il.idm.videogentransformations.transformations

import org.junit.Test
import org.eclipse.emf.common.util.URI

import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenChekerHelper
import org.junit.Assert
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils
import fr.istic.m2il.idm.videogentransformations.helpers.CSVHelper
import org.junit.Before
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils

class VideoGenPlayTransformationsTest {
	
	@Before
	def void setUp(){
		if(System.getProperty("videogenspecification") === null)
			System.setProperty("videogenspecification", "samples/sample1.videogen")
	}

	@Test 
	def void checksValidVideoGenSpecification(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
		}
		println(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen))
	}	
	
	@Test
	def void checksValidFiles(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
		}
		println(VideoGenChekerHelper.isAllMediasFilesExist(videoGen))
	}
	
	@Test
	def void checksValidIds(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
		println(VideoGenChekerHelper.isAllMediasIdIsUnique(videoGen))
	}
	
	@Test
	def void generateRandomPlayList(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
		
			VideoGenPlayTransformations.generateRandomPlayList(videoGen)
			
		}
	}
	
	@Test
	def void makeThumbnails(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
		
			val thumbs = VideoGenPlayTransformations.makeThumbnails(videoGen)
			println(thumbs.size)
		}
	}
	
	@Test
	def void makeWebPage(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			VideoGenConfigs.setGifResolutions(190, 60)
		val htmls = VideoGenPlayTransformations.makeWebPage(videoGen)
		for(html: htmls)
			println(html)
		}
	}
	
	@Test
	def void videoMaxDuration(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
		VideoGenConfigs.setGifResolutions(190, 60)
		println(VideoGenAnalysisTransformations.getMaxDuration(videoGen))
		}
	}
	
	@Test
	def void videoToGif(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			if(System.getProperty("gif_width") === null)
				VideoGenConfigs.setGifResolutions(190, 60)
			else{
				VideoGenConfigs.setGifResolutions(Integer.parseInt(System.getProperty("gif_width")), Integer.parseInt(System.getProperty("gif_heigth")))	
			}
		VideoGenPlayTransformations.videoGensToGifs(videoGen)
		}
	}
	
	@Test
	def void checksVariantsNumber() throws Exception{
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			VideoGenConfigs.setGifResolutions(190, 60)
			Assert.assertTrue(
				"Le nombre de variants trouvées",
				VideoGenUtils.getVariantNumber(videoGen) == VideoGenUtils.generatePlaylists(videoGen).size
			)
		}
	}
	
	@Test
	def void concatVideos(){
		
		if(System.getProperty("concat_files") !== null){
			if(System.getProperty("output_folder") === null)
				VideoGenConfigs.setOutPutFoulder("output")
			var files = System.getProperty("concat_files").split(" ")
			
			val resolutions = newArrayList
			for(file : files){
				if(file !== null && 
					(
						(
						!file.replace(".","@").split("@").get(1).equals("jpg") && 
						!file.replace(".","@").split("@").get(1).equals("png") &&
						!file.replace(".","@").split("@").get(1).equals("gif") &&
						!file.replace(".","@").split("@").get(1).equals("bpm") &&
						!file.replace(".","@").split("@").get(1).equals("tiff")
						)
					)
				){
					resolutions.add(FFMPEGHelper.getVideoResolution(file))
				}
			
			}
			var maxOutputWidth = 0
			var maxOutputHeight = 0
		
			for(resolution : resolutions){
				if(resolution.get(0) > maxOutputWidth){
					maxOutputWidth = resolution.get(0)
				}
				if(resolution.get(1) > maxOutputHeight){
					maxOutputHeight = resolution.get(1)
				}
			}
			var index = 0;
			for(f: files){
				 files.set(index, FFMPEGHelper.homogenizeMediaResolution(
															f, 
															FFMPEGHelper.getVideoResolution(f).get(0), 
															FFMPEGHelper.getVideoResolution(f).get(1), 
															maxOutputWidth, 
															maxOutputHeight
															)
				)
				index++;
			}
			
			FFMPEGHelper.concatVideos(files, CommonUtils.getOutPutFileName(VideoGenConfigs.outPutFoulder + "/concat_videos." + System.getProperty("concat_format")))
		}
	}
	
	@Test
	def void checksCSVLinesNumber(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			var boolean csv_type
			var boolean isDuration
			if(System.getProperty("csv_duration") === null){
				isDuration = false
			}
			else{
				if(System.getProperty("csv_duration").equals("true"))
					isDuration = true
				else isDuration = false
			}
			if(System.getProperty("csv_type") === null)
				csv_type = false
			else{
				if(System.getProperty("csv_type").equals("true"))
					csv_type = true
				else csv_type = false
			}
			if(System.getProperty("gif_width") === null)
				VideoGenConfigs.setGifResolutions(190, 60)
			else{
				VideoGenConfigs.setGifResolutions(Integer.parseInt(System.getProperty("gif_width")), Integer.parseInt(System.getProperty("gif_heigth")))	
			}
			Assert.assertTrue(
			"Le fichier CSV produit pour la spécification videogen du fichier " + System.getProperty("videogenspecification")  +" contient autant de lignes que de nombre de variantes possibles (+ 1)." ,
			(VideoGenUtils.getVariantNumber(videoGen) +1) == CSVHelper.create(videoGen, isDuration ,csv_type).size)
		}
	}
	
	@Test
	def void cheksThumbsNumber(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			VideoGenConfigs.setGifResolutions(190, 60)
			Assert.assertTrue("Le nombre de vignettes produites par la spécification videogen du fichier " + System.getProperty("videogenspecification") + " est bien égal au nombre de medias", 
			VideoGenUtils.getVideoGenMediasNumber(videoGen) == VideoGenPlayTransformations.makeThumbnails(videoGen).size
			)
		}	
	}
	
	@Test
	def void checksWebPageThumbs(){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")))
		if(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)){
			if(System.getProperty("output_folder") === null){
				VideoGenConfigs.setOutPutFoulder("output")
				VideoGenConfigs.initSubOutPutFolders
			}
			VideoGenConfigs.setGifResolutions(190, 60)
			Assert.assertTrue("Le nombre de vignettes produites par la spécification videogen du fichier "+ System.getProperty("videogenspecification") +" est bien égal au nombre de medias", 
			VideoGenUtils.getVideoGenMediasNumber(videoGen) == VideoGenPlayTransformations.makeThumbnails(videoGen).size
		)
		}
	}
	
}