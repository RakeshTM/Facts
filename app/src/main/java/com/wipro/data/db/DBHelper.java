package com.wipro.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.wipro.data.db.model.Facts;
import com.wipro.data.db.model.FactsRow;
import com.wipro.data.db.model.FactsTitle;
import com.wipro.di.annotations.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DBHelper implements IDBHelper {

    private final FactsDatabase mDatabase_;

    @Inject
    DBHelper(@ApplicationContext Context context) {
        this.mDatabase_ = Room.databaseBuilder(context, FactsDatabase.class, context.getPackageName() + ".db").build();
    }

    @Override
    public void saveFactsTitle(List<FactsTitle> titles) {
        mDatabase_.titleDao().insertTitle(titles);
    }

    @Override
    public void saveFactsRow(List<FactsRow> rows) {
        mDatabase_.rowDao().insertRows(rows);
    }

    @Override
    public void deleteData() {
        mDatabase_.titleDao().deleteTitles();
        mDatabase_.rowDao().deleteRows();
    }

    @Override
    public List<Facts> fetchFactsData() {
        return mDatabase_.factsDao().getFactsData();
    }
}