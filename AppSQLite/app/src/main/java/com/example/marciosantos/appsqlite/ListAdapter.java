package com.example.marciosantos.appsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<LivrariaEntidade> livrariaEntidadeArrayList;

    public ListAdapter(Context context, int layout, ArrayList<LivrariaEntidade> livrariaEntidadeArrayList) {
        this.context = context;
        this.layout = layout;
        this.livrariaEntidadeArrayList = livrariaEntidadeArrayList;
    }

    @Override
    public int getCount() {
        return livrariaEntidadeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return livrariaEntidadeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtNome, txtEvento, txtEndereco;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtNome = row.findViewById(R.id.listNome);
            holder.txtEvento = row.findViewById(R.id.listEvento);
            holder.txtEndereco = row.findViewById(R.id.listEndereco);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        LivrariaEntidade livrariaEntidade = livrariaEntidadeArrayList.get(position);
        holder.txtNome.setText(livrariaEntidade.getNome());
        holder.txtEvento.setText(livrariaEntidade.getEvento());
        holder.txtEndereco.setText(livrariaEntidade.getEndereco());

        return row;
    }

}
