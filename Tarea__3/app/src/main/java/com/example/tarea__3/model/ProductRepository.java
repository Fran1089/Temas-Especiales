package com.example.tarea__3.model;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class ProductRepository {

    private ProductDao dao;
    @Getter
    private final LiveData<List<Product>> products;

    public ProductRepository(Application app) {
        ProductDataBase database = ProductDataBase.getInstance(app);
        dao = database.productDao();
        products = dao.getAllProducts();
    }

    public void insertP(Product product){
        ProductDataBase.databaseWriteExecutor.execute(() ->
                dao.addProduct(product));
    }

    public void updateP(Product product){
        ProductDataBase.databaseWriteExecutor.execute(() ->
                dao.editProduct(product));
    }


    public void deleteP(Product product){
        ProductDataBase.databaseWriteExecutor.execute(() ->
                dao.delProduct(product));
    }


    /*
    public Product findbyId(int integer){
        AtomicReference<Product> aux = null;
        ProductDataBase.databaseWriteExecutor.execute(() ->
                        aux.set(dao.findById(integer))
                );
        return aux.get();
    }
     */


    public LiveData<List<Product>> getAll(){
        return products;
    }





    /*
    public Product getProduct(Integer product) throws ExecutionException, InterruptedException {
        return new getProductAsync(dao).execute(product).get();
    }

    private static class insertProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao asyncDao;

        insertProductAsync(ProductDao asyncProdDao){
            this.asyncDao = asyncProdDao;
        }


        @Override
        protected Void doInBackground(final Product... products) {
            asyncDao.addProduct(products[0]);
            return null;
        }
    }


    private static class updateProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao asyncDao;

        updateProductAsync(ProductDao asyncProdDao){
            this.asyncDao = asyncProdDao;
        }


        @Override
        protected Void doInBackground(final Product... products) {
            //Product search = asyncDao.findById(products[0].getUid());

            asyncDao.editProduct(products[0]);
            return null;
        }
    }


    private static class removeProductAsync extends AsyncTask<Product, Void, Void> {
        private ProductDao asyncDao;

        removeProductAsync(ProductDao asyncProdDao){
            this.asyncDao = asyncProdDao;
        }


        @Override
        protected Void doInBackground(final Product... products) {
            //Product search = asyncDao.findById(products[0].getUid());

            asyncDao.delProduct(products[0]);
            return null;
        }
    }



    private static class getProductAsync extends AsyncTask<Integer, Void, Product> {
        private ProductDao asyncDao;

        getProductAsync(ProductDao asyncProdDao){
            this.asyncDao = asyncProdDao;
        }


        @Override
        protected Product doInBackground(Integer... integers) {
            return asyncDao.findById(integers[0]);
        }
    }
    */







}
