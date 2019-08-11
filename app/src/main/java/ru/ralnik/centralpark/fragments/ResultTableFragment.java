package ru.ralnik.centralpark.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.ralnik.centralpark.R;
import ru.ralnik.model.Flat;
import ru.ralnik.myLibrary.recycleview.MyRecycleViewAdapter;
import ru.ralnik.myLibrary.recycleview.MyRecycleViewItemSelected;


public class ResultTableFragment extends Fragment {

    Context context;

    public ResultTableFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result_table, container, false);
        RecyclerView rv = (RecyclerView) root.findViewById(R.id.recycleview);
        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(17,
                2,
                3,
                2,
                15.5F,
                1000000.0F,
                1));
        flats.add(new Flat(18,
                2,
                3,
                3,
                15.5F,
                1000001.0F,
                1));
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(getContext(),flats);
        adapter.setOnClickListener(new MyRecycleViewItemSelected(this.context));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        this.context = (FragmentActivity) context;
        super.onAttach(context);
    }
}
