/**
 * @author Melanie Bopfinger
 */

package de.iu.radioapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.model.Genre;
import de.iu.radioapp.model.Song;

public class SongInfoService {

    private final AppRepository repository;
    private final Random random = new Random();

    private Song currentSong;

    public SongInfoService(AppRepository repository) {
        this.repository = repository;
        selectRandomSong();
    }

    /**
     * Liefert den aktuell ausgewählten Song.
     */
    public Song getCurrentSong() {
        return currentSong;
    }

    /**
     * Wählt zufällig einen neuen Song aus den vorhandenen Musikdaten aus.
     */
    public Song selectRandomSong() {

        List<Song> songs = repository.getSongs();

        if (songs == null || songs.isEmpty()) {
            currentSong = null;
            return null;
        }

        int randomIndex = random.nextInt(songs.size());
        currentSong = songs.get(randomIndex);

        return currentSong;
    }

    /**
     * Liefert alle vorhandenen Songs.
     */
    public List<Song> getAllSongs() {
        return repository.getSongs();
    }

    /**
     * Filtert die vorhandenen Songs nach Genre.
     */
    public List<Song> getSongsByGenre(Genre genre) {

        List<Song> result = new ArrayList<>();

        if (genre == null) {
            return result;
        }

        for (Song song : repository.getSongs()) {
            if (song.getGenre() == genre) {
                result.add(song);
            }
        }

        return result;
    }

    /**
     * Bereitet die Informationen des aktuellen Songs als Text auf.
     */
    public String getCurrentSongInfoText() {

        Song song = getCurrentSong();

        if (song == null) {
            return "Aktuell sind keine Songinformationen verfügbar.";
        }

        return "Titel: " + song.getName()
                + "\nInterpret: " + song.getAlbum().getMusician().getName()
                + "\nAlbum: " + song.getAlbum().getName()
                + "\nVeröffentlicht: " + song.getAlbum().getReleaseYear();
    }
}