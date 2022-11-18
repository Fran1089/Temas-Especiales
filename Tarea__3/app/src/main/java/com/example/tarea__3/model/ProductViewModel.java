package com.example.tarea__3.model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository pRepo;

    private LiveData<List<Product>> products;


    public ProductViewModel(@NonNull @NotNull Application app){
        super(app);
        pRepo = new ProductRepository(app);
        products = pRepo.getAll();

    }


    public LiveData<List<Product>> getProducts() { return products; }

    public void insertP(Product product) {pRepo.insertP(product);}
    public void updateP(Product product) {pRepo.updateP(product);}
    public void deleteP(Product product) {pRepo.deleteP(product);}

    /*
    public Product findById(Integer product) throws ExecutionException, InterruptedException {
        return pRepo.findbyId(product);
    }

     */



}
