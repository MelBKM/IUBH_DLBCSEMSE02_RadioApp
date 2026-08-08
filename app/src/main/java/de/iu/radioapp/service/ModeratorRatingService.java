package de.iu.radioapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.iu.radioapp.UserMessage;
import de.iu.radioapp.data.AppRepository;
import de.iu.radioapp.model.Rating;
import de.iu.radioapp.model.RatingType;

public class ModeratorRatingService {

    private final AppRepository repository;
    private final Random random = new Random();

    private final String[] moderators = {
            "Peter Muster",
            "Anna Berger",
            "Max Hoffmann",
            "Laura Stein"
    };

    private String currentModerator;

    public ModeratorRatingService(AppRepository repository) {
        this.repository = repository;
        selectRandomModerator();
    }

    public String getCurrentModerator() {
        return currentModerator;
    }

    public String selectRandomModerator() {
        int randomIndex = random.nextInt(moderators.length);
        currentModerator = moderators[randomIndex];
        return currentModerator;
    }

    public List<Rating> getModeratorRatings() {

        List<Rating> moderatorRatings = new ArrayList<>();

        for (Rating rating : repository.getRatings()) {
            if (rating.getRatingType() == RatingType.PRESENTER) {
                moderatorRatings.add(rating);
            }
        }

        return moderatorRatings;
    }

    public boolean addModeratorRating(
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
                RatingType.PRESENTER
        );

        repository.getRatings().add(rating);

        return true;
    }

    public List<UserMessage> getModeratorMessages() {

        List<UserMessage> messages = new ArrayList<>();

        for (Rating rating : getModeratorRatings()) {
            messages.add(
                    new UserMessage(
                            rating.getUsername(),
                            rating.getDescription(),
                            (int) rating.getPoints()
                    )
            );
        }

        return messages;
    }

    public double getAverageModeratorRating() {

        List<Rating> ratings = getModeratorRatings();

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Rating rating : ratings) {
            sum += rating.getPoints();
        }

        return sum / ratings.size();
    }

    public boolean rateModerator(
            String moderatorName,
            int rating,
            String message) {

        // Der Moderatorname wird aktuell nur in der UI angezeigt.
        // Das Rating-Modell speichert nur den bewertenden Nutzer.
        return addModeratorRating(
                "guest_user",
                message,
                rating
        );
    }
}