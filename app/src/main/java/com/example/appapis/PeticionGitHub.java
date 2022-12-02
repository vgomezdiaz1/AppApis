package com.example.appapis;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PeticionGitHub extends Thread{
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
                //Comentamos el log que segun esta solo imprime la direccion de memoria
                //Log.v("Json", isr.toString());
                //Iniciamos un lector de Json
                JsonReader jr = new JsonReader(isr);
                        /*
                        //Lee el nombre de la clave pero no avanza hay que guardarlo en un string
                        String clave = jr.nextName();
                        //Si no nos vale la clave con skipvalue saltamos a la siguiente linea
                        //jr.skipValue();
                        //Para leer el valor y saltar al siguiente usamos nextString
                        String valor = jr.nextString();
                        */
                //Entramos dentro de la llave del json para leer el objeto json
                jr.beginObject();
                //Mientras siga habiendo valores vamos avanzando
                while(jr.hasNext()){
                    String clave = jr.nextName();
                    if(clave.equals("organization_url")){
                        String valor = jr.nextString();
                        Log.v("elementos", clave + " = " + valor);
                    }else if(clave.equals("keys_url")){
                        String valor = jr.nextString();
                        Log.v("elementos", clave + " = " + valor);
                    }else{
                        //si no lees lo que quieres, como no pasas de linea necesitas este else
                        jr.skipValue();
                    }
                }
                //Siempre hay que cerrar el objeto que estamos leyendo
                jr.endObject();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
