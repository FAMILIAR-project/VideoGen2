import org.eclipse.emf.common.util.URI
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import java.io.File

/*
 * Displays :
 * Authors/Version of the model
 * List of the input videos for the generator (dispalying the size of each video)
 * Displays the number of possibilities of generated videos
 * Max & Min size of the possibly generated video
 *  
 */
 
class Empirical_Study {
	
	def static void main(String[] args){
		
		var playList = newHashMap();		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("Videos_Elements/InputFiles/Input_ModelsPlaylist/model.videogen"))
		println("Model Author: "+videoGen.information.authorName)		
		println("Model Version : "+videoGen.information.version)		
		println("")

		var VariantesList= newArrayList()
		VariantesList.add(new variety())

		if (videoGen instanceof VideoGeneratorModel){
			for (Media media : videoGen.getMedias()) {
				if (media instanceof MandatoryMedia) {
					if (media.getDescription() instanceof MediaDescription){
						var videoname = media.getDescription().location.split("/").get(2)
						val file =new File("Videos_Elements/InputFiles/InputVideos/"+videoname)
						if (!playList.containsKey(videoname)){
							playList.put(videoname,file.length)
						}			
						val VariantesTemp = newArrayList()
						for( i : VariantesList){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
						VariantesList = VariantesTemp							
					}					
				}
				else if (media instanceof OptionalMedia) {
					if (media.getDescription() instanceof MediaDescription){
						var videoname = media.getDescription().location.split("/").get(2)
						val file =new File("Videos_Elements/InputFiles/InputVideos/"+videoname)
						if (!playList.containsKey(videoname)){
							playList.put(videoname,file.length)
						}
						val VariantesTemp = newArrayList()
						for( i : VariantesList){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
						VariantesList.addAll(VariantesTemp)	
					}		
				}
				else if (media instanceof AlternativesMedia) {
					val VariantesTemp = newArrayList()
					for (MediaDescription medialter : media.getMedias()){
						var videoname = medialter.location.split("/").get(2)
						val file =new File("Videos_Elements/InputFiles/InputVideos/"+videoname)
						if (!playList.containsKey(videoname)){
							playList.put(videoname,file.length)
						}
						for( i : VariantesList){
							VariantesTemp.add(i.clone().AddVideo(videoname,file.length))
						}
					}
					VariantesList = VariantesTemp			
				}
			}
		}
		
		println("Nombre de séquences videos utilisées pour consituer une playliste : "+playList.size)
		var it = playList.entrySet().iterator();
	    while (it.hasNext()) {
	        var pair = it.next();
	        System.out.println(pair.getKey()+ " qui pèse "+(pair.getValue()/1048576f)+" mo");
	    }
		println("")

		var maxSize = 0L
		
		var minSize  = 9999999999999L
		
		
		for (c : VariantesList){
			if (c.TotalSize> maxSize )	{
				maxSize = c.TotalSize
			}	
			
			if (c.TotalSize < minSize )	{
				minSize = c.TotalSize
			}		
		}
		
		println(VariantesList.size+" variantes possible.")
		println("Le poid maximum d'une variante : "+(maxSize/1048576f)+" mo");
		println("Le poid minimum d'une variante : "+(minSize/1048576f)+" mo");		
	}

}