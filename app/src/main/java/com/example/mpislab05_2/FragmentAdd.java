package com.example.mpislab05_2;

import android.annotation.SuppressLint;
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

public class FragmentAdd extends Fragment {

    private DataBase db;
    private EditText editTextAdd;
    private Button buttonAdd;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        db = new DataBase(getActivity());
        editTextAdd = view.findViewById(R.id.editTextNote);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> {
            String description = editTextAdd.getText().toString();

            if (description.isEmpty()) {
                showAlertDialog("Error", getString(R.string.field_error));
                return;
            }

            db.addNote(description);
            Toast.makeText(getActivity(), "Note added", Toast.LENGTH_SHORT).show();

            editTextAdd.setText("");
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
