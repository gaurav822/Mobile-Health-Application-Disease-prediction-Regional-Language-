package com.example.patientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class Chooselanguage extends AppCompatActivity {

    Button button;
    RadioButton languageradioButton;
    RadioGroup radioGroup;
    private static final int RB1_ID = 1;//first radio button id
    private static final int RB2_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselanguage);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
    }

    public void onLanguageSelection(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        languageradioButton = (RadioButton) findViewById(selectedId);

        if(selectedId==-1){
            Toast.makeText(Chooselanguage.this,"Please Choose at least one Language", Toast.LENGTH_SHORT).show();
        }

        else{

            if(languageradioButton.getText().equals("  English")){
                setLocale("en");
                Intent dsp=new Intent(Chooselanguage.this,MainActivity.class);
                startActivity(dsp);
            }

            else{
                setLocale("ne");
                Intent dsp=new Intent(Chooselanguage.this,MainActivity.class);
                startActivity(dsp);

            }

        }

    }

    private void setLocale(String lang) {

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(String lang){

        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("My_Lang","");
        setLocale(language);
    }
}