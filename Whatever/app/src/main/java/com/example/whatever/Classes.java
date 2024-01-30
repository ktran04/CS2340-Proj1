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

public class Classes extends AppCompatActivity {
    private Map<String, List<String>> courseInfo = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        Button back = findViewById(R.id.back_to_home);
        Button add = findViewById(R.id.add_class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Classes.this, MainActivity.class));
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
        LayoutInflater inflater = LayoutInflater.from(Classes.this);
        View dialogLayout = inflater.inflate(R.layout.enter_class_details, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(Classes.this);
        dialog.setView(dialogLayout);
        TextView name = (TextView) dialogLayout.findViewById(R.id.name);
        TextView instructor = (TextView) dialogLayout.findViewById(R.id.instructor);
        TextView time = (TextView) dialogLayout.findViewById(R.id.time);
        TextView location = (TextView) dialogLayout.findViewById(R.id.location);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> data = new ArrayList<>();
                courseInfo.put((String) name.getText(), data);
                data.add((String) instructor.getText());
                data.add((String) time.getText());
                data.add((String) location.getText());
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