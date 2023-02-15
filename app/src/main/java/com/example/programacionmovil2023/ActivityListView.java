package com.example.programacionmovil2023;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.programacionmovil2023.configuracion.*;
import com.example.programacionmovil2023.transacciones.Personas;
import com.example.programacionmovil2023.transacciones.Transacciones;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    //Variables Globales

    SQLiteConexion conexion;
    ListView listView;
    ArrayList<Personas> listapersonas;
    ArrayList<String> arregloPersonas;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listView = (ListView) findViewById(R.id.listview);

        ObtenerListaPersonas();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloPersonas);
        listView.setAdapter(adp);
    }

    private void ObtenerListaPersonas(){

        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;
        listapersonas = new ArrayList<Personas>();

        //Cursor

        Cursor cursor = db.rawQuery("SELECT * FROM personas", null);
        while (cursor.moveToNext())
        {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

            listapersonas.add(person);
        }

        cursor.close();
        FillList();

    }

    private void FillList(){
        arregloPersonas = new ArrayList<String>();
        for (int i = 0; i < listapersonas.size(); i++)
        {
            arregloPersonas.add(listapersonas.get(i).getId() + " | " +
                    listapersonas.get(i).getNombres() + " | " +
                    listapersonas.get(i).getApellidos() + " | " +
                    listapersonas.get(i).getEdad() + " | " +
                    listapersonas.get(i).getCorreo() + " | ");
        }
    }

}