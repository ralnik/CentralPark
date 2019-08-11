package ru.ralnik.centralpark;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendToEmailActivity {
    Activity activity;
    View rootView;
    @BindView(R.id.email_edit) EditText email_edit;
    @BindView(R.id.button_send_to_email) ImageView button_send_to_email;
    @BindView(R.id.button_back) ImageView button_back;

    public SendToEmailActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.send_to_email_layout, null); // Получаем layout по его ID
        ButterKnife.bind(this,rootView);
        //initialize all components
        init();
        // show activity
        show();
    }

    private void init(){
        button_send_to_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void show(){
//        Dialog alertDialog = new Dialog(this.activity);
//        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        alertDialog.setContentView(this.rootView);
//        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setView(this.rootView);
        builder.setCancelable(true);
        builder.create();
        Dialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button_back.setOnClickListener(new btnCloseOnClick(alertDialog));
        /*
        //Если нужно добавить снизу диалогового окна 2 кнопки ОК и Отмена
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            public void onClick(DialogInterface dialog, int whichButton) {
               // MainActivity.doSaveSettings(); // Переход в сохранение настроек MainActivity
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        */
    }

    private class btnCloseOnClick implements View.OnClickListener{
        Dialog dialog;
        public btnCloseOnClick(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view) {
            //vvvv.selectBySubId(0);
            dialog.dismiss();
        }
    }
}
