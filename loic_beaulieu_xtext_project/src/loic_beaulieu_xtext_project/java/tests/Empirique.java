package loic_beaulieu_xtext_project.java.tests;

import java.io.IOException;

import org.junit.Test;

import loic_beaulieu_xtext_project.java.Main;
import loic_beaulieu_xtext_project.java.exception.BadProba;

public class Empirique {
	
	@Test
	public void generationEmpirique1() {
		try {
			Main.genererVideo("videogenFile/empirique/empirique1.videogen");
		} catch (BadProba | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void generationEmpirique1Inverse() {
		try {
			Main.genererVideo("videogenFile/empirique/empirique1inverse.videogen");
		} catch (BadProba | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void generationEmpirique2() {
		try {
			Main.genererVideo("videogenFile/empirique/empirique2.videogen");
		} catch (BadProba | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void generationEmpirique2Inverse() {
		try {
			Main.genererVideo("videogenFile/empirique/empirique2inverse.videogen");
		} catch (BadProba | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void generationEmpirique3() {
		try {
			Main.genererVideo("videogenFile/empirique/empirique3.videogen");
		} catch (BadProba | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
