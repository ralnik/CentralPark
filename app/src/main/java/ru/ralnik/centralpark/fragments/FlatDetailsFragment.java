package ru.ralnik.centralpark.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.ralnik.PDF.PDF_Creator;
import ru.ralnik.centralpark.R;
import ru.ralnik.centralpark.SendToEmailActivity;
import ru.ralnik.email.EmailSender;
import ru.ralnik.model.Flat;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.myLibrary.NavigationButton.OnDemonstrationButtonClickListener;
import ru.ralnik.sqlitedb.FlatRepository;

public class FlatDetailsFragment extends Fragment {
    Context context;
    private final Flat flat;
    private Unbinder unbinder;
    @BindView(R.id.buttonShowFavorites) DemonsrationButton buttonShowFavorites;
    @BindView(R.id.buttonSendToEmail) DemonsrationButton buttonSendToEmail;
    @BindView(R.id.buttonStar) DemonsrationButton buttonStar;
    @BindView(R.id.sheet2) FrameLayout sheet2;

    @BindView(R.id.titleCountRooms) TextView titleCountRooms;
    @BindView(R.id.titlePrice) TextView titlePrice;
    @BindView(R.id.textCorpus) TextView textCorpus;
    @BindView(R.id.textNumberFlats) TextView textNumberFlats;
    @BindView(R.id.textFloor) TextView textFloor;
    @BindView(R.id.textSquare) TextView textSquare;
    @BindView(R.id.textStatus) TextView textStatus;

    @BindView(R.id.imgPlanFlat) ImageView imgPlanFlat;
    @BindView(R.id.imgPlanFloor) ImageView imgPlanFloor;

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

        buttonShowFavorites.setOnDemonstrationButtonClickListener(new buttonShowFavoritesOnClick());
        buttonSendToEmail.setOnDemonstrationButtonClickListener(new buttonSendToEmailOnClick());
        buttonStar.setOnDemonstrationButtonClickListener(new buttonStarOnClick());


        //Заполняем данные о квартире
        writeDataAboutFlat();

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
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void writeDataAboutFlat(){
        if(flat.getFavorite() == 1){
            buttonStar.setStatus(true);
        }else{
            buttonStar.setStatus(false);
        }
        titleCountRooms.setText(flat.getNameCountRoomsFlat());
        titlePrice.setText(Flat.makePrettyCost(flat.getPrice().toString())+" руб.");
        textCorpus.setText(flat.getCorpus()+"");
        textNumberFlats.setText(flat.getNom_kv()+"");
        textFloor.setText(flat.getEtag()+"");
        textSquare.setText(flat.getPloshad()+"");
        textStatus.setText(flat.getCorrectStatus());
//        //***********Меняем планировку на ту, что нада*****************
//        //Example:  plan_floor9_corpus2
//        String fileNameFloor = "plan_floor"+flat.getEtag()+"_corpus"+flat.getCorpus();
//
//        //Example: plan_flat95_floor6_corpus2
//        String fileNameFlat = "plan_flat" + flat.getNom_kv() + "_floor" + flat.getEtag() + "_corpus"+flat.getCorpus();
//
//        int floorResID=0;
//        int flatResID=0;
//        try {
//            floorResID = getResources().getIdentifier(fileNameFloor, "drawable", getPackageName());
//            flatResID =  getResources().getIdentifier(fileNameFlat, "drawable", getPackageName());
//        }catch (Exception e){
//            Log.d("myDebug"," "+e);
//        }
//        imgPlanFloor.setImageResource(floorResID);
//        imgPlanFlat.setImageResource(flatResID);
//        //*******************************************************
    }

    /**
     *
     *  PRIVATE CLASSES of Buttons Listener
     *
     */

    private class buttonShowFavoritesOnClick implements OnDemonstrationButtonClickListener{
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            FavoritesFragment favoritesFragment = new FavoritesFragment();

            ft.replace(R.id.conteiner,favoritesFragment,TagsFragment.TAG_6);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private class buttonSendToEmailOnClick implements OnDemonstrationButtonClickListener{
        @Override
        public void onClick(View view) {
            PDF_Creator pdf = new PDF_Creator(getContext(),"/storage/emulated/0/Download/1/");
            pdf.create();
            new SendToEmailActivity(getActivity());

        }
    }

    private class buttonStarOnClick implements OnDemonstrationButtonClickListener{

        @Override
        public void onClick(View view) {
            if(buttonStar.getStatus()) {
               new FlatRepository(getContext()).insertFavorite(flat.getId());
            }else {
                new FlatRepository(getContext()).deleteFavoriteById(flat.getId());
            }
        }
    }



}
