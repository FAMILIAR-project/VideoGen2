package fr.istic.m2il.idm.videogenapp.domain;



import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ismael
 */

@Entity
@Table(name = "videogen")
public class VideoGen implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "version")
    private String version;

    @ElementCollection
    List<String> videosUrls = new ArrayList<>();

    public List<String> getVideosUrls() {
        return videosUrls;
    }

    public void setVideosUrls(List<String> videosUrls) {
        this.videosUrls = videosUrls;
    }

    public VideoGen(String author, String date) {
        this.author = author;
        this.date = date;
    }

    public VideoGen() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
