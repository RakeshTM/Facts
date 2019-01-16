package com.wipro.data.db.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class Facts {

    @Embedded
    private FactsTitle factsTitle;

    @Relation(parentColumn = "titleId", entityColumn = "titleId", entity = FactsRow.class)
    private List<FactsRow> factsRows;

    public FactsTitle getFactsTitle() {
        return factsTitle;
    }

    public void setFactsTitle(FactsTitle factsTitle) {
        this.factsTitle = factsTitle;
    }

    public List<FactsRow> getFactsRows() {
        return factsRows;
    }

    public void setFactsRows(List<FactsRow> factsRows) {
        this.factsRows = factsRows;
    }
}
