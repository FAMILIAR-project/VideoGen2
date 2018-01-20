import java.io.BufferedWriter
import java.util.Random
import java.io.IOException
import java.io.Writer
import java.io.OutputStreamWriter
import java.io.FileOutputStream

class FileWriter {
/*
 * @param : filelist which contains the location of the videos
 * @return : the name of the txt file that contains filelist content, the txt file will be in the accepted format
 * for ffmpeg
 */
static def String FileWriter(String filelist) {

	// generation of a Random txt filename
	var name = "video" + new Random().nextInt(1000).toString()

	// write the location of each video in the txt file
	var Writer writer = null
	try {
		writer = new BufferedWriter(
			new OutputStreamWriter(new FileOutputStream("Videos_Elements/InputFiles/Input_TextPlaylist/"+name + ".txt"), "utf-8"))
		writer.write(filelist)
		writer.close()
	} catch (IOException ex) {
	} finally {
		try {
		} catch (Exception ex) { 
		}
	}
return name;
}		

}
