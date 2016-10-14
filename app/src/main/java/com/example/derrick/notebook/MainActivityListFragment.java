package com.example.derrick.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.input;

public class MainActivityListFragment extends ListFragment {
    private List<Note> notes;
    private NoteAdapter adapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         notes = new ArrayList();
        notes.add(new Note("Note1 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","this is a body of our note bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", Note.Category.PERSONAL));
        notes.add(new Note("Note2","this is a body of our note", Note.Category.TECHNICAL));
        notes.add(new Note("Note3","this is a body of our note", Note.Category.QUOTE));
        notes.add(new Note("Note4","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note5","this is a body of our note", Note.Category.PERSONAL));
        notes.add(new Note("Note6","this is a body of our note", Note.Category.TECHNICAL));
        notes.add(new Note("Note7","this is a body of our note", Note.Category.QUOTE));
        notes.add(new Note("Note8","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note9","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note10","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note11","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note12","this is a body of our note", Note.Category.FINANCE));
        notes.add(new Note("Note13","this is a body of our note", Note.Category.FINANCE));
         adapter = new NoteAdapter(getActivity(),notes);
        setListAdapter(adapter);

        //getLIstView().setDivider();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchDetailActivity(position);
    }
    private void  launchDetailActivity(int position){
        Note note =(Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(),NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA,note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA,note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA,note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA,note.getId());

        startActivity(intent);
    }
}
