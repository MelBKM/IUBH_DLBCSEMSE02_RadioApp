package de.iu.radioapp;

import android.os.Bundle;

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
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import androidx.lifecycle.ViewModelProvider;

import de.iu.radioapp.fragments.PlaylistScreen;

public class MainActivity extends AppCompatActivity {
    private ServiceViewModel serviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMainActivity();
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
        serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
        AppRepository appRepository = new AppRepository(this);
        serviceViewModel.setSongInfoService(new SongInfoService(appRepository));
        serviceViewModel.setPlaylistService(new PlaylistService(appRepository));
        serviceViewModel.setSongRequestService(new SongRequestService());
        serviceViewModel.setModeratorRatingService(new ModeratorRatingService());
    }

    private void loadPlaylistScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, PlaylistScreen.class, null)
                .commit();

    }



    private void setupNavigation() {
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
                    serviceViewModel.getModeratorRatingService().rateModerator(
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