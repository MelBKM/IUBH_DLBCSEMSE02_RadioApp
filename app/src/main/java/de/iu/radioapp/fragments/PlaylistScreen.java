package de.iu.radioapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import de.iu.radioapp.R;
import de.iu.radioapp.ServiceViewModel;
import de.iu.radioapp.model.Album;
import de.iu.radioapp.model.Song;


public class PlaylistScreen extends Fragment {

    private ServiceViewModel serviceViewModel;
    private View view;

    public PlaylistScreen() {
        // Required empty public constructor
    }

    public static PlaylistScreen newInstance() {
        return new PlaylistScreen();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceViewModel = new ViewModelProvider(requireActivity()).get(ServiceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_playlist_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCurrentSong();
        setupRatingFunction();
    }

    protected void loadCurrentSong() {
        Song currentSong = serviceViewModel.getSongInfoService().getCurrentSong();

        if (currentSong == null) {
            return;
        }

        Album albumModel = currentSong.getAlbum();

        TextView playlistName = view.findViewById(R.id.textViewCurrentPlaylist);
        TextView songTitle = view.findViewById(R.id.textViewCurrentSongTitle);
        TextView interpreter = view.findViewById(R.id.textViewCurrentSongInterpreter);
        TextView album = view.findViewById(R.id.textViewCurrentSongAlbum);
        TextView releaseDate = view.findViewById(R.id.textViewCurrentSongReleaseDate);
        ImageView imageViewCurrentSongAlbumCover = view.findViewById(R.id.imageViewCurrentSongAlbumCover);

        playlistName.setText("Aktuelle Playlist"); // TODO: Get name from Service
        songTitle.setText(currentSong.getName());
        interpreter.setText(albumModel.
                getMusician().
                getName()
        );
        album.setText(albumModel
                .getName()
        );
        releaseDate.setText(
                String.valueOf(
                        albumModel.getReleaseYear()
                )
        );

        imageViewCurrentSongAlbumCover.
                setImageDrawable(ResourcesCompat.getDrawable(requireContext().getResources(),
                        albumModel.getCoverImageId(),
                        requireContext().getTheme())
                );

    }

    private void setupRatingFunction() {
        RatingBar ratingBarPlaylist =
                view.findViewById(R.id.ratingBarPlaylist);

        ratingBarPlaylist.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {

                    if (fromUser) {
                        boolean success =
                                serviceViewModel.getPlaylistService().ratePlaylist(
                                        (int) rating
                                );

                        if (success) {
                            Toast.makeText(
                                    requireContext(),
                                    "Playlist-Bewertung gespeichert.",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });

    }
}