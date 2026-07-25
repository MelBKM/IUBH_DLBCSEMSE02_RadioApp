/**
 * @author Melanie Bopfinger
 */
package de.iu.radioapp.service;

public class ModeratorRatingService {

    public String getCurrentModerator() {
        return "Peter Pan"; // TODO: Needs model and service
    }
    public boolean rateModerator(String moderatorName, int rating, String message) {
        if (isEmpty(moderatorName)) {
            return false;
        }

        if (rating < 1 || rating > 5) {
            return false;
        }

        System.out.println("Moderator-Bewertung wurde weitergeleitet:");
        System.out.println("Moderator: " + moderatorName);
        System.out.println("Bewertung: " + rating);
        System.out.println("Nachricht: " + message);

        return true;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}