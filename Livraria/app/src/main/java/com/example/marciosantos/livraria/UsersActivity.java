package com.example.marciosantos.livraria;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class UsersActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.list_view_usuarios);

        ImageView imgTipo = findViewById(R.id.imageTipo);
        imgTipo.setImageResource(R.drawable.ic_usuarios);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UsersActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
