package fr.istic.idm.model.mediasequence.visitors;

public class VisitorFactory {
	
	/**
	 * Create and return an instance of VideoGenCompilerVisitor: (this method can be extend to choose against available commands: ffmpeg or vlc)
	 * @return VideoGenCompilerVisitor
	 */
	public static VideoGenCompilerVisitor createCompilerVisitor(VarianteInformationsVisitor visitor) {
		return createFFMPEGMediaSequenceVisitor(visitor);
	}
	
	public static VarianteInformationsVisitor createInformationsVisitor() {
		return new VarianteInformationsVisitor();
	}
	
	public static VariantesInformationsVisitor createVariantesInformationsVisitor() {
		return new VariantesInformationsVisitor();
	}
	
	/**
	 * Create and return an instance of FFMPEGMediaSequenceVisitor
	 * @return FFMPEGMediaSequenceVisitor
	 */
	public static FFMPEGMediaSequenceVisitor createFFMPEGMediaSequenceVisitor(VarianteInformationsVisitor visitor) {
		return new FFMPEGMediaSequenceVisitor(visitor);
	}
	
	public static MediaFilenameMediaSequenceVisitor createMediaFilenameMediaSequenceVisitor() {
		return new MediaFilenameMediaSequenceVisitor();
	}
}
