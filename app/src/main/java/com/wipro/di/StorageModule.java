package com.wipro.di;

import android.content.Context;

import com.wipro.data.DataManager;
import com.wipro.data.IDataManager;
import com.wipro.data.db.DBHelper;
import com.wipro.data.db.IDBHelper;
import com.wipro.data.network.INetworkHelper;
import com.wipro.data.network.NetworkHelper;
import com.wipro.data.sp.ISPHelper;
import com.wipro.data.sp.SPHelper;
import com.wipro.di.annotations.ApplicationContext;

import dagger.Binds;
import dagger.Module;

@Module
public interface StorageModule {

    @Binds
    @ApplicationContext
    Context bindContext(Context context);

    @Binds
    INetworkHelper bindNetworkHelper(NetworkHelper nh);

    @Binds
    IDBHelper bindDBHelper(DBHelper dh);

    @Binds
    ISPHelper bindSPHelper(SPHelper sh);

    @Binds
    IDataManager bindDataManager(DataManager dm);

}
