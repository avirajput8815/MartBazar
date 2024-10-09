package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Home_Fragment extends Fragment {

    RecyclerView rcyView;
    RecyclerView rcyView1;
    TextView viewAll;
    List<Product> productList;
    Database_Handler sqLiteDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcyView = view.findViewById(R.id.rcView);
        rcyView1 = view.findViewById(R.id.rcView1);
        viewAll = view.findViewById(R.id.viewAllProduct);

        viewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(),Product_Viewall.class);
                intent.putExtra("product", (Serializable) productList);
                startActivity(intent);
            }
        });

String val  = "12";
int value = Integer.parseInt(val);

        ArrayList<DataList> list = new ArrayList<>();
//        ArrayList<Product> listProduct = new ArrayList<>();
//        ((Dashboard_Activity)getActivity()).database.
        list.add(new DataList(R.drawable.shirt_images));
        list.add(new DataList(R.drawable.gentswear));
        list.add(new DataList(R.drawable.casual_shoes));
        list.add(new DataList(R.drawable.lower_tshirt));
        list.add(new DataList(R.drawable.laptop));
        list.add(new DataList(R.drawable.crux));

//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));
//        listProduct.add(new Data_Product("Product name", R.drawable.img_1, "5", "60000", R.drawable.sign_up));


        Adapter adapter = new Adapter(list);
        LinearLayoutManager managerList = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,true);
        rcyView.setLayoutManager(managerList);
        rcyView.setAdapter(adapter);
        rcyView.scrollToPosition(list.size()-1);


        Adapter_Product adapter_product = new Adapter_Product(productList, getContext());
        LinearLayoutManager managerList1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,true);
        rcyView1.setLayoutManager(managerList1);
        rcyView1.setAdapter(adapter_product);

        return view;
    }

        // json file ko read kr rha or string mai string kr rha hai
        public String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getActivity().getAssets().open("Products.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                // read product.json or set in json string
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //
    public void loadData(){
        try {
            String json = loadJSONFromAsset();
            JSONObject obj = new JSONObject(json);
            JSONArray products = obj.getJSONArray("products");

            // Gson class hai jo json file handle krne mai help krti hai
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>(){}.getType();

            // string passs kiya woh list bna kr de rha hai
            productList = gson.fromJson(products.toString(), type);
        } catch (Exception e) {
           Log.d("Exp: ",e.getMessage());
        }
    }


}