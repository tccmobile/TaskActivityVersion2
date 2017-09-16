package com.example.drwill.activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.drwill.activitydemo.MESSAGE";

    Button clickButton;
    EditText taskText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickButton = (Button)findViewById(R.id.clickButton);
        taskText = (EditText)findViewById(R.id.taskText);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,TaskActivity.class);
                String task = taskText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE,task);
                startActivity(intent);
            }
        });
    }
}
