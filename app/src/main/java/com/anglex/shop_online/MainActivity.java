package com.anglex.shop_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.anglex.shop_online.CTDC.CTDC_Category;
import com.anglex.shop_online.CTDC.CTDC_Product;
import com.anglex.shop_online.models.Category;
import com.anglex.shop_online.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView category_recycler, product_recycler;
    CTDC_Category CTDC_Category;
    static CTDC_Product ctdc_product;
    static List<Product> productList = new ArrayList<>();
    static List<Product> allProductList = new ArrayList<>();

    ConstraintLayout menu_selection;
    Boolean is_menu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu_selection = findViewById(R.id.constraintLayout2);
        menu_selection.setMaxWidth(0);
        menu_selection.setX(-360);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(0, "All"));
        categoryList.add(new Category(1, "Sites"));
        categoryList.add(new Category(2, "Games"));
        categoryList.add(new Category(3, "Apps"));
        categoryList.add(new Category(4, "Other"));

        setCategoryRecycler(categoryList);

        productList.add(new Product(1, 1, "website", "Многостраничный сайт", "от 30.000₽", "Адаптивный сайт с анимацией, состоящий из определенного набора страниц.", "Адаптивный сайт с анимацией и интерактивными элементами, состоящий из определенного набора страниц: главная, о компании, каталог, контакты и т. д. Такие сайты можно использовать для гораздо большего количества целей и задач, по сравнению с лендингами. После создания вы получаете архив со всеми файлами для установки сайта на своем хостинге. Предподложительное время создания - 4-6 недель.", "#256EFF", "https://anglex555.github.io"));
        productList.add(new Product(2, 3, "java_app", "Android приложение на Java", "от 60.000₽", "Создание мобильного  приложения на языке программирования Java в android studio.", "Создание мобильного  приложения на языке программирования Java в android studio. В разработке приложения может использоваться ваш личный дизайн и цветовая палитрв. Посли создания приложения вы получите архив со всеми файлами. Предподложительное время создания - 3-6 недель.", "#A5CB3A", ""));
        productList.add(new Product(3, 2, "unreal_game", "Компьютерная игра на Unreal Engine", "от 50.000₽", "Создание компьютерной игры в среде разработки Unreal Engine, исходя из ваших идей.", "Создание компьютерной игры на движке Unreal Engine. В разработке будут использоваться ваши идеи и зарисовки, если такие имеются. Посли создания игры вы получите архив со всеми файлами и экзэшник для запуска. Предподложительное время создания - 1-3 месяца.", "#E5827D", ""));
        productList.add(new Product(4, 2, "unity_game", "Компьютерная игра на Unity", "от 50.000₽", "Создание компьютерной игры в среде разработки Unity, исходя из ваших идей.", "Создание компьютерной игры на движке Unity. В разработке будут использоваться ваши идеи и зарисовки, если такие имеются. Посли создания игры вы получите архив со всеми файлами и экзэшник для запуска. Предподложительное время создания - 1-3 месяца.", "#202020", ""));
        productList.add(new Product(5, 1, "landing", "Одностраничный сайт-лендинг", "от 10.000₽", "Одностраничный сайт с индивидуальным дизайном и информацией о товаре или услуге.", "Одностраничный, адаптивный сайт-лендинг с индивидуальным дизайноми, анимацией и интерактивными элементами, с информацией о товаре или услуге. После создания вы получаете архив со всеми файлами для установки сайта на своем хостинге. Предподложительное время создания - 2-4 недели.", "#FF5D3A", ""));

        allProductList.addAll(productList);

        setProductRecycler(productList);
    }



    private void setProductRecycler(List<Product> product_list) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        product_recycler = findViewById(R.id.products_items);
        product_recycler.setLayoutManager(layoutManager);

        ctdc_product = new CTDC_Product(this, product_list);
        product_recycler.setAdapter(ctdc_product);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        category_recycler = findViewById(R.id.category_items_list);
        category_recycler.setLayoutManager(layoutManager);

        CTDC_Category = new CTDC_Category(this, categoryList);
        category_recycler.setAdapter(CTDC_Category);
    }

    public static void showProductsByCategory(int category) {
        List<Product> filterProducts = new ArrayList<>();
        productList.clear();
        productList.addAll(allProductList);

        if (category != 0) {
            for(Product product: productList) {
                if(product.getCategory() == category){
                    filterProducts.add(product);
                }
            }
        }else {
            filterProducts.addAll(allProductList);
        }

        productList.clear();
        productList.addAll(filterProducts);

        ctdc_product.notifyDataSetChanged();
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