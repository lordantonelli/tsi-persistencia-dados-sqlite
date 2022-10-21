package com.example.navegaoactivities.persistenciadedados.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.navegaoactivities.persistenciadedados.R;

public class MusicasActivity extends AppCompatActivity {

    // Atributos relativos aos objetos gráficos da interface
    private TextView lblArtista;
    private EditText txtTitulo;
    private Button btnAdiciona;
    private ListView listaMusicas;
    // Cursor com os dados recuperados do BD
    private Cursor cursorMusicas;
    // Adapter da lista de musicas
    private AdapterMusicas adapterMusicas;
    // Referência para o banco de dados
    private SQLiteDatabase bd;
    // ID do artista cujas musicas são exibidas nesta activity
    private int idArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas);

        // Ligando atributos com os IDs dos objetos gráficos
        lblArtista = findViewById( R.id.lblArtista );
        txtTitulo = findViewById( R.id.txtTitulo );
        listaMusicas = findViewById( R.id.listaMusicas );
        btnAdiciona = findViewById( R.id.btnAdicionarMusica);
    }

}