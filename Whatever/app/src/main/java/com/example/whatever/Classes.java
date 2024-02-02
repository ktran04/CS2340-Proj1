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
        ArrayList<ClassEntry> backingList = new ArrayList<>();
        ArrayAdapter<ClassEntry> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, backingList);
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
                backingList.add(new ClassEntry(classStr, timeStr, instructorStr, locationStr));
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
                ClassEntry now = backingList.get(position);

                enterClass.setText(now.getName());
                enterTime.setText(now.getTime());
                enterInstructor.setText(now.getInstructor());
                enterLocation.setText(now.getLocation());

                Toast.makeText(Classes.this, now.getName() + " has been selected to be edited or deleted.", Toast.LENGTH_SHORT).show();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    ClassEntry now = backingList.get(selected);
                    now.setName(enterClass.getText().toString());
                    now.setTime(enterTime.getText().toString());
                    now.setInstructor(enterInstructor.getText().toString());
                    now.setLocation(enterLocation.getText().toString());
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
}