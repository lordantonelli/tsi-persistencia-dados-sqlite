package com.example.navegaoactivities.persistenciadedados.ex2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.navegaoactivities.persistenciadedados.R;

public class AdapterArtistas extends CursorAdapter {
    public AdapterArtistas(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // Método que apenas "infla" o xml do item da lista e retorna.
    // Não há preenchimento de dados neste momento.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemDaLista = inflater.inflate(R.layout.ex2_item_lista, parent, false);
        return itemDaLista;
    }

    // Método que pega os dados lidos do bd no cursor, e coloca no item da lista
    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lblNome = view.findViewById( R.id.lblNome );
        TextView lblGenero = view.findViewById( R.id.lblGenero );
        // Extrair as informações da posição atual do cursor
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String genero = cursor.getString(cursor.getColumnIndex("genero"));
        // Insere as informações na interface
        lblNome.setText( nome );
        lblGenero.setText( genero );
    }
}