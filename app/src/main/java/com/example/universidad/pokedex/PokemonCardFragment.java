package com.example.universidad.pokedex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PokemonCardFragment extends Fragment {
    public OnPokemonPressListener listener;

    public PokemonCardFragment() {
        // Required empty public constructor
    }

    public static PokemonCardFragment newInstance() {
        PokemonCardFragment fragment = new PokemonCardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_card, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPokemonPressListener) {
            this.listener = (OnPokemonPressListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    public interface OnPokemonPressListener {
        public void onPokemonPress(int index);
    }
}
