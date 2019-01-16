package com.wipro.data.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wipro.R;
import com.wipro.data.network.model.NetworkFactsModel;
import com.wipro.data.network.service.IRequest;
import com.wipro.di.annotations.ApplicationContext;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public final class NetworkHelper implements INetworkHelper {

    private final Retrofit mRetrofit_;

    @Inject
    NetworkHelper(@ApplicationContext Context context) {
        mRetrofit_ = new Retrofit.Builder().baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public void fetchFacts(final NetworkFactsCallback callback) {
        final IRequest request = mRetrofit_.create(IRequest.class);
        Call<NetworkFactsModel> call = request.fetchFacts();
        call.enqueue(new Callback<NetworkFactsModel>() {
            @Override
            public void onResponse(@NonNull Call<NetworkFactsModel> call, @NonNull Response<NetworkFactsModel> response) {
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable("Failed");
                    return;
                }
                NetworkFactsModel body = response.body();
                if (body == null) {
                    callback.onDataNotAvailable("Failed");
                    return;
                }
                callback.onFactsLoaded(body);

            }

            @Override
            public void onFailure(Call<NetworkFactsModel> call, Throwable t) {
                if(t instanceof ConnectException || t instanceof UnknownHostException) {
                    callback.onDataNotAvailable("No Connection");
                } else {
                    callback.onDataNotAvailable("Something went wrong");
                }
            }
        });


    }


}
