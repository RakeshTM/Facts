package com.wipro.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wipro.data.db.model.Facts;

import java.util.List;

@Dao
public interface FactsDao {

    @Transaction
    @Query(value = "SELECT * from tblTitle")
    List<Facts> getFactsData();

}
