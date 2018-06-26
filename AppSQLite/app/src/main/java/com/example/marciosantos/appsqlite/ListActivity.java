package com.example.marciosantos.appsqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.marciosantos.appsqlite.R.layout.row;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<LivrariaEntidade> mList;
    private ListAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new ListAdapter(this, row, mList);
        mListView.setAdapter(mAdapter);

        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM LIVRARIA");
        mList.clear();

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String evento = cursor.getString(2);
            String endereco = cursor.getString(3);

            mList.add(new LivrariaEntidade(id, nome, evento, endereco));
        }

        mAdapter.notifyDataSetChanged();

        if(mList.size() == 0){
            Toast.makeText(this, "Não existem registros salvos!", Toast.LENGTH_SHORT).show();
        }
    }

}
