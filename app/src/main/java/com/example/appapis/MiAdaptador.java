package com.example.appapis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {

    ArrayList<Farmacia> lista;

    public MiAdaptador(ArrayList<Farmacia> lista) {
        this.lista = lista;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtNombre;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            txtNombre = itemView.findViewById(R.id.textNombreFarmacia);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());

        View v = inflador.inflate(R.layout.elemento,parent, false);

        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNombre.setText(lista.get(position).getNombre());
        if(lista.get(position).getTurno().equals("14-B")){
            holder.img.setImageDrawable(holder.itemView.getResources().getDrawable(R.mipmap.ic_verde));
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
