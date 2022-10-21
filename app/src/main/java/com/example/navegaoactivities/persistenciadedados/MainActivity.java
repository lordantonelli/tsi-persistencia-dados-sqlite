package com.example.navegaoactivities.persistenciadedados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navegaoactivities.persistenciadedados.ex1.Exemplo1Activity;
import com.example.navegaoactivities.persistenciadedados.ex2.Exemplo2Activity;

public class MainActivity extends AppCompatActivity {

    private Button btnExemplo1;
    private Button btnExemplo2;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnExemplo1 = findViewById(R.id.btnExemplo1);
        this.btnExemplo2 = findViewById(R.id.btnExemplo2);
        this.btnSair = findViewById(R.id.btnSair);

        this.btnExemplo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Exemplo1Activity.class);
                startActivity(i);
            }
        });

        this.btnExemplo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Exemplo2Activity.class);
                startActivity(i);
            }
        });

        this.btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}


