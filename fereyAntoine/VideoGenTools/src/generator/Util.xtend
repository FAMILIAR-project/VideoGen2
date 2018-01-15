package generator

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.List
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter
import org.xtext.example.mydsl.videoGen.FlipFilter
import org.xtext.example.mydsl.videoGen.NegateFilter
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoText

class Util {
	
	
	def private String ffmpegConcatenateCommand(String mpegPlaylistFile, String outputPath) '''
		ffmpeg -y -f  concat -safe 0 -i «mpegPlaylistFile» -c copy  «outputPath»
	'''
	
	def private String ffmpegComputeDuration(String locationVideo) '''
		ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 «locationVideo»
	'''
	def private String ffmpegGeneretedVignette(String locationVideo, String locationImage) '''
		ffmpeg -y -i «locationVideo» -r 1 -t 00:00:01 -ss 00:00:11 -f image2 «locationImage»
	'''
	def private String ffmpegPaletteGen(String locationVideo) '''
		ffmpeg -y -ss 1 -i «locationVideo» -vf fps=10,scale=320:-1:flags=lanczos,palettegen palette.png
	'''
	def private String ffmpegGenerateGif(String locationVideo, String locationOuptut) '''
		ffmpeg -y -ss 1 -i «locationVideo» -i palette.png -filter_complex fps=10,scale=320:-1:flags=lanczos[x];[x][1:v]paletteuse «locationOuptut»
	'''
	def private String ffmpegAddText(String locationVideo,String locationOuptut,String bullshit) '''
		ffmpeg -y -i «locationVideo» -vf drawtext=«bullshit» -c:a ac3 «locationOuptut»
	'''
	def private String ffmpegBlackAndWhite(String locationVideo,String locationOuptut) '''
		ffmpeg -y -i «locationVideo» -vf hue=s=0 -c:a ac3 «locationOuptut»
	'''
	def private String ffmpegNegate(String locationVideo,String locationOuptut) '''
		ffmpeg -y -i «locationVideo» -vf lutrgb=r=negval:g=negval:b=negval -c:a ac3 «locationOuptut»
	'''
	def private String ffmpegFlip(String locationVideo,String typeflip,String locationOuptut) '''
		ffmpeg -y -i «locationVideo» -vf «typeflip» -c:a ac3 «locationOuptut»
	'''
	def private String ffmpegPerdiod(String locationVideo,String locationOuptut,int minute,int second) '''
		ffmpeg -y -ss 00:00:00 -i «locationVideo» -t 00:«minute»:«second» -c:a ac3 «locationOuptut»
	'''

val private DIR_TEMP="/tmp/"
	/**
	 * applique la modification filter
	 */
	 def String filter(VideoDescription video){
	 	
	 	var locationOutput = DIR_TEMP +"f_"+Util.getID(video);
	 	var choise = video.filter;
	 	var command ="";
	 	var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
	 	if(choise instanceof NegateFilter){
	 		
	 		command = ffmpegNegate(path,locationOutput)
	 		
	 	}else if(choise instanceof BlackWhiteFilter){
	 		command = ffmpegBlackAndWhite(path,locationOutput)
	 	}else if(choise instanceof FlipFilter){
	 		switch ((choise as FlipFilter).orientation){
	 		case "h":  command = ffmpegFlip(path,"hflip",locationOutput)
	 		case "horizontal":  command = ffmpegFlip(path,"hflip",locationOutput)
	 		case "v":  command = ffmpegFlip(path,"vflip",locationOutput)
	 		case "vertical":  command = ffmpegFlip(path,"vflip",locationOutput)
	 		}
	 	}
	 	
	 	
	 	var p1 = Runtime.runtime.exec(command);
		p1.waitFor;
		
		return locationOutput;
	 }
	 /**
	 * applique la modification duration
	 */
	 def String duration(VideoDescription video){
	 	
	 	var locationOutput = DIR_TEMP +"d_"+Util.getID(video);
	 	var seconde = video.duration;
	 	var mins = seconde / 60;
    	var secs = seconde - mins * 60;
    	var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
	 	var command1 = ffmpegPerdiod(path,locationOutput,mins,secs);
		var p1 = Runtime.runtime.exec(command1);
		p1.waitFor;
		
		return locationOutput;
	 }
	
	/**
	 * applique la modification text
	 */
	def String addText(VideoDescription video,VideoText videoconfig){
		var text = videoconfig.content;
		var position = videoconfig.position;
		var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
		var x = "x=(w-text_w)/2";
		var y = "y=(h-text_h)/2";
		var color ="white";
		var size = 32;
		switch (position){
	 		case "TOP":  {x="x=(w-text_w)/2";y="y=(h-text_h)/10";}
	 		case "CENTER":  {x="x=(w-text_w)/2";y="y=(h-text_h)/2";}					
	 		case "BOTTOM":  {x="x=(w-text_w)/2";y="y=(h-text_h)/1.1";}
	 		}
	 	if(videoconfig.color !==null){		
	 		color = videoconfig.color.toLowerCase 		
	 	}
	 	if(videoconfig.size !== 0){
	 		size = videoconfig.size;
	 	}

	 	var locationOutput = DIR_TEMP+"t_"+Util.getID(video);
		var command1 = ffmpegAddText(path,locationOutput,"fontfile=/usr/share/fonts/truetype/freefont/FreeSerif.ttf:text='"+text+"':fontcolor="+color+":fontsize="+size+":box=1:boxcolor=black@0.5:boxborderw=5:"+x+":"+y);
		
		var p1 = Runtime.runtime.exec(command1);
//		printInput(p1)
//		printError(p1)
		p1.waitFor;
		
		return locationOutput;
		
	}
	
	
	/**
	 * créer un gif pour une videoDescription
	 */
	def String generateGif(VideoDescription video){
		var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
		var locationVideo = path.replaceFirst("^~",System.getProperty("user.home"));
		var locationOutput = locationVideo.substring(0,locationVideo.length() - 3)+"gif"
		var command1 = ffmpegPaletteGen(locationVideo);
		var p1 = Runtime.runtime.exec(command1);
		p1.waitFor;
		
		var command2 = ffmpegGenerateGif(locationVideo,locationOutput);
		var p2 = Runtime.runtime.exec(command2);
		p2.waitFor;
		
		return locationOutput;
	}
	/**
	 * créer un gif pour un fichier video
	 */
	def String generateGif(String locationVideo,String locationOutput ){
		
		var command1 = ffmpegPaletteGen(locationVideo);
		var p1 = Runtime.runtime.exec(command1);
		p1.waitFor;
		
		var command2 = ffmpegGenerateGif(locationVideo,locationOutput);
		var p2 = Runtime.runtime.exec(command2);
		p2.waitFor;
		
		return locationOutput;
	}
	/**
	 * créer une vignette pour une VideoDescription à l'emplacement de celle-ci
	 */
	def String generateVignette(VideoDescription video){
		var locationVideo = video.location.replaceFirst("^~",System.getProperty("user.home"));
		
		var locationOutput = locationVideo.substring(0,locationVideo.length() - 3)+"png"
		var command1 = ffmpegGeneretedVignette(locationVideo,
			locationOutput);
		var p1 = Runtime.runtime.exec(command1);
		p1.waitFor;
		return locationOutput;
	}
	
	/**
	 * Longueur d'une video en seconde
	 */
	def Float getDuration(VideoDescription video){
		var locationVideo = video.location.replaceFirst("^~",System.getProperty("user.home"));
		var command1 = ffmpegComputeDuration(locationVideo);
		var p1 = Runtime.runtime.exec(command1)
		p1.waitFor
		return Float.parseFloat(printInput(p1));
	}
	
	def void generateVideo(List<VideoDescription> playlist, String outputPathFile){

		for(VideoDescription video : playlist){
			
			Dofilter(video);
		}
		var nameFile = writePlaylist(playlist);
		var command = ffmpegConcatenateCommand(nameFile, outputPathFile);
		
		var p = Runtime.runtime.exec(command)
		
//		printInput(p)
//		printError(p)
		p.waitFor
		
		
	}
	
	def private VideoDescription Dofilter(VideoDescription video){
		//println(video.location);
		var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
		var source = new File(path);
		var dest = new File(DIR_TEMP +Util.getID(video));
		Files.copy(source.toPath,dest.toPath,StandardCopyOption.REPLACE_EXISTING);
		video.location = dest.absolutePath;
		if(video.duration != 0){
			video.location = duration(video);
		}
		
		if(video.filter !== null){
			video.location = filter(video);
		}
		
		if(video.text !== null){
			video.location = addText(video,video.text);
			
		}
		
		return video;
	}
	
	

	/**
	 * Print la sortie standard
	 */
	def private String printInput(Process p) {
		var output = new BufferedReader(new InputStreamReader(p.getInputStream()));
		var ligne = "";
		var string = "";
		while ((ligne = output.readLine()) !== null) {
			string += ligne;
			//println(ligne);

		}
		return string;
	}

	/**
	 * Print la sortie erreur
	 */
	def private String printError(Process p) {
		var output = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		var ligne = "";
		var string = "";
		while ((ligne = output.readLine()) !== null) {
			string += ligne;
			println(ligne);
		}
		return string;
	}
	
	/**
	 * ecrit une playlist
	 */
	def private String writePlaylist(List<VideoDescription> playlist) throws IOException {
		var string = "";
		var date = Calendar.getInstance().getTime();
        var sdf = new SimpleDateFormat("MMdd_HHmmss_SSS");
        var nameFile = DIR_TEMP+sdf.format(date)+".ffconcat";
		val writer = new BufferedWriter(new FileWriter(nameFile));
		
		for (VideoDescription video : playlist) {
			var path = video.location.replaceFirst("^~",System.getProperty("user.home"));
			string += "file '" + path + "'\n";
			writer.write("file '" + path + "'\n");
		}

		writer.close();

		return nameFile;
	}
	
	def private static String getID(VideoDescription video){
		
		//var source = new File(video.location);
		return video.videoid+".mp4";
	} 
	
	def private void clean(List<VideoDescription> playlist){
		for(VideoDescription video : playlist){
			new File(DIR_TEMP+Util.getID(video)).delete
		}
		
	}
	
}
		