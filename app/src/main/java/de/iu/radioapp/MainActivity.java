package de.iu.radioapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.iu.radioapp.data.AppRepository;


import de.iu.radioapp.service.SongInfoService;
import de.iu.radioapp.service.PlaylistService;
import de.iu.radioapp.service.SongRequestService;
import de.iu.radioapp.service.ModeratorRatingService;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;


import de.iu.radioapp.model.Song;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Repository
        AppRepository repository = new AppRepository(this);

        // 2. Services
        SongInfoService songInfoService =
                new SongInfoService(repository);

        PlaylistService playlistService =
                new PlaylistService(repository);

        SongRequestService songRequestService =
                new SongRequestService();

        ModeratorRatingService moderatorRatingService =
                new ModeratorRatingService();

        // 3. Songinformationen anzeigen
        Song currentSong = songInfoService.getCurrentSong();

        if (currentSong != null) {

            TextView playlistName =
                    findViewById(R.id.textViewCurrentPlaylist);

            TextView songTitle =
                    findViewById(R.id.textViewCurrentSongTitle);

            TextView interpreter =
                    findViewById(R.id.textViewCurrentSongInterpreter);

            TextView album =
                    findViewById(R.id.textViewCurrentSongAlbum);

            TextView releaseDate =
                    findViewById(R.id.textViewCurrentSongReleaseDate);

            playlistName.setText("Aktuelle Playlist");
            songTitle.setText(currentSong.getName());
            interpreter.setText(
                    currentSong.getAlbum().getMusician().getName()
            );
            album.setText(currentSong.getAlbum().getName());
            releaseDate.setText(
                    String.valueOf(
                            currentSong.getAlbum().getReleaseYear()
                    )
            );
        }

        // 4. Playlist bewerten
        RatingBar ratingBarPlaylist =
                findViewById(R.id.ratingBarPlaylist);

        ratingBarPlaylist.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {

                    if (fromUser) {

                        boolean success =
                                playlistService.ratePlaylist(
                                        (int) rating
                                );

                        if (success) {
                            Toast.makeText(
                                    this,
                                    "Playlist-Bewertung gespeichert.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });

        // 5. HIER die Buttons
        Button songRequestButton =
                findViewById(R.id.buttonSongRequest);

        //   Demo „Song wünschen“, leitet weiter zu RequestSong
        songRequestButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RequestSong.class);
            startActivity(intent);
        });


        Button moderatorButton =
                findViewById(R.id.buttonModeratorRating);


        // Moderator bewerten
        moderatorButton.setOnClickListener(v -> {

            boolean success =
                    moderatorRatingService.rateModerator(
                            "Peter Muster",
                            5,
                            "Mir gefallen deine Witze."
                    );

            if (success) {
                Toast.makeText(
                        this,
                        "Moderator wurde bewertet.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
    }