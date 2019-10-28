package ru.ralnik.centralpark;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.config.myConfig;
import ru.ralnik.email.EmailSender;

public class SendToEmailActivity {
    Activity activity;
    View rootView;
    @BindView(R.id.email_edit) EditText email_edit;
    @BindView(R.id.button_send_to_email) ImageView button_send_to_email;
    @BindView(R.id.button_back) ImageView button_back;

    String title;
    String text;
    String from;
    String to;
    String attach;

    myConfig cfg;

    public SendToEmailActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.send_to_email_layout, null); // Получаем layout по его ID
        ButterKnife.bind(this,rootView);
        cfg = new myConfig(activity.getApplicationContext());
        //initialize all components
        init();
        // show activity
        show();
    }

    private void init(){
        button_send_to_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sender_mail_async async_sending = new sender_mail_async(activity);
                async_sending.execute();
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


    private class sender_mail_async extends AsyncTask<Object, String, Boolean> {
        private ProgressDialog statusDialog;
        private Activity sendMailActivity;

        public sender_mail_async(Activity activity) {
            sendMailActivity = activity;

        }

        @Override
        protected void onPreExecute() {
            statusDialog = new ProgressDialog(sendMailActivity);
            statusDialog.setMessage("Sending email....");
            statusDialog.setIndeterminate(false);
            statusDialog.setCancelable(false);
            statusDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Log.d("myDebug","Отправка мыла должна получится!");
            Toast.makeText(sendMailActivity, "email отправлен!!!", Toast.LENGTH_LONG).show();
            statusDialog.dismiss();
        }

        @Override
        protected Boolean doInBackground(Object... params) {

            try {

                title = "title";
                text = "text";
                from = cfg.getEmailFrom();
                to = email_edit.getText().toString();
                attach = "/storage/emulated/0/Download/1/sample.pdf";
                publishProgress("Sending email....");
                EmailSender sender = new EmailSender(cfg.getEmailLogin(), cfg.getEmailPassword(), cfg.getSmtp());
                sender.sendMail(title, text, from, to, attach);

            } catch (Exception e) {
                publishProgress(e.getMessage());
                Toast.makeText(sendMailActivity, "Отправка мыла не получилась!", Toast.LENGTH_SHORT).show();
                Log.d("myDebug","Отправка мыла не получилась!");
            }

            return false;
        }
    }
}
