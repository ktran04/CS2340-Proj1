package com.example.whatever;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Assignments extends AppCompatActivity {
    private Map<String, List<String>> assignmentsInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Button back = findViewById(R.id.back_to_home);
        Button add = findViewById(R.id.add_assignments);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assignments.this, MainActivity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMethod();
            }
        });
    }

    private void dialogMethod() {
        LayoutInflater inflater = LayoutInflater.from(Assignments.this);
        View dialogLayout = inflater.inflate(R.layout.enter_assignments_details, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(Assignments.this);
        dialog.setView(dialogLayout);
        TextView title = (TextView) dialogLayout.findViewById(R.id.title);
        TextView dueDate = (TextView) dialogLayout.findViewById(R.id.dueDate);
        TextView Class = (TextView) dialogLayout.findViewById(R.id.Class);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> data = new ArrayList<>();
                assignmentsInfo.put((String) title.getText(), data);
                data.add((String) dueDate.getText());
                data.add((String) Class.getText());
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}