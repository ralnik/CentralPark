package ru.ralnik.centralpark;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.centralpark.fragments.TagsFragment;
import ru.ralnik.centralpark.fragments.FilterFragment;
import ru.ralnik.centralpark.fragments.SettingsFragment;
import ru.ralnik.httpPlayer.HttpPlayerFactory;
import ru.ralnik.httpPlayer.VVVVPlayer;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.myLibrary.NavigationButton.OnDemonstrationButtonClickListener;

public class MainActivity extends AppCompatActivity {
    //UI-Views
    @BindView(R.id.buttonOption) DemonsrationButton buttonSetting;
    @BindView(R.id.buttonPause) DemonsrationButton buttonPause;
    @BindView(R.id.buttonVolume) DemonsrationButton buttonVolume;

    @BindView(R.id.buttonLifeIsland) DemonsrationButton buttonLifeIsland;
    @BindView(R.id.buttonLocation) DemonsrationButton buttonLocation;
    @BindView(R.id.buttonInfraRaion) DemonsrationButton buttonInfraRaion;
    @BindView(R.id.buttonPerspective) DemonsrationButton buttonPerspective;
    @BindView(R.id.buttonTransport) DemonsrationButton buttonTransport;
    @BindView(R.id.buttonInfra) DemonsrationButton buttonInfra;
    @BindView(R.id.buttonComfort) DemonsrationButton buttonComfort;
    @BindView(R.id.buttonArchitecture) DemonsrationButton buttonArchitecture;
    @BindViews({R.id.buttonLifeIsland, R.id.buttonLocation, R.id.buttonInfraRaion, R.id.buttonPerspective, R.id.buttonTransport, R.id.buttonInfra,R.id.buttonComfort, R.id.buttonArchitecture}) List<DemonsrationButton> demonsrationButtonList;

    VVVVPlayer vvvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //убирается панель уведомления и панель навигации, и приложение делается во весь экран
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonSetting.setOnClickListener(new buttonSettingsOnClick());

        for(DemonsrationButton button : demonsrationButtonList) {
            button.setOnDemonstrationButtonClickListener(new demonstrationButtonsClicks());
        }

        loadMainFragment();

        vvvv = HttpPlayerFactory.getInstance(this).getCommand();
        vvvv.play();
    }

    private void loadMainFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        FilterFragment filterFragment = new FilterFragment();

        ft.add(R.id.conteiner,filterFragment, TagsFragment.TAG_1);
        ft.commit();
    }

    /**
     *
     *  PRIVATE CLASSES of Buttons Listener
     *
     */
    private class demonstrationButtonsClicks implements OnDemonstrationButtonClickListener{

        @Override
        public void onClick(View v) {
            for (DemonsrationButton button : demonsrationButtonList){
                button.setStatus(false);
            }
            switch (v.getId()){
                case R.id.buttonLifeIsland:
                    buttonLifeIsland.setStatus(true);

                    Log.d("myDebug", "idTrack = 1");
                    break;
                case R.id.buttonLocation:
                    buttonLocation.setStatus(true);
                    Log.d("myDebug", "idTrack = 2");
                    break;
                case R.id.buttonInfraRaion:
                    buttonInfraRaion.setStatus(true);
                    Log.d("myDebug", "idTrack = 3");
                    break;
                case R.id.buttonPerspective:
                    buttonPerspective.setStatus(true);
                    Log.d("myDebug", "idTrack = 4");
                    break;
                case R.id.buttonTransport:
                    buttonTransport.setStatus(true);
                    Log.d("myDebug", "idTrack = 5");
                    break;
                case R.id.buttonInfra:
                    buttonInfra.setStatus(true);
                    Log.d("myDebug", "idTrack = 6");
                    break;
                case R.id.buttonComfort:
                    buttonComfort.setStatus(true);
                    Log.d("myDebug", "idTrack = 7");
                    break;
                case R.id.buttonArchitecture:
                    buttonArchitecture.setStatus(true);
                    Log.d("myDebug", "idTrack = 8");
                    break;
            }

        }
    }

    private class buttonSettingsOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            SettingsFragment settingsFragment = new SettingsFragment();

            ft.add(R.id.conteiner,settingsFragment,TagsFragment.TAG_3);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}