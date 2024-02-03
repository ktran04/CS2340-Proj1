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

public class ToDoList<ItemEntry> extends AppCompatActivity {
    int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do);
        Button back = findViewById(R.id.back_to_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToDoList.this, MainActivity.class));
            }
        });

        ListView toDoListView = (ListView) findViewById(R.id.todo_list);
        ArrayList<String> backingList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, backingList);
        toDoListView.setAdapter(adapter);

        EditText item = findViewById(R.id.toDoItem);

        Button addItem = findViewById(R.id.todo_add);
        addItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String itemStr = item.getText().toString();

                backingList.add((itemStr));
                adapter.notifyDataSetChanged();

                item.setText("");
            }
        });

        Button edit = findViewById(R.id.todo_edit);
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
                String now;
                now = backingList.get(position);

                item.setText(now);

                Toast.makeText(ToDoList.this,"\"" + now + "\" has been selected to be edited or deleted.", Toast.LENGTH_SHORT).show();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {

                    backingList.set(selected, item.getText().toString());

                    adapter.notifyDataSetChanged();

                    item.setText("");
                    selected = -1;
                }
            }
        });

        Button del = findViewById(R.id.todo_delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1) {
                    backingList.remove(selected);
                    adapter.notifyDataSetChanged();
                    selected = -1;

                    item.setText("");
                }
            }
        });
    }
}