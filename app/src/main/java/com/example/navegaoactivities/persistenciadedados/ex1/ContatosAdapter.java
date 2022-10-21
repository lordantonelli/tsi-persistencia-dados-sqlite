package com.example.navegaoactivities.persistenciadedados.ex1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.navegaoactivities.persistenciadedados.R;

public class ContatosAdapter extends CursorAdapter {

    public ContatosAdapter(Context context, Cursor cursor) {
        // Transfere o contexto e o cursor com dados para a superclasse
        super(context, cursor, 0);
    }

    // Método que apenas infla o xml com o desenho do item da lista,
    // e retorna o objeto "inflado" (view)
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Recupera o objeto inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        // Infla o xml, gerando a visualização (view)
        View v = inflater.inflate(R.layout.ex1_item_lista, parent, false);
        // Retorna o objeto inflado (a view gerada)
        return v;
    }

    // Método que recebe a view inflada (o item da lista)
    // e coloca os dados nos seus objetos gráficos internos
    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Recuperando os objetos gráficos de dentro da view recebid
        TextView lblNome = view.findViewById( R.id.lblNome );
        TextView lblEmail = view.findViewById( R.id.lblEmail );
        // O cursor já vem posicionado na linha correta...
        // Basta recuperarmos os dados
        String nome  = cursor.getString(cursor.getColumnIndex( "nome"  ));
        String email = cursor.getString(cursor.getColumnIndex( "email" ));
        // Finalmente, colocamos os dados nos objetos gráficos que estão dentro do item da lista
        lblNome.setText( nome );
        lblEmail.setText( email );
    }

}