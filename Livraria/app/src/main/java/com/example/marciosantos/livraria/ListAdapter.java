package com.example.marciosantos.livraria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import entidades.Usuario;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Usuario> usuariosArrayList;

    public ListAdapter(Context context, int layout, ArrayList<Usuario> usuariosArrayList) {
        this.context = context;
        this.layout = layout;
        this.usuariosArrayList = usuariosArrayList;
    }

    @Override
    public int getCount() {
        return usuariosArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return usuariosArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtNome, txtTelefone, txtEndereco;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtNome = row.findViewById(R.id.name_user);
            holder.txtTelefone = row.findViewById(R.id.telefone_user);
            holder.txtEndereco = row.findViewById(R.id.endereco_user);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Usuario usuario = usuariosArrayList.get(position);
        holder.txtNome.setText(usuario.getNome());
        holder.txtTelefone.setText(usuario.getTelefone());
        holder.txtEndereco.setText(usuario.getEndereco());

        return row;
    }
}
