package de.iu.radioapp.data;

import android.content.Context;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import de.iu.radioapp.model.Album;
import de.iu.radioapp.model.Genre;
import de.iu.radioapp.model.Musician;
import de.iu.radioapp.model.Song;

public class AppRepository {

    private final ArrayList<Musician> musicians = new ArrayList<>();
    private final ArrayList<Album> albums = new ArrayList<>();
    private final ArrayList<Song> songs = new ArrayList<>();

    /**
     * Creates the data objects the music data from the JSON file.
     *
     * @param context the Android context used to access the assets folder
     */
    public AppRepository(Context context) {
        loadMusicData(context);
    }

    /**
     * Reads the JSON file and creates Musician, Album, and Song objects.
     *
     * @param context the Android context used to access the assets folder
     */
    private void loadMusicData(Context context) {
        try {
            String jsonString = readJsonFromAssets(context, "data/music_data.json");

            JSONArray musicianArray = new JSONArray(jsonString);

            for (int i = 0; i < musicianArray.length(); i++) {
                JSONObject musicianObject = musicianArray.getJSONObject(i);

                String musicianName = musicianObject.getString("name");

                Musician musician = new Musician(musicianName);
                musicians.add(musician);

                JSONArray albumArray = musicianObject.getJSONArray("albums");

                for (int j = 0; j < albumArray.length(); j++) {
                    JSONObject albumObject = albumArray.getJSONObject(j);

                    String albumTitle = albumObject.getString("title");
                    int albumYear = albumObject.getInt("year");

                    Album album = new Album(albumTitle, albumYear, musician);
                    albums.add(album);
                    musician.addAlbumToMusician(album);

                    JSONArray songArray = albumObject.getJSONArray("songs");

                    for (int k = 0; k < songArray.length(); k++) {
                        JSONObject songObject = songArray.getJSONObject(k);

                        String songTitle = songObject.getString("title");
                        int durationSeconds = songObject.getInt("durationSeconds");

                        String genreText = songObject.getString("genre");
                        Genre genre = Genre.valueOf(genreText);

                        Song song = new Song(
                                songTitle,
                                durationSeconds,
                                genre,
                                album
                        );

                        songs.add(song);
                        album.addSongToAlbum(song);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a JSON file from the assets folder and returns it as a String.
     *
     * @param context the Android context
     * @param filePath the path inside the assets folder
     * @return the content of the JSON file as a String
     * @throws Exception if the file cannot be read
     */
    private String readJsonFromAssets(Context context, String filePath) throws Exception {
        StringBuilder builder = new StringBuilder();

        InputStream inputStream = context.getAssets().open(filePath);
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();

        return builder.toString();
    }

    public ArrayList<Musician> getMusicians() {
        return musicians;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}