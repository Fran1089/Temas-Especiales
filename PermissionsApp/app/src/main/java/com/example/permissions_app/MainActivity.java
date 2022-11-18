package com.example.permissions_app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PermissionsSwitch> switch_permissions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_permissions = new ArrayList<>();
        switch_permissions.add(new PermissionsSwitch("android.permission.READ_EXTERNAL_STORAGE", "Storage", findViewById(R.id.storageSwitch)));
        switch_permissions.add(new PermissionsSwitch("android.permission.ACCESS_COARSE_LOCATION", "Location", findViewById(R.id.locationSwitch)));
        switch_permissions.add(new PermissionsSwitch("android.permission.CAMERA", "Camera", findViewById(R.id.camaraSwitch)));
        switch_permissions.add(new PermissionsSwitch("android.permission.CALL_PHONE", "Phone", findViewById(R.id.phoneSwitch)));
        switch_permissions.add(new PermissionsSwitch("android.permission.READ_CONTACTS", "Contact", findViewById(R.id.contactSwitch)));


        for(PermissionsSwitch aux:switch_permissions){
            if(ContextCompat.checkSelfPermission(this, aux.getPermission()) == PackageManager.PERMISSION_GRANTED){
                aux.getAux().setChecked(true);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            for (String a: permissions){
                PermissionsSwitch permissionsSwitch = GetName(a);
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, permissionsSwitch.getPermission_name()+" permission granted", Toast.LENGTH_SHORT).show();
                }
            }
            Intent intent = new Intent(this, CheckPermiss   ions.class);
            startActivity(intent);
        }
    }


    public PermissionsSwitch GetName(String a){
        PermissionsSwitch aux2 =new PermissionsSwitch();
        for(PermissionsSwitch b:switch_permissions) {
            if(b.getPermission().equalsIgnoreCase(a)){
                return b;
            }
        }

        return aux2;
    }

    public void GenerateMessages(View view){

        ArrayList<String> permissions_name = new ArrayList<>();

        for(PermissionsSwitch aux:switch_permissions){
            if(aux.getAux().isChecked() && !(ContextCompat.checkSelfPermission(this, aux.getPermission()) == PackageManager.PERMISSION_GRANTED)){
                permissions_name.add(aux.getPermission());
            }
        }
        Intent intent = new Intent(this, CheckPermissions.class);

        if(permissions_name.isEmpty()){
            startActivity(intent);
        } else {
            RequestPermissions(permissions_name);
        }


    }

    public void RequestPermissions( ArrayList<String> a){
        ActivityCompat.requestPermissions(this, a.toArray(new String[0]), 1);
    }

}