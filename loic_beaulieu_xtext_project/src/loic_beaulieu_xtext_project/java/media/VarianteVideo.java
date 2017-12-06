package loic_beaulieu_xtext_project.java.media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VarianteVideo {

	private double proba;
	private List<IdFile> listIdFile;
	
	public VarianteVideo() {
		proba = 1;
		listIdFile = new ArrayList<>();
	}
	
	public void addFile(IdFile file) {
		listIdFile.add(file);
	}

	public double getProba() {
		return proba;
	}

	public void setProba(double proba) {
		this.proba = proba;
	}

	@Override
	public String toString() {
		return "VarianteVideo [proba=" + proba + ", listIdFile=" + listIdFile + "]";
	}
	
	public VarianteVideo copy() {
		VarianteVideo copy = new VarianteVideo();
		
		for (IdFile idFile : listIdFile) {
			copy.addFile(idFile);
		}
		copy.proba = this.proba;
		
		return copy;
	}
	
	public String generateVideo() throws IOException {
		
		for (IdFile idFile : listIdFile) {
			File file = new File(idFile.getPath());
			if (!file.isFile()) {
				throw new IOException(file.getAbsolutePath()+" is not a file");
			}
		}
		
		
		
		
		return "";
	}
}
