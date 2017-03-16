package com.example.universidad.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ListFragment fragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        switch (item.getItemId()) {
            case R.id.sort_region:
                fragment.adapter.regions = true;
                Toast.makeText(getBaseContext(), "Regional Pokédex", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sort_generation:
                fragment.adapter.regions = false;
                Toast.makeText(getBaseContext(), "Pokémon by generation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sort_national:
                Toast.makeText(getBaseContext(), "National Pokédex", Toast.LENGTH_SHORT).show();
                break;
        }

        fragment.adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction() {

    }
}
