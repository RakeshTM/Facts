package com.wipro.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wipro.data.db.model.FactsTitle;

import java.util.List;

@Dao
public interface TitleDao {

    @Insert
    void insertTitle(List<FactsTitle> titles);

    @Query("DELETE FROM tblTitle")
    void deleteTitles();
}
