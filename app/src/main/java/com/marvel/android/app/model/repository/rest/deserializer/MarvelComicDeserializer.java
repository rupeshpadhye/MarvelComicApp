package com.marvel.android.app.model.repository.rest.deserializer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.marvel.android.app.model.entities.Comic;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class MarvelComicDeserializer implements JsonDeserializer<List<Comic>> {

    @Override
    public List<Comic> deserialize(JsonElement je, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
        Type listType = new TypeToken<List<Comic>>() {}.getType();
        JsonElement data = je.getAsJsonObject().get("data");
        JsonElement results = data.getAsJsonObject().get("results");
        Log.d("RUPESH comics",results.toString());
        return new Gson().fromJson(results, listType);
    }
}