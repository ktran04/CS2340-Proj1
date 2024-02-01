package com.example.whatever;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Assignments extends AppCompatActivity {
    int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        Spinner spinner = findViewById(R.id.spinner);
        String[] stuff = new String[]{"Due Date", "Associated Class"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stuff);
        spinner.setAdapter(spinnerAdapter);

        AssignmentComparator comp = new AssignmentComparator();

        Button back = findViewById(R.id.assignments_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assignments.this, MainActivity.class));
            }
        });

        ListView assignmentListView = findViewById(R.id.assignment_list);
        List<AssignmentData> backingList = new ArrayList<>();
        ArrayAdapter<AssignmentData> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, backingList);
        assignmentListView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    comp.setMode('D');
                } else {
                    comp.setMode('C');
                }
                comp.sort(backingList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                comp.setMode('D');
            }
        });

        EditText enterName = findViewById(R.id.assignment_name);
        EditText enterDate = findViewById(R.id.due_date);
        EditText enterClass = findViewById(R.id.assignment_class);


        Button add = findViewById(R.id.assignment_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = enterName.getText().toString();
                String dateStr = enterDate.getText().toString();
                String classStr = enterClass.getText().toString();
                backingList.add(new AssignmentData(nameStr, dateStr, classStr));
                comp.sort(backingList);
                adapter.notifyDataSetChanged();

                enterName.setText("");
                enterDate.setText("");
                enterClass.setText("");
            }
        });

        Button edit = findViewById(R.id.assignment_edit);
        assignmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                AssignmentData now = backingList.get(position);
                enterName.setText(now.getTitle());
                enterDate.setText(now.getDueDate());
                enterClass.setText(now.getAssociatedClass());

                Toast.makeText(Assignments.this, now.getTitle() + " has been selected to be edited or deleted.", Toast.LENGTH_SHORT).show();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    String nameStr = enterName.getText().toString();
                    String dateStr = enterDate.getText().toString();
                    String classStr = enterClass.getText().toString();
                    backingList.set(selected, new AssignmentData(nameStr, dateStr, classStr));
                    comp.sort(backingList);
                    adapter.notifyDataSetChanged();

                    enterClass.setText("");
                    enterDate.setText("");
                    enterName.setText("");
                    selected = -1;
                }
            }
        });

        Button del = findViewById(R.id.assignment_delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    backingList.remove(selected);
                    adapter.notifyDataSetChanged();
                    selected = -1;

                    enterClass.setText("");
                    enterDate.setText("");
                    enterName.setText("");
                }
            }
        });
    }

}