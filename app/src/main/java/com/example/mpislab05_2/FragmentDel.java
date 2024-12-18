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

public class FragmentDel extends Fragment {

    private DataBase db;
    private EditText editTextDel;
    private Button buttonDel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_del, container, false);
        db = new DataBase(getActivity());
        editTextDel = view.findViewById(R.id.editTextDel);
        buttonDel = view.findViewById(R.id.buttonDel);

        buttonDel.setOnClickListener(v -> {
            String noteId = editTextDel.getText().toString();

            if (noteId.isEmpty()) {
                showAlertDialog("Error", getString(R.string.field_error));
                return;
            }

            db.deleteNoteById(Integer.parseInt(noteId));
            Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_SHORT).show();

            editTextDel.setText("");
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
