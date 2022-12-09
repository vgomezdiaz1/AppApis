package com.example.appapis;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class PeticionZaragozaFarmacias extends Thread {

    ArrayList<Farmacia> lista;

    public PeticionZaragozaFarmacias(ArrayList<Farmacia> lista){
        this.lista = lista;
    }

    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL("https://www.zaragoza.es/sede/servicio/farmacia?rf=html&srsname=wgs84&tipo=guardia&start=0&rows=50&distance=500");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/geo+json");
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                JsonReader jr = new JsonReader(isr);
                jr.beginObject();
                String nombreFarmacia = "";
                HashMap<String, String> hm = new HashMap<>();
                while (jr.hasNext()) {
                    String clavePrincipal = jr.nextName();
                    if (clavePrincipal.equals("features")) {
                        jr.beginArray();
                        while (jr.hasNext()) {
                            jr.beginObject();
                            Farmacia f = new Farmacia();
                            while (jr.hasNext()) {
                                String properties = jr.nextName();
                                if (properties.equals("properties")) {
                                    jr.beginObject();
                                    while (jr.hasNext()) {
                                        String clave = jr.nextName();
                                        if (clave.equals("title")) {
                                            nombreFarmacia = jr.nextString();
                                            f.setNombre(nombreFarmacia);
                                        } else if (clave.equals("guardia")) {
                                            jr.beginObject();
                                            while (jr.hasNext()) {
                                                String guardias = jr.nextName();
                                                String valor = jr.nextString();
                                                if(guardias.equals("fecha")){
                                                    f.setFecha(valor);
                                                }else if(guardias.equals("turno")){
                                                    f.setTurno(valor);
                                                }else if(guardias.equals("horario")){
                                                    f.setHorario(valor);
                                                }else if(guardias.equals("sector")){
                                                    f.setSector(valor);
                                                }else{
                                                    jr.skipValue();
                                                }
                                            }
                                            jr.endObject();
                                        } else {
                                            jr.skipValue();
                                        }
                                    }
                                    jr.endObject();
                                } else {
                                    jr.skipValue();
                                }
                            }
                            lista.add(f);
                            jr.endObject();
                        }
                        jr.endArray();
                    } else {
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
