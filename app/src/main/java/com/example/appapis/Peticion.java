package com.example.appapis;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Peticion extends Thread{
    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL("https://api.github.com/");
            //Cuidado con la url si es https o no
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //Si hay que poner cabeceras por usuario y contraseña por ejemplo
            //conn.setRequestProperty("user", "ejemplo");
            conn.setRequestProperty("Accept","application/vnd.github.v3+json");
            //preguntamos cual es el numero de respuesta para saber si funciona o no
            if(conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                Log.v("Json", isr.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
