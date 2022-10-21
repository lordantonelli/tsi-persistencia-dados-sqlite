package com.example.navegaoactivities.persistenciadedados.ex2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class AdapterMusicas extends CursorAdapter {

    public AdapterMusicas(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // Método que apenas "infla" o xml do item da lista e retorna.
    // Não há preenchimento de dados neste momento.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemDaLista = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return itemDaLista;
    }

    // Método que pega os dados lidos do bd no cursor e coloca no item da lista
    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text1 = view.findViewById( android.R.id.text1 );
        // Rxtrair as informações da posição atual do cursor
        String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
        text1.setText( titulo );
    }

}