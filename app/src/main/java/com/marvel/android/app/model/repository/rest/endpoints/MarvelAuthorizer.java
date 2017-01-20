package com.marvel.android.app.model.repository.rest.endpoints;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class MarvelAuthorizer {
    private String mApiClient;
    private String mApiSecret;

    public MarvelAuthorizer(String apiClient, String apiSecret) {
        mApiClient = apiClient;
        mApiSecret = apiSecret;
    }

    public void setApiClient(String mApiClient) {
        this.mApiClient = mApiClient;
    }

    public void setApiSecret(String mApiSecret) {
        this.mApiSecret = mApiSecret;
    }

    public String getApiClient() {
        return mApiClient;
    }

    public String getApiSecret() {
        return mApiSecret;
    }
}
