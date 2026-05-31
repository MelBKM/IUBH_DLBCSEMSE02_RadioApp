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

        AppRepository repository = new AppRepository(this);

        Log.d("MainActivity", "Songs loaded: " + repository.getSongs().size());


        SongInfoService songInfoService = new SongInfoService(repository);
        PlaylistService playlistService = new PlaylistService(repository);
        SongRequestService songRequestService = new SongRequestService();
        ModeratorRatingService moderatorRatingService = new ModeratorRatingService();

        Log.d("MainActivity", "Songs loaded: " + repository.getSongs().size());
        Log.d("MainActivity", songInfoService.getCurrentSongInfoText());

    }
}