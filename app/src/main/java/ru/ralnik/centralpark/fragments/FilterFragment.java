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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.ralnik.centralpark.R;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.myLibrary.NavigationButton.OnDemonstrationButtonClickListener;
import ru.ralnik.myseekbarrange.SeekbarRangeAdvance;
import ru.ralnik.sqlitedb.SQL.CreateSQLQuery;


public class FilterFragment extends Fragment {

    @BindView(R.id.seekbarCost) SeekbarRangeAdvance seekbarCost;
    @BindView(R.id.seekbarFloor) SeekbarRangeAdvance seekbarFloor;
    @BindView(R.id.seekbarSquare) SeekbarRangeAdvance seekbarSquare;

    @BindView(R.id.buttonSearch) DemonsrationButton buttonSearch;
    @BindView(R.id.sheet2) FrameLayout sheet2;

    @BindView(R.id.buttonKorpus1) DemonsrationButton buttonKorpus1;
    @BindView(R.id.buttonKorpus2) DemonsrationButton buttonKorpus2;
    @BindView(R.id.buttonKorpus3) DemonsrationButton buttonKorpus3;
    @BindView(R.id.buttonKorpus4) DemonsrationButton buttonKorpus4;
    @BindView(R.id.buttonKorpus5) DemonsrationButton buttonKorpus5;
    @BindView(R.id.buttonKorpus6) DemonsrationButton buttonKorpus6;

    @BindView(R.id.buttonRoom1) DemonsrationButton buttonRoom1;
    @BindView(R.id.buttonRoom2) DemonsrationButton buttonRoom2;
    @BindView(R.id.buttonRoom3) DemonsrationButton buttonRoom3;
    @BindView(R.id.buttonRoom4) DemonsrationButton buttonRoom4;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filter, container, false);
        unbinder = ButterKnife.bind(this, root);
        buttonKorpus1.setTag(1);
        buttonKorpus2.setTag(2);
        buttonKorpus3.setTag(3);
        buttonKorpus4.setTag(4);
        buttonKorpus5.setTag(5);
        buttonKorpus6.setTag(6);

        buttonRoom1.setTag(1);
        buttonRoom2.setTag(2);
        buttonRoom3.setTag(3);
        buttonRoom4.setTag(4);

        buttonSearch.setOnDemonstrationButtonClickListener(new buttonSearchOnClick());

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     *
     *  PRIVATE CLASSES of Buttons Listener
     *
     */
    private class buttonSearchOnClick implements OnDemonstrationButtonClickListener{

        @Override
        public void onClick(View view) {
            CreateSQLQuery sql = new CreateSQLQuery("select * from flats where status>0 ");

            sql.whereRange("etag",seekbarFloor.getMinValue().toString(),
                    seekbarFloor.getMaxValue().toString())
                    .whereRange("ploshad",((Double) seekbarSquare.getMinValue()-0.1) + " ",
                            ((Double) seekbarSquare.getMaxValue()+0.1) + " ")
                    .whereRange("price",seekbarCost.getMinValue() + "*1000000",
                            seekbarCost.getMaxValue() + "*1000000")
                    .whereIN("corpus",
                                    buttonKorpus1,
                                    buttonKorpus2,
                                    buttonKorpus3,
                                    buttonKorpus4,
                                    buttonKorpus5,
                                    buttonKorpus6)
                    .whereIN("comnat",
                                    buttonRoom1,
                                    buttonRoom2,
                                    buttonRoom3,
                                    buttonRoom4)
                    .orderBy("price");

            Log.d("myDebug",sql.toString());
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ResultTableFragment tableFragment = new ResultTableFragment(sql.toString());

            ft.replace(R.id.conteiner,tableFragment, TagsFragment.TAG_2);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
