package com.example.programacionmovil2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.programacionmovil2023.configuracion.SQLiteConexion;
import com.example.programacionmovil2023.transacciones.Transacciones;

public class MainActivity extends AppCompatActivity {
    // Objetos globales

    EditText nombres, apellidos, correo, edad;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // liga la interfaz con la lógica

        // guardando los valores de la interfez en nuevas variables
        nombres = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        correo = (EditText) findViewById(R.id.correo);
        edad = (EditText) findViewById(R.id.edad);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });

    }

    private void AgregarPersona() {

        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombres", nombres.getText().toString());
            valores.put("apellidos", apellidos.getText().toString());
            valores.put("edad", edad.getText().toString());
            valores.put("correo", correo.getText().toString());

            Long Resultado = db.insert(Transacciones.tablapersonas, "id", valores);
            Toast.makeText(this, Resultado.toString(), Toast.LENGTH_SHORT).show();

            ClearScreen();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "No se pudo insertar el dato", Toast.LENGTH_LONG).show();
        }

    }
    private void ClearScreen(){
        nombres.setText(Transacciones.Empty);
        apellidos.setText(Transacciones.Empty);
        correo.setText(Transacciones.Empty);
        edad.setText(Transacciones.Empty);
    }

    private void MostrarCliente() {

        String mensaje = nombres.getText().toString() +
                " | " + apellidos.getText().toString() +
                " | " + edad.getText().toString() +
                " | " + correo.getText().toString();

        Toast.makeText( this, mensaje, Toast.LENGTH_SHORT).show();
    }
}