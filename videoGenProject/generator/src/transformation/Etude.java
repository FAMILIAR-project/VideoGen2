package transformation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import util.Utils;

public class Etude {

	
	/**
	 * generer toutes les variantes poussibles d'un model 
	 * @param videoGen
	 * @return
	 */
	public static void getAllVariants(VideoGeneratorModel videoGen) {

		ArrayList<ArrayList<VideoDescription>> list_of_mediaList = new ArrayList<ArrayList<VideoDescription>>();

		list_of_mediaList.add(new ArrayList<VideoDescription>());
		
		ArrayList<String> list_of_videoId = new ArrayList<String>();
		
		for (Media media : videoGen.getMedias()) {
			if (media instanceof MandatoryMedia) {
				
				VideoDescription mMedia = (VideoDescription) ((MandatoryMedia) media).getDescription();
				list_of_videoId.add(mMedia.getVideoid());
				
				for (ArrayList<VideoDescription> mediaList : list_of_mediaList) {
					mediaList.add(mMedia);
				}
			}

			if (media instanceof OptionalMedia) {
				// on duplique notre list puis on ajoute media au 2em
				ArrayList<ArrayList<VideoDescription>> duplicate = clone(list_of_mediaList);
				
				VideoDescription oMedia = (VideoDescription) ((OptionalMedia) media).getDescription();
				list_of_videoId.add(oMedia.getVideoid());
				
				for (ArrayList<VideoDescription> mediaList : duplicate) {
					mediaList.add(oMedia);
				}
				list_of_mediaList.forEach(x -> System.out.println(x.toString()));
				list_of_mediaList.addAll(duplicate);
			}
			if (media instanceof AlternativesMedia) {
				// on creer autant de copie que media altirna pour chaque elms

				EList<MediaDescription> altMedias = ((AlternativesMedia) media).getMedias();
				ArrayList<ArrayList<VideoDescription>> duplicate = clone(list_of_mediaList);
				for (MediaDescription m : altMedias) {
					ArrayList<ArrayList<VideoDescription>> tmp = clone(duplicate);
					
					VideoDescription altMedia = (VideoDescription) m;
					list_of_videoId.add(altMedia.getVideoid());
					
					for (ArrayList<VideoDescription> mediaList : tmp) {
						mediaList.add(altMedia);
					}
					list_of_mediaList.addAll(tmp);
				}
			}
		}
		getSizes(list_of_mediaList, list_of_videoId);

	}

	public static void getSizes(ArrayList<ArrayList<VideoDescription>> content, ArrayList<String> entete) {
		ArrayList<Long> real_size = realSize(content);
		int id = 0;
		String csv = "id; ";
		for (String s: entete) {
			csv += s + "; ";
		}
		csv += "taille; taille reel\n";
		for (ArrayList<VideoDescription> mediaList : content) {
			Boolean[] tab = new Boolean[entete.size()];	
			double taille = 0.;
			for (VideoDescription elm: mediaList) {
				int ind = entete.indexOf(elm.getVideoid());
				tab[ind] = true;
				File file = new File(elm.getLocation());
				taille += file.length();
			}
			String ligne = (id++) +"; ";
			for (int i = 0 ; i<tab.length;i++) {
				if (tab[i] != null) ligne += "TRUE; ";
				else ligne += "FALSE; ";
			}
			
			csv += ligne + taille + "; "+ real_size.get(id-1) +"\n";
		}
		//creation du fichier
		Utils.toFile(csv, "textFiles/tab.csv");
		
	}

	//fonction auxl pour cloner une liste de VideoDescription
	static ArrayList<ArrayList<VideoDescription>> clone(ArrayList<ArrayList<VideoDescription>> l) {
		ArrayList<ArrayList<VideoDescription>> res = new ArrayList<>();

		for (ArrayList<VideoDescription> list : l) {
			ArrayList<VideoDescription> lres = new ArrayList<>();
			list.forEach(x -> lres.add(x));
			res.add(lres);
		}

		return res;
	}
	
	
	static ArrayList<Long> realSize(ArrayList<ArrayList<VideoDescription>> l){
		//file '4.mp4'
		ArrayList<Long> res = new ArrayList<>();
		for(ArrayList<VideoDescription> descList: l) {
			String variante = "";
			for(VideoDescription d:descList) {
				variante += '\n'+ "file ../" + d.getLocation();
			}
			Utils.toFile(variante, "textFiles/tmpVariante.tmp");
			try {
				String cmd = "ffmpeg -f concat -safe 0 -i " + "textFiles/tmpVariante.tmp" + " -c copy " + "generatedVideos/tmpVariante" + ".mp4";
				System.out.println(cmd);
				String[] a = new String[] {"/bin/sh", "-c", cmd};
				Runtime.getRuntime().exec(a)
						.waitFor();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			res.add(new File("generatedVideos/tmpVariante.mp4").length());
			
			try {
				String cmd = "rm generatedVideos/tmpVariante.mp4";
				String[] b = new String[] {"/bin/sh", "-c", cmd};
				Runtime.getRuntime().exec(b).waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return res;
		
	}

}
