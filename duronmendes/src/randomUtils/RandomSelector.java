package randomUtils;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoDescription;

public class RandomSelector {

	ArrayList<VideoDescription> items = new ArrayList<>();
	
    Random rand = new Random();
    int totalSum = 0;

    public RandomSelector(AlternativesMedia alternativesMedia) {

    	EList<MediaDescription> listMediaDesc = alternativesMedia.getMedias();
    	
    	for (MediaDescription mediaDescription : listMediaDesc) {
    		if(mediaDescription instanceof VideoDescription)
    		{
    			items.add((VideoDescription) mediaDescription);
    		}
		}
 
    	for(VideoDescription item : items) {
            totalSum = totalSum + item.getProbability();
        }
    }

    public int getRandom() {
        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
             sum = sum + items.get(i++).getProbability();
        }
        return Math.max(0,i-1);
    }

}
