package com.example.marciosantos.projetosqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listCerveja extends AppCompatActivity {

    private static final String TAG = "listCerveja";

    DataBaseHelper mDataBaseHelper;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cerveja);

        mListView = findViewById(R.id.listViewCerveja);
        mDataBaseHelper = new DataBaseHelper(this);

        populaListView();
    }

    public void populaListView(){
        Cursor data = mDataBaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }
}
