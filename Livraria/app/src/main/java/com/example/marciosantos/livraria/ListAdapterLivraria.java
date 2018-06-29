package com.example.marciosantos.livraria;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import entidades.Livraria;

public class ListAdapterLivraria extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Livraria> livrariaArrayList;

    public ListAdapterLivraria(Context context, int layout, ArrayList<Livraria> livrariaArrayList) {
        this.context = context;
        this.layout = layout;
        this.livrariaArrayList = livrariaArrayList;
    }

    @Override
    public int getCount() {
        return livrariaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return livrariaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtNome, txtEvento, txtEndereco;
        ImageView imgMaps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtNome = row.findViewById(R.id.nameLivraria);
            holder.txtEvento = row.findViewById(R.id.eventoLivraria);
            holder.txtEndereco = row.findViewById(R.id.enderecoEvento);
            holder.imgMaps = row.findViewById(R.id.imageMapsLivraria);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Livraria livraria = livrariaArrayList.get(position);
        holder.txtNome.setText(livraria.getNome());
        holder.txtEvento.setText(livraria.getEvento());
        holder.txtEndereco.setText(livraria.getEndereco());
        holder.imgMaps.setImageResource(R.drawable.ic_location);

        return row;
    }
}
