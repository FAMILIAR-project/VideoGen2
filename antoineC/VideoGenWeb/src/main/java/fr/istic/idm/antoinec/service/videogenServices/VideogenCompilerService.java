package fr.istic.idm.antoinec.service.videogenServices;

import fr.istic.idm.antoinec.domain.Media;
import fr.istic.idm.antoinec.domain.VideoGen;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideogenCompilerService {

    /**
     * Retourne une liste d'entité Media représentant les fichiers Images et vidéos requis par le fichier .videogen pour sont bon fonctionnement
     * @param videoGen
     * @return
     */
    public List<Media> retrieveAllRequiredMedias(VideoGen videoGen) throws FileNotFoundException {
        List<Media> medias = new ArrayList<>();


        return medias;
    }
}
