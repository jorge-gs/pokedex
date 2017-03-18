package com.example.universidad.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by universidad on 3/17/17.
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView networkImage;
        TextView description;

        public ViewHolder(View view) {
            super(view);

            this.networkImage = (NetworkImageView) view.findViewById(R.id.pokemon_card_image);
            this.description = (TextView) view.findViewById(R.id.pokemon_card_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pokemon_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.networkImage.setImageUrl(Pokemon.pokemon.get(position).image, VolleySingleton.getInstance(holder.itemView.getContext()).getImageLoader());
        holder.description.setText(Pokemon.pokemon.get(position).entry + ". " + Pokemon.pokemon.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return Pokemon.pokemon.size();
    }
}
