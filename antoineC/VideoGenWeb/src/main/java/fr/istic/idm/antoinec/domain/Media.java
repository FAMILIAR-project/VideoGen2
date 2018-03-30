package fr.istic.idm.antoinec.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {

    /**
     * Nom du fichier médias d'une grammaire videogen
     */
    @JsonProperty
    private String filename;

    /**
     * Indique si le fichier appartient ou non à la grammaire
     */
    @JsonProperty
    private boolean presence;

    public Media(String filename) {
        this.filename = filename;
        this.presence = false;
    }

    public Media(String filename, boolean presence) {
        this.filename = filename;
        this.presence = presence;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }
}
