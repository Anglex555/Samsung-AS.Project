package com.anglex.shop_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.anglex.shop_online.models.Order;
import com.anglex.shop_online.models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    ConstraintLayout menu_selection;
    Boolean is_menu = false;
    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        orderList = findViewById(R.id.orderList);

        List<String> products_titles = new ArrayList<>();
        for(Product product: MainActivity.allProductList) {
            if(Order.order_products.contains(product.getId())) {
                products_titles.add(product.getTitle());
            }
        }

        if(products_titles.size() <= 0) products_titles.add("Пока что у вас в корзине ничего нет");

        orderList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products_titles));

        menu_selection = findViewById(R.id.constraintLayout2);
        menu_selection.setMaxWidth(0);
        menu_selection.setX(-360);
    }

    public void openMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openContacts(View view) {
        Intent intent = new Intent(this, СontactsActivity.class);
        startActivity(intent);
    }

    public void openMenu(View view) {
        if (is_menu) {
//            TranslateAnimation menu_anim_close = new TranslateAnimation(0, -360, 0, 0);
//            menu_anim_close.setDuration(250);
//            menu_anim_close.setFillAfter(true);
//            menu_anim_close.setInterpolator(new AccelerateInterpolator(1.0f));
//            menu_selection.startAnimation(menu_anim_close);
            menu_selection.setMaxWidth(0);
            is_menu = false;
        }else {
            menu_selection.setMaxWidth(1000);
            TranslateAnimation menu_anim = new TranslateAnimation(-360, 0, 0, 0);
            menu_anim.setDuration(250);
            menu_anim.setFillAfter(true);
            menu_anim.setInterpolator(new AccelerateInterpolator(1.0f));
            menu_selection.startAnimation(menu_anim);
            menu_selection.setX(0);
            is_menu = true;
        }
    }
}