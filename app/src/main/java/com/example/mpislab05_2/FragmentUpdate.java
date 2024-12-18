package com.example.mpislab05_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentUpdate extends Fragment {

    private DataBase db;
    private EditText editTextUpdateId, editTextUpdateDescription;
    private Button buttonUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        db = new DataBase(getActivity());
        editTextUpdateId = view.findViewById(R.id.noteIdInput);
        editTextUpdateDescription = view.findViewById(R.id.newDescriptionInput);
        buttonUpdate = view.findViewById(R.id.updateButton);

        buttonUpdate.setOnClickListener(v -> {
            String noteId = editTextUpdateId.getText().toString();
            String newDescription = editTextUpdateDescription.getText().toString();

            if (noteId.isEmpty() || newDescription.isEmpty()) {
                showAlertDialog("Error", getString(R.string.field_error));
                return;
            }

            db.updateNoteById(Integer.parseInt(noteId), newDescription);
            Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();

            editTextUpdateId.setText("");
            editTextUpdateDescription.setText("");
        });
        return view;
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
