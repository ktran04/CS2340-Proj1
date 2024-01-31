package com.example.whatever;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Classes extends AppCompatActivity {
    int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        Button back = findViewById(R.id.back_to_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Classes.this, MainActivity.class));
            }
        });

        ListView courseListView = (ListView) findViewById(R.id.course_list);
        ArrayList<String> backingList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, backingList);
        courseListView.setAdapter(adapter);

        EditText enterClass = findViewById(R.id.course_title);
        EditText enterTime = findViewById(R.id.course_time);
        EditText enterInstructor = findViewById(R.id.course_instructor);
        EditText enterLocation = findViewById(R.id.course_location);

        Button addCourse = findViewById(R.id.course_add);
        addCourse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String classStr = enterClass.getText().toString();
                String timeStr = enterTime.getText().toString();
                String instructorStr = enterInstructor.getText().toString();
                String locationStr = enterLocation.getText().toString();
                backingList.add("Course: " + classStr + "\nTime: " + timeStr + "\nInstructor: " + instructorStr + "\nLocation: " + locationStr);
                adapter.notifyDataSetChanged();

                enterClass.setText("");
                enterTime.setText("");
                enterInstructor.setText("");
                enterLocation.setText("");
            }
        });

        Button edit = findViewById(R.id.course_edit);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                String[] splitByColon = backingList.get(position).split(":");
                String now;
                for (int i = 1; i < 5; i++) {
                    now = "";
                    for (int j = 1; j < splitByColon[i].length() && splitByColon[i].charAt(j) != '\n'; j++) {
                        now += splitByColon[i].charAt(j);
                    }
                    splitByColon[i] = now;
                }

                enterClass.setText(splitByColon[1]);
                enterTime.setText(splitByColon[2]);
                enterInstructor.setText(splitByColon[3]);
                enterLocation.setText(splitByColon[4]);

                Toast.makeText(Classes.this, splitByColon[1] + " has been selected to be edited or deleted.", Toast.LENGTH_SHORT).show();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    String classStr = enterClass.getText().toString();
                    String timeStr = enterTime.getText().toString();
                    String instructorStr = enterInstructor.getText().toString();
                    String locationStr = enterLocation.getText().toString();
                    backingList.set(selected, "Course: " + classStr + "\nTime: " + timeStr + "\nInstructor: " + instructorStr + "\nLocation: " + locationStr);
                    adapter.notifyDataSetChanged();

                    enterClass.setText("");
                    enterTime.setText("");
                    enterInstructor.setText("");
                    enterLocation.setText("");
                    selected = -1;
                }
            }
        });

        Button del = findViewById(R.id.course_delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    backingList.remove(selected);
                    adapter.notifyDataSetChanged();
                    selected = -1;

                    enterClass.setText("");
                    enterTime.setText("");
                    enterInstructor.setText("");
                    enterLocation.setText("");
                }
            }
        });
    }
    /*
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
    */
}