import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

class projet {
	
	def static void main(String[] args) {
		// charge le fichier videogen
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"))
		
		// fichier text contenant les videos de la génération au format accepté par FFMPEG	
		var filelist = ""
		
		// parcourt le fichier videogen
		if (videoGen instanceof VideoGeneratorModel){
			for (Media media : videoGen.getMedias()) {
				// si c'est une video obligatoire
				if (media instanceof MandatoryMedia) {
					if (media.getDescription() instanceof MediaDescription){
						filelist = filelist+"file '"+media.getDescription().location+"'\n"			
					}				
				}
				// si c'est une video optionnel
				else if (media instanceof OptionalMedia) {
					if (media.getDescription() instanceof MediaDescription){
						// random 50% de chance d'ajouter la video
						if (new Random().nextInt(100) > 50 ){
							filelist = filelist+"file '"+media.getDescription().location+"'\n"
						}
					}		
				}
				// si c'est une video alternative
				else if (media instanceof AlternativesMedia) {
					var alternativesize = media.getMedias().size
					// choisi aléatoirement une video parmis les alternatives
					val md = media.getMedias().get(new Random().nextInt(alternativesize))
					if (md instanceof MediaDescription){
					    filelist = filelist+"file '"+md.location+"'\n"
					}
				}
			}
		}
		
		// créé le nom aléatoire
		var name = new Random().nextInt(9999).toString()
		
		// crée un fichier texte avec les video a utilisé pour cette génération
		var Writer writer = null
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("public/TempTextFile/"+name+".txt"), "utf-8"))
		    writer.write(filelist)
		    writer.close()
		} catch (IOException  ex) {
		} finally {
		   try {} catch (Exception ex) {/*ignore*/}
		}


		//appel la commande FFMPEG pour crée la video a partir du fichier text
		// resources se trouve dans le dossier webapp, m�me dossier qu'IDM.jar
		var command = "resources/ffmpeg/bin/ffmpeg.exe -f concat -safe 0 -i public/TempTextFile/"+name+".txt -y -c copy public/GeneratedVideo/"+name+".mp4"		
			
		var p = Runtime.runtime.exec(command)
		p.waitFor()

		val file = new File("public/TempTextFile/"+name+".txt")
		file.delete()
    		
		//retourne le lien de la video 
		println(name+".mp4")
	
	}
}
