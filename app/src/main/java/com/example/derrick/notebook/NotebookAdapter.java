package com.example.derrick.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.category;
import static android.R.attr.version;
import static com.example.derrick.notebook.R.mipmap.p;

/**
 * Created by Derrick on 2016/10/16.
 */

public class NotebookAdapter {
    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;
    private static final String NOTE_TABLE = "note";
    private static final String COLUM_ID = "_id";
    private static final String COLUM_TITLE = "title";
    private static final String COLUM_MESSAGE = "message";
    private static final String COLUM_CATEGORY = "category";
    private static final String COLUM_DATE = "date";

    private String[] allColumns = {COLUM_ID, COLUM_TITLE, COLUM_MESSAGE, COLUM_CATEGORY, COLUM_DATE};
    private static final String CREATE_TABLE_NOTE = "create table " + NOTE_TABLE + "("
            + COLUM_ID + " integer primary key autoincrement, "
            + COLUM_TITLE + " text not null, "
            + COLUM_MESSAGE + " text not null, "
            + COLUM_CATEGORY + " text not null, "
            + COLUM_DATE + " );";

    private SQLiteDatabase sqlDB;
    private Context context;
    private NotebookDBHelper notebookDBHelper;

    public NotebookAdapter(Context context) {
        this.context = context;
    }

    public NotebookAdapter open() throws android.database.SQLException {
        notebookDBHelper = new NotebookDBHelper(context);
        sqlDB = notebookDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        notebookDBHelper.close();
    }

    public Note createNote(String title, String message, Note.Category category) {
        ContentValues values = new ContentValues();
        values.put(COLUM_TITLE, title);
        values.put(COLUM_MESSAGE, message);
        values.put(COLUM_CATEGORY, category.name());
        values.put(COLUM_DATE, Calendar.getInstance().getTimeInMillis() + "");

        long insertId = sqlDB.insert(NOTE_TABLE, null, values);
        Cursor cursor = sqlDB.query(NOTE_TABLE, allColumns, COLUM_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Note note = cursorToNote(cursor);
        return note;
    }
    public long updateNote(long idToUpdate,String title,String message ,Note.Category category){
        ContentValues values = new ContentValues();
        values.put(COLUM_TITLE, title);
        values.put(COLUM_MESSAGE, message);
        values.put(COLUM_CATEGORY, category.name());
        values.put(COLUM_DATE, Calendar.getInstance().getTimeInMillis() + "");

        //should be return 1
        return sqlDB.update(NOTE_TABLE,values,COLUM_ID +" = " +idToUpdate,null);
    }

    public long deleteNote(long idToDelete){
        return sqlDB.delete(NOTE_TABLE,COLUM_ID + " = "+ idToDelete,null);
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();
        String sql = "select * from " + NOTE_TABLE + " ;";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
        }
        cursor.close();
        return notes;
    }



    private Note cursorToNote(Cursor cursor) {
        Note note = new Note(cursor.getString(1), cursor.getString(2), Note.Category.valueOf(cursor.getString(3)),
                cursor.getLong(0), cursor.getLong(4));

        return note;
    }

    private static class NotebookDBHelper extends SQLiteOpenHelper {
        public NotebookDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_NOTE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(NotebookAdapter.class.getName(), "Updating db from version " + oldVersion + "to"
                    + newVersion + " which will destroy all data.");

            db.execSQL("DROP TABLE IF EXISTS" + NOTE_TABLE);
            onCreate(db);
        }
    }
}
