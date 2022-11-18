package com.example.parcial1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Form extends AppCompatActivity {


    TextView name_text, name_article, price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item_layout);

        name_text = (TextView) findViewById(R.id.vendorText);
        name_article = (TextView) findViewById(R.id.descriptionText);
        price = (TextView) findViewById(R.id.preciotext);


    }

    public void CreateItem(View view){
        MainActivity.aux.add(new Articulo((String) name_text.getText(), (String) name_article.getText(), (float) 24.2));
        ClearForm(view);
    }

    public void ClearForm(View view){
        name_text.setText("");
        name_article.setText("");
        price.setText("");

    }

    public void Return(View view){

    }

}
