package com.wipro.data;

import com.wipro.data.db.model.Facts;

import java.util.List;

public interface IDataManager {
    void fetchFacts(boolean forceLoad, LoadFactsCallback callback);

    boolean isAppInitialised();

    void setAppInitialised();

    interface LoadFactsCallback {

        void onFactsLoaded(List<Facts> facts);

        void onDataNotAvailable(String message);
    }

}
