package com.example.universidad.pokedex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CardFragment.OnFragmentInteractionListener, PokemonCardFragment.OnPokemonPressListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SortListFragment fragment = SortListFragment.newInstance();
        replaceFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        SortListFragment sortListFragment = (fragment instanceof SortListFragment) ? (SortListFragment) fragment : null ;
        if (sortListFragment == null) {
            sortListFragment = SortListFragment.newInstance();
            replaceFragment(sortListFragment);
        }

        switch (item.getItemId()) {
            case R.id.sort_region:
                sortListFragment.adapter.displaysRegions = true;
                Toast.makeText(getBaseContext(), "Regional Pokédex", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sort_generation:
                sortListFragment.adapter.displaysRegions = false;
                Toast.makeText(getBaseContext(), "Pokémon by generation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sort_national:
                Pokemon.requestPokemon(0, true, this);

                Toast.makeText(getBaseContext(), "National Pokédex", Toast.LENGTH_SHORT).show();
                return  super.onOptionsItemSelected(item);
        }

        sortListFragment.adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int position) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        SortListFragment sortListFragment = (fragment instanceof SortListFragment) ? (SortListFragment) fragment : null;
        if (sortListFragment != null) {
            Pokemon.requestPokemon(position + 1, sortListFragment.adapter.displaysRegions, this);
        }
    }

    @Override
    public void onPokemonPress(int index) {
        PokemonFragment fragment = PokemonFragment.newInstance(index);
        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
