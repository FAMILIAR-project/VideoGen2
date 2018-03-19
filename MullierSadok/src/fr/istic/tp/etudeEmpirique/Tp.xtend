import fr.istic.sadokmullier.idm.VideoGenHelper
import java.io.File
import java.util.ArrayList
import java.util.HashMap
import java.util.Map
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

import static org.junit.Assert.*

class Tp {
	
	var myMap = new HashMap<Integer, String>
	var sizeList = new ArrayList<Long>
	
	var size = 0
	var header = ""
	val ctTrue= "True"
	val ctFalse = "False"
	
	@Test
	def void testLoadModel() {
		
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"))
		assertNotNull(videoGen)
		
		checkVideoGen(videoGen)		
		
		videoGen.medias.forEach[ m |  
			if(m instanceof MandatoryMedia) { 
	 			addMandatory(m)
	      	} 
	       
	      	if(m instanceof OptionalMedia) { 
				addOptional(m)
	     	 } 
	       
	      	if(m instanceof AlternativesMedia) { 
	         	addAlternative(m)
		      } 
		]
		
		val headers = header.split(";")
		
		myMap.forEach[p1, p2|
			var totalSize = 0L
			var array  = p2.split(";")
			for(var i=0; i<array.length;i++){
				if(array.get(i).equals(ctTrue)){
					totalSize = sizeList.get(i) + totalSize
				}
			}
			myMap.put(p1,myMap.get(p1) + totalSize)
		]
		assertEquals(nbVariante(videoGen), myMap.size)
		displayCSVMap
	}
	
	/**
	 * Vérifie le model
	 * s'il n'est pas valide une exception sera levé
	 */
	def checkVideoGen(VideoGeneratorModel model) {
		model.medias.forEach[ m |  
			if(m instanceof MandatoryMedia) {
	 			if(!checkFileExists(m.description.location)){
	 				throw new IllegalArgumentException("Le fichier "+ m.description.location + "n'existe pas")
	 			}
	      	} 
	       
	      	if(m instanceof OptionalMedia) { 
				if(!checkFileExists(m.description.location)){
	 				throw new IllegalArgumentException("Le fichier "+ m.description.location + "n'existe pas")
	 			}
	     	 } 
	       
	      	if(m instanceof AlternativesMedia) {
	      		var totalProb = 0
	      		for(MediaDescription mm : m.medias){
	      			if(!checkFileExists(mm.location)){
		 				throw new IllegalArgumentException("Le fichier "+ mm.location + "n'existe pas")
		 			}
		 			
		 			//Check probability
		 			if(mm instanceof VideoDescription){
		 				totalProb = (mm as VideoDescription).probability + totalProb
		 			}
	      		 }
	      		 if(totalProb!=1){
	      		 	throw new IllegalArgumentException("La somme des probabilités ne fait pas 1")
	      		 }
		    } 
		]
		
	}
	/**
	 * @return:  Vrai si le fichier existe
	 */
	def boolean checkFileExists(String path){
		val file = new File(path)
		return file.exists
	}
	/**
	 * génère une vriante avec la video mandatory
	 */
	def void addMandatory(MandatoryMedia m) {
		initMap()
		if(m.description instanceof VideoDescription) {
			val videoDesc = m.description as VideoDescription
			header += videoDesc.videoid + ";"
			addSizeToList(videoDesc.location)
			
			myMap.forEach[p1, p2| 
				myMap.put(p1, p2 + ctTrue +  ";")
			]
		}
	}
	/**
	 * génère une vriante avec la video optionel
	 */
	def void addOptional(OptionalMedia m) {
		initMap()
		if(m.description instanceof VideoDescription) {
			val videoDesc = m.description as VideoDescription
			header += videoDesc.videoid + ";"
			addSizeToList(videoDesc.location)
			val oldSize = size
			dupliqueMap()
			myMap.forEach[p1, p2| 
				if (p1 <= oldSize){
					myMap.put(p1, p2 + ctTrue +  ";")
				}
				else {
					myMap.put(p1, p2 + ctFalse +  ";")
				}
			]
		}
	}
	/**
	 * génère une vriante avec les video alternatives
	 */
	def void addAlternative(AlternativesMedia m) {
		initMap()
		if(m instanceof AlternativesMedia) { 
	        val taille = m.medias.size
	        val oldSize = size
	        var index = 1
			dupliqueMap(taille-1) 
	        for(MediaDescription media : m.medias){
	          if(media instanceof VideoDescription){ 
             	header += media.videoid + ";"
             	addSizeToList(media.location)
             	for(Map.Entry<Integer, String> entry : myMap.entrySet()){
             		var p1 = entry.key
             		var p2 = entry.value
					if ((p1 <= (oldSize * index)) && (p1 > (oldSize * (index-1)))){
						myMap.put(p1, p2 + ctTrue +  ";")
					}
					else {
						myMap.put(p1, p2 + ctFalse +  ";")
					}
             	}
             	index += 1
	          }
	        } 
	      } 
		
	}
	/**
	 * duplique la map une fois
	 */
	private def void dupliqueMap(){
		dupliqueMap(1)
	}
	/**
	 * duplique la map n fois
	 */
	private def void dupliqueMap(int n){
		val newMap = new HashMap<Integer, String>
		
		for(var i = 0;i<n;i++){
			myMap.forEach[p1, p2|
				size += 1
				newMap.put(p1,p2)
				newMap.put(size, p2)
			]
		}
		myMap = newMap	
	}
	
	/**
	 * init de la map
	 */
	private def void initMap() {
		if (size == 0) {
			myMap.put(size+1, "")
			size = 1
		}
	}
	
	/**
	 * affiche la map
	 */
	private def void displayCSVMap(){
		println(header)
		myMap.forEach[p1, p2|
			println(p1 + " -> " + p2)
		]
	}
	
	/**
	 * Tp4 nbVariante
	 */
	def int nbVariante(VideoGeneratorModel videoGen) {
		var nb = 0
		for (Media m : videoGen.medias) {
			if(nb == 0){
				nb = 1
			}
			if(m instanceof MandatoryMedia) { 
	      	} 
	       
	      	if(m instanceof OptionalMedia) { 
				nb *=2
	     	 } 
	       
	      	if(m instanceof AlternativesMedia) { 
	         	nb *= m.medias.size
		      } 
		}
		nb
	}
	
	/**
	 * ajout de la taille 
	 */
	def void addSizeToList(String pathFile){
		val file =new File(pathFile);
		if(file.exists()){
			sizeList.add( new Long(file.length()))
		}
		else{
			println("/!\\ Le fichier est vide")
		}		
	}
}