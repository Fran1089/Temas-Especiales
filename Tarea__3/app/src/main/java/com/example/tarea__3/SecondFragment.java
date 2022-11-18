package com.example.tarea__3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.tarea__3.databinding.FragmentSecondBinding;
import com.example.tarea__3.model.Product;
import com.example.tarea__3.model.ProductViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private ProductViewModel productViewModel;

    private Product product;


    FirebaseStorage fStorage;
    StorageReference fReference;
    public Uri imagePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        fStorage = FirebaseStorage.getInstance();
        fReference = fStorage.getReference();
        if(getArguments() != null){
            product = (Product) getArguments().getSerializable("product");

            binding.inputProductDescription.setText(product.getProductName());
            binding.inputProductVendor.setText(product.getVendor());
            String aux = String.valueOf(product.getPrice());
            binding.inputPrice.setText(aux);

            long size = 1024*1024;

            StorageReference path = fReference.child("myimages/"+product.getImgRef());
            path.getBytes(size).addOnSuccessListener(bytes -> {
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                binding.imageView2.setImageBitmap(bm);
            });
            //System.out.println(getArguments().getDouble("price"));


            //binding.inputPrice.setText((int) getArguments().getDouble("price"));
        }


        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.clearButton.setOnClickListener(view1 -> {
            binding.inputPrice.setText("");
            binding.inputProductDescription.setText("");
            binding.inputProductVendor.setText("");
        });
        binding.deleteButton.setOnClickListener(view1 -> {
            if(getArguments() != null){
                productViewModel.deleteP(product);
            }
            NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);

        });
        binding.saveButton.setOnClickListener(view1 -> {
            if(getArguments() != null){

                product.setProductName(binding.inputProductDescription.getText().toString());
                product.setVendor(binding.inputProductVendor.getText().toString());
                product.setPrice(Double.parseDouble(binding.inputPrice.getText().toString()));

                //productViewModel.updateP(product);

                FirebaseUpdate(product);

            } else {
                Product aux = new Product(binding.inputProductDescription.getText().toString(), binding.inputProductVendor.getText().toString(), Double.parseDouble(binding.inputPrice.getText().toString()));
                //productViewModel.insertP(aux);
                FirebaseCreate(aux);

            }


        });


        binding.imageView2.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),542);
            //registerForActivityResult(Intent.createChooser(intent, "Selecciona una imagen"), 22);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 542){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),imagePath);
                binding.imageView2.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    private void FirebaseUpdate(Product aux){
        if(imagePath == null){
            productViewModel.updateP(aux);
            NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
        }
        else {
            StorageReference ref = fReference.child("myimages/"+ aux.getImgRef());
            ref.putFile(imagePath).addOnSuccessListener(taskSnapshot -> {
                //product.setUri(ref.getDownloadUrl().getResult());
                Toast.makeText(requireContext(), "Imagen subida con exito", Toast.LENGTH_SHORT).show();

                productViewModel.updateP(aux);
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }). addOnFailureListener(e -> {
                Toast.makeText(requireContext(), "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
            }).addOnProgressListener(snapshot -> {
                double progress = (100.0 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
            });

        }
    }

    private void FirebaseCreate(Product aux){
        if(imagePath == null){
            return;
        }
        else {
            aux.setImgRef(UUID.randomUUID().toString());
            StorageReference ref = fReference.child("myimages/"+ aux.getImgRef());

            ref.putFile(imagePath).addOnSuccessListener(taskSnapshot -> {
                //product.setUri(ref.getDownloadUrl().getResult());
                Toast.makeText(requireContext(), "Imagen subida con exito", Toast.LENGTH_SHORT).show();
                productViewModel.insertP(aux);

                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }). addOnFailureListener(e -> {
                Toast.makeText(requireContext(), "No se pudo subir la imagen", Toast.LENGTH_SHORT).show();
            }).addOnProgressListener(snapshot -> {
                double progress = (100.0 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
            });

        }
    }



}