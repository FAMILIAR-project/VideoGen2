import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.io.File

/*
 * Affiche le nombre de variantes possibles 
 * Affiche le poid de chaque variantes
 * 
 */
class analysis {
	
	def static void main(String[] args){
		var differenteVideo = newHashMap();
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"))
		println("Auteur du fichier videogen : "+videoGen.information.authorName)		
		println("Version du fichier videogen : "+videoGen.information.version)		
		println("")

		var Variantes= newArrayList()
		Variantes.add(new variante())

		if (videoGen instanceof VideoGeneratorModel){
			for (Media media : videoGen.getMedias()) {
				if (media instanceof MandatoryMedia) {
					if (media.getDescription() instanceof MediaDescription){
						var videoname = media.getDescription().location.split("/").get(2)
						val file =new File("public/OriginVideo/"+videoname)
						if (!differenteVideo.containsKey(videoname)){
							differenteVideo.put(videoname,file.length)
						}			
						val VariantesTemp = newArrayList()
						for( i : Variantes){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
						Variantes = VariantesTemp							
					}					
				}
				else if (media instanceof OptionalMedia) {
					if (media.getDescription() instanceof MediaDescription){
						var videoname = media.getDescription().location.split("/").get(2)
						val file =new File("public/OriginVideo/"+videoname)
						if (!differenteVideo.containsKey(videoname)){
							differenteVideo.put(videoname,file.length)
						}
						val VariantesTemp = newArrayList()
						for( i : Variantes){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
						Variantes.addAll(VariantesTemp)	
					}		
				}
				else if (media instanceof AlternativesMedia) {
					val VariantesTemp = newArrayList()
					for (MediaDescription medialter : media.getMedias()){
						var videoname = medialter.location.split("/").get(2)
						val file =new File("public/OriginVideo/"+videoname)
						if (!differenteVideo.containsKey(videoname)){
							differenteVideo.put(videoname,file.length)
						}
						for( i : Variantes){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
					}
					Variantes = VariantesTemp			
				}
			}
		}
		
		println(differenteVideo.size+" videos utilisées:")
		var it = differenteVideo.entrySet().iterator();
	    while (it.hasNext()) {
	        var pair = it.next();
	        System.out.println(pair.getKey()+ " de "+(pair.getValue()/1048576f)+" mo");
	    }
		println("")

		var maxSize = 0L
		var minSize  = 9999999999999L
		var maxduree = 0L
		var minduree = 99999999999999999L
		
		for (c : Variantes){
			if (c.TotalSize> maxSize )	{
				maxSize = c.TotalSize
			}	
			
			if (c.TotalSize < minSize )	{
				minSize = c.TotalSize
			}		
			
			if (c.Totalduree> maxduree )	{
				maxduree = c.Totalduree
			}		
			
			if (c.Totalduree < minduree )	{
				minduree = c.Totalduree
			}	
		}
		
		println(Variantes.size+" variantes possible.")
		println("Poid max variante: "+(maxSize/1048576f)+" mo");
		println("Poid min variante: "+(minSize/1048576f)+" mo");
		println("Durée max variante: "+maxduree+" secondes (non correct)");
		println("Durée min variante: "+minduree+" secondes (non correct)");
		println("")
		
		println("liste des poids possible des vidéos en Mega-Octets (estimation avec additions des poids vidéo):")
		for (c : Variantes){
			print(c.GetPoidMO()+"  ")
		}
		println("")
		println("")
				
		
	}
	
	// non fonctionnel
	static def Float getDuration(String path){
		var cmd = "ffprobe -i "+path+" -show_entries format=duration -v quiet -of csv=\"p=0\""
				println(cmd)
		
		var p = Runtime.getRuntime.exec(cmd)
	
	    var in = p.getInputStream();
	    var c =0;
	    while ((c = in.read()) != -1) {
	     println( c);
	    }
	    in.close();
		
		return 0f;
	}

}