package fr.istic.idm.antoinec.service.videogenServices;

import fr.istic.idm.VideoGenCompiler;
import fr.istic.idm.antoinec.VideoGenWebApp;
import fr.istic.idm.antoinec.domain.Media;
import fr.istic.idm.antoinec.domain.VideoGen;
import fr.istic.idm.exception.InvalidVideoGenGrammarException;
import fr.istic.idm.model.Variante;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideogenCompilerService {

    private static final Logger log = LoggerFactory.getLogger(VideogenCompilerService.class);
    /**
     * Retourne une liste d'entité Media représentant les fichiers Images et vidéos requis par le fichier .videogen pour sont bon fonctionnement
     * @param videoGen
     * @return
     */
    public List<Media> retrieveAllRequiredMedias(VideoGen videoGen) throws FileNotFoundException {

        List<Media> medias = new ArrayList<>();

        VideoGenCompiler compiler = new VideoGenCompiler(FileUtils.getFile(VideoGenService.UPLOAD_DIR, videoGen.getId().toString()).getPath(), videoGen.getId().toString() + ".videogen");

        compiler.generateVariantes();

        for(String filename : compiler.getVariantes().getAllMediaFilenames()) {
            medias.add(new Media(filename));
        }

        return medias;
    }

    public File getVariante(VideoGen videoGen) throws InvalidVideoGenGrammarException {
        VideoGenCompiler compiler = new VideoGenCompiler(FileUtils.getFile(VideoGenService.UPLOAD_DIR, videoGen.getId().toString()).getPath(), videoGen.getId().toString() + ".videogen");
        Variante variante = compiler.generateVariante();
        return variante.compile();
    }
}
