package com.example.appapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Como hay que leer desde internet hay que darle permiso para acceder a internet desde el manifest

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PeticionGitHub p = new PeticionGitHub();
        p.start();

        PeticionZaragozaAnimales p1 = new PeticionZaragozaAnimales();
        p1.start();

    }
}