package com.example.universidad.pokedex;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by universidad on 3/15/17.
 */

public class SortItem {
    int drawable;
    int name;

    public SortItem(int image, int name) {
        this.drawable = image;
        this.name = name;
    }

    public static SortItem[] regions = {
            new SortItem(R.drawable.region_kanto, R.string.region_kanto),
            new SortItem(R.drawable.region_johto, R.string.region_johto),
            new SortItem(R.drawable.region_hoenn, R.string.region_hoenn),
            new SortItem(R.drawable.region_sinnoh, R.string.region_sinnoh),
            new SortItem(R.drawable.region_unova, R.string.region_unova),
            new SortItem(R.drawable.region_kalos, R.string.region_kalos),
            new SortItem(R.drawable.region_alola, R.string.region_alola)
    };

    public static SortItem[] generations = {
            new SortItem(R.drawable.gen_one, R.string.generation_one),
            new SortItem(R.drawable.gen_two, R.string.generation_two),
            new SortItem(R.drawable.gen_three, R.string.generation_three),
            new SortItem(R.drawable.gen_four, R.string.generation_four),
            new SortItem(R.drawable.gen_five, R.string.generation_five),
            new SortItem(R.drawable.gen_six, R.string.generation_six),
            new SortItem(R.drawable.gen_seven, R.string.generation_seven)
    };

    public static List<JSONObject> pokedex = new ArrayList<JSONObject>();
    public static List<JSONObject> generation = new ArrayList<JSONObject>();
    public static List<JSONObject> species = new ArrayList<JSONObject>();
    public static List<JSONObject> varieties = new ArrayList<JSONObject>();
    public static List<JSONObject> pokemon = new ArrayList<JSONObject>();
    public static List<String> imageURL = new ArrayList<String>();

    public void getPokedex() {
        JsonObjectRequest request = new JsonObjectRequest("http://pokeapi.co/api/v2/pokedex/2/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray entries = response.getJSONArray("pokemon_entries");
                    for (int i = 0; i < entries.length(); i++) {
                        JSONObject entry = entries.getJSONObject(i);
                        JSONObject species = entry.getJSONObject("pokemon_species");
                        SortItem.species.add(species);
                        String url = species.getString("url");
                        getSpecies(url);
                    }

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void getSpecies(String url) {
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pokemon.add(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
