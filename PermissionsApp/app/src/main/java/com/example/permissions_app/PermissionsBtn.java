package com.example.permissions_app;

import android.widget.Button;

public class PermissionsBtn {


    String permission;
    String permission_name;
    static int request_code = 1;

    Button aux;

    public PermissionsBtn(){

    }

    public PermissionsBtn(String permission, String permission_name, Button aux) {
        this.permission = permission;
        this.permission_name = permission_name;
        this.aux = aux;
    }

    public Button getAux() {
        return aux;
    }

    public String getPermission() {
        return permission;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public static int getRequest_code() {
        return request_code;
    }
}
