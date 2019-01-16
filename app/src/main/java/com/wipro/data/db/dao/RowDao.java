package com.wipro.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wipro.data.db.model.FactsRow;

import java.util.List;

@Dao
public interface RowDao {

    @Insert
    void insertRows(List<FactsRow> factsRows);

    @Query("DELETE FROM tblRow")
    void deleteRows();
}
