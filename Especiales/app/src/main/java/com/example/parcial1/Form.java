package com.example.parcial1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import org.w3c.dom.Text;

public class Form extends AppCompatActivity {


    TextView name_text, name_article, price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item_layout);

        name_text = (TextView) findViewById(R.id.articletext);
        name_article = (TextView) findViewById(R.id.descripciontext);
        price = (TextView) findViewById(R.id.preciotext);


    }

    public void CreateItem(View view){
        MainActivity.AddItem(new Articulo((String) name_text.getText(), (String) name_article.getText(), (float) 24.2));
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
