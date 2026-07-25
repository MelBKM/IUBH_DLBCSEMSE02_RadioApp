package de.iu.radioapp.model;

/**
 * This class stores information about songs.
 * It provides methods to get name, duration, musician and album this song was released on
 *
 * @author Julian Engler
 */
public class Song {
    private final String name;
    private final int duration;
    private final Genre genre;
    private final Album album;

    /**
     * Creates a new musician with the given name
     *
     * @param name of the song to be created
     * @param duration of the song to be created
     * @param genre of the song to be created
     * @param album that this song was released on
     * @see Album
     * @see Genre
     */
    public Song(String name, int duration, Genre genre, Album album) {
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.album = album;
    }

    /**
     * This method provides information about the name of the song
     *
     * @return String containing the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * This method provides information about the genre of the song
     *
     * @return Genre of the song
     * @see Genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * This method provides information about the duration of the song
     *
     * @return int containing the duration of the song
     */
    public int getDuration() {
        return duration;
    }

    /**
     * This method provides information about the album this song was released on
     *
     * @return Album that this song was released on
     * @see Album
     */
    public Album getAlbum() {
        return album;
    }
}
