/**
 * @author Melanie Bopfinger
 */
package de.iu.radioapp.service;

import java.util.List;

import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.model.Song;

public class PlaylistService {

    private final AppRepository repository;

    public PlaylistService(AppRepository repository) {
        this.repository = repository;
    }

    public List<Song> getPlaylist() {
        return repository.getSongs();
    }

    public boolean ratePlaylist(int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }

        System.out.println("Playlist-Bewertung wurde weitergeleitet: " + rating);
        return true;
    }
}