package com.example.marciosantos.listview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
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
<<<<<<< HEAD
=======
    private EditText nameEditTxt, enderecoEditTxt;
    private int idRestaurante = 0;
>>>>>>> New updates in project
    private List listRestaurante = new ArrayList();;
    private Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        lv = findViewById(R.id.listView);
        registerForContextMenu(lv);

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

=======
        lv = findViewById(R.id.listView);
        registerForContextMenu(lv);

>>>>>>> New updates in project
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_plus);
        actionBar.setDisplayUseLogoEnabled(true);
    }

<<<<<<< HEAD
    private void displayInputDialog() {
=======
    private void displayInputDialog(String newName, String newEndereco, String newValue, final int position) {
>>>>>>> New updates in project
        d=new Dialog(this);
        d.setContentView(R.layout.inputdialog);
        d.setTitle(R.string.titleDialog);

<<<<<<< HEAD
        final EditText nameEditTxt = d.findViewById(R.id.nome);
        final EditText enderecoEditTxt = d.findViewById(R.id.endereco);
=======
        nameEditTxt = d.findViewById(R.id.nome);
        enderecoEditTxt = d.findViewById(R.id.endereco);
>>>>>>> New updates in project
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

<<<<<<< HEAD
=======
        if(newName != null && newEndereco != null && newValue != null){
            nameEditTxt.setText(newName);
            enderecoEditTxt.setText(newEndereco);
            spinner.setTag(newValue);
        }

>>>>>>> New updates in project
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
<<<<<<< HEAD
                    listRestaurante.add(restaurante);
=======
                    restaurante.setId(position);
                    listRestaurante.add(restaurante);
                    idRestaurante ++;
>>>>>>> New updates in project

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
<<<<<<< HEAD
            displayInputDialog();
=======
            displayInputDialog(null, null, null, idRestaurante);
>>>>>>> New updates in project
        }
            return super.onOptionsItemSelected(item);
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
<<<<<<< HEAD
                //lv.updateViewLayout();
            case R.id.delete:
                lv.removeViewAt(info.position);
                return true;
        }

        return super.onContextItemSelected(item);
=======
                updateItemList(info.position);
                return true;
            case R.id.delete:
                deleteItemList(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deleteItemList(int position){
        listRestaurante.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void updateItemList(int position){
        Restaurante restaurante = (Restaurante) listRestaurante.get(position);
        displayInputDialog(restaurante.getNome(), restaurante.getEndereco(), restaurante.getTipo(), restaurante.getId());
>>>>>>> New updates in project
    }
}