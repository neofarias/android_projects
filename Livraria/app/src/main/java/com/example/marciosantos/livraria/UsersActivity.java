package com.example.marciosantos.livraria;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {

    private EditText mNome, mEndereco, mTelefone;
    private Button btnAdd;
    public static SQLiteHelper mSQLiteHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        ImageView imgTipo = findViewById(R.id.imageView);
        imgTipo.setImageResource(R.drawable.ic_usuarios);
        mNome = findViewById(R.id.editNome);
        mTelefone = findViewById(R.id.editTelefone);
        mEndereco = findViewById(R.id.editEndereco);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSQLiteHelper.insertDataUsuario(
                            mNome.getText().toString().trim(),
                            mTelefone.getText().toString().trim(),
                            mEndereco.getText().toString().trim());
                            Toast.makeText(UsersActivity.this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();

                            mNome.setText("");
                            mTelefone.setText("");
                            mEndereco.setText("");
                            listaUsuariosSalvos();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void listaUsuariosSalvos(){
        Intent intent = new Intent(UsersActivity.this, ListaUsersActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UsersActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
