package com.example.derrick.notebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public static final String NOTE_ID_EXTRA="com.example.derrick.Note Identifier";
    public static final String NOTE_TITLE_EXTRA="com.example.derrick.Note Title";
    public static final String NOTE_MESSAGE_EXTRA="com.example.derrick.Note Message";
    public static final String NOTE_CATEGORY_EXTRA="com.example.derrick.Note Category";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}