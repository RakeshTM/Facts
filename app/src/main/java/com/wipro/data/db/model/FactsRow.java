package com.wipro.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.wipro.utils.StringUtils;

@Entity(tableName = "tblRow", foreignKeys = @ForeignKey(entity = FactsTitle.class, parentColumns = "titleId", childColumns = "titleId", onDelete = ForeignKey.CASCADE))
public class FactsRow {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    private int rowId;

    @ColumnInfo(name = "titleId")
    private int titleId;

    @ColumnInfo(name = "rowTitle")
    private String rowTitle;

    @ColumnInfo(name = "rowDescription")
    private String rowDescription;

    @ColumnInfo(name = "rowImageUrl")
    private String rowImageUrl;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getRowTitle() {
        return rowTitle;
    }

    public void setRowTitle(String rowTitle) {
        this.rowTitle = rowTitle;
    }

    public String getRowDescription() {
        return rowDescription;
    }

    public void setRowDescription(String rowDescription) {
        this.rowDescription = rowDescription;
    }

    public String getRowImageUrl() {
        return rowImageUrl;
    }

    public void setRowImageUrl(String rowImageUrl) {
        this.rowImageUrl = rowImageUrl;
    }

    public boolean check(FactsRow factsRow) {
        return StringUtils.compare(getRowTitle(), factsRow.getRowTitle())
                && StringUtils.compare(getRowDescription(), factsRow.getRowDescription())
                && StringUtils.compare(getRowImageUrl(), factsRow.getRowImageUrl());
    }

}
