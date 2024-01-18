// MainActivity.java

package com.example.firstprojectithink;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Assignment> assignmentList;
    private AssignmentAdapter assignmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignmentList = new ArrayList<>();
        assignmentAdapter = new AssignmentAdapter(assignmentList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(assignmentAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle adding new assignment
                // You can open a new activity or dialog for adding assignment details
            }
        });

        // Add sample assignments for testing
        assignmentList.add(new Assignment("Assignment 1", "Math", "2023-02-01"));
        assignmentList.add(new Assignment("Assignment 2", "History", "2023-02-15"));
        assignmentList.add(new Assignment("Assignment 3", "Physics", "2023-03-01"));

        // Sort assignments by due date initially
        sortAssignmentsByDueDate();
    }

    // Method to sort assignments by due date
    private void sortAssignmentsByDueDate() {
        Collections.sort(assignmentList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment assignment1, Assignment assignment2) {
                return assignment1.getDueDate().compareTo(assignment2.getDueDate());
            }
        });
        assignmentAdapter.notifyDataSetChanged();
    }

    // Method to sort assignments by course
    private void sortAssignmentsByCourse() {
        Collections.sort(assignmentList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment assignment1, Assignment assignment2) {
                return assignment1.getCourse().compareTo(assignment2.getCourse());
            }
        });
        assignmentAdapter.notifyDataSetChanged();
    }
}
