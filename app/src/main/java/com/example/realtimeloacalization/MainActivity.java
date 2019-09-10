package com.example.realtimeloacalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button changelanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changelanguage = findViewById(R.id.button);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showchangelanguage();
            }
        });
    }

    private void showchangelanguage() {
        final String[] list = {"हिंदी", "Arebic", "English"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change Language");
        builder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(i==1)
                {
                    setLocal("Hi");
                    recreate();
                }
                else if(i==2)
                {
                    setLocal("ar");
                    recreate();
                }
                else
                {
                    setLocal("En");
                    recreate();
                }

                dialogInterface.dismiss();

            }
        });

        AlertDialog dialogbox = builder.create();
        dialogbox.show();
    }

    private void setLocal(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void  loadlocale()
    {
        SharedPreferences preferences=getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language=preferences.getString("My Lang","");
        setLocal(language);


    }











}



