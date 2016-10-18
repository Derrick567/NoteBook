package com.example.derrick.notebook;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.Build.VERSION_CODES.N;
import static android.provider.CallLog.Calls.NEW;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA="New Note";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        createAndAddFragment();
    }


    private void createAndAddFragment(){
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch=(MainActivity.FragmentToLaunch)
                intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_EXTRA);
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (fragmentToLaunch){
            case VIEW:
                setTitle(R.string.view_fragment_title);
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                fragmentTransaction.add(R.id.note_container,noteViewFragment,"NOTE_VIEW_FRAGMENT");
                break;
            case EDIT:
                setTitle(R.string.edit_fragment_title);
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                fragmentTransaction.add(R.id.note_container,noteEditFragment,"NOTE_EDIT_FRAGMENT");
                break;
            case CREATE:
                setTitle(R.string.create_fragment_title);
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA,true);
                noteCreateFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.note_container,noteCreateFragment,"NOTE_CREATE_FRAGMENT");
                break;
        }

        fragmentTransaction.commit();


    }
}
