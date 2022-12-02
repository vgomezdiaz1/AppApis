package com.example.appapis;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PeticionZaragozaAnimales extends Thread{
    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL("https://www.zaragoza.es/sede/servicio/mascotas?rf=html&start=0&rows=50");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //Si hay que poner cabeceras por usuario y contraseña por ejemplo
            //conn.setRequestProperty("user", "ejemplo");
            conn.setRequestProperty("Accept","application/json");
            //preguntamos cual es el numero de respuesta para saber si funciona o no
            if(conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                //Comentamos el log que segun esta solo imprime la direccion de memoria
                //Log.v("Json", isr.toString());
                //Iniciamos un lector de Json
                JsonReader jr = new JsonReader(isr);
                jr.beginObject();
                while(jr.hasNext()){
                    String clave = jr.nextName();
                    if(clave.equals("result")){
                        jr.beginArray();
                        //Mientras siga habiendo objetos del array
                        while(jr.hasNext()){
                            //Abrimos otro objeto por que cada elemento del array es un objeto
                            jr.beginObject();
                            String nombre = "";
                            String raza = "";
                                while(jr.hasNext()){
                                    String claveNivel2 = jr.nextName();
                                    if(claveNivel2.equals("nombre")){
                                        nombre = jr.nextString();
                                    }else if(claveNivel2.equals("raza")){
                                        raza = jr.nextString();
                                    }else{
                                        jr.skipValue();
                                    }
                                }
                            Log.v("Animales", nombre + " = " + raza);
                            jr.endObject();
                        }
                        jr.endArray();
                    }else{
                        jr.skipValue();
                    }
                }
                jr.endObject();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
