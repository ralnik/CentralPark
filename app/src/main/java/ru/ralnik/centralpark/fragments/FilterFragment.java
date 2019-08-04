package ru.ralnik.centralpark.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.ralnik.centralpark.R;
import ru.ralnik.centralpark.fragments.TagsFragment;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;


public class FilterFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filter, container, false);

        DemonsrationButton buttonSearch = (DemonsrationButton) root.findViewById(R.id.buttonSearch);
        FrameLayout sheet2 = (FrameLayout) root.findViewById(R.id.sheet2);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ResultTableFragment tableFragment = new ResultTableFragment();

                ft.replace(R.id.conteiner,tableFragment, TagsFragment.TAG_2);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                GenplanFragment genplanFragment = new GenplanFragment();

                ft.replace(R.id.conteiner,genplanFragment, TagsFragment.TAG_4);
                ft.commit();
            }
        });

        return root;
    }
}
