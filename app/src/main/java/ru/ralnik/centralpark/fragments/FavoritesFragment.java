package ru.ralnik.centralpark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ru.ralnik.centralpark.R;
import ru.ralnik.model.Flat;

public class FavoritesFragment extends Fragment {

    private final Flat flat;

    public FavoritesFragment(Flat flat) {
        this.flat = flat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }
}
