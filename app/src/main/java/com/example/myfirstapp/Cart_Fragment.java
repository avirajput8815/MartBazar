package com.example.myfirstapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;


public class Cart_Fragment extends Fragment implements Adapter_Add_Cart.CartListener {

    RecyclerView recyclerView;
    List<Product> productList;
    Adapter_Add_Cart adapter;
    TextView totalamount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // kisi view ka id kaise create krte hai

        recyclerView = view.findViewById(R.id.prodtRc);

        totalamount = view.findViewById(R.id.TotalAmount);
        productList = Database_Handler.getInstance(getContext()).getProductFromCart();

        Log.d("cartfragment: ","" +  productList);

        // Adapter class ka object
        adapter = new Adapter_Add_Cart(productList , getContext() , this);

        LinearLayoutManager managerList = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(managerList);
        recyclerView.setAdapter(adapter);
        totalAmount();
        return view;
    }

    @Override
    public void increaseQuantity(Product product) {
        int quantity = product.getQuantity()+1;
        product.setQuantity(quantity);
        Database_Handler.getInstance(getContext()).updateQuantity(product);
        productList = Database_Handler.getInstance(getContext()).getProductFromCart();
        adapter.updateData(productList);
        totalAmount();
    }

    @Override
    public void decreaseQuantity(Product product) {
        if(product.getQuantity() > 1) {
            int quantity = product.getQuantity()-1;
            product.setQuantity(quantity);
            Database_Handler.getInstance(getContext()).updateQuantity(product);
            productList = Database_Handler.getInstance(getContext()).getProductFromCart();
            adapter.updateData(productList);
            totalAmount();
        }
    }

    @Override
    public void deleteProduct(Product product) {
        Database_Handler.getInstance(getContext()).deleteQuantity(product);
        productList.remove(product);
        productList = Database_Handler.getInstance(getContext()).getProductFromCart();
        adapter.updateData(productList);
        totalAmount();
    }


    public void totalAmount() {
        int total = 0;
        if(productList.size()>0){
            for (Product product : productList){
                total += product.getPrice() * product.getQuantity();
            }
            totalamount.setText("$"+total);
        }else {
            totalamount.setText("");
        }

    }
}