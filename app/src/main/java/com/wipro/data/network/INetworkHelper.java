package com.wipro.data.network;

import com.wipro.data.network.model.NetworkFactsModel;

public interface INetworkHelper {

    interface NetworkFactsCallback {

        void onFactsLoaded(NetworkFactsModel model);

        void onDataNotAvailable(String message);
    }

    void fetchFacts(NetworkFactsCallback callback);
}
