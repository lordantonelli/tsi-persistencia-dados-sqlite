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
        btnAdiciona.setOnClickListener( new EscutadorAdiciona() );

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "artistasmusicas", MODE_PRIVATE, null );
        // Recuperando o objeto Intent que criou esta activity
        Intent i = getIntent();
        // Recuperando o ID do artista
        idArtista = i.getIntExtra("id",0);
        // Recuperando o nome do artista
        lblArtista.setText( i.getStringExtra("nome") );

        // Criando cursor com os dados vindos do banco
        String cmd = "SELECT _rowid_ _id, titulo FROM musicas WHERE idArtista = " + idArtista;
        cursorMusicas = bd.rawQuery( cmd, null );
        // Criando o objeto adapter, passando o cursor
        adapterMusicas = new AdapterMusicas( this, cursorMusicas );
        // Associando o adapter a lista de artistas
        listaMusicas.setAdapter(adapterMusicas);
    }

    // Classe interna, escutador do botão Adiciona
    private class EscutadorAdiciona implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Variável para pegar o titulo da música
            String titulo;
            // Pegando os dados na interface
            titulo = txtTitulo.getText().toString();
            // Montando SQL para inserir dados
            String cmd = "INSERT INTO musicas (idArtista, titulo) VALUES (";
            cmd = cmd + idArtista;
            cmd = cmd + ", '";
            cmd = cmd + titulo;
            cmd = cmd + "')";
            // Rxecutando comando
            bd.execSQL( cmd );
            // Limpando a interface
            txtTitulo.setText("");
            // Renovando o cursor do adapter, já que temos novos dados no bd
            cmd = "SELECT _rowid_ _id, titulo FROM musicas WHERE idArtista = " + idArtista;
            cursorMusicas = bd.rawQuery( cmd, null );
            adapterMusicas.changeCursor(cursorMusicas);
        }
    }
}