package com.example.appapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("https://api.github.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}