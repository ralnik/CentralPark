package ru.ralnik.myLibrary.MessageDialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.centralpark.R;
import ru.ralnik.config.myConfig;

public class AskingPasswordDialog {
    private Activity activity;
    @BindView(R.id.input_text) EditText userInput;
    @BindView(R.id.buttonApply) Button buttonApply;
    @BindView(R.id.buttonCancel) Button buttonCancel;
    View root;
    private String password = "";
    private Dialog alertDialog;
    private String host = "";
    private myConfig cfg;

    public AskingPasswordDialog(Activity activity) {
        this.activity = activity;
        root = activity.getLayoutInflater().inflate(R.layout.prompt,null);
        ButterKnife.bind(this,root);
        cfg = new myConfig(activity.getApplicationContext());
    }

    public AskingPasswordDialog setPassword(String password) {
        this.password = password;
        return this;
    }

    public AskingPasswordDialog setHost(String host){
        this.host = host;
        return this;
    }

    public AskingPasswordDialog start(){
        show();
        return this;
    }

    private void show(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this.activity);
        builder.setView(this.root);
        builder.setCancelable(true);
        builder.create();
        alertDialog = builder.show();

        buttonApply.setOnClickListener(new buttonsOnClick());
        buttonCancel.setOnClickListener(new buttonsOnClick());
    }

    private void showWrongMessege(){
        Toast toast2 = Toast.makeText(activity, "Неверный пароль!", Toast.LENGTH_LONG);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
    }

    /*
        Private Classes
     */

    private class buttonsOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.buttonApply){
                if(userInput.getText().toString().equals(password)){
                    cfg.setHost(host);
                }else{
                    showWrongMessege();
                }
                alertDialog.dismiss();
            }
            if(view.getId() == R.id.buttonCancel){
                alertDialog.dismiss();
            }

        }
    }




}
