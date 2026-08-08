package de.iu.radioapp.service;

import java.util.ArrayList;
import java.util.List;

import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.model.Rating;
import de.iu.radioapp.model.RatingType;
import de.iu.radioapp.model.Song;

public class PlaylistService {

    private final AppRepository repository;

    public PlaylistService(AppRepository repository) {
        this.repository = repository;
    }

    public List<Song> getPlaylist() {
        return repository.getSongs();
    }

    /**
     * Liefert den Namen der aktuell dargestellten Playlist.
     * Da in der Datenschicht derzeit kein eigener Playlistname hinterlegt ist,
     * wird ein fester Wert als Stub verwendet.
     */
    public String getPlaylistName() {

        return "Aktuelle Playlist";
    }

    /**
     * Liefert ausschließlich Bewertungen, die zur Playlist gehören.
     */
    public List<Rating> getPlaylistRatings() {

        List<Rating> playlistRatings = new ArrayList<>();

        for (Rating rating : repository.getRatings()) {

            if (rating.getRatingType() == RatingType.PLAYLIST) {
                playlistRatings.add(rating);
            }
        }

        return playlistRatings;
    }

    /**
     * Fügt eine neue Playlist-Bewertung hinzu.
     */
    public boolean addPlaylistRating(
            String username,
            String description,
            double points) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (points < 1 || points > 5) {
            return false;
        }

        Rating rating = new Rating(
                username.trim(),
                description == null ? "" : description.trim(),
                points,
                RatingType.PLAYLIST
        );

        repository.getRatings().add(rating);

        return true;
    }

    /**
     * Berechnet den Durchschnitt aller Playlist-Bewertungen.
     */
    public double getAveragePlaylistRating() {

        List<Rating> ratings = getPlaylistRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Rating rating : ratings) {
            sum += rating.getPoints();
        }

        return sum / ratings.size();
    }

    // Alte Methode kann bestehen bleiben
    public boolean ratePlaylist(int rating) {

        return rating >= 1 && rating <= 5;
    }
}