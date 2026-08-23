package de.iu.radioapp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.lifecycle.ViewModelProvider;

import de.iu.radioapp.data.AppRepository;

import de.iu.radioapp.fragments.ModeratorChatScreen;
import de.iu.radioapp.service.SongInfoService;
import de.iu.radioapp.service.PlaylistService;
import de.iu.radioapp.service.SongRequestService;
import de.iu.radioapp.service.ModeratorRatingService;

import de.iu.radioapp.fragments.PlaylistScreen;
import de.iu.radioapp.fragments.RequestSongScreen;
import de.iu.radioapp.fragments.ModeratorRatingScreen;


public class MainActivity extends AppCompatActivity {

    private final int PLAYLIST_SCREEN = 0;
    private final int REQUEST_SONG_SCREEN = 1;
    private final int MODERATOR_RATING_SCREEN = 2;
    private final int MODERATOR_CHAT_SCREEN = 3;

    private ImageButton buttonPlaylist;
    private ImageButton buttonRequestSong;
    private ImageButton buttonModeratorRating;
    private ImageButton buttonModeratorChat;

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
        serviceViewModel.setAppRepository(appRepository);
        serviceViewModel.setSongInfoService(new SongInfoService(appRepository));
        serviceViewModel.setPlaylistService(new PlaylistService(appRepository));
        serviceViewModel.setSongRequestService(new SongRequestService());
        serviceViewModel.setModeratorRatingService(new ModeratorRatingService(appRepository));
    }

    private void setupNavigation() {
        buttonPlaylist = findViewById(R.id.activity_main_buttonPlaylist);
        buttonPlaylist.setOnClickListener(v -> {
            loadPlaylistScreen();
        });

        buttonRequestSong = findViewById(R.id.activity_main_buttonSongRequest);
        buttonRequestSong.setOnClickListener(v -> {
            loadRequestSongScreen();
        });

        buttonModeratorRating = findViewById(R.id.activity_main_buttonModeratorRating);
        buttonModeratorRating.setOnClickListener(v -> {
            loadModeratorRatingScreen();
        });

        buttonModeratorChat = findViewById(R.id.activity_main_buttonModeratorChat);
        buttonModeratorChat.setOnClickListener(v -> {
            loadModeratorChatScreen();
        });
    }

    private void loadPlaylistScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.activity_main_fragmentContainerView, PlaylistScreen.class, null)
                .commit();
        disableButtonForCurrentScreen(PLAYLIST_SCREEN);
    }

    private void loadRequestSongScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.activity_main_fragmentContainerView, RequestSongScreen.class, null)
                .commit();
        disableButtonForCurrentScreen(REQUEST_SONG_SCREEN);
    }

    private void loadModeratorRatingScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.activity_main_fragmentContainerView, ModeratorRatingScreen.class, null)
                .commit();
        disableButtonForCurrentScreen(MODERATOR_RATING_SCREEN);
    }

    private void loadModeratorChatScreen() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.activity_main_fragmentContainerView, ModeratorChatScreen.class, null)
                .commit();
        disableButtonForCurrentScreen(MODERATOR_CHAT_SCREEN);
    }

    private void disableButtonForCurrentScreen(int currentScreen) {
        if (currentScreen == PLAYLIST_SCREEN) {
            buttonPlaylist.setEnabled(false);
            buttonPlaylist.setImageDrawable(getDrawable(R.drawable.navigation_playlist_blue));
        } else {
            buttonPlaylist.setEnabled(true);
            buttonPlaylist.setImageDrawable(getDrawable(R.drawable.navigation_playlist));
        }

        if (currentScreen == REQUEST_SONG_SCREEN) {
              buttonRequestSong.setEnabled(false);
              buttonRequestSong.setImageDrawable(getDrawable(R.drawable.navigation_request_song_blue));
        } else {
              buttonRequestSong.setEnabled(true);
              buttonRequestSong.setImageDrawable(getDrawable(R.drawable.navigation_request_song));
        }


         if (currentScreen == MODERATOR_RATING_SCREEN) {                                                                             
               buttonModeratorRating.setEnabled(false);
               buttonModeratorRating.setImageDrawable(getDrawable(R.drawable.navigation_moderator_rating_blue));
         } else {
               buttonModeratorRating.setEnabled(true);
               buttonModeratorRating.setImageDrawable(getDrawable(R.drawable.navigation_moderator_rating));
         }

         if (currentScreen == MODERATOR_CHAT_SCREEN) {
               buttonModeratorChat.setEnabled(false);
               buttonModeratorChat.setImageDrawable(getDrawable(R.drawable.navigation_request_song_blue));
         } else {
             buttonModeratorChat.setEnabled(true);
             buttonModeratorChat.setImageDrawable(getDrawable(R.drawable.navigation_request_song));
         }
    }

}