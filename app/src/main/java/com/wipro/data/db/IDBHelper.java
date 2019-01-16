package com.wipro.data.db;

import com.wipro.data.db.model.Facts;
import com.wipro.data.db.model.FactsRow;
import com.wipro.data.db.model.FactsTitle;

import java.util.List;

public interface IDBHelper {
    void deleteData();

    void saveFactsTitle(List<FactsTitle> titles);

    void saveFactsRow(List<FactsRow> rows);

    List<Facts> fetchFactsData();
}
