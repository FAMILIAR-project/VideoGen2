package fr.istic.idm.model.mediasequence.visitors;

public class VisitorFactory {
	
	/**
	 * Create and return an instance of VideoGenCompilerVisitor: (this method can be extend to choose against available commands: ffmpeg or vlc)
	 * @return VideoGenCompilerVisitor
	 */
	public static VideoGenCompilerVisitor createCompilerVisitor() {
		return createFFMPEGMediaSequenceVisitor();
	}
	
	
	/**
	 * Create and return an instance of FFMPEGMediaSequenceVisitor
	 * @return FFMPEGMediaSequenceVisitor
	 */
	public static FFMPEGMediaSequenceVisitor createFFMPEGMediaSequenceVisitor() {
		return new FFMPEGMediaSequenceVisitor();
	}
}
