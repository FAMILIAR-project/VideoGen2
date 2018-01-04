package fr.istic.idm.model.mediasequence.visitors;

public class VisitorFactory {
	
	public static FFMPEGMediaSequenceVisitor createFFMPEGMediaSequenceVisitor() {
		return new FFMPEGMediaSequenceVisitor();
	}
}
