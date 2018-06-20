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
    private ImageView mImageUsers;

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
        mImageUsers = findViewById(R.id.imageView);

        mSQLiteHelper = new SQLiteHelper(this, "LIVRARIA.sqlite", null, 1);
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS LIVRARIA (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, evento VARCHAR, endereco VARCHAR, id_localizacao INTEGER, FOREIGN KEY (id_localizacao) REFERENCES MAPS (id));");
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS USUARIO (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, endereco VARCHAR, telefone VARCHAR. id_livraria INTEGER, id_localizacao INTEGER, FOREIGN KEY (id_livraria) REFERENCES LIVRARIA (id), FOREIGN KEY (id_localizacao) REFERENCES MAPS (id));");
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MAPS (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude VARCHAR, longitude VARCHAR);");

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
