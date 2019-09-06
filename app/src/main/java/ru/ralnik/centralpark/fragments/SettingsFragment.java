package ru.ralnik.centralpark.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.ralnik.centralpark.R;
import ru.ralnik.config.myConfig;
import ru.ralnik.httpPlayer.HttpPlayerFactory;
import ru.ralnik.httpPlayer.VVVVPlayer;
import ru.ralnik.myLibrary.MessageDialogs.AskingPasswordDialog;
import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;

public class SettingsFragment extends Fragment {
    @BindViews({R.id.musicSeekBar,R.id.effectSeekBar}) List<SeekBar> listSeekbars;
    @BindView(R.id.musicSeekBar) SeekBar musicSeekbar;
    @BindView(R.id.effectSeekBar) SeekBar effectSeekbar;
    @BindView(R.id.editWaitTime) EditText editWaitTime;
    @BindView(R.id.switcherTimer) DemonsrationButton switcherTimer;
    @BindView(R.id.editIP) EditText editIP;
    @BindView(R.id.buttonSave) ImageView buttonSave;
    @BindView(R.id.buttonUpdateDB) ImageView buttonUpdateDB;
    private Unbinder unbinder;
    private Context context;
    private myConfig cfg;
    VVVVPlayer vvvv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cfg = new myConfig(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, root);

        vvvv = HttpPlayerFactory.getInstance(context).getCommand();

        buttonSave.setOnClickListener(new buttonSaveOnClick());
        buttonUpdateDB.setOnClickListener(new buttonUpdateDBOnClick());

        musicSeekbar.setProgress(cfg.getVolumeProgress());
        effectSeekbar.setProgress(cfg.getEffectProgress());

        for(SeekBar seekbar : listSeekbars){
            seekbar.setOnSeekBarChangeListener(new seekbarOnChange(seekbar));
        }

        editWaitTime.setText(String.valueOf(cfg.getTimer()));
        editIP.setText(cfg.getHost());

        if(cfg.getDisableTimer()) {
            switcherTimer.setStatus(true);
        }else {
            switcherTimer.setStatus(false);
        }

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


    /**
     *
     *  PRIVATE CLASSES of Buttons Listener
     *
     */

    private class buttonSaveOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(!editIP.getText().toString().equals(cfg.getHost())) {
                AskingPasswordDialog dialog = new AskingPasswordDialog(getActivity());
                dialog.setHost(editIP.getText().toString())
                        .setPassword("realred")
                        .start();
            }

            //Log.d(TAG,"save config");
            if(switcherTimer.getStatus()) {
                cfg.setDisableTimer(true);
            }else{
                cfg.setDisableTimer(false);
            }

            cfg.setVolumeProgress(String.valueOf(musicSeekbar.getProgress()));
            cfg.setEffectProgress(String.valueOf(effectSeekbar.getProgress()));

            cfg.setTimer(String.valueOf(editWaitTime.getText()));
            /*
            вот с этим нужно подумать как сделать чтобы счетчик врубился на главной форме, как то передать туда параметр
            setTimer(cfg.getTimer());

             */

        }
    }

    private class buttonUpdateDBOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    private class seekbarOnChange implements SeekBar.OnSeekBarChangeListener{
        private SeekBar seekbar;
        public seekbarOnChange(SeekBar seekbar) {
            this.seekbar = seekbar;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(this.seekbar.getId() == R.id.musicSeekBar)
                vvvv.volume(seekBar.getProgress());

            if(this.seekbar.getId() == R.id.effectSeekBar)
                vvvv.volumeEffect(seekBar.getProgress());

        }
    }
}
