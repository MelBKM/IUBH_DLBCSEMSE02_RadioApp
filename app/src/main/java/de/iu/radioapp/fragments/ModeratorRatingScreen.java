package de.iu.radioapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import de.iu.radioapp.R;
import de.iu.radioapp.ServiceViewModel;
import de.iu.radioapp.service.ModeratorRatingService;


public class ModeratorRatingScreen extends Fragment {

    private View view;
    private ServiceViewModel serviceViewModel;

    private Button buttonConfirm;
    private Button buttonCancel;
    private RatingBar ratingBarModerator;
    private EditText textEditAdditionalMessage;
    private TextView textViewCurrentModerator;

    public static ModeratorRatingScreen newInstance(String param1, String param2) {
        return new ModeratorRatingScreen();
    }

    public ModeratorRatingScreen() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_moderator_rating_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButtonConfirm();
        setupButtonCancel();
        setupTextViewCurrentModerator();
        setupRatingBar();
        setupTextEditAdditionalMessage();
        clean_screen();
    }

    private void setupButtonConfirm() {
        buttonConfirm = view.findViewById(R.id.moderator_rating_btnConfirm);
        buttonConfirm.setClickable(false);
        buttonConfirm.setOnClickListener(v -> {
            send();
        });
    }

    private void setupButtonCancel() {
        buttonCancel = view.findViewById(R.id.moderator_rating_btnCancel);
        buttonCancel.setOnClickListener(v -> {
            cancel();
        });
    }

    private void setupTextEditAdditionalMessage() {
        textEditAdditionalMessage = view.findViewById(R.id.moderator_rating_editTextAdditionalMessage);
    }

    private void setupRatingBar() {
        ratingBarModerator = view.findViewById(R.id.moderator_rating_ratingBarModerator);
        ratingBarModerator.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {
                    validate();
                });

    }

    private void setupTextViewCurrentModerator() {
        textViewCurrentModerator = view.findViewById(R.id.moderator_rating_textViewCurrentModerator);
        textViewCurrentModerator.setText(serviceViewModel.
                getModeratorRatingService().
                getCurrentModerator()
        );
    }

    private void validate() {
        buttonConfirm.setEnabled(ratingBarModerator.getRating() > 0);
    }

    private void clean_screen() {
        ratingBarModerator.setRating(0);
        textEditAdditionalMessage.setText("");
        validate();
    }

    private void cancel() {
        clean_screen();
    }

    private void send() {

        String moderatorName =
                textViewCurrentModerator.getText().toString();

        int rating =
                (int) ratingBarModerator.getRating();

        String message =
                textEditAdditionalMessage.getText().toString();

        ModeratorRatingService moderatorRatingService =
                serviceViewModel.getModeratorRatingService();

        boolean success =
                moderatorRatingService.addModeratorRating(
                        moderatorName,
                        message,
                        rating
                );

        if (success) {

            clean_screen();

            Toast.makeText(
                    requireContext(),
                    String.format(
                            "Moderator-Bewertung gespeichert.\nModerator: %s\nRating: %d",
                            moderatorName,
                            rating
                    ),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}