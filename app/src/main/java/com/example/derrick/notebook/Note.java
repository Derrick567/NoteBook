package com.example.derrick.notebook;

import static android.R.attr.id;

/**
 * Created by Derrick on 2016/10/13.
 */

public class Note {
    private String title;
    private String message;
    private long id;
    private long createDateMilli;
    private Category category;

    public enum Category {PERSONAL, TECHNICAL, QUOTE, FINANCE}


    public Note(String title, String message, Category category) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.createDateMilli = 0;
        this.id = 0;
    }

    public Note(String title, String message, long id, long createDateMilli, Category category) {
        this.title = title;
        this.message = message;
        this.id = id;
        this.createDateMilli = createDateMilli;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Category getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public long getCreateDateMilli() {
        return createDateMilli;
    }

    @Override
    public String toString() {
        return String.format("id : %f title : %s Message : %s IconId : %s", id, title, message, category.name());
    }

    public int getDrawable(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category category) {
        switch (category) {
            case PERSONAL:
                return R.mipmap.p;

            case TECHNICAL:
                return R.mipmap.t;

            case QUOTE:
                return R.mipmap.q;

            case FINANCE:
                return R.mipmap.f;

        }
        return R.mipmap.p;
    }
}
