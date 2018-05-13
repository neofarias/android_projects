package com.example.marciosantos.listview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Crud adapter;
    private Dialog d;
    private Spinner spinner;
    private String value = "";
    private List listRestaurante = new ArrayList();;
    private Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);

       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing()) {
                        displayInputDialog();
                    } else {
                        d.dismiss();
                    }
                }
            }
        });*/

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_plus);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    private void displayInputDialog() {
        d=new Dialog(this);
        d.setContentView(R.layout.inputdialog);
        d.setTitle(R.string.titleDialog);

        final EditText nameEditTxt = d.findViewById(R.id.nome);
        final EditText enderecoEditTxt = d.findViewById(R.id.endereco);
        Button addBtn = d.findViewById(R.id.addBtn);

        //Spinner
        spinner = d.findViewById(R.id.spinnerTipos);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.typeList ,android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //Seleção do registro no spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                value = item.toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setEnabled(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name = nameEditTxt.getText().toString();
                String endereco = enderecoEditTxt.getText().toString();

                //Validate and Save
                if(name.length()>0 && name != null) {
                    restaurante = new Restaurante();
                    restaurante.setNome(name);
                    restaurante.setEndereco(endereco);
                    restaurante.setTipo(value);
                    listRestaurante.add(restaurante);

                    adapter = new Crud(getApplicationContext(), listRestaurante);
                    lv.setAdapter(adapter);
                    d.hide();
                } else {
                    Toast.makeText(MainActivity.this, "Nome não pode ser vazio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_id:
            displayInputDialog();
        }
            return super.onOptionsItemSelected(item);
    }
}