package loic_beaulieu_xtext_project.java;
import java.io.IOException;
import java.util.List;

import loic_beaulieu_xtext_project.java.media.IdFile;
import loic_beaulieu_xtext_project.java.utils.CalculeVariante;
import loic_beaulieu_xtext_project.java.utils.GenerationVideo;

public class Main {

	public static void main(String[] args) {
		try {
			GenerationVideo gen = new GenerationVideo("videogenFile/proba/proba_alternative_1.videogen");
			CalculeVariante cal = gen.generateVariante();
			System.out.println(cal);
			System.out.println("variante choisis: \n"+cal.getRandomVariante());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
