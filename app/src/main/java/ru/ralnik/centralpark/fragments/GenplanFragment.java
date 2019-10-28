package ru.ralnik.centralpark.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.ralnik.centralpark.R;


public class GenplanFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_genplan, container, false);

        FrameLayout sheet1 = (FrameLayout) root.findViewById(R.id.sheet1);

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                FilterFragment filterFragment = new FilterFragment();

                ft.add(R.id.conteiner,filterFragment, TagsFragment.TAG_1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return root;
    }
}
