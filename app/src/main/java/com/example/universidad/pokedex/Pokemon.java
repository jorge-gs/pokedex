package com.example.universidad.pokedex;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by universidad on 3/17/17.
 */

public class Pokemon {
    int entry;
    String name;
    String description;
    String imageURL;

    public Pokemon(int entry, String name, String description, String image) {
        this.entry = entry;
        this.name = name;
        this.description = description;
        this.imageURL = image;
    }

    public static List<Pokemon> pokemonList = new ArrayList<Pokemon>();

    public static void requestPokemon(int gen, boolean region, final Context context) {
        String url = region ? "http://pokeapi.co/api/v2/pokedex/N/" : "http://pokeapi.co/api/v2/generation/N/";
        url = url.replace("N", "" + (region ? gen + 1: gen));

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray entries = response.getJSONArray("entries");
                    for (int i = 0; i < entries.length(); i++) {
                        int entry_number = entries.getJSONObject(i).getInt("entry_number");

                        String pokemon_name = null;
                        JSONObject pokemon_species = entries.getJSONObject(i).getJSONObject("pokemon_species");
                        JSONArray names = pokemon_species.getJSONArray("names");
                        for (int j = 0; j < names.length(); j++) {
                            JSONObject name = names.getJSONObject(j);
                            JSONObject language = name.getJSONObject("language");
                            if (language.getString("name").equals("en")) {
                                pokemon_name = language.getString("name");
                                break;
                            }
                        }

                        String description = null;
                        JSONArray flavor_texts = pokemon_species.getJSONArray("flavor_text_entries");
                        for (int j = 0; j < flavor_texts.length(); j++) {
                            JSONObject flavor_text = flavor_texts.getJSONObject(j);
                            JSONObject language = flavor_text.getJSONObject("language");
                            if (language.getString("name").equals("en")) {
                                description = flavor_text.getString("flavor_text");
                                break;
                            }
                        }

                        String pokemonURL = null;
                        JSONArray varieties = pokemon_species.getJSONArray("varieties");
                        for (int j = 0; j < varieties.length(); j++) {
                            JSONObject variety = varieties.getJSONObject(j);
                            if (variety.getBoolean("is_default")) {
                                JSONObject pokemon = variety.getJSONObject("pokemonList");
                                pokemonURL = pokemon.getString("url");
                                break;
                            }
                        }

                        if (pokemon_name != null && description != null && pokemonURL != null) {
                            createPokemon(entry_number, pokemon_name, description, pokemonURL, context);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        queue.add(request);
    }

    private static void createPokemon(final int entry, final String name, final String description, String pokemonURL, Context context) {
        final JsonObjectRequest request = new JsonObjectRequest(pokemonURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject sprites = response.getJSONObject("sprites");
                    String url = sprites.getString("front_default");
                    Pokemon.pokemonList.add(new Pokemon(entry, name, description, url));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        queue.add(request);
    }
}
