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
        btnAdicionar.setOnClickListener( new EscutadorAdiciona() );
        listaArtistas = findViewById( R.id.listaArtistas );
        listaArtistas.setOnItemClickListener( new EscutadorCliqueComum() );

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "artistasmusicas", MODE_PRIVATE, null );
        // String para comandos SQL
        String cmd;
        // Criar a tabela artistas, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS artistas (";
        cmd = cmd + "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, genero VARCHAR)";
        bd.execSQL( cmd );
        // Criar a tabela musicas, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS musicas (";
        cmd = cmd + "id INTEGER PRIMARY KEY AUTOINCREMENT, idArtista INTEGER, titulo VARCHAR)";
        bd.execSQL( cmd );

        // Criando cursor com os dados vindos do banco
        cursorArtistas = bd.rawQuery( "SELECT _rowid_ _id, id, nome, genero FROM artistas", null );
        //Criando o objeto adapter, passando o cursor
        adapterArtistas = new AdapterArtistas( this, cursorArtistas );
        // Associando o adapter a lista de artistas
        listaArtistas.setAdapter(adapterArtistas);
    }

    // Classe interna, escutador do botão Adiciona
    private class EscutadorAdiciona implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Variáveis para pegar os dados
            String artista, genero;
            // Pegando os dados na interface
            artista = txtArtista.getText().toString();
            genero = txtGenero.getText().toString();
            // Montando SQL para inserir dados
            String cmd = "INSERT INTO artistas (nome, genero) VALUES ('";
            cmd = cmd + artista;
            cmd = cmd + "', '";
            cmd = cmd + genero;
            cmd = cmd + "')";
            // Executando comando
            bd.execSQL( cmd );
            // Limpando a interface
            txtArtista.setText("");
            txtGenero.setText("");
            // Renovando o cursor do adapter, já que temos novos dados no bd
            cursorArtistas = bd.rawQuery( "SELECT _rowid_ _id, id, nome, genero FROM artistas", null );
            adapterArtistas.changeCursor(cursorArtistas);
        }
    }

    // classe interna do escutador de cliques normais na lista
    private class EscutadorCliqueComum implements AdapterView.OnItemClickListener {
        @SuppressLint("Range")
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Recupera o cursor, posicionado na linha relativa ao item clicado
            Cursor c = (Cursor) adapterArtistas.getItem(i);
            // Criando o intent para abrir a outra activity
            Intent intent = new Intent( getApplicationContext(), MusicasActivity.class );
            // Colocando o id do artista dentro do intent
            intent.putExtra( "id", c.getInt(c.getColumnIndex("id")) );
            intent.putExtra( "nome", c.getString(c.getColumnIndex("nome")));
            // Iniciando a outra activity
            startActivity(intent);
        }
    }
}