package com.example.tarea__3.model;


import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface ProductDao {


    @Insert
    void addProduct(Product product);


    @Query("SELECT * FROM product")
    List<Product> allProducts();


    @Query("SELECT * FROM product WHERE uid = :uid")
    Product findById(int uid);

    @Delete
    void delProduct(Product product);

    @Update
    void editProduct(Product product);

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();
}
