import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.io.File
import java.util.Collections

/*
 * Affiche le nombre de variantes possibles 
 * Affiche le poid de chaque variantes
 * 
 */
class analysis {
	
	def static void main(String[] args){
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"))
		println("Auteur du fichier videogen : "+videoGen.information.authorName)		
		println("Version du fichier videogen : "+videoGen.information.version)
		
		var listpoid = newArrayList()
		listpoid.add(0l)
		
		if (videoGen instanceof VideoGeneratorModel){
			for (Media media : videoGen.getMedias()) {
				
				if (media instanceof MandatoryMedia) {
					if (media.getDescription() instanceof MediaDescription){
						val file =new File("public/OriginVideo/"+media.getDescription().location)
						val listpoidtemp = newArrayList()
						for( i : listpoid){
							listpoidtemp.add(i+file.length)
						}
						listpoid = listpoidtemp						
					}					
				}
				else if (media instanceof OptionalMedia) {
					if (media.getDescription() instanceof MediaDescription){
						val file =new File("public/OriginVideo/"+media.getDescription().location)
						val listpoidtemp = newArrayList() 
						for( i : listpoid){
							listpoidtemp.add(i+file.length)
						}
						listpoid.addAll(listpoidtemp)
					}		
				}
				else if (media instanceof AlternativesMedia) {
					var listpoidalter = newArrayList()
					for (MediaDescription medialter : media.getMedias()){
						val file =new File("public/OriginVideo/"+medialter.location)
						for( i : listpoid){
							listpoidalter.add(i+file.length)
						}
					}
					listpoid=listpoidalter
				}
			}
		}
		println(listpoid.size+" variantes possible.")
		println("Poid max variante: "+Collections.max(listpoid)+"octets soit "+(Collections.max(listpoid)/1048576f)+"mo");
		println("Poid min variante: "+Collections.min(listpoid)+"octets soit "+(Collections.min(listpoid)/1048576f)+"mo");
		println("")
		
		println("liste des poid possible des vidéos en Octets :")
		println(listpoid)
		println("")
		
		var listpoidmo = newArrayList()
		for (c : listpoid){
			listpoidmo.add(c/1048576f)
			
		}
		println("liste des poid possible des vidéos en Mega-Octets :")
		println(listpoidmo)
		println("")		
		
	}
}