package ru.ralnik.centralpark.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.ralnik.PDF.PDF_Creator;
import ru.ralnik.centralpark.R;
import ru.ralnik.centralpark.SendToEmailActivity;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.myLibrary.recycleview.FavoriteFlatsRecycleViewAdapter;
import ru.ralnik.sqlitedb.FlatRepository;

public class FavoritesFragment extends Fragment {
    @BindView(R.id.recycleviewFavoriteFlat) RecyclerView recyclerView;
    @BindView(R.id.buttonClear) DemonsrationButton buttonClear;
    @BindView(R.id.buttonSendToEmail) DemonsrationButton buttonSendToEmail;

    private Unbinder unbinder;
    Context context;

    public FavoritesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, root);
        loadFavoriteFlatsInRecycleView();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FlatRepository(context).deleteFavoriteAll();
                loadFavoriteFlatsInRecycleView();
            }
        });

        buttonSendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PDF_Creator pdf = new PDF_Creator(getContext(),"/storage/emulated/0/Download/1/flats_favorites.pdf");
                pdf.create();
                new SendToEmailActivity(getActivity());
            }
        });

        return root;
    }

    private void loadFavoriteFlatsInRecycleView(){
        FavoriteFlatsRecycleViewAdapter adapter = new FavoriteFlatsRecycleViewAdapter(getContext(),new FlatRepository(context).getFavoriteFlats());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onAttach(Context context) {
        this.context = (FragmentActivity) context;
        super.onAttach(context);
    }


}
