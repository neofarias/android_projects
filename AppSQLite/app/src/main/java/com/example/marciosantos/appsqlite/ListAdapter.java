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
    private ArrayList<Model> modelArrayList;

    public ListAdapter(Context context, int layout, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.layout = layout;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtNome, txtQuantidade, txtTipo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtNome = row.findViewById(R.id.listNome);
            holder.txtQuantidade = row.findViewById(R.id.listQuantidade);
            holder.txtTipo = row.findViewById(R.id.listTipo);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Model model = modelArrayList.get(position);
        holder.txtNome.setText(model.getNome());
        holder.txtQuantidade.setText(model.getQuantidade());
        holder.txtTipo.setText(model.getTipo());

        return row;
    }

}
