package ru.ralnik.centralpark;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import ru.ralnik.json.JSONParser;

public class preloading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //убирается панель уведомления и панель навигации, и приложение делается во весь экран
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_preloading);
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        MyTaskLoading myTask = new MyTaskLoading();
                        myTask.execute();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }


    private void startApp() {
        Intent intent = new Intent(preloading.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class MyTaskLoading extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
//            JSONParser json = new JSONParser(getApplicationContext(),"http://feeds.l-invest.ru/realred/");
//            json.parser();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startApp();
        }
    }
}
