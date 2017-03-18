package com.example.universidad.pokedex;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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
    String image;

    public Pokemon(int entry, String name, String description, String image) {
        this.entry = entry;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public static List<Pokemon> pokemon = new ArrayList<Pokemon>();

    public static void requestPokemon(int gen, final boolean isRegion, final Context context) {
        String url = isRegion ? "http://pokeapi.co/api/v2/pokedex/N/" : "http://pokeapi.co/api/v2/generation/N/";
        url = url.replace("N", "" + (isRegion ? gen + 1: gen));

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (isRegion) {
                        JSONArray entries = response.getJSONArray("pokemon_entries");
                        for (int i = 0; i < 1/*entries.length()*/; i++) {
                            JSONObject entry = entries.getJSONObject(i);
                            JSONObject species = entry.getJSONObject("pokemon_species");
                            int number = entry.getInt("entry_number");

                            Pokemon.requestSpecies(species, i, context);
                            Pokemon pokemon = new Pokemon(number, null, null, null);
                            Pokemon.pokemon.add(pokemon);
                        }
                    } else {
                        JSONArray pokemon_species = response.getJSONArray("pokemon_species");
                        for (int i = 0; i < pokemon_species.length(); i++) {
                            JSONObject species = pokemon_species.getJSONObject(i);

                            Pokemon.requestSpecies(species, i, context);
                        }
                    }
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

    private static void requestSpecies(JSONObject species, final int index, final Context context) {
        try {
            String url = species.getString("url");
            final JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Pokemon pokemon = Pokemon.pokemon.get(index);

                        JSONArray names = response.getJSONArray("names");
                        for (int i = 0; i < names.length(); i++) {
                            JSONObject localizedName = names.getJSONObject(i);
                            JSONObject language = localizedName.getJSONObject("language");
                            String languageName = language.getString("name");
                            if (languageName.equals("en")) {
                                String name = localizedName.getString("name");
                                pokemon.name = name;
                                break;
                            }
                        }

                        JSONArray descriptions = response.getJSONArray("flavor_text_entries");
                        for (int i = 0; i < descriptions.length(); i++) {
                            JSONObject description = descriptions.getJSONObject(i);
                            JSONObject language = description.getJSONObject("language");
                            String languageName = language.getString("name");
                            if (languageName.equals("en")) {
                                String text = description.getString("flavor_text");
                                pokemon.description = text;
                                break;
                            }
                        }

                        JSONArray varieties = response.getJSONArray("varieties");
                        for (int i = 0; i < varieties.length(); i++) {
                            JSONObject variety = varieties.getJSONObject(i);
                            boolean isDefault = variety.getBoolean("is_default");
                            if (isDefault) {
                                JSONObject pokemonJSON = variety.getJSONObject("pokemon");

                                Pokemon.requestImage(pokemonJSON, index, context);
                                break;
                            }
                        }
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

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private static void requestImage(JSONObject pokemon, final int index, Context context) {
        try {
            String url = pokemon.getString("url");
            JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject sprites = response.getJSONObject("sprites");
                        String url = sprites.getString("front_default");

                        Pokemon.pokemon.get(index).image = url;
                        Pokemon.pokemon.size();
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
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

}
