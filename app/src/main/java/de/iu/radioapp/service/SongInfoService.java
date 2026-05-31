/**
 * @author Melanie Bopfinger
 */

package de.iu.radioapp.service;

import java.util.ArrayList;
import java.util.List;

import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.model.Genre;
import de.iu.radioapp.model.Song;

public class SongInfoService {

    private final AppRepository repository;

    public SongInfoService(AppRepository repository) {
        this.repository = repository;
    }

    public Song getCurrentSong() {
        if (repository.getSongs().isEmpty()) {
            return null;
        }

        return repository.getSongs().get(0);
    }

    public List<Song> getAllSongs() {
        return repository.getSongs();
    }

    public List<Song> getSongsByGenre(Genre genre) {
        List<Song> result = new ArrayList<>();

        for (Song song : repository.getSongs()) {
            if (song.getGenre() == genre) {
                result.add(song);
            }
        }

        return result;
    }

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