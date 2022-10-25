package com.example.parcial1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder>
{
    private List<Articulo> aux;

    public Adapter(List<Articulo> aux) {this.aux = aux; }



    @NotNull
    @Override

    public Adapter.Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout , parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
        Articulo articulo = aux.get(position);

        holder.nombre_articulo.setText(articulo.getNombre());
        holder.descripcion_articulo.setText(articulo.getDescripcion());
        holder.precio.setText("24");

    }

    @Override
    public int getItemCount() {
        return aux.size();
    }


    public class Holder extends RecyclerView.ViewHolder {


        TextView nombre_articulo;

        TextView descripcion_articulo;

        TextView precio;


        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            nombre_articulo = itemView.findViewById(R.id.articletext);
            descripcion_articulo = itemView.findViewById(R.id.descripciontext);
            precio = itemView.findViewById(R.id.precio);

        }
    }


}
