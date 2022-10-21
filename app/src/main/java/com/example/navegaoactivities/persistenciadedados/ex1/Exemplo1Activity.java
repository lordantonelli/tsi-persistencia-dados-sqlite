package com.example.navegaoactivities.persistenciadedados.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.navegaoactivities.persistenciadedados.R;

public class Exemplo1Activity extends AppCompatActivity {

    // Atributos relativos aos objetos gráficos
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnInsere;
    private ListView lista;

    // Referência para o banco de dados
    SQLiteDatabase bd;

    // Referência para o adapter da lista
    ContatosAdapter adapter;

    // Referência para o cursor que popula a lista
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo1);

        // Ligando atributos aos ID's na interface
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        lista = findViewById(R.id.lista);
        btnInsere = findViewById(R.id.btnInserir);
        btnInsere.setOnClickListener(new EscutadorInserir());

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase("exemplo1", MODE_PRIVATE, null);
        // String para comandos SQL
        String cmd;
        // Criar a tabela, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS contatos (nome VARCHAR, email VARCHAR)";
        // Executa comando no bd
        bd.execSQL( cmd );

        // Consulta para gerar o Cursor
        cursor = bd.rawQuery("SELECT _rowid_ _id, nome, email FROM contatos", null);
        // Criar o adapter, passando o contexto e o cursor
        adapter = new ContatosAdapter(this, cursor);
        // Associar a lista com o adapter criado
        lista.setAdapter(adapter);
    }

    // classe interna, escutador do botão Inserir
    private class EscutadorInserir implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Variáveis para pegar os dados
            String nome, email;
            // Pegando os dados na interface
            nome = txtNome.getText().toString();
            email = txtEmail.getText().toString();
            // Montando SQL para inserir dados
            String cmd = "INSERT INTO contatos (nome, email) VALUES ('";
            cmd = cmd + nome;
            cmd = cmd + "', '";
            cmd = cmd + email;
            cmd = cmd + "')";
            // Executando comando
            bd.execSQL( cmd );
            // Limpando a interface
            txtNome.setText("");
            txtEmail.setText("");
            // Trocando o cursor do adapter
            cursor = bd.rawQuery( "SELECT _rowid_ _id, nome, email FROM contatos", null );
            adapter.changeCursor(cursor);
        }
    }

}