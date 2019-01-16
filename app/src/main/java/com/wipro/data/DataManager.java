package com.wipro.data;

import com.wipro.data.db.IDBHelper;
import com.wipro.data.db.model.Facts;
import com.wipro.data.network.INetworkHelper;
import com.wipro.data.network.model.NetworkFactsModel;
import com.wipro.data.sp.ISPHelper;
import com.wipro.mapper.ModelMapper;
import com.wipro.utils.ThreadExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DataManager implements IDataManager {

    private final ISPHelper mSPHelper_;
    private final INetworkHelper mNetworkHelper_;
    private final IDBHelper mDbHelper_;
    private final ThreadExecutors mExecutors_;

    @Inject
    public DataManager(INetworkHelper networkHelper, IDBHelper dbHelper, ISPHelper spHelper, ThreadExecutors executors) {
        mNetworkHelper_ = networkHelper;
        mDbHelper_ = dbHelper;
        mSPHelper_ = spHelper;
        this.mExecutors_ = executors;
    }

    @Override
    public void fetchFacts(boolean forceLoad, LoadFactsCallback callback) {
        if (forceLoad) {
            fetchDataFromNetwork(callback);
        } else {
            fetchDataFromDB(callback);
        }
    }

    private void fetchDataFromDB(final LoadFactsCallback callback) {
        mExecutors_.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                loadDBData(callback);
            }
        });
    }

    private void fetchDataFromNetwork(final LoadFactsCallback callback) {
        mNetworkHelper_.fetchFacts(new INetworkHelper.NetworkFactsCallback() {
            @Override
            public void onFactsLoaded(NetworkFactsModel networkModel) {
                modelMap(networkModel, callback);
            }

            @Override
            public void onDataNotAvailable(String message) {
                callback.onDataNotAvailable(message);
            }
        });
    }

    private void modelMap(final NetworkFactsModel networkModel, final LoadFactsCallback callback) {
        mExecutors_.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final ModelMapper.FactsDBModel factsDBModel = ModelMapper.convertNetworkToDBModel(networkModel);
                refreshLocalDataSource(factsDBModel);
                loadDBData(callback);
            }
        });
    }

    private void loadDBData(final LoadFactsCallback callback) {
        final List<Facts> facts = getFactsData();
        mExecutors_.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                callback.onFactsLoaded(facts);
            }
        });
    }

    private List<Facts> getFactsData() {
        return mDbHelper_.fetchFactsData();
    }

    private void refreshLocalDataSource(ModelMapper.FactsDBModel dbModel) {
        mDbHelper_.deleteData();
        mDbHelper_.saveFactsTitle(dbModel.getFactsTitles());
        mDbHelper_.saveFactsRow(dbModel.getFactsRows());
    }

    @Override
    public boolean isAppInitialised() {
        return mSPHelper_.isAppInitialized();
    }

    @Override
    public void setAppInitialised() {
        mSPHelper_.setAppInitialized();
    }
}
