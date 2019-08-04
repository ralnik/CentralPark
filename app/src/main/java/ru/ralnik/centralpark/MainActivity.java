package ru.ralnik.centralpark;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.ralnik.centralpark.fragments.TagsFragment;
import ru.ralnik.centralpark.fragments.FilterFragment;
import ru.ralnik.centralpark.fragments.SettingsFragment;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;

public class MainActivity extends AppCompatActivity {



    //UI-Views
    DemonsrationButton buttonSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //убирается панель уведомления и панель навигации, и приложение делается во весь экран
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttonSetting = (DemonsrationButton) findViewById(R.id.buttonOption);

        loadMainFragment();
    }

    private void loadMainFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        FilterFragment filterFragment = new FilterFragment();

        ft.add(R.id.conteiner,filterFragment, TagsFragment.TAG_1);
        ft.commit();
    }

    public void buttonSettingsOnClick(View v){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        SettingsFragment settingsFragment = new SettingsFragment();

        ft.add(R.id.conteiner,settingsFragment,TagsFragment.TAG_3);
        ft.addToBackStack(null);
        ft.commit();
    }

}