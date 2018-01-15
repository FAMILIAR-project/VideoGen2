package utils

import java.io.File

class CommonUtils {
	
	static def String getOutPutFileName(String filename){
		var fileoutputIndex = 0;
		val file = filename.replace(".", "@").split("@");
        var outputFile =  file.get(0) + "_"+ fileoutputIndex +"." + file.get(1)
        while(new File(outputFile).exists){
        	fileoutputIndex++;
        	outputFile =  file.get(0) + "_"+ fileoutputIndex +"." + file.get(1)
        }
        outputFile
	}
	
	static def String writeFile(String file){
		//traduction xtend de java
	}
}