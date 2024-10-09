package com.example.myfirstapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_Add_Cart extends RecyclerView.Adapter<Adapter_Add_Cart.ViewHolder> {

    List<Product> list_Prodt;
    private Context context;
    CartListener listener;
    int totalAmount;

    public Adapter_Add_Cart(List<Product> list_Prodt , Context context , CartListener listener) {
        this.list_Prodt = list_Prodt;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_add_cart , parent , false);
        return new Adapter_Add_Cart.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Add_Cart.ViewHolder holder,
                                 int position) {

        Glide.with(context).load("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTpCqsNlFX7uqklhoAusNQcxLELexXZrwXZx5zt3ymBa00boZZa_3M3SXqSq4vi4Y_JHyKob8mp1sYIvDBLLdLs_LnEqUZVromonqx0-On1hfuQPej9hH1s0g")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.productName.setText(list_Prodt.get(position).getTitle());
        double rating = list_Prodt.get(position).getRating();
        holder.rating.setRating((float) rating);
        holder.rating.setEnabled(false);
         holder.price.setText("$"+list_Prodt.get(position).getPrice());
        holder.count.setText(""+list_Prodt.get(position).getQuantity());

        holder.addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.increaseQuantity(list_Prodt.get(position));
                notifyItemChanged(position);
            }
        });
        holder.minusCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.decreaseQuantity(list_Prodt.get(position));
                notifyItemChanged(position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteProduct(list_Prodt.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list_Prodt == null){
            return 0;
        }
        return list_Prodt.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView productName;
        RatingBar rating;
        TextView price;
        ImageView delete;
        ImageView addCount;
        TextView count;
        ImageView minusCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.Image);
            productName = itemView.findViewById(R.id.ProductName);
            rating = itemView.findViewById(R.id.Rating);
            price = itemView.findViewById(R.id.Price);
            delete = itemView.findViewById(R.id.Delete);
            addCount = itemView.findViewById(R.id.AddCount);
            count = itemView.findViewById(R.id.Count);
            minusCount = itemView.findViewById(R.id.MinusCount);
        }
    }
    interface CartListener {

        void increaseQuantity(Product product);

        void decreaseQuantity(Product product);

        void deleteProduct(Product product);

    }

    public void updateData(List<Product> products){
        list_Prodt.clear();
        list_Prodt.addAll(products);
        notifyDataSetChanged();
    }
}