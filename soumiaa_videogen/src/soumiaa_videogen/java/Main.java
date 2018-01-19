package soumiaa_videogen.java;
import java.io.IOException;
import java.util.List;

import soumiaa_videogen.java.exception.BadProba;
import soumiaa_videogen.java.media.IdFile;
import soumiaa_videogen.java.utils.CalculeVariante;
import soumiaa_videogen.java.utils.GenerationVideo;

public class Main {

	public static void main(String[] args) {
		try {
			GenerationVideo gen = new GenerationVideo("videogenFile/Examples/example.videogen");
			CalculeVariante cal = gen.generateVariante();
			System.out.println(cal);
			System.out.println("variante choisis: \n"+cal.getRandomVariante().generateVideo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String genererVideo(String textVideoGen) throws BadProba, IOException {
		GenerationVideo gen = new GenerationVideo(textVideoGen);
		CalculeVariante cal = gen.generateVariante();
		
		return cal.getRandomVariante().generateVideo();
	}
}
