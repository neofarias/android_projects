package com.example.marciosantos.listview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
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
    private EditText nameEditTxt, enderecoEditTxt;
    private int idRestaurante = 0, imgId;
    private Button addBtn;
    private ImageView imgTipo;
    private List listRestaurante = new ArrayList();
    private Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        registerForContextMenu(lv);

        imgTipo = findViewById(R.id.imageTipo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_plus);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    @SuppressLint("ResourceType")
    private void displayInputDialog(final String newName, final String newEndereco, final int position) {
        d=new Dialog(this);
        d.setContentView(R.layout.inputdialog);
        d.setTitle(R.string.titleDialog);

        nameEditTxt = d.findViewById(R.id.nome);
        enderecoEditTxt = d.findViewById(R.id.endereco);
        addBtn = d.findViewById(R.id.addBtn);
        addBtn.setBackgroundResource(R.color.colorBtnDialog);
        addBtn.setText(R.string.btnDialogAdd);

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
                imgId = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(newName != null && newEndereco != null){
            addBtn.setBackgroundResource(R.color.colorBtnUpdate);
            addBtn.setText(R.string.btnDialogUpdate);

            nameEditTxt.setText(newName);
            enderecoEditTxt.setText(newEndereco);
        }

        addBtn.setEnabled(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditTxt.getText().toString();
                String endereco = enderecoEditTxt.getText().toString();

            if(newName != null && newEndereco != null){
                Restaurante updateRestaurante = (Restaurante) listRestaurante.get(position);
                updateRestaurante.setNome(name);
                updateRestaurante.setEndereco(endereco);
                updateRestaurante.setTipo(value);
                adapter.notifyDataSetChanged();
                d.hide();
                Toast.makeText(MainActivity.this, "Registro atualizado com sucesso!", Toast.LENGTH_LONG).show();
            } else if(name.length() > 0 && name != null) {
                    restaurante = new Restaurante();
                    restaurante.setNome(name);
                    restaurante.setEndereco(endereco);
                    restaurante.setTipo(value);
                    restaurante.setId(position);
                    configImage();
                    listRestaurante.add(restaurante);
                    idRestaurante ++;

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
            displayInputDialog(null, null, idRestaurante);
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
        displayInputDialog(restaurante.getNome(), restaurante.getEndereco(), restaurante.getId());
    }

    public void configImage(){
        if(imgId == 0){
            imgTipo.setImageDrawable(getResources().getDrawable(R.drawable.ic_buffet));
        } else if(imgId == 1){
            imgTipo.setImageDrawable(getResources().getDrawable(R.drawable.ic_fast_food));
        } else if(imgId == 2){
            imgTipo.setImageDrawable(getResources().getDrawable(R.drawable.ic_if_food_dome_379338));
        }
    }
}