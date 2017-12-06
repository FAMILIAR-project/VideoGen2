package loic_beaulieu_xtext_project.java.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import loic_beaulieu_xtext_project.java.media.IdFile;
import loic_beaulieu_xtext_project.java.media.VarianteVideo;
import loic_beaulieu_xtext_project.java.media.video.IdVideo;


public class CalculeVariante {
		
	private List<VarianteVideo> listVariante2;
	
	private List<IdFile> listItem;

	public CalculeVariante() {
		listItem = new ArrayList<>();
		
		listVariante2 = new ArrayList<>();
		listVariante2.add(new VarianteVideo());
	}
	

	
	private List<VarianteVideo> copyOfListVariante(List<VarianteVideo> toCopy){
		List<VarianteVideo> copy = new ArrayList<>();
		for (VarianteVideo varianteVideo : toCopy) {
			copy.add(varianteVideo.copy());
		}
		return copy;
	}
	
	
	public void addMandatoryVideo(IdFile video) {
		listItem.add(video);		
		for (VarianteVideo variante : listVariante2) {
			variante.addFile(video);
		}
	}
	
	public void addOptionnalVideo(IdFile video) {
		listItem.add(video);
				
		List<VarianteVideo> copy = copyOfListVariante(listVariante2);
		for (VarianteVideo variante : listVariante2) {
			variante.setProba(variante.getProba()*(1-video.getProba()));
		}
		
		for (VarianteVideo variante : copy) {
			variante.addFile(video);
			variante.setProba(variante.getProba()*(video.getProba()));
		}
		listVariante2.addAll(copy);
	}
	
	
	public void addAlternativeVideo(List<IdFile> listAlternative) {
		List<VarianteVideo> originVarianteVideo = copyOfListVariante(listVariante2);
		listVariante2 = new ArrayList<>();
		for (IdFile alternativeVideo : listAlternative) {
			List<VarianteVideo> copyWithAlternative = copyOfListVariante(originVarianteVideo);
			for (VarianteVideo variante : copyWithAlternative) {
				variante.addFile(alternativeVideo);
				variante.setProba(variante.getProba()*(alternativeVideo.getProba()));
			}
			listVariante2.addAll(copyWithAlternative);
		}		
	}
	
	public String toString() {
		String aRetourner = "Calcul variante : \n";
		aRetourner += "Liste des id : ";
		
		for (IdFile idPathVideo : listItem) {
			aRetourner += idPathVideo.getId()+" ";
		}
		aRetourner += "\n";
		
		aRetourner+="\nlistVariante=\n";
		
		for (VarianteVideo variante : listVariante2) {
			aRetourner+=variante+"\n";
		}
		
		return aRetourner;
	}
	
	

	
	public VarianteVideo getRandomVariante() {
		double rand = ((double) (new Random().nextInt(101)+1))/100;
		double acc = 0;
		for (VarianteVideo variante : listVariante2) {
			acc += variante.getProba();
			
			if (rand <= acc) {
				return variante;
			}
		}
		return listVariante2.get(listVariante2.size()-1);
		
	}
	
}
