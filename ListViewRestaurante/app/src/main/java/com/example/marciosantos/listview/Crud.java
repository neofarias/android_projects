package com.example.marciosantos.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Crud extends BaseAdapter{

    private Context mContext;
    private List<Restaurante> listRestaurant;

    public Crud(Context mContext, List<Restaurante> listRestaurant) {
        this.mContext = mContext;
        this.listRestaurant = listRestaurant;
    }

    @Override
    public int getCount() {
        return listRestaurant.size();
    }

    @Override
    public Object getItem(int position) {
        return listRestaurant.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_view_itens, null);

        TextView txtName = v.findViewById(R.id.name_restaurant);
        TextView txtEndereco = v.findViewById(R.id.endereco_restaurant);
        TextView txtTipo = v.findViewById(R.id.tipo_restaurant);

        txtName.setText(listRestaurant.get(position).getNome());
        txtEndereco.setText(listRestaurant.get(position).getEndereco());
        txtTipo.setText(listRestaurant.get(position).getTipo());

        v.setTag(listRestaurant.get(position).getId());
        return v;
    }

    /*
<<<<<<< HEAD
    public void save(String name, String endereco, String tipo) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(name);
        restaurante.setEndereco(endereco);
        restaurante.setTipo(tipo);
    }


=======
>>>>>>> New updates in project
    public Boolean update(int position,String newName) {
        try {
            //names.remove(position);
           // names.add(position,newName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
<<<<<<< HEAD
    }

    public Boolean delete(int position) {
        try {
            //names.remove(position);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
=======
    }*/

>>>>>>> New updates in project
}