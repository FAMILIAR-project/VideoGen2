import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.util.Random
import java.io.Writer
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.io.IOException

class projet {
	public static Random x
	public static  int alternativesize
	
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
						x = new Random()
						// random 50% de chance d'ajouter la video
						if (x.nextInt(100) > 50 ){
							filelist = filelist+"file '"+media.getDescription().location+"'\n"
						}
					}		
				}
				// si c'est une video alternative
				else if (media instanceof AlternativesMedia) {
					alternativesize = media.getMedias().size
					// choisi aléatoirement une video parmis les alternatives
					val md = media.getMedias().get(x.nextInt(alternativesize))
					if (md instanceof MediaDescription){
					    filelist = filelist+"file '"+md.location+"'\n"
					}
				}
			}
		}
		
		// crée un fichier texte avec les video a utilisé pour cette génération
		var Writer writer = null
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("public/temp.txt"), "utf-8"));
		    writer.write(filelist);
		} catch (IOException ex) {
		  	System.err.println("erreur d'écriture de fichier temporaire")		
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

		// créé le nom aléatoire
		var name = x.nextInt(99999999)

		//appel la commande FFMPEG pour crée la video a partir du fichier text
		var command = "ffmpeg -f concat -safe 0 -i public/temp.txt -y -c copy public/videogen/"+name+".mp4"		
		var p = Runtime.runtime.exec(command)
		p.waitFor
		
		//retourne le lien de la video 
		println("videogen/"+name+".mp4")
	
	}
}