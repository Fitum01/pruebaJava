package com.example.pruebajava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorListView extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String> names;
    public AdaptadorListView(Context context, int layout, ArrayList<String> names){

        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //copiamos la vista
        View v=convertView;

        //inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v=layoutInflater.inflate(R.layout.lista_item, null);

        String currentName = names.get(position);

        //referenciamos el elemnto a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textView);
        textView.setText(currentName);

        //devolvermos la vista inflada
        return v;
    }
}

