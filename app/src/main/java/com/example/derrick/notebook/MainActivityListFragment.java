package com.example.derrick.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        NotebookAdapter dbAdapter =new NotebookAdapter(getActivity());
        dbAdapter.open();
        notes = dbAdapter.getAllNotes();
        dbAdapter.close();
         adapter = new NoteAdapter(getActivity(),notes);
        setListAdapter(adapter);
        registerForContextMenu(getListView());
        //getLIstView().setDivider();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchDetailActivity(MainActivity.FragmentToLaunch.VIEW,position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater =getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
                int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        Note note  =(Note)getListAdapter().getItem(position);
        switch (item.getItemId()){
            case R.id.edit:
                launchDetailActivity(MainActivity.FragmentToLaunch.EDIT,position);
                return true;
            case R.id.delete:
                NotebookAdapter notebookAdapter= new NotebookAdapter(getActivity());
                notebookAdapter.open();
                notebookAdapter.deleteNote(note.getId());


                notes.clear();
                notes.addAll(notebookAdapter.getAllNotes());
                adapter.notifyDataSetChanged();
                notebookAdapter.close();
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void  launchDetailActivity(MainActivity.FragmentToLaunch ftl, int  position){
        Note note =(Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(),NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA,note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA,note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA,note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA,note.getId());

        switch(ftl){
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_EXTRA,MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_EXTRA,MainActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);
    }
}
