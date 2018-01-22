import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

public class VideoGenApp {

	public static void main(String[] args) {
        VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
//        assertNotNull(videoGen);
        if (videoGen == null) {
        	System.out.println("Erreur au chargement");
			System.exit(0);
		}
        
        EList<Media> listeMedias = videoGen.getMedias();
        
        List<Taille> tailles = new ArrayList<Taille>();
        
        tailles.add(new Taille(0));
        
        for(Taille t : tailles) {
            System.out.println("taille:" + t.getValeur());
        }
        
        for (Media media : listeMedias) {
            
            if (media instanceof MandatoryMedia) {
                MandatoryMedia mand = (MandatoryMedia) media;
                MediaDescription description = mand.getDescription();
                if (description instanceof VideoDescription) {
                    
                    
                    VideoDescription vdesc = (VideoDescription) description;
                    //System.out.println("video: mandatory " + vdesc.getVideoid());

                    long octets = new File(vdesc.getLocation()).length();
                    System.out.println(vdesc.getVideoid()+" : "+octets);
                    for (Taille tt : tailles) {
                        tt.setValeur(tt.getValeur() + octets);
                    }
                    

                    /*
                     * var command = ...        
var p = Runtime.runtime.exec(command)
p.waitFor

                     */
                }
                
            }
            else if (media instanceof OptionalMedia){
                OptionalMedia opt = (OptionalMedia) media;
                MediaDescription description = opt.getDescription();
                if (description instanceof VideoDescription) {
                    Random rand = new Random();
                    int proba = rand.nextInt(100  + 1);
                    VideoDescription vdesc = (VideoDescription) description;
                    if (proba<50) {
                        
                    //System.out.println("video: optional " + vdesc.getVideoid());
                    }
                    
                    long octets = new File(vdesc.getLocation()).length();
                    System.out.println(vdesc.getVideoid()+" : "+octets);
                    List<Taille> listeRef = new ArrayList<Taille>();
                    for (Taille tt : tailles) {
                        listeRef.add(tt);
                    }
                    for (Taille tt : listeRef) {
                        tt.setValeur(tt.getValeur() + octets);
                        tailles.add(tt);
                    }
                }
            }
            else if (media instanceof AlternativesMedia) {
                AlternativesMedia alternativesMedia = (AlternativesMedia) media;
                //EList<Media> listeAltMedias = alt.getMedias();
                EList<MediaDescription> alternatives = alternativesMedia.getMedias();
                int inter = 100/alternatives.size();
                Random rand = new Random();
                int proba = rand.nextInt(100  + 1);
                int index = proba/inter;
                if (alternatives.get(index) instanceof VideoDescription) {
                    VideoDescription vdesc = (VideoDescription) alternatives.get(index);
                    //System.out.println("video: alternative " + vdesc.getVideoid());
                }
                
                for(MediaDescription altDesc : alternatives) {
                    if (altDesc instanceof VideoDescription) {
                        long octets = new File(altDesc.getLocation()).length();
                        System.out.println(((VideoDescription) altDesc).getVideoid()+" : "+octets);
                        List<Taille> listeRef = new ArrayList<Taille>();
                        for (Taille tt : tailles) {
                            listeRef.add(tt);
                        }
                        for (Taille tt : listeRef) {
                            tt.setValeur(tt.getValeur() + octets);
                            tailles.add(tt);
                        }
                    }
                }
                
            }
        }
    }
}

class Taille {
    private long valeur;

    public long getValeur() {
        return valeur;
    }

    public void setValeur(long valeur) {
        this.valeur = valeur;
    }

    public Taille(long valeur) {
        super();
        this.valeur = valeur;
    }
    
}
