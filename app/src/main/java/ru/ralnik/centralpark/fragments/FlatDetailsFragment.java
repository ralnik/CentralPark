package ru.ralnik.centralpark.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.ralnik.centralpark.R;
import ru.ralnik.centralpark.SendToEmailActivity;
import ru.ralnik.model.Flat;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;

public class FlatDetailsFragment extends Fragment {
    FragmentActivity context;
    private Flat flat;
    private Unbinder unbinder;
    @BindView(R.id.buttonShowFavorites) DemonsrationButton buttonShowFavorites;
    @BindView(R.id.buttonSendToEmail) DemonsrationButton buttonSendToEmail;
    @BindView(R.id.sheet2) FrameLayout sheet2;

    public FlatDetailsFragment(Flat flat) {
        this.flat = flat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flat_details, container, false);
        unbinder = ButterKnife.bind(this, root);

        buttonShowFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = context.getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                FavoritesFragment favoritesFragment = new FavoritesFragment(flat);

                ft.add(R.id.conteiner,favoritesFragment,TagsFragment.TAG_6);
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

        buttonSendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendToEmailActivity(getActivity());
            }
        });

        return root;
    }


    @Override
    public void onAttach(Context context) {
        this.context = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
