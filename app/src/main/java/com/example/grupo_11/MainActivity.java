package com.example.grupo_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
         //Crear intent para la activity a la que llamaremos
        Intent intent = new Intent(MainActivity.this, AvtivityBusqueda.class);


        // Crear un objeto handler y usar el etodo postDelayed para retrasar la abertura de la ActivityBusqueda por 3 segundos
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 3000);

        

    }
}
