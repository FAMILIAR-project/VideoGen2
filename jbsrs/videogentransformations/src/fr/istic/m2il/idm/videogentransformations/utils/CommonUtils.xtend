package fr.istic.m2il.idm.videogentransformations.utils

import java.io.File
import java.io.BufferedWriter
import java.io.IOException
import java.io.FileWriter

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
		
	}
	
	static def writeFileOnDisk(String file, String[] contents){
		
		var File f
		if(!new File(file).exists){
			f = new File(file)
			f.getParentFile().mkdirs 
			f.createNewFile
		}
		
		var fwriter = new FileWriter(f.absolutePath)
		var bwriter = new BufferedWriter(fwriter)
		
		try{
			for(content:contents){
				bwriter.write(content)
			}
		}catch(IOException e){
			
		}finally{
			try {

				if (bwriter !== null)
					bwriter.close();

				if (fwriter !== null)
					fwriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	}
}