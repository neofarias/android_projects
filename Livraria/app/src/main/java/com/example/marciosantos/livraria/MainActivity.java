package com.example.marciosantos.livraria;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import entidades.Livraria;
import entidades.Maps;

public class MainActivity extends AppCompatActivity {

    public static SQLiteHelper mSQLiteHelper;
    private ListView mListView;
    private ListAdapterLivraria mAdapter = null;
    private ArrayList<Livraria> mList;
    private Double latitude, longitude;
    private String txtPesquisa;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new ListAdapterLivraria(this, R.layout.list_view_livraria, mList);
        mListView.setAdapter(mAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_user_add);
        actionBar.setLogo(R.drawable.ic_user_list);
        actionBar.setDisplayUseLogoEnabled(true);

        mSQLiteHelper = new SQLiteHelper(this, "LIVRARIA.sqlite", null, 1);
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS USUARIO (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, telefone VARCHAR, endereco VARCHAR, sexo VARCHAR);");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id = id + 1;
                Cursor c = MainActivity.mSQLiteHelper.getData("SELECT * FROM MAPS WHERE id =" + id);
                while (c.moveToNext()){
                    id = c.getInt(0);
                    latitude = c.getDouble(1);
                    longitude = c.getDouble(2);
                }

                Maps entidade = new Maps();
                position = Integer.parseInt(String.valueOf(id));
                entidade.setId(position);
                entidade.setLatitude(latitude);
                entidade.setLongitude(longitude);

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("EntidadeMaps", entidade);
                startActivity(intent);
            }
        });

        //this.criaMapas();
        //this.criarLivrarias();
        this.montaListaLivraria();
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
            case R.id.search_button:
                SearchView view = (SearchView) item.getActionView();
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Cursor cursor = MainActivity.mSQLiteHelper.getData("Select * from LIVRARIA where nome like "+"'"+"%"+newText+"%"+"'");

                        ArrayList<Livraria> mListTemp;
                        mListTemp = new ArrayList<>();
                        mAdapter = new ListAdapterLivraria(MainActivity.this, R.layout.list_view_livraria, mListTemp);
                        mListView.setAdapter(mAdapter);

                        while (cursor.moveToNext()){
                            int id = cursor.getInt(0);
                            String nome = cursor.getString(1);
                            String evento = cursor.getString(2);
                            String endereco = cursor.getString(3);
                            mListTemp.add(new Livraria(id, nome, evento, endereco));
                        }
                        mAdapter.notifyDataSetChanged();
                        txtPesquisa = newText;
                        return true;
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

    public void montaListaLivraria(){
        Cursor cursor = MainActivity.mSQLiteHelper.getData("Select * from LIVRARIA");
        mList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String evento = cursor.getString(2);
            String endereco = cursor.getString(3);
            mList.add(new Livraria(id, nome, evento, endereco));
        }
        mAdapter.notifyDataSetChanged();

        if(mList.size() == 0){
            Toast.makeText(this, "Não existem registros salvos!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        this.sharedPreferences = getSharedPreferences(txtPesquisa, Context.MODE_PRIVATE);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    public void criarLivrarias(){
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS LIVRARIA (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, evento VARCHAR, endereco VARCHAR, id_localizacao INTEGER, FOREIGN KEY (id_localizacao) REFERENCES MAPS (id));");
        mSQLiteHelper.queryData("INSERT INTO LIVRARIA (id, nome, evento, endereco, id_localizacao) VALUES (null, 'Saraiva - Praia de Belas', 'Tarde de Autógrafos', 'Av. Praia de Belas, 1181' ,1);");
        mSQLiteHelper.queryData("INSERT INTO LIVRARIA (id, nome, evento, endereco, id_localizacao) VALUES (null, 'Cultura - Bourbon Country', 'Lançamento de Livros', 'Av. Túlio de Rose, 80' ,2);");
        mSQLiteHelper.queryData("INSERT INTO LIVRARIA (id, nome, evento, endereco, id_localizacao) VALUES (null, 'Cameron', 'Apresentação de MPB', 'Av. Ipiranga, 5200' ,3);");
        mSQLiteHelper.queryData("INSERT INTO LIVRARIA (id, nome, evento, endereco, id_localizacao) VALUES (null, 'Siciliano', 'Inauguração de nova filial', 'Rua dos Andradas, 1276' ,4);");
    }

    public void criaMapas(){
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MAPS (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude Long, longitude Long);");
        mSQLiteHelper.queryData("INSERT INTO MAPS (id, latitude, longitude) VALUES (null, -30.0500065, -51.2288272);");
        mSQLiteHelper.queryData("INSERT INTO MAPS (id, latitude, longitude) VALUES (null, -30.0223784, -51.1631202);");
        mSQLiteHelper.queryData("INSERT INTO MAPS (id, latitude, longitude) VALUES (null, -30.0549950, -51.1874176);");
        mSQLiteHelper.queryData("INSERT INTO MAPS (id, latitude, longitude) VALUES (null, -30.0296787, -51.2289673);");
    }

}
