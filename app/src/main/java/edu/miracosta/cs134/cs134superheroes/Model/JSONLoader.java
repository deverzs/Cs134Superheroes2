package edu.miracosta.cs134.cs134superheroes.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONLoader {
    public static final String TAG = "LOADER" ;
    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<Superheroes> loadJSONFromAsset(Context context) throws IOException {
        List<Superheroes> allHeroesList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("cs134superheroes.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        Log.e(TAG, "CALLING JSON ++++ " );
        try {
            JSONObject jsonRootObject = new JSONObject(json);
            //get the JSONarray
            JSONArray allHeroesJSON = jsonRootObject.getJSONArray("CS134Superheroes");

            // DONE: Loop through all the countries in the JSON data, create a Country
            // DONE: object for each and add the object to the allCountriesList
            JSONObject heroJSON;
            //how many items in array
            int count = allHeroesJSON.length();
            String name, superpower, oneThing, fileName;

            for (int i = 0; i < count ; i++)
            {
                Log.e(TAG, "ADDING ++++ " + i);
                //pull out the objects from JSONarray
                heroJSON = allHeroesJSON.getJSONObject(i);
                //get the data fro the JSONobject
                name = heroJSON.getString("Name"); //these have to be cap cuz it has to match the file
                superpower = heroJSON.getString("Superpower");
                oneThing = heroJSON.getString("OneThing");
                fileName = heroJSON.getString("FileName");
                allHeroesList.add(new Superheroes(name, oneThing, superpower, fileName));

            }






        } catch (JSONException e) {
            Log.e("JSON looader error", e.getMessage());
        }

        return allHeroesList;
    }
}
