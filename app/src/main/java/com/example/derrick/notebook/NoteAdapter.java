package com.example.derrick.notebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derrick on 2016/10/13.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    
    public static class ViewHolder{
        TextView note_title;
        TextView note_text;
        ImageView note_icon;
    }


    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Note note =getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){

            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.note_title= (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.note_text= (TextView) convertView.findViewById(R.id.item_body);
            viewHolder.note_icon= (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.note_title.setText(note.getTitle());
        viewHolder.note_text.setText(note.getMessage());
        viewHolder.note_icon.setImageResource(note.getDrawable());



        return  convertView;
    }
}
