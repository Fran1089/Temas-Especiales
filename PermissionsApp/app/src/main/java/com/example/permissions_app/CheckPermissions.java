package com.example.permissions_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CheckPermissions extends AppCompatActivity {


    ArrayList<PermissionsBtn> btn_permissions;

    PermissionsBtn storage;
    PermissionsBtn location;
    PermissionsBtn camera;
    PermissionsBtn phone;
    PermissionsBtn contacts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permissions);



        storage = new PermissionsBtn("android.permission.READ_EXTERNAL_STORAGE", "Storage", findViewById(R.id.storageBtn));
        location = new PermissionsBtn("android.permission.ACCESS_COARSE_LOCATION", "Location", findViewById(R.id.locationBtn));
        camera = new PermissionsBtn("android.permission.CAMERA", "Camera", findViewById(R.id.cameraBtn));
        phone = new PermissionsBtn("android.permission.CALL_PHONE", "Phone", findViewById(R.id.phoneBtn));
        contacts = new PermissionsBtn("android.permission.READ_CONTACTS", "Contact", findViewById(R.id.contactsBtn));

        //for(btn_permissions)

        storage.getAux().setOnClickListener(view -> {
            if((ContextCompat.checkSelfPermission(this, storage.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        location.getAux().setOnClickListener(view -> {
            if((ContextCompat.checkSelfPermission(this, location.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        camera.getAux().setOnClickListener(view -> {
            if((ContextCompat.checkSelfPermission(this, camera.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        phone.getAux().setOnClickListener(view -> {
            if((ContextCompat.checkSelfPermission(this, phone.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"8297891923"));
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

        contacts.getAux().setOnClickListener(view -> {
            if((ContextCompat.checkSelfPermission(this, contacts.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });




    }
}