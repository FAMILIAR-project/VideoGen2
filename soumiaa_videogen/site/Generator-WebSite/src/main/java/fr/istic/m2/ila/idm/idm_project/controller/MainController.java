package fr.istic.m2.ila.idm.idm_project.controller;

import fr.istic.m2.ila.idm.idm_project.domain.FormTextVideoGen;
import soumiaa_videogen.java.Constante;
import soumiaa_videogen.java.Main;
import soumiaa_videogen.java.exception.BadProba;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Value("${video.pathVideo.original}")
    private String pathOriginalVideo = "";

    @Value("${video.pathVideo.generated}")
    private String pathGenerateddVideo = "";

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Model model) {

        FormTextVideoGen formText = new FormTextVideoGen();
        formText.setTextVideoGen("VideoGen {\n" +
                "   mandatory videoseq v0 \"pluie.mp4\"\n" +
                "   mandatory videoseq v2 \"oiseau.mp4\"\n" +
                "   mandatory videoseq v3 \"oiseau1.mp4\"\n" +
                "}");

        model.addAttribute("formVideoGen", formText);


        model.addAttribute("videoGenerated", null);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String generateVideoFromVideoGen(FormTextVideoGen videoGen,Model model) {
        Constante.setOriginalPathDirectory("src/main/resources/static/video/original");
        Constante.setOutputVideoGenerated("src/main/resources/static/video/generated");
        Constante.setTmpDirectory("tmpFileVideoGen");
        Constante.setTmpDirectoryImg("src/main/resources/static/video/imgTmp");

        try {
            File fileTmp = new File("tmpFileVideoGen/text.videogen");
            fileTmp.createNewFile();

            String text =  videoGen.getTextVideoGen().replace("\r","");
            PrintWriter writer = new PrintWriter(fileTmp, "UTF-8");
            writer.print(text);
            writer.close();


            String newVideo = Main.genererVideo(fileTmp.getPath());


            model.addAttribute("videoGenerated", "video/generated/"+new File(newVideo).getName());
            FormTextVideoGen formText = new FormTextVideoGen();
            formText.setTextVideoGen(text);
            model.addAttribute("formVideoGen", formText);
        } catch (BadProba badProba) {
            badProba.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }


    @RequestMapping(value="/videoDispo", method=RequestMethod.GET)
    public String afficherTouteLesVideosDispo(Model model) {
        List<String> listeVideo = new ArrayList<>();

        File[] files = new File(pathOriginalVideo).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                listeVideo.add("video/original/"+file.getName());
            }
        }

        model.addAttribute("videos", listeVideo);
        return "videoDispo";
    }

    @RequestMapping(value="/videoGenerated", method=RequestMethod.GET)
    public String afficherVideoDejaGenerer(Model model) {
        List<String> listeVideo = new ArrayList<>();

        File[] files = new File(pathGenerateddVideo).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                listeVideo.add("video/generated/"+file.getName());
            }
        }

        model.addAttribute("videos", listeVideo);
        return "videoGenerated";
    }
}
