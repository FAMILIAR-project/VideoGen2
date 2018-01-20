package fr.istic.idm.antoinec.service;

import fr.istic.idm.antoinec.domain.VideoGen;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class VideoGenService {
    private static Logger log = LoggerFactory.getLogger(VideoGenService.class);


    @Autowired
    private FileUploader uploader;

    /**
     * Crée l'arborescence de fichiers requise à une entité Videogen
     * @param videoGen
     * @return
     */
    public boolean createNewVideogenFileStructure(VideoGen videoGen) {
        if(videoGen.getFile() == null) {
            log.error("Aucun fichier videogen reçu");
            return false;
        }

        String name = videoGen.getName();

        File directory = FileUtils.getFile(System.getProperty("user.dir"), FilenameUtils.normalize(videoGen.getId() + '-' + name));

        if(!directory.mkdir()) {
            log.error("Impossible de créer le dossier " + directory.getPath());
            return false;
        }

        if(!uploader.upload(videoGen.getFile(), FileUtils.getFile(directory, FilenameUtils.normalize(name)))) {
            return false;
        }

        // TODO: Here Change structure of File
        for(String media : videoGen.getMedias()) {
            if(!uploader.upload(videoGen.getFile(), FileUtils.getFile(directory, FilenameUtils.normalize(name)))) {
                return false;
            }
        }
        return true;
    }
}
