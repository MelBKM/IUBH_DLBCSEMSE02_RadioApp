/**
 * @author Melanie Bopfinger
 */
package de.iu.radioapp.service;

public class SongRequestService {

    public boolean sendSongRequest(String title, String album, String interpret, String information) {
        if (isEmpty(title)) {
            return false;
        }

        if (isEmpty(album)) {
            return false;
        }

        if (isEmpty(interpret)) {
            return false;
        }

        System.out.println("Songwunsch wurde weitergeleitet:");
        System.out.println("Titel: " + title);
        System.out.println("Album: " + album);
        System.out.println("Interpret: " + interpret);
        System.out.println("Weitere Informationen: " + information);

        return true;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}