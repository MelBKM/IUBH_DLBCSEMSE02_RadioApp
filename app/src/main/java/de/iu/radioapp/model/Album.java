package de.iu.radioapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about albums.
 * It provides methods to get name, releaseYear, musician and a list of songs that are on this album
 *
 * @author Julian Engler
 */
public class Album {

    private String name;
    private int releaseYear;
    private Musician musician;
    private List<Song> songs;

    /**
     * Creates a new album with the given name, releaseYear and musician
     *
     * @param name of the musician to be created
     * @param releaseYear of the album to be created
     * @param musician associated with this album
     * @see Musician
     */
    public Album(String name, int releaseYear, Musician musician) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.musician = musician;
        this.songs = new ArrayList<>();
    }

    /**
     * This method provides information about the name of the album
     *
     * @return String containing the name of the album
     */
    public String getName() {
        return name;
    }

    /**
     * This method provides information about the year this album was released
     *
     * @return int containing the year this album was released
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * This method provides the object of the musician associated with this album
     *
     * @return Musician associated with this album
     * @see Musician
     */
    public Musician getMusician() {
        return musician;
    }

    /**
     * This method provides the objects of the songs that are on this album
     *
     * @return List<Song> containing the objects of the songs that are on this album
     * @see List
     * @see Song
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * This method provides a way to add a song to this album.
     * If the List for the songs has not been instantiated yet, a new ArrayList will be created
     *
     * @param song that should be added to this album
     * @see Song
     */
    public void addSongToAlbum(Song song){
        if (this.songs == null){
            this.songs = new ArrayList<>();
        }

        this.songs.add(song);
    }
}
