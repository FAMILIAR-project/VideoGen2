import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReporter {
	
	private List<Medias> variantes;
	private Medias medias;
	
	public CSVReporter(List<Medias> variantes, Medias medias) {
		this.variantes = variantes;
		this.medias = medias;
	}
	
	//create the csv report for generated videos
	public void report() throws IOException, InterruptedException {
		String reportPath = "report.csv";
		File reportFile = new File(reportPath);
		reportFile.createNewFile();
		FileWriter fw = new FileWriter(reportFile);
		VideoGenToolBox vgtb = new VideoGenToolBox();
		List<String> mediasName = new ArrayList<>();
		List<String> mediasSize = new ArrayList<>();
		for(VideoGenMedia media : this.medias.getMedias()) {
			if(media instanceof VideoGenAlternativeMedia) {
				for(VideoGenMedia amedia : ((VideoGenAlternativeMedia)media).getAlternatives().getMedias()) {
					mediasName.add(amedia.getName());
					mediasSize.add(Long.toString(amedia.getTaille()));
				}
			} else {
				mediasName.add(media.getName());
				mediasSize.add(Long.toString(media.getTaille()));
			}
		}
		for(String names : mediasName) {
			fw.append(names+",");
		}
		fw.append("taille théorique,");
		fw.append("taille réelle,");
		fw.append("\n");
		int indiceVariante = 0;
		for(Medias variante : variantes){
			List<String> varianteMedias = variante.getMediaNames();
			for(int i = 0; i<mediasName.size(); i++) {
				if(varianteMedias.contains(mediasName.get(i))) {
					fw.append("X,");
				} else {
					fw.append(",");
				}
			}
			indiceVariante++;
			vgtb.generateMp4FileForOneVariante(variante, "destination" + indiceVariante);
			File file = new File("destination" + indiceVariante + ".mp4");
			fw.append(variante.getSize() + ",");
			fw.append(file.length()+",");
			fw.append("\n");
		}
		for(String sizes : mediasSize) {
			fw.append(sizes+",");
		}
		fw.append(";\n");
        fw.flush();
        fw.close();
	}
}
