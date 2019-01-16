package com.wipro.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tblTitle")
public class FactsTitle {

    @PrimaryKey
    @ColumnInfo(name = "titleId")
    private int titleId;

    @ColumnInfo(name = "title")
    private String title;

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
