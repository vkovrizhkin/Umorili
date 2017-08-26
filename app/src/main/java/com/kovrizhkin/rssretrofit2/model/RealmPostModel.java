package com.kovrizhkin.rssretrofit2.model;

import io.realm.RealmObject;

/**
 * Created by Kovrizhkin V.A. on 26.08.2017.
 */

public class RealmPostModel extends RealmObject {

    private String text;

    private String link;

   // private String name;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
