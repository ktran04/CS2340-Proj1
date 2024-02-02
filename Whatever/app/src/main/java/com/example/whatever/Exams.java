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

public class Exams extends AppCompatActivity {
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);
        Button back = findViewById(R.id.back_to_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exams.this, MainActivity.class));
            }
        });

        ListView courseListView = (ListView) findViewById(R.id.exam_list);
        ArrayList<ExamEntry> backingList = new ArrayList<>();
        ArrayAdapter<ExamEntry> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, backingList);
        courseListView.setAdapter(adapter);

        EditText date = findViewById(R.id.date);
        EditText examTime = findViewById(R.id.exam_time);
        EditText examLocation = findViewById(R.id.exam_location);
        //EditText enterLocation = findViewById(R.id.course_location);

        Button addExams = findViewById(R.id.exam_add);
        addExams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String dateStr = date.getText().toString();
                String timeStr = examTime.getText().toString();
                String examLocationStr = examLocation.getText().toString();
                //String locationStr = enterLocation.getText().toString();
                backingList.add(new ExamEntry(dateStr, timeStr, examLocationStr));
                adapter.notifyDataSetChanged();

                date.setText("");
                examTime.setText("");
                examLocation.setText("");
            }
        });

        Button edit = findViewById(R.id.exam_edit);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                ExamEntry now = backingList.get(position);

                date.setText(now.getDate());
                examTime.setText(now.getTime());
                examLocation.setText(now.getLocation());

                Toast.makeText(Exams.this, "The exam on " + now.getTime() + " at " + now.getLocation() + " has been selected to be edited or deleted.", Toast.LENGTH_SHORT).show();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    ExamEntry now = backingList.get(selected);
                    now.setDate(date.getText().toString());
                    now.setTime(examTime.getText().toString());
                    now.setLocation(examLocation.getText().toString());
                    adapter.notifyDataSetChanged();

                    date.setText("");
                    examTime.setText("");
                    examLocation.setText("");
                    selected = -1;
                }
            }
        });

        Button del = findViewById(R.id.exam_delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    backingList.remove(selected);
                    adapter.notifyDataSetChanged();
                    selected = -1;

                    date.setText("");
                    examTime.setText("");
                    examLocation.setText("");
                    //enterLocation.setText("");
                }
            }
        });
    }
}