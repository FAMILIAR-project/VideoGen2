package fr.istic.m2il.idm.videogentransformations.configs

import java.io.File

class VideoGenConfigs {
	
	static var File outPutFolder
	static var int[] gifResolutions = newIntArrayOfSize(2)
	static var String serverIP = "localhost:8080/"
	
	static def void setOutPutFoulder(String outPut){
		outPutFolder = new File(outPut)
		if(!outPutFolder.exists)
			outPutFolder = null
	}
	
	static def File getOutPutFoulder(){
		outPutFolder
	}
	
	static def void setGifResolutions(int width, int heigth){
		gifResolutions.set(0, width)
		gifResolutions.set(1, heigth)
	}
	
	static def int[] getGifResolutions(){
		gifResolutions
	}
	
	static def void setServerIP(String ip){
		serverIP = ip
	}
	
	static def String getServerIP(){
		serverIP
	}
	
	static def void initSubOutPutFolders(){
		
		if(outPutFolder !== null){
			new File(outPutFolder.absolutePath + "/filtered/").mkdir
			new File(outPutFolder.absolutePath + "/resizes/").mkdir
			new File(outPutFolder.absolutePath + "/gifs/").mkdir
			new File(outPutFolder.absolutePath + "/thumbs/").mkdir
			new File(outPutFolder.absolutePath + "/playlists/").mkdir
		}
	}
}	