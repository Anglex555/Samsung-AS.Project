package com.anglex.shop_online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Toast;

public class СontactsActivity extends AppCompatActivity {

    ConstraintLayout menu_selection;
    Boolean is_menu = false;

    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        menu_selection = findViewById(R.id.constraintLayout2);
        menu_selection.setMaxWidth(0);
        menu_selection.setX(-360);
    }

    public void openMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    public void openWhatsApp(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.link/roc6k7"));
        startActivity(browserIntent);
    }

    public void openVK(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/id355899375"));
        startActivity(browserIntent);
    }

    public void clickSendEmail(View view) {
        sendEmail();
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

    private void sendEmail() {
        if (checkFields() == 0) return;
        String title = "Вопрос";
        String email = editTextEmail.getText().toString().trim();
        String name = editTextSubject.getText().toString().trim();
        String question = editTextMessage.getText().toString().trim();
        String message = "Имя:\n" + name + "\n\nПочта:\n" + email + "\n\nВопрос:\n" + question;

        SendMail sm = new SendMail(this, "as.aa.project@mail.ru", title, message);

        sm.execute();
    }

    public int checkFields() {
        if (editTextEmail.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Введите почту", Toast.LENGTH_LONG).show();
            return 0;
        }
        if (editTextSubject.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Введите имя", Toast.LENGTH_LONG).show();
            return 0;
        }
        if (editTextMessage.getText().toString().trim().length() == 0){
            Toast.makeText(this, "Введите вопрос", Toast.LENGTH_LONG).show();
            return 0;
        }
        return 1;
    }
}