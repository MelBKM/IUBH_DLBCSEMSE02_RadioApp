package de.iu.radioapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;

import de.iu.radioapp.R;
import de.iu.radioapp.ServiceViewModel;
import de.iu.radioapp.UserMessage;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModeratorChatScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModeratorChatScreen extends Fragment {

    private View view;
    private ServiceViewModel serviceViewModel;

    private final List<UserMessage> TEST_MESSAGES = new ArrayList<UserMessage>(4) {{
        add(new UserMessage("Alice", "Hallo, wie geht’s?", 4));
        add(new UserMessage("Bob", "Ich finde den Song super!", 5));
        add(new UserMessage("Charlie", "Kannst du das nächste Lied spielen?", 3));
        add(new UserMessage("Diana", "Tolles Programm heute!", 5));
    }};

    private final UserMessage[] CURRENT_MESSAGES = {
            new UserMessage("", "", 0),
            new UserMessage("", "", 0),
            new UserMessage("", "", 0),
            new UserMessage("", "", 0)
    };

    private final Integer[] bubbleList = new Integer[4];

    public static ModeratorChatScreen newInstance() {
        return new ModeratorChatScreen();
    }

    public ModeratorChatScreen() {
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
        view = inflater.inflate(R.layout.fragment_moderator_chat_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateBubbleList();
        updateData();

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            updateData();
        });
    }

    private void populateBubbleList(){
        bubbleList[0] = R.id.fragmentContainerViewBubble1;
        bubbleList[1] = R.id.fragmentContainerViewBubble2;
        bubbleList[2] = R.id.fragmentContainerViewBubble3;
        bubbleList[3] = R.id.fragmentContainerViewBubble4;
    }


    private void updateData() {
        if (TEST_MESSAGES.isEmpty()) {
            return;
        }
        addNewMessageToCurrentData(TEST_MESSAGES.remove(0));
        updateBubbles();
    }


    private void addNewMessageToCurrentData(UserMessage userMessage) {
        for (int i = 3; i >= 1 ; i--) {
            CURRENT_MESSAGES[i] = CURRENT_MESSAGES[i-1];
        }
        CURRENT_MESSAGES[0] = userMessage;
    }

    private void updateBubbles() {
        for (int i = 0; i <= 3 ; i++) {
            int bubbleID = bubbleList[i];
            UserMessage userMessage = CURRENT_MESSAGES[i];
            if (userMessage.getUserName().isEmpty()) {
                continue;
            }
            addMessageData(bubbleID, userMessage);
        }
    }

    private void addMessageData(int bubbleId, UserMessage userMessage) {
        Bundle args = new Bundle();
        args.putInt("rating", userMessage.getRating());
        args.putString("user", userMessage.getUserName());
        args.putString("message", userMessage.getMessage());
        getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(bubbleId, ChatBubble.class, args)
                .commit();
    }
}