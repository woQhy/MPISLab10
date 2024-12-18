package com.example.mpislab05_2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentShow extends Fragment {

    private ListView listView;
    private DataBase db;
    private TextView notesCounter, noItemsText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        listView = view.findViewById(R.id.listView);
        noItemsText = view.findViewById(R.id.noItemsText);
        db = new DataBase(getActivity());

        notesCounter = view.findViewById(R.id.notesCounter);

        ImageButton refreshButton = view.findViewById(R.id.reloadBtn);
        refreshButton.setOnClickListener(v -> loadNotes());

        loadNotes();
        return view;
    }

    private void loadNotes() {
        int notesCount = db.getNotesCount();

        if (notesCount == 0) {
            noItemsText.setVisibility(View.VISIBLE);
        } else {
            noItemsText.setVisibility(View.GONE);
        }
        notesCounter.setText("Total Notes: " + notesCount);

        Cursor cursor = db.getAllNotes();
        Adapter adapter = new Adapter(getActivity(), cursor);

        listView.setAdapter(adapter);
    }
}
