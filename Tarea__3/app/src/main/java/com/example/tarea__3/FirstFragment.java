package com.example.tarea__3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.tarea__3.databinding.FragmentFirstBinding;
import com.example.tarea__3.interfaces.RecyclerInterface;
import com.example.tarea__3.model.Product;
import com.example.tarea__3.model.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements RecyclerInterface{

    private FragmentFirstBinding binding;

    List<Product> inventory;

    ListAdapter adapter;



    private ProductViewModel productViewModel;

    public static List<Product> aux = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        adapter = new ListAdapter(null, (RecyclerInterface) this);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        productViewModel.getProducts().observe(getViewLifecycleOwner(), products -> adapter.setProducts(products));

        //aux.add(new Product("Chaleco Black", "Moncler", 22.5));
        //aux.add(new Product("Zapatos Negros", "Ferragamo", 26.5));
        //aux.add(new Product("Buzo de Prada", "Prada", 25.5));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.recyclerView.setAdapter(adapter);


        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    @Override
    public void onEditProduct(int position) {
        inventory = adapter.getProducts();
        Product product = inventory.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        System.out.println(product.getVendor());

        NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);

    }
}