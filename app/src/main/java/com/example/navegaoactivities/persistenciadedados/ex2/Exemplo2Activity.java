package com.example.navegaoactivities.persistenciadedados.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.navegaoactivities.persistenciadedados.R;

public class Exemplo2Activity extends AppCompatActivity {

    // Atributos relativos aos objetos gráficos da interface
    private EditText txtArtista;
    private EditText txtGenero;
    private Button btnAdicionar;
    private ListView listaArtistas;
    // Cursor com os dados recuperados do BD
    Cursor cursorArtistas;
    // Adapter da lista de artistas
    AdapterArtistas adapterArtistas;
    // Referência para o banco de dados
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo2);

        // Ligando atributos com os IDs dos objetos gráficos
        txtArtista = findViewById( R.id.txtArtista );
        txtGenero = findViewById( R.id.txtGenero );
        btnAdicionar = findViewById( R.id.btnAdicionar );
        listaArtistas = findViewById( R.id.listaArtistas );
    }

}