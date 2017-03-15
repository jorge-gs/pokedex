package com.example.universidad.pokedex;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by universidad on 3/15/17.
 */

public class RegionListAdapter extends RecyclerView.Adapter<RegionListAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(View view) {
            super(view);

            this.image = (ImageView) view.findViewById(R.id.region_preview);
            this.name = (TextView) view.findViewById(R.id.region_name);
        }
    }

    @Override
    public int getItemCount() {
        return Region.regions.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drawable image = ResourcesCompat.getDrawable(holder.itemView.getResources(), Region.regions[position].drawable, null);
        holder.image.setImageDrawable(image);
        holder.name.setText(holder.itemView.getResources().getString(Region.regions[position].name));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: set callback
            }
        });
    }

    @Override
    public RegionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_region_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
}
