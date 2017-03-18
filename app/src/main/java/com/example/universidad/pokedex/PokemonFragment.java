package com.example.universidad.pokedex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class PokemonFragment extends Fragment {
    public PokemonFragment() {
        // Required empty public constructor
    }

    int index;

    public static PokemonFragment newInstance(int index) {
        PokemonFragment fragment = new PokemonFragment();
        fragment.index = index;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.pokemon_sprite);
        imageView.setImageUrl(Pokemon.pokemon.get(index).image, VolleySingleton.getInstance(getContext()).getImageLoader());
        TextView text = (TextView) view.findViewById(R.id.pokemon_name);
        text.setText(Pokemon.pokemon.get(index).name);
        TextView descrption = (TextView) view.findViewById(R.id.pokemon_description);
        descrption.setText(Pokemon.pokemon.get(index).description);

        return view;
    }
}
