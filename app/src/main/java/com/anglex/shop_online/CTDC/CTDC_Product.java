package com.anglex.shop_online.CTDC;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anglex.shop_online.ProductActivity;
import com.anglex.shop_online.R;
import com.anglex.shop_online.models.Product;

import java.util.List;

public class CTDC_Product extends RecyclerView.Adapter<CTDC_Product.ProductViewHolder> {

    Context context;
    List<Product> products;

    public CTDC_Product(Context context, List<Product> product_list) {
        this.context = context;
        this.products = product_list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View product_items = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new CTDC_Product.ProductViewHolder(product_items);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.product_bg.setCardBackgroundColor(Color.parseColor(products.get(position).getColor()));
        int image_id = context.getResources().getIdentifier(products.get(position).getImg(), "drawable", context.getPackageName());
        holder.product_image.setImageResource(image_id);
        holder.product_title.setText(products.get(position).getTitle());
        holder.product_description.setText(products.get(position).getDescription());
        holder.product_price.setText(products.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);

                intent.putExtra("color", Color.parseColor(products.get(position).getColor()));
                intent.putExtra("image", image_id);
                intent.putExtra("title", products.get(position).getTitle());
                intent.putExtra("description", products.get(position).getFull_description());
                intent.putExtra("price", products.get(position).getPrice());
                intent.putExtra("url", products.get(position).getUrl());
                intent.putExtra("id", products.get(position).getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder {

        CardView product_bg;
        ImageView product_image;
        TextView product_title, product_description, product_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_bg = itemView.findViewById(R.id.cardView);
            product_image = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);
            product_description = itemView.findViewById(R.id.product_description);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }

}
