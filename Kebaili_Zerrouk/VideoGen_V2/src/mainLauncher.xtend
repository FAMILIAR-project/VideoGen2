
import org.eclipse.emf.common.util.URI
import java.io.File

class mainLauncher {
	def static void main(String[] args) {
		//Load the .videogen Model
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("Videos_Elements/InputFiles/Input_ModelsPlaylist/model.videogen"))
		
		var filelist= VideoLocationTXT.fromModelToTXTFile(videoGen)
		var fileName = FileWriter.FileWriter(filelist)
		
		
		//FFMPEG command call to create the video using the links contained in the txt file 
		var command = "ffmpeg -f concat -safe 0 -i Videos_Elements/InputFiles/Input_TextPlaylist/"+fileName+".txt -y -c copy Videos_Elements/GeneratedVideo/"+fileName+".mp4"		
				
		var p = Runtime.runtime.exec(command)
		p.waitFor

		val file = new File("Videos_Elements/Input_TextPlaylist/"+fileName+".txt")
		file.delete()
    		
		//retourne le lien de la video 
		println(fileName+".mp4")
		
	}
}


