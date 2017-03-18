package com.example.universidad.pokedex;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

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

    public static void requestPokemon(int gen, final boolean region, final Context context) {
        String url = region ? "http://pokeapi.co/api/v2/pokedex/N/" : "http://pokeapi.co/api/v2/generation/N/";
        url = url.replace("N", "" + (region ? gen + 1: gen));

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (region) {
                        JSONArray pokemon_entries = response.getJSONArray("pokemon_entries");
                        for (int i = 0; i < pokemon_entries.length(); i++) {
                            JSONObject pokemon_entry = pokemon_entries.getJSONObject(i);
                            JSONObject pokemon_species = pokemon_entry.getJSONObject("pokemon_species");
                            int entry_number = pokemon_entry.getInt("entry_number");
                            String url = pokemon_species.getString("url");

                            requestSpecies(url, entry_number, context);
                        }
                    } else  {
                        JSONArray species = response.getJSONArray("pokemon_species");
                        for (int i = 0; i < species.length(); i++) {
                            JSONObject pokemon_species = species.getJSONObject(i);
                            String url = pokemon_species.getString("url");
                            int entry_number = i + 1;

                            requestSpecies(url, entry_number, context);
                        }
                    }
                } catch (JSONException exception) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void requestSpecies(final String url, final int entry, final Context context) {
        final JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String pokemon_name = null;
                    JSONArray localizedNames = response.getJSONArray("names");
                    for (int i = 0; i < localizedNames.length(); i++) {
                        JSONObject localzedName = localizedNames.getJSONObject(i);
                        JSONObject language = localzedName.getJSONObject("language");
                        String language_name = language.getString("name");
                        if (language_name.equals("en")) {
                            pokemon_name = localzedName.getString("name");
                            break;
                        }
                    }

                    String pokemon_description = null;
                    JSONArray text_entries = response.getJSONArray("flavor_text_entries");
                    for (int i = 0; i < text_entries.length(); i++) {
                        JSONObject text_entry = text_entries.getJSONObject(i);
                        JSONObject language = text_entry.getJSONObject("language");
                        String language_name = language.getString("name");
                        if (language_name.equals("en")) {
                            pokemon_description = text_entry.getString("flavor_text");
                            break;
                        }
                    }

                    String pokemonURL = null;
                    JSONArray varieties = response.getJSONArray("varieties");
                    for (int i = 0; i < varieties.length(); i++) {
                        JSONObject variety = varieties.optJSONObject(i);
                        boolean is_default = variety.getBoolean("is_default");
                        if (is_default) {
                            JSONObject pokemon = variety.getJSONObject("pokemon");
                            pokemonURL = pokemon.getString("url");
                        }
                    }

                    requestImage(pokemonURL, entry, pokemon_name, pokemon_description, context);
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    public static void requestImage(String url, final int entry, final String name,  final String description, final Context context) {
        //Log.d("Pokemon", "Entry: " + entry + " Name: " + name + " url: " + url);

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Pokemon", "received response for: " + name);
                try {
                    JSONObject sprites = response.getJSONObject("sprites");
                    String url = sprites.getString("front_default");

                    Log.d("Pokemon", url);
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }
}
