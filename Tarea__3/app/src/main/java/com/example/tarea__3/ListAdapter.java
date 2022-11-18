package com.example.tarea__3;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tarea__3.interfaces.RecyclerInterface;
import com.example.tarea__3.model.Product;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{


    private final RecyclerInterface interfaceAux;


    String[] list;

    List<Product> aux;


    FirebaseStorage fStorage;
    StorageReference fReference;


    public ListAdapter(List<Product> data, RecyclerInterface recyclerInterface){
        this.aux = data;
        this.interfaceAux = recyclerInterface;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        fStorage = FirebaseStorage.getInstance();
        fReference = fStorage.getReference();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false), interfaceAux);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        long size = 1024*1024;

        System.out.println(aux.get(position).getImgRef());

        StorageReference path = fReference.child("myimages/"+aux.get(position).getImgRef());
        path.getBytes(size).addOnSuccessListener(bytes -> {
            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            holder.img.setImageBitmap(bm);
        });

        holder.desc.setText(aux.get(position).getProductName());
        holder.vendor.setText(aux.get(position).getVendor());
        holder.price.setText(aux.get(position).getPrice().toString());
    }

    @Override
    public int getItemCount() {
        if(aux != null){
            return aux.size();
        }
        return 0;
    }



    public List<Product> getProducts() {
        return aux;
    }

    public void setProducts(List<Product> aux_products){
        aux = aux_products;
        notifyDataSetChanged();
    }

    //View Holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView desc, vendor, price;

        Button editBtn;

        ImageView img;

        public MyViewHolder(@NonNull @NotNull View itemView, RecyclerInterface interfaceAux) {
            super(itemView);

            editBtn = itemView.findViewById(R.id.editItem);

            desc = itemView.findViewById(R.id.descriptionText);
            vendor = itemView.findViewById(R.id.vendorText);
            price = itemView.findViewById(R.id.priceField);
            img = itemView.findViewById(R.id.imageView);
            //textView=itemView.findViewById(R.id.textView);

            editBtn.setOnClickListener(view -> {
                if (interfaceAux != null){
                    int viewPosition = getAdapterPosition();

                    if(viewPosition != RecyclerView.NO_POSITION){
                        interfaceAux.onEditProduct(viewPosition);
                    }

                }
            });
        }
    }

}
