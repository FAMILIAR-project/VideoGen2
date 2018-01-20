package fr.istic.idm.antoinec.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A VideoGen.
 */
@Entity
@Table(name = "video_gen")
public class VideoGen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The filename of the .videogen file
     */
    @Column(name = "name")
    private String name;


    /**
     * Liste des Médias requis par le fichier videogen
     */
    @Transient
    @JsonProperty
    private List<Media> medias;

    @ManyToOne
    private User owner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public VideoGen name(String name) {
        this.name = name;
        return this;
    }

    public List<Media> getMedias() {
        return medias;
    }


    public void addMedia(Media media) {
        if(this.medias == null)
            this.medias = new ArrayList<>();
        this.medias.add(media);

    }

    public void removeMedia(Media media) {
        if(this.medias == null)
            this.medias = new ArrayList<>();
        this.medias.remove(media);

    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public VideoGen owner(User user) {
        this.owner = user;
        return this;
    }

    public void setOwner(User user) {
        this.owner = user;
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
            ", name='" + getName() + "'" +
            "}";
    }
}
