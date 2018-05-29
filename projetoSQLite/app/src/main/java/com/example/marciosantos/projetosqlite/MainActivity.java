package com.example.marciosantos.projetosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mDataBaseHelper;
    private Button btnAdd, btnView;
    private EditText nomeEditText, quantidadeEditText, tipoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBaseHelper = new DataBaseHelper(this);

        nomeEditText = findViewById(R.id.nomeEditText);
        quantidadeEditText = findViewById(R.id.quantidadeEditText);
        tipoEditText = findViewById(R.id.tipoEditText);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString();
                String quantidade = quantidadeEditText.getText().toString();
                String tipo = tipoEditText.getText().toString();

                if(nomeEditText.length() != 0 && quantidadeEditText.length() != 0 && tipoEditText.length() != 0){
                    salvaDados(nome, quantidade, tipo);
                    nomeEditText.setText("");
                    quantidadeEditText.setText("");
                    tipoEditText.setText("");
                } else {
                    toastMessage(getResources().getString(R.string.saveError));
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listCerveja.class);
                startActivity(intent);
            }
        });
    }

    public void salvaDados(String newName, String newQuantidade, String newTipo){
        Boolean insertDados = mDataBaseHelper.addData(newName, newQuantidade, newTipo);

        if(insertDados){
            toastMessage(getResources().getString(R.string.addOK));
        } else {
            toastMessage(getResources().getString(R.string.addError));
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
