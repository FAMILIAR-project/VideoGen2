package configs

import java.io.File

class VideoGenConfigs {
	
	static var File outPutFolder
	
	static def setOutPutFoulder(String outPutFolder){
		outPutFolder = new File(outPutFolder)
	}
	
	static def File getOutPutFoulder(){
		outPutFolder
	}
}	