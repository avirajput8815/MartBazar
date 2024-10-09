package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter_PdView extends RecyclerView.Adapter<Adapter_PdView.ViewHolder> {

    List<Product> list_Prodt;
    private Context context;
    ProductListener listener;

    public Adapter_PdView(List<Product> list_Prodt, Context context,ProductListener listener) {
        this.list_Prodt = list_Prodt;
        this.context = context;
        this.listener = listener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_pd_view , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_PdView.ViewHolder holder, int position) {

        // glide third party library hai jo server se url(image ka) load krta hai
        Glide.with(context).load("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTpCqsNlFX7uqklhoAusNQcxLELexXZrwXZx5zt3ymBa00boZZa_3M3SXqSq4vi4Y_JHyKob8mp1sYIvDBLLdLs_LnEqUZVromonqx0-On1hfuQPej9hH1s0g")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageVA);
//        holder.imageView.setImageResource((list_Product.get(position).getThumbnail()));
        holder.price.setText(""+list_Prodt.get(position).getPrice());
        holder.starRating.setText(""+list_Prodt.get(position).getRating());
        holder.nameProdt.setText(list_Prodt.get(position).getTitle());
        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.addToCart(list_Prodt.get(position));
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


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageVA;
        TextView price;
        TextView starRating;
        TextView nameProdt;
        Button addcart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageVA = itemView.findViewById(R.id.imageProduct);
            price = itemView.findViewById(R.id.price);
            starRating = itemView.findViewById(R.id.rating);
            nameProdt = itemView.findViewById(R.id.product_name);
            addcart = itemView.findViewById(R.id.cart);


        }
    }

}
