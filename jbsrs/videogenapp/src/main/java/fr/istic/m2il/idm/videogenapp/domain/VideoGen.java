package fr.istic.m2il.idm.videogenapp.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A VideoGen.
 */
@Entity
@Table(name = "videogen")
public class VideoGen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Size(max = 50)
    @Column(name = "author", length = 50)
    private String author;

    @Size(max = 50)
    @Column(name = "date", length = 50)
    private String date;

    @Size(max = 50)
    @Column(name = "version", length = 50)
    private String version;

    @ElementCollection
    private List<String> videosUrls = new ArrayList<>();

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getVideosUrls() {
        return videosUrls;
    }

    public void setVideosUrls(List<String> videosUrls) {
        this.videosUrls = videosUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoGen videoGen = (VideoGen) o;
        if (videoGen.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), videoGen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VideoGen{" +
            "id=" + getId() +
            "}";
    }
}
