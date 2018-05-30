package com.example.marciosantos.projetosqlite;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class DisplayDialog extends AppCompatActivity{

    private Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        dialog = new Dialog(this);
    }

    public void setDialog(){

    }

    private void setCampos(){
        EditText nomeEditText = dialog.findViewById(R.id.nomeEditText);
    }

}
