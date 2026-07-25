package de.iu.radioapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;

import androidx.lifecycle.ViewModelProvider;

import de.iu.radioapp.data.AppRepository;

import de.iu.radioapp.service.SongInfoService;
import de.iu.radioapp.service.PlaylistService;
import de.iu.radioapp.service.SongRequestService;
import de.iu.radioapp.service.ModeratorRatingService;

import de.iu.radioapp.fragments.PlaylistScreen;
import de.iu.radioapp.fragments.RequestSongScreen;
import de.iu.radioapp.fragments.ModeratorRatingScreen;


public class MainActivity extends AppCompatActivity {

    private Button buttonPlaylist;
    private Button buttonRequestSong;
    private Button buttonModeratorRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMainActivity();
        setupNavigation();
        connectServices();
        loadPlaylistScreen();
    }

    private void setupMainActivity() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void connectServices() {
        ServiceViewModel serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
        AppRepository appRepository = new AppRepository(this);
        serviceViewModel.setSongInfoService(new SongInfoService(appRepository));
        serviceViewModel.setPlaylistService(new PlaylistService(appRepository));
        serviceViewModel.setSongRequestService(new SongRequestService());
        serviceViewModel.setModeratorRatingService(new ModeratorRatingService());
    }

    private void setupNavigation() {
        buttonPlaylist = findViewById(R.id.buttonPlaylist);
        buttonPlaylist.setOnClickListener(v -> {
            loadPlaylistScreen();
        });

        buttonRequestSong = findViewById(R.id.buttonSongRequest);
        buttonRequestSong.setOnClickListener(v -> {
            loadRequestSongScreen();
        });

        buttonModeratorRating = findViewById(R.id.buttonModeratorRating);
        buttonModeratorRating.setOnClickListener(v -> {
            loadModeratorRatingScreen();
        });

    }

    private void loadPlaylistScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, PlaylistScreen.class, null)
                .commit();
        buttonPlaylist.setClickable(false);
        buttonRequestSong.setClickable(true);
        buttonModeratorRating.setClickable(true);
    }

    private void loadRequestSongScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, RequestSongScreen.class, null)
                .commit();
        buttonPlaylist.setClickable(true);
        buttonRequestSong.setClickable(false);
        buttonModeratorRating.setClickable(true);
    }

    private void loadModeratorRatingScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ModeratorRatingScreen.class, null)
                .commit();

        buttonPlaylist.setClickable(true);
        buttonRequestSong.setClickable(true);
        buttonModeratorRating.setClickable(false);
    }

}