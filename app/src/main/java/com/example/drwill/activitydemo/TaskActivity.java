package com.example.drwill.activitydemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class TaskActivity extends AppCompatActivity {

    TextView messageView;
    ListView taskList;
    ArrayList<String> tasks=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Set<String> set;
    SharedPreferences prefs;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        loadTasks();

        messageView = (TextView)findViewById(R.id.messageView);
        taskList =(ListView)findViewById(R.id.taskList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(adapter);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        messageView.setText(message);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                CharSequence text = "Task: " + tasks.get(position);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        tasks.add(message);
        Collections.sort(tasks);
        System.out.println("tasks = " + tasks);
        adapter.notifyDataSetChanged();

        saveTasks();

    }


    void loadTasks(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        set = prefs.getStringSet("tasks", null);
        if (set == null){
            System.out.println("Nothing there!");
        }else {
            System.out.println("set = " + set);
            tasks.addAll(set);
            Collections.sort(tasks);
        }
    }
    void saveTasks(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        set = new HashSet<String>();
        set.addAll(tasks);
        editor.putStringSet("tasks", set);
        editor.apply();
    }
}
