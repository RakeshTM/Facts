package com.wipro.data.network.service;

import com.wipro.data.network.model.NetworkFactsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRequest {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<NetworkFactsModel> fetchFacts();
}
