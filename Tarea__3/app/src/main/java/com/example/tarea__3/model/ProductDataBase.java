package com.example.tarea__3.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Product.class}, version = 2, exportSchema = false)
public abstract class ProductDataBase extends RoomDatabase {


    public abstract ProductDao productDao();

    public static volatile ProductDataBase INSTANCE;

    static  final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
    public static ProductDataBase getInstance(Context aux){
        if(INSTANCE == null){
            synchronized (ProductDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(aux.getApplicationContext(), ProductDataBase.class, "DB").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }





}
