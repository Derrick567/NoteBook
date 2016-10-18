package com.example.derrick.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public static final String NOTE_ID_EXTRA="com.example.derrick.Identifier";
    public static final String NOTE_TITLE_EXTRA="com.example.derrick.Title";
    public static final String NOTE_MESSAGE_EXTRA="com.example.derrick.Message";
    public static final String NOTE_CATEGORY_EXTRA="com.example.derrick.Category";
    public static final String NOTE_FRAGMENT_TO_EXTRA="com.example.derrick.fragment_to_load";

    public enum  FragmentToLaunch{VIEW,EDIT ,CREATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
            }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings){return true;}
        else if(id==R.id.action_add_note){
            Intent intent = new Intent(this,NoteDetailActivity.class);
            intent.putExtra(NOTE_FRAGMENT_TO_EXTRA , FragmentToLaunch.CREATE);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);


    }
}