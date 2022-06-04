package com.anglex.shop_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anglex.shop_online.models.Order;
import com.google.android.material.button.MaterialButton;

public class ProductActivity extends AppCompatActivity {

    ConstraintLayout menu_selection, product_main;
    MaterialButton demo_button, order_button;
    ImageView product_image;
    TextView product_title, product_description, product_price;
    String product_url;
    Boolean is_menu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        product_main = findViewById(R.id.product_page_main);
        product_image = findViewById(R.id.product_page_image);
        product_title = findViewById(R.id.product_page_title);
        product_description = findViewById(R.id.product_page_description);
        product_price = findViewById(R.id.product_page_price);

        //demo_button = findViewById(R.id.url_button);
        order_button = findViewById(R.id.order_button);
        menu_selection = findViewById(R.id.constraintLayout2);
        menu_selection.setMaxWidth(0);
        menu_selection.setX(-360);

        product_main.setBackgroundColor(getIntent().getIntExtra("color", 0));
        product_image.setImageResource(getIntent().getIntExtra("image", 0));
        product_title.setText(getIntent().getStringExtra("title"));
        product_description.setText(getIntent().getStringExtra("description"));
        product_price.setText(getIntent().getStringExtra("price"));
        product_url = getIntent().getStringExtra("url");

        int order_id = getIntent().getIntExtra("id", 0);
        if (Order.order_products.contains(order_id)) order_button.setText("Убрать из корзины");

//        demo_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(product_url));
//                startActivity(browserIntent);
//            }
//        });
    }

    public void AddToOrder(View view){
        int order_id = getIntent().getIntExtra("id", 0);
        if (Order.order_products.contains(order_id)) {
            Order.order_products.remove(order_id);
            order_button.setText("Добавить в корзину");
            Toast.makeText(this, "Убрано из корзины", Toast.LENGTH_LONG).show();
        }else {
            Order.order_products.add(order_id);
            order_button.setText("Убрать из корзины");
            Toast.makeText(this, "Добавлено в корзину", Toast.LENGTH_LONG).show();
        }
    }

    public void openMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
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