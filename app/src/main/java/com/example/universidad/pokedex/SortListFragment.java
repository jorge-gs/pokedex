package com.example.universidad.pokedex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SortListFragment extends Fragment {
    public ListAdapter adapter = new ListAdapter();

    public SortListFragment() {
        // Required empty public constructor
    }

    public static SortListFragment newInstance() {
        SortListFragment fragment = new SortListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recycler = (RecyclerView) view;
            recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recycler.setAdapter(adapter);
        }

        return view;
    }
}
