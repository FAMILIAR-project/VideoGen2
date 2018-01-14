package loic_beaulieu_xtext_project.java.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import loic_beaulieu_xtext_project.java.media.IdFile;
import loic_beaulieu_xtext_project.java.media.VarianteVideo;
import loic_beaulieu_xtext_project.java.media.video.IdVideo;


/**
 * Classe qui permet d'utilisé l'algo de génération de variante de video en fonction de plusieurs video.
 * @author loic
 *
 */
public class CalculeVariante {
		
	private List<VarianteVideo> listVariante2;
	
	private List<IdFile> listItem;

	public CalculeVariante() {
		listItem = new ArrayList<>();
		
		listVariante2 = new ArrayList<>();
		listVariante2.add(new VarianteVideo());
	}
	

	/**
	 * copy profonde d'une liste de variante de vidéo
	 * @param toCopy
	 * @return
	 */
	private List<VarianteVideo> copyOfListVariante(List<VarianteVideo> toCopy){
		List<VarianteVideo> copy = new ArrayList<>();
		for (VarianteVideo varianteVideo : toCopy) {
			copy.add(varianteVideo.copy());
		}
		return copy;
	}
	
	/**
	 * ajoute une video/image obligatoire. consiste juste à ajouté la video dans toute les possibilité possible
	 * @param video
	 */
	public void addMandatoryVideo(IdFile video) {
		listItem.add(video);		
		for (VarianteVideo variante : listVariante2) {
			variante.addFile(video);
		}
	}
	
	/**
	 * ajoute une video/image optionnel. Cela aura pour effet de cloné la liste des possibilité de variante et à y rajouté cette video optionel tout en modifiant les probabilité
	 * @param video
	 */
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
	
	/**
	 * ajoute une liste video/image alternative. Cela consiste à cloné la liste des possibilité pour chaque alternative et à y rajouter la video tout en modifiant les probabilités en conséquence
	 * @param listAlternative
	 */
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
	
	

	/**
	 * Retourne une variante possible proportionel à sa probabilité d'apparition
	 * @return
	 */
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
