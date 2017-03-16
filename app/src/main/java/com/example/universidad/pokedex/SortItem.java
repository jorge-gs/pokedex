package com.example.universidad.pokedex;

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
}
