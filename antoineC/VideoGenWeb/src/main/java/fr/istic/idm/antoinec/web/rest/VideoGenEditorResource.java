package fr.istic.idm.antoinec.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/api/videogen-editor")
public class VideoGenEditorResource {

    private final static Logger log = LoggerFactory.getLogger(VideoGenEditorResource.class);

    @PostMapping("/test")
    public void compileVideoGenFile(@RequestBody String videogen, RedirectAttributes redirectAttributes) {

        log.info("Received VideoGen Source Code {}", videogen);
        /*try {
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
        }


        try {
            VideoGenCompiler.main(new String[] {storageService.loadFile(file.getOriginalFilename()).getFile().getAbsolutePath()});
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "FAIL to compile " + file.getOriginalFilename() + "!");
        }*/
    }
}
