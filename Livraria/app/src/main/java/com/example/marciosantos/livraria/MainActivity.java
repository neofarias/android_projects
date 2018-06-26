package com.example.marciosantos.livraria;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_user_add);
        actionBar.setLogo(R.drawable.ic_user_list);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_id:
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
                break;
            case R.id.user_list_id:
                Intent intentListUser = new Intent(MainActivity.this, ListaUsersActivity.class);
                startActivity(intentListUser);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
