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
    }

}