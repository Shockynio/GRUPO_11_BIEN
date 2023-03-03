package com.example.grupo_11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.grupo_11.adaptadores.ListaEmpleadosAdapter;
import com.example.grupo_11.db.DbEmpleados;
import com.example.grupo_11.db.DbHelper;
import com.example.grupo_11.entidades.Empleados;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaEmpleados;
    ArrayList <Empleados> listaArrayEmpleados;
    ListaEmpleadosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEmpleados= findViewById(R.id.listaEmpleados);
        txtBuscar= findViewById(R.id.txtBuscar);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmpleados dbEmpleados = new DbEmpleados((MainActivity.this));

        listaArrayEmpleados = new ArrayList<>();

        adapter = new ListaEmpleadosAdapter(dbEmpleados.mostrarEmpleados());
        listaEmpleados.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        adapter.filtrado(s);
        return false;
    }
}