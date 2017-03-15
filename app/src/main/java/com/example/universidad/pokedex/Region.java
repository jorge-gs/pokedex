package com.example.universidad.pokedex;

import android.graphics.drawable.Drawable;

/**
 * Created by universidad on 3/15/17.
 */

public class Region {
    int drawable;
    int name;

    public Region(int image, int name) {
        this.drawable = image;
        this.name = name;
    }

    public static Region[] regions = {
            new Region(R.drawable.region_kanto, R.string.region_kanto),
            new Region(R.drawable.region_johto, R.string.region_johto),
            new Region(R.drawable.region_hoenn, R.string.region_hoenn),
            new Region(R.drawable.region_sinnoh, R.string.region_sinnoh),
            new Region(R.drawable.region_unova, R.string.region_unova),
            new Region(R.drawable.region_kalos, R.string.region_kalos),
            new Region(R.drawable.region_alola, R.string.region_alola)
    };
}
