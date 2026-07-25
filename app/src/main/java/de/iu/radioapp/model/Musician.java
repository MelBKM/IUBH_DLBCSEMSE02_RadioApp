package de.iu.radioapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about musicians.
 * It provides methods to get name and album associated with the musician in question.
 *
 * @author Julian Engler
 */
public class Musician {

    private final String name;
    private List<Album> albums;

    /**
     * Creates a new musician with the given name
     *
     * @param name of the musician to be created
     */
    public Musician(String name){
        this.name = name;
        this.albums = new ArrayList<>();
    }

    /**
     * This method provides information about the name of the musician
     *
     * @return String containing the name of the musician
     */
    public String getName() {
        return name;
    }

    /**
     * This method provides information about the albums associated with this musician
     *
     * @return List containing the albums of this musician
     * @see Album
     * @see List
     */
    public List<Album> getAlbums() {
        return albums;
    }

    /**
     * This method provides a way to add an album to this musician.
     * If the List for the albums has not been instantiated yet, a new ArrayList will be created
     *
     * @param album that should be added to this album
     * @see Album
     */
    public void addAlbumToMusician(Album album){
        if (this.albums == null){
            this.albums = new ArrayList<>();
        }

        this.albums.add(album);
    }
}
