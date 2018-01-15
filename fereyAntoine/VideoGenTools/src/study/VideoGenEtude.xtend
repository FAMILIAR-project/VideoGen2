package study

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.Media
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import generator.Util

class VideoGenEtude {
	
	var listeId = new ArrayList<String>();
	
	var util = new Util();
	/**
	 * Liste toutes les variantes
	 */
	def Integer varianteModel(VideoGeneratorModel videoGen,String path) {
		listeId = new ArrayList<String>();
		var nb_variante = 1;

		var variante = new ArrayList<List<VideoDescription>>;

		variante.add(new ArrayList())
		for (Media media : videoGen.medias) {

			if (media instanceof MandatoryMedia) {
				if (media.description instanceof VideoDescription) {
					listeId.add((media.description as VideoDescription).videoid);

					for (List<VideoDescription> liste : variante) {
						liste.add((media.description as VideoDescription));
					}
				}

			} else if (media instanceof OptionalMedia) {
				if (media.description instanceof VideoDescription) {

//					nb_variante = nb_variante * 2

					listeId.add((media.description as VideoDescription).videoid);
					var temp = new ArrayList<List<VideoDescription>>();
					for (List<VideoDescription> listetemp : variante) {
						temp.add(new ArrayList<VideoDescription>(listetemp));
					}

					for (List<VideoDescription> liste : variante) {
						liste.add((media.description as VideoDescription));
					}

					variante.addAll(temp);

				}

			} else if (media instanceof AlternativesMedia) {

//				nb_variante = nb_variante * (media as AlternativesMedia).medias.size;

				variante = VarianteAlternative((media as AlternativesMedia).medias, variante);

			}

		}
		
		//assertEquals(nb_variante, variante.size);

		var i = 0;
		for (List<VideoDescription> liste : variante) {
			i++;
			util.generateVideo(liste, "/tmp/resultat" + i + ".mp4");
			util.generateGif("/tmp/resultat" + i + ".mp4","/tmp/resultat" + i + ".gif");
		}
		writeCSV(path, variante, nb_variante);
		
		return variante.size;
	}
	/**
	 * Variante pour les alternatives
	 */
	def private ArrayList<List<VideoDescription>> VarianteAlternative(EList<MediaDescription> descriptions,
		ArrayList<List<VideoDescription>> variante) {
		var newlist = new ArrayList<List<VideoDescription>>();

		for (MediaDescription media : descriptions) {
			listeId.add((media as VideoDescription).videoid);
			// copy
			var temp = new ArrayList<List<VideoDescription>>();
			for (List<VideoDescription> listetemp : variante) {
				temp.add(new ArrayList<VideoDescription>(listetemp));
			}
			//
			if (media instanceof VideoDescription) {

				for (List<VideoDescription> liste : temp) {
					liste.add((media));
				}
				newlist.addAll(temp);

			}
		}
		return newlist;

	}
/**
	 * ecrit une playlist
	 */
	def private String writeCSV(String filename, ArrayList<List<VideoDescription>> variante,
		int nb_variante) throws IOException {
		var string = "";
		val writer = new BufferedWriter(new FileWriter(filename));
		//val reader = new BufferedReader(new FileReader(filename));
		var i = 0;
//		writer.write("id;")
		for (String id : listeId) {
			writer.write(id + ";");
		}

		writer.write("size;realsize;gifsize\n")

		for (List<VideoDescription> liste : variante) {
			i++
//			writer.write(i + ";");
			for (String id : listeId) {
				var in = false;
				for (VideoDescription media : liste) {
					if (media.videoid.equals(id)) {
						in = true;
					}
				}
				writer.write(in.toString().toUpperCase() + ";");
			}
			var size = 0l;
			for (VideoDescription media : liste) {
				var path = media.location.replaceFirst("^~",System.getProperty("user.home"));
				println(path)
				var file = new File(path);
				size = size + file.length;
			}
			writer.write(size + ";");
			writer.write(new File("/tmp/resultat" + i + ".mp4").length + ";");
			writer.write(new File("/tmp/resultat" + i + ".gif").length + ";\n");
		}

		writer.close();

//		while ((motLu = reader.readLine()) !== null) {
//			compteur++;
//
//		}
		
		return string;
	}
	
	/**
	 * la variante la plus longue
	 */
	def void maxVarianteModel(VideoGeneratorModel videoGen) {

		
		var result = new Float(0f);
		for (Media media : videoGen.medias) {

			if (media instanceof MandatoryMedia) {
				if (media.description instanceof VideoDescription) {

					result = result + util.getDuration((media.description as VideoDescription));

				}

			} else if (media instanceof OptionalMedia) {
				if (media.description instanceof VideoDescription) {
					result = result + util.getDuration((media.description as VideoDescription));

				}

			} else if (media instanceof AlternativesMedia) {
				result = result + maxVarianteAlternative((media as AlternativesMedia).medias);

			}
		}
		println("duration max : " + result);
	}

	/**
	 * la longeure de la variante la plus longue
	 */
	def private Float maxVarianteAlternative(EList<MediaDescription> descriptions) {
		var max = new Float(0f);
		for (MediaDescription media : descriptions) {
			if (media instanceof VideoDescription) {
				val video = (media as VideoDescription);
				var temp = util.getDuration(video);
				if (temp > max) {

					max = temp;

				}
			}
		}

		return max;

	}
}
