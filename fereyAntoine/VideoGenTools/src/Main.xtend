import generator.VideoGenHelper
import study.VideoGenEtude

class Main {
	def static void main(String[] args) {
//		val video = new VideoGenHelper().loadVideoGenerator("exemple.videogen")
//		var generetor = new Generator();
//		
//		if(generetor.hasError(video)){
//			return;
//		}
//		
//		println("Step 1 : file is okay");
//		generetor.generateVignette(video);
//		//generetor.generateGif(video,"/home/aferey/Documents/IDM/Video/resultat1.gif");
//		generetor.generate(video,"/home/aferey/Documents/IDM/Video/resultat1.mp4");
		
		// etude 
		var etude = new VideoGenEtude();
		val video1 = new VideoGenHelper().loadVideoGenerator("videogenEtude/example1.videogen");
		etude.varianteModel(video1,"example1.csv");
			
		val video2 = new VideoGenHelper().loadVideoGenerator("videogenEtude/example2.videogen");
		etude.varianteModel(video2,"example2.csv");
			
		val video3 = new VideoGenHelper().loadVideoGenerator("videogenEtude/example3.videogen");
		etude.varianteModel(video3,"example3.csv");
	
		
	}
}