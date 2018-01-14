package fr.istic.m2il.idm.videogenapp.domain;


import javax.persistence.*;

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

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "version")
    private String version;

    @ElementCollection
    @CollectionTable(name = "videos_urls", joinColumns = @JoinColumn(name = "videogen_id"))
    @Column(name = "video_url")
    List<String> videosUrls = new ArrayList<>();

    public List<String> getVideosUrls() {
        return videosUrls;
    }

    public void setVideosUrls(List<String> videosUrls) {
        this.videosUrls = videosUrls;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public VideoGen author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public VideoGen date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public VideoGen version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
            ", author='" + getAuthor() + "'" +
            ", date='" + getDate() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
