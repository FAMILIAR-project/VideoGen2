import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription

import static org.junit.Assert.*

class VideoGenTest1 {
	val playlist = <VideoDescription>newArrayList
	val descriptions = newArrayList()
	String Playlist
	//val listPlaylist= newArrayList(Playlist)
	var List<String> msg = new ArrayList
	
	 
	int alt=0
	int opt=0
	int poss=0
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
	
	int i
	
	String saut
	
	@Test
	def void testLoadModel() {
		
		
		assertNotNull(videoGen)
		println(videoGen.information.authorName)		
		// and then visit the model
		// eg access video sequences: videoGen.videoseqs
		//getPossiility()
		
		videoGen.medias.forEach[m |
			
			if (m instanceof MandatoryMedia)
			{
				
				if(m.description instanceof VideoDescription)
				{
					//executeVLC((m.description as VideoDescription))
					//playlist.add(m.description)
				}	
			}
			if (m instanceof OptionalMedia)
			{
				//random
				//idem
				
			}
			if (m instanceof AlternativesMedia)
			{
				//random with choice
				//idem
			}
			
			
			
			
			
			
		]
		getSequences()
		
		
	}
	def getSequences(){
					saut="\n";
		videoGen.medias.forEach[m |
			
			if (m instanceof MandatoryMedia)
			{
				for(i=0;i<3;i++){
					 
					msg.add(m.description.location)
					//Playlist=msg.get(i)
					Playlist="2"
					println(Playlist+"xxxxxx")
					//Playlist=Playlist+m.description.toString
					
					//msg.set(i, Playlist)
					
					
				}
				
			}
			if (m instanceof OptionalMedia)
			{
				//random
				//idem
				
			}
			if (m instanceof AlternativesMedia)
			{
				//random with choice
				//idem
			}
			
			
			
			
			
			
		]
		println(msg)
		
		
	}
	def copmpilePlaylist(ArrayList<MediaDescription> l){
		//FileHandler f = "playlist.txt"
		for(description : descriptions){
			//l.add(description)
			
		}
		
		}	
	def int getPossiility(){
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"))
		assertNotNull(videoGen)
		println(videoGen.information.authorName)		
		//listPlaylist.add();
		videoGen.medias.forEach[m |
			
			
			if (m instanceof OptionalMedia)
			{
				//random
				//idem
				opt=opt+2
			}
			if (m instanceof AlternativesMedia)
			{
				//random with choice
				//idem
				
				alt=alt+m.medias.size()
				//println("alternative "+m.medias.size)		
				
			}	
		]
		if(opt==0) opt++
		if(alt==0) alt++
		poss=opt*alt
		//println(poss)		
		return poss
		
	}
	def compileffFile(){
		
		
		
	}
	def executeVLC(VideoDescription d){
		
		var p =Runtime.runtime.exec("vlc "+d.location)
		p.waitFor
	}
}