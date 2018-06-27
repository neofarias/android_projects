package com.example.marciosantos.livraria;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import entidades.Usuario;

public class UsersActivity extends AppCompatActivity {

    private EditText mNome, mEndereco, mTelefone;
    private Button btnAdd;
    private Spinner spinner;
    private String valueSexo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Intent intent = getIntent();
        final Usuario usuario = intent.getParcelableExtra("Entidade");

        ImageView imgTipo = findViewById(R.id.imageView);
        imgTipo.setImageResource(R.drawable.ic_usuarios);
        mNome = findViewById(R.id.editNome);
        mTelefone = findViewById(R.id.editTelefone);
        mEndereco = findViewById(R.id.editEndereco);
        btnAdd = findViewById(R.id.btnAdd);

        mNome.setText("");
        mTelefone.setText("");
        mEndereco.setText("");

        //Spinner
        spinner = findViewById(R.id.spinnerSexo);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.typeList ,android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //Seleção do registro no spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                valueSexo = item.toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        if(usuario != null){
            mNome.setText(usuario.getNome());
            mTelefone.setText(usuario.getTelefone());
            mEndereco.setText(usuario.getEndereco());
            valueSexo = usuario.getSexo();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(usuario == null) {
                        MainActivity.mSQLiteHelper.insertDataUsuario(
                                mNome.getText().toString().trim(),
                                mTelefone.getText().toString().trim(),
                                mEndereco.getText().toString().trim(),
                                valueSexo);
                        Toast.makeText(UsersActivity.this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                        listaUsuariosSalvos();
                    } else {
                        MainActivity.mSQLiteHelper.updateDataUsuario(
                                mNome.getText().toString().trim(),
                                mTelefone.getText().toString().trim(),
                                mEndereco.getText().toString().trim(),
                                valueSexo,
                                usuario.getId());
                        Toast.makeText(UsersActivity.this, "Registro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                        listaUsuariosSalvos();
                    }
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
