package com.anglex.shop_online.CTDC;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anglex.shop_online.MainActivity;
import com.anglex.shop_online.R;
import com.anglex.shop_online.models.Category;

import java.util.List;

public class CTDC_Category extends RecyclerView.Adapter<CTDC_Category.CategoryViewHolder> {

    Context context;
    List<Category> categorys;

    public CTDC_Category(Context context, List<Category> categorys) {
        this.context = context;
        this.categorys = categorys;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View category_items = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(category_items);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.category_item.setText(categorys.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showProductsByCategory(categorys.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView category_item;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category_item = itemView.findViewById(R.id.category_item);
        }
    }
}
