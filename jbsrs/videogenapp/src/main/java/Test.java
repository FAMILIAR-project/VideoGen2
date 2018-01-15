import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import java.io.File;

/**
 * @author ismael
 */

public class Test {

    public static void main(String[] args){

        /*VideoGenConfigs.setOutPutFoulder("data/output");
        VideoGenConfigs.setServerIP("http://localhost:8080/");
        VideoGenConfigs.setGifResolutions(190, 60);
        VideoGenConfigs.initSubOutPutFolders();
       // log.info("OUTput ", VideoGenConfigs.getOutPutFoulder().getAbsolutePath());
        //String specificationpath = this.videoGenService.getRandomVideoGenSpecification();
        VideoGeneratorModel videoGeneratorModel =
            new VideoGenHelper().loadVideoGenerator(org.eclipse.emf.common.util.URI.createURI("data/input/videogen/sample9.videogen"));
        //new File(getClass().getClassLoader().getResource(specificationpath).toURI()).getAbsolutePath().toString()));
        String playlist = VideoGenPlayTransformations.generateRandomPlayList(videoGeneratorModel);*/
        File f = new File(Test.class.getResource("data/output/file.txt").getPath());

        System.err.println(f.getAbsolutePath());
    }
}
