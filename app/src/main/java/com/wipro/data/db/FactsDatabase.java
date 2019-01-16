package com.wipro.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wipro.data.db.dao.FactsDao;
import com.wipro.data.db.dao.RowDao;
import com.wipro.data.db.dao.TitleDao;
import com.wipro.data.db.model.FactsRow;
import com.wipro.data.db.model.FactsTitle;

@Database(entities = {FactsTitle.class, FactsRow.class}, version = 1)
public abstract class FactsDatabase extends RoomDatabase {

    abstract FactsDao factsDao();

    abstract RowDao rowDao();

    abstract TitleDao titleDao();
}
