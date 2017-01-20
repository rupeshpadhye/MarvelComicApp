package com.marvel.android.app.model.repository.rest.endpoints.interceptor;


import com.marvel.android.app.model.repository.rest.RestDataSource;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by RUPESH on 1/20/2017.
 */

public class SignInInterceptor implements Interceptor {

    private final String mApiKey;
    private final String mApiSecret;

    public SignInInterceptor(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String marvelHash = generateMarvelHash(mApiKey, mApiSecret);
        Request oldRequest = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

       authorizedUrlBuilder.addQueryParameter(RestDataSource.PARAM_API_KEY, mApiKey)
                .addQueryParameter(RestDataSource.PARAM_TIMESTAMP, getUnixTimeStamp())
                .addQueryParameter(RestDataSource.PARAM_HASH, marvelHash);

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }

    public static String generateMarvelHash (String publicKey, String privateKey) {
        String marvelHash = "";
        try {
            String timeStamp    = getUnixTimeStamp();
            String marvelData   = timeStamp + privateKey + publicKey;

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(marvelData.getBytes());

            StringBuilder stringBuilder = new StringBuilder(2 * hash.length);

            for (byte b : hash)
                stringBuilder.append(String.format("%02x", b&0xff));

            marvelHash = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("[DEBUG]" + " MarvelApiUtils generateMarvelHash - " +
                    "NoSuchAlgorithmException");
        }

        return marvelHash;
    }

    public static String getUnixTimeStamp () {
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
