package com.example.marciosantos.livraria;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private ListView mListView;
    private ListAdapterUser mAdapter = null;
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
        mAdapter = new ListAdapterUser(this, R.layout.list_view_usuarios, mList);
        mListView.setAdapter(mAdapter);

        this.montaListaUsuarios();
    }

    public void montaListaUsuarios(){
        Cursor cursor = MainActivity.mSQLiteHelper.getData("Select * from USUARIO");
        mList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String telefone = cursor.getString(2);
            String endereco = cursor.getString(3);
            String sexo = cursor.getString(4);

            mList.add(new Usuario(id, nome, telefone, endereco, sexo));
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
                this.updateUsuario(info.position);
                return true;
            case R.id.delete:
                this.deleteUsuario(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void updateUsuario(int position){
        int id = 0;
        String nome = "", telefone = "", endereco = "", sexo = "";

        Cursor c1 = MainActivity.mSQLiteHelper.getData("SELECT id FROM USUARIO");
        ArrayList<Integer> arrayID = new ArrayList<>();
        while (c1.moveToNext()){
            arrayID.add(c1.getInt(0));
        }

        Cursor c = MainActivity.mSQLiteHelper.getData("SELECT * FROM USUARIO WHERE id =" + arrayID.get(position));
        while (c.moveToNext()){
            id = c.getInt(0);
            nome = c.getString(1);
            telefone = c.getString(2);
            endereco = c.getString(3);
            sexo = c.getString(4);
        }

        Usuario entidade = new Usuario();
        entidade.setId(id);
        entidade.setNome(nome);
        entidade.setTelefone(telefone);
        entidade.setEndereco(endereco);
        entidade.setSexo(sexo);

        Intent intent = new Intent(ListaUsersActivity.this, UsersActivity.class);
        intent.putExtra("Entidade", entidade);
        startActivity(intent);
    }

    public void deleteUsuario(final int idPosition){
        Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM USUARIO");
        ArrayList<Integer> arrayID = new ArrayList<>();
        while (c.moveToNext()){
            arrayID.add(c.getInt(0));
        }

        try {
            MainActivity.mSQLiteHelper.deleteDataUsuario(arrayID.get(idPosition));
        } catch (Exception e){
            Log.e("error", e.getMessage());
        }
        this.montaListaUsuarios();
    }

}
