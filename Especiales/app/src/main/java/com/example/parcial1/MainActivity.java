package com.example.parcial1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<Articulo> aux;

    static Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rw = findViewById(R.id.recyclerview);
        if(aux == null){
            aux = new ArrayList<>();
            aux.add(new Articulo("Duki", "Desde el fin del mundo es el primer album oficial", 299));
            aux.add(new Articulo("CRO", "Vamos a ver si esto funciona", 600));
        }


        adapter = new Adapter(aux);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rw.setLayoutManager(linearLayoutManager);
        rw.setAdapter(adapter);

    }

    public void NewView(View view){
        Intent intent = new Intent(this, Form.class);
        startActivity(intent);
    }

    public static void AddItem(Articulo articulo){
        aux.add(articulo);
        adapter = new Adapter(aux);
        adapter.notifyDataSetChanged();
    }



}