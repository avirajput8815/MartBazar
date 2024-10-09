package com.example.myfirstapp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder> {

    List<Product> list_Product;
    private Context mContext;

    public Adapter_Product(List<Product> list_Product,Context context) {
        this.list_Product = list_Product;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_product , parent , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // glide third party library hai jo server se url(image ka) load krta hai
        Glide.with(mContext).load("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTpCqsNlFX7uqklhoAusNQcxLELexXZrwXZx5zt3ymBa00boZZa_3M3SXqSq4vi4Y_JHyKob8mp1sYIvDBLLdLs_LnEqUZVromonqx0-On1hfuQPej9hH1s0g")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        // holder.imageView.setImageResource((list_Product.get(position).getThumbnail()));
        holder.likeimg.setImageResource(R.drawable.likeviewall);
        holder.pprice.setText(""+list_Product.get(position).getPrice());
        holder.ratingStar.setText(""+list_Product.get(position).getRating());
        holder.productName.setText(list_Product.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(list_Product == null){
            return 0;
        }
        return list_Product.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        ImageView likeimg;
        TextView pprice;
        TextView ratingStar;
        TextView productName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageProduct);
            likeimg = itemView.findViewById(R.id.like);
            pprice = itemView.findViewById(R.id.price);
            ratingStar = itemView.findViewById(R.id.rating);
            productName = itemView.findViewById(R.id.product_name);
        }
    }
}

