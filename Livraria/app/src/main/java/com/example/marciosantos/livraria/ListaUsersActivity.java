package com.example.marciosantos.livraria;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import entidades.Usuario;

public class ListaUsersActivity extends AppCompatActivity {

    public static SQLiteHelper mSQLiteHelper;
    private ListView mListView;
    private ListAdapter mAdapter = null;
    private ArrayList<Usuario> mList;
    private ListView lv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        lv = findViewById(R.id.listViewUsers);
        registerForContextMenu(lv);

        mListView = findViewById(R.id.listViewUsers);
        mList = new ArrayList<>();
        mAdapter = new ListAdapter(this, R.layout.list_view_usuarios, mList);
        mListView.setAdapter(mAdapter);

        Cursor cursor = MainActivity.mSQLiteHelper.getData("Select * from USUARIO");
        mList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String telefone = cursor.getString(2);
            String endereco = cursor.getString(3);

            mList.add(new Usuario(id, nome, telefone, endereco));
        }

        mAdapter.notifyDataSetChanged();

        if(mList.size() == 0){
            Toast.makeText(this, "Não existem registros salvos!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.update:
                //updateItemList(info.position);
                return true;
            case R.id.delete:
                //deleteItemList(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
