package de.iu.radioapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.iu.radioapp.R;
import de.iu.radioapp.ServiceViewModel;
import de.iu.radioapp.service.SongRequestService;


public class RequestSongScreen extends Fragment {

    private View view;
    private ServiceViewModel serviceViewModel;

    private EditText textInputSongTitle;
    private EditText textInputSongAlbum;
    private EditText textInputSongInterpreter;
    private EditText editTextAdditionalMessage;
    private Button buttonConfirm;
    private Button buttonCancel;

    public RequestSongScreen() {
        // Required empty public constructor
    }

    public static RequestSongScreen newInstance(String param1, String param2) {
        return new RequestSongScreen();
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
        view = inflater.inflate(R.layout.fragment_request_song_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButtonConfirm();
        setupButtonCancel();
        setupTextInputSongTitle();
        setupTextInputSongAlbum();
        setupTextInputSongInterpreter();
        setupEditTextAdditionalMessage();
    }

    private void setupButtonConfirm() {
        buttonConfirm = view.findViewById(R.id.btnConfirm);
        buttonConfirm.setClickable(false);
        buttonConfirm.setOnClickListener(v -> {
            send();
        });
    }

    private void setupButtonCancel() {
        buttonCancel = view.findViewById(R.id.btnCancel);
        buttonCancel.setOnClickListener(v -> {
            cancel();
        });
    }

    private void setupTextInputSongTitle() {
        textInputSongTitle = view.findViewById(R.id.textInputSongTitle);
        textInputSongTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {validate();}
        });
    }

    private void setupTextInputSongAlbum() {
        textInputSongAlbum = view.findViewById(R.id.textInputSongAlbum);
        textInputSongAlbum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {validate();}
        });
    }

    private void setupTextInputSongInterpreter() {
        textInputSongInterpreter = view.findViewById(R.id.textInputSongInterpreter);
        textInputSongInterpreter.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {validate();}
        });
    }

    private void setupEditTextAdditionalMessage() {
        editTextAdditionalMessage = view.findViewById(R.id.editTextAdditionalMessage);
    }

    private void validate() {
        String title = textInputSongTitle.getText().toString();
        String album = textInputSongAlbum.getText().toString();
        String interpreter = textInputSongInterpreter.getText().toString();
        buttonConfirm.setEnabled(!title.isEmpty()
                && !album.isEmpty()
                && !interpreter.isEmpty());
    }

    private void clean_screen() {
        textInputSongTitle.setText("");
        textInputSongAlbum.setText("");
        textInputSongInterpreter.setText("");
        editTextAdditionalMessage.setText("");
        validate();
    }

    private void cancel() {
        clean_screen();
    }

    private void send() {
        String title = textInputSongTitle.getText().toString();
        String album = textInputSongAlbum.getText().toString();
        String interpreter = textInputSongInterpreter.getText().toString();
        String additionalMessage = editTextAdditionalMessage.getText().toString();

        SongRequestService songRequestService = serviceViewModel
                .getSongRequestService();

        boolean success = songRequestService.sendSongRequest(title,
                album,
                interpreter
                ,additionalMessage
        );
        if (success) {
            clean_screen();
            Toast.makeText(
                    requireContext(),
                    "Song-Request gesendet",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}