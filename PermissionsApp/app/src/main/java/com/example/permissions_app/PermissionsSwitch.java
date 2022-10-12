package com.example.permissions_app;

import android.widget.Switch;

public class PermissionsSwitch {

    String permission;
    String permission_name;

    Switch aux;

    static int request_code = 1;

    public PermissionsSwitch(){

    }

    public PermissionsSwitch(String permission, String permission_name, Switch aux) {
        this.permission = permission;
        this.permission_name = permission_name;
        this.aux = aux;
    }

    public Switch getAux() {
        return aux;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public static int getRequest_code() {
        return request_code;
    }

    public String getPermission() {
        return permission;
    }


}
