package com.example.appapis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

        ArrayList<Farmacia> lista = new ArrayList<>();

        PeticionZaragozaFarmacias p2 = new PeticionZaragozaFarmacias(lista);
        p2.start();
        try {
            p2.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        RecyclerView rv = findViewById(R.id.listaFarmacias);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        MiAdaptador adaptador = new MiAdaptador(lista);
        rv.setAdapter(adaptador);
    }
}