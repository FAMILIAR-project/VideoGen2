package fr.istic.idm.antoinec.service.videogenServices;

import fr.istic.idm.antoinec.domain.Media;
import fr.istic.idm.antoinec.domain.VideoGen;
import fr.istic.idm.antoinec.web.rest.errors.BadRequestAlertException;
import fr.istic.idm.antoinec.web.rest.errors.InternalServerErrorException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

@Service
public class VideoGenService {
    public static final File UPLOAD_DIR = FileUtils.getFile(System.getProperty("user.dir"), "VIDEOGEN_UPLOAD");

    private static Logger log = LoggerFactory.getLogger(VideoGenService.class);
    private final VideogenCompilerService compilerService;

    public VideoGenService(VideogenCompilerService compilerService) {
        this.compilerService = compilerService;

        if(!UPLOAD_DIR.exists()) {
            try {
                FileUtils.forceMkdir(UPLOAD_DIR);
            } catch (IOException e) {
                throw new InternalServerErrorException("The server doesn't have sufficient right to create directory " + UPLOAD_DIR.getAbsolutePath());
            }

        }
    }

    /**
     * Hydrate l'objet Videogen avec les médias requis et un boolean pour chaque médias indiquant si il existe ou non sur le serveur
     * @param videoGen
     */
    public void hydrateWithFileHierarchy(VideoGen videoGen) {
        File directory = FileUtils.getFile(UPLOAD_DIR, FilenameUtils.normalize(videoGen.getId().toString()));

        if(!directory.exists())
            throw new InternalServerErrorException("Impossible de récupéré les fichiers sur le serveur");

        try {
            videoGen.setMedias(compilerService.retrieveAllRequiredMedias(videoGen));
            log.info("Media size: {}", videoGen.getMedias().size());

            FileUtils.listFiles(directory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE).forEach(new Consumer<File>() {
                @Override
                public void accept(File file) {

                    for(Media media : videoGen.getMedias()) {
                        if(media.getFilename().equals(file.getName())) {
                            videoGen.getMedias().get(videoGen.getMedias().indexOf(media)).setPresence(true);
                        }
                    }
                }
            });
        } catch (FileNotFoundException e) {
            throw new InternalServerErrorException("Impossible d'accéder à un fichier, peut-être un problème de droits");
        }


    }

    public void persistChanges(VideoGen videoGen, MultipartFile videogenFile, MultipartFile[] assets) {
        File directory = FileUtils.getFile(UPLOAD_DIR, FilenameUtils.normalize(videoGen.getId().toString()));

        if(!directory.exists()) {
            create(videoGen, directory, videogenFile, assets);
        } else {
            update(videoGen, directory, videogenFile, assets);
        }
    }

    private void create(VideoGen videoGen, File directory, MultipartFile videogenFile, MultipartFile[] assets) {
        log.info("Create File hierarchy for new Videogen entity");
        try {
            FileUtils.forceMkdir(directory);
        } catch (IOException e) {
            throw new InternalServerErrorException("The server doesn't have sufficient right to create directory " + directory.getAbsolutePath());
        }

        try {
            videogenFile.transferTo(FileUtils.getFile(directory, FilenameUtils.normalize(videoGen.getId().toString() + ".videogen")));

            for(MultipartFile asset : assets) {
                log.info("Transfer file: {} {} {}", asset.getOriginalFilename(), asset.getName(), asset.getSize());
                asset.transferTo(FileUtils.getFile(directory, FilenameUtils.normalize(asset.getOriginalFilename())));
            }
        } catch (IOException e) {
            throw new BadRequestAlertException("Impossible d'enregistré la grammaire", VideoGen.class.getName(), "ERROR");
        }

        return;
    }

    public void delete(Long id) {
        log.info("Delete File hierarchy for videogen id");
        File directory = FileUtils.getFile(UPLOAD_DIR, id.toString());

        if(directory.exists()) {
            try {
                FileUtils.forceDelete(directory);
            } catch (IOException e) {
                throw new InternalServerErrorException("Impossible de supprimer les fichiers lié à l'entité VideoGen en cours de suppression");
            }
        }
    }
    private void update(VideoGen videoGen, File directory, MultipartFile videogenFile, MultipartFile[] assets) {
        log.info("Update File hierarchy for given Videogen entity");
        try {
            for(MultipartFile asset : assets) {
                File dest = FileUtils.getFile(directory, asset.getOriginalFilename());
                if(dest.exists())
                    FileUtils.forceDelete(dest);
                asset.transferTo(dest);
            }
        } catch (IOException e) {
            throw new BadRequestAlertException(e.getMessage(), VideoGen.class.getName(), "ERROR");
        }

        return;
    }
}
