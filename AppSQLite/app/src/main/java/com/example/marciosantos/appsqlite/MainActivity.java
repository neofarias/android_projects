package com.example.marciosantos.appsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditNome, mEditQuantidade, mEditTipo;
    private Button mBtnAdd, mBtnList;
    private ImageView mImageBeer;

    public static SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditNome = findViewById(R.id.editNome);
        mEditQuantidade = findViewById(R.id.editQuantidade);
        mEditTipo = findViewById(R.id.editTipo);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnList = findViewById(R.id.btnList);
        mImageBeer = findViewById(R.id.imageView);

        mSQLiteHelper = new SQLiteHelper(this, "CERVEJA.sqlite", null, 1);
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CERVEJA (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, quantidade VARCHAR, tipo VARCHAR);");


        mImageBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSQLiteHelper.insertData(
                            mEditNome.getText().toString().trim(),
                            mEditQuantidade.getText().toString().trim(),
                            mEditTipo.getText().toString().trim());

                    Toast.makeText(MainActivity.this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                    mEditNome.setText("");
                    mEditQuantidade.setText("");
                    mEditTipo.setText("");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }
}