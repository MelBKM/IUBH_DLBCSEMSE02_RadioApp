package de.iu.radioapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import de.iu.radioapp.R;

public class ChatBubble extends Fragment {

    private static final String ARG_RATING = "rating";
    private static final String ARG_USER = "user";
    private static final String ARG_MESSAGE = "message";

    private View view;
    private int rating;
    private String user;
    private String message;

    public ChatBubble() {
        // Required empty public constructor
    }

    public static ChatBubble newInstance(int rating, String user, String message) {
        ChatBubble fragment = new ChatBubble();
        Bundle args = new Bundle();
        args.putInt(ARG_RATING, rating);
        args.putString(ARG_USER, user);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            rating = getArguments().getInt(ARG_RATING);
            user = getArguments().getString(ARG_USER);
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat_bubble, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadUserData();
    }

    private void loadUserData() {
        TextView textViewUser = view.findViewById(R.id.textViewUser);
        TextView textViewUserMessage = view.findViewById(R.id.textViewUserMessage);
        RatingBar ratingBarChatBubble = view.findViewById(R.id.ratingBarChatBubble);

        if (user.isEmpty()) {
            textViewUser.setText("");
            ratingBarChatBubble.setVisibility(View.GONE);
            textViewUserMessage.setText("");
            return;
        }

        textViewUser.setText(user);
        textViewUserMessage.setText(message);
        ratingBarChatBubble.setRating(rating);
        ratingBarChatBubble.setEnabled(false);
    }
}
