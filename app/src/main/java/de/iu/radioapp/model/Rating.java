package de.iu.radioapp.model;

import java.util.List;

/**
 * This class stores information about ratings.
 * It provides methods to get username, description and points given by this rating
 *
 * @author Julian Engler
 */
public class Rating {

    private final String username;
    private final String description;
    private final double points;

    private final RatingType ratingType;

    /**
     * Creates a new rating with the given username, description and points
     *
     * @param username of the user of the radio app
     * @param description of the rating that is given
     * @param points that were granted by the user
     * @param ratingType defines if the rating belongs to a playlist or presenter
     */
    public Rating(String username, String description, double points, RatingType ratingType) {
        this.username = username;
        this.description = description;
        this.points = points;
        this.ratingType = ratingType;
    }

    /**
     * This method provides information about the name of the user who gave this rating
     *
     * @return String containing the name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method provides information about the description for this rating
     *
     * @return String containing the description for this rating
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method provides information about the points given by the user within this rating
     *
     * @return double containing the points given by the user within this rating
     */
    public double getPoints() {
        return points;
    }

    /**
     * This method provides information about the type of the rating (PLAYLIST / PRESENTER)
     *
     * @return RatingType
     */
    public RatingType getRatingType() {
        return ratingType;
    }
}
