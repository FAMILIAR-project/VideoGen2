package configs

import java.io.File

class VideoGenConfigs {
	
	static var File outPutFolder
	static var int[] gifResolutions = newIntArrayOfSize(2)
	static var String serverIP = ""
	
	static def void setOutPutFoulder(String outPutFolder){
		outPutFolder = new File(outPutFolder)
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
}	