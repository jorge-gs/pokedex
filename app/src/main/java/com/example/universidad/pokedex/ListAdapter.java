package com.example.universidad.pokedex;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by universidad on 3/15/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    boolean regions = true;

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
        return SortItem.regions.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SortItem[] items = this.regions ? SortItem.regions : SortItem.generations;

        Drawable image = ResourcesCompat.getDrawable(holder.itemView.getResources(), items[position].drawable, null);
        holder.image.setImageDrawable(image);
        holder.name.setText(holder.itemView.getResources().getString(items[position].name));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: set callback
            }
        });
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
}