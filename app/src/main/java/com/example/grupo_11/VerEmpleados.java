package com.example.grupo_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.grupo_11.db.DbEmpleados;
import com.example.grupo_11.entidades.Empleados;

public class VerEmpleados extends AppCompatActivity {

    // Declaración de objetos EditText, Button, Empleados y Switch
    EditText txtDni, txtNombre, txtApellidos, txtTelefono, txtDepartamento, txtLocalidad, txtDireccion, txtEmail, txtHorario;
    Button btnGuarda;
    Empleados empleados;
    int id= 0;
    Switch swtEdicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empleados);

        // Encontrar vistas por su id y asignarlas a los objetos correspondientes
        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDepartamento = findViewById(R.id.txtDepartamento);
        txtLocalidad = findViewById(R.id.txtLocalidad);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEmail = findViewById(R.id.txtEmail);
        txtHorario = findViewById(R.id.txtHorario);
        swtEdicion = findViewById(R.id.swtEdición);

        // Obtener el id del empleado de la actividad anterior y mostrar su información
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = 0;
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        // Obtener la información del empleado desde la base de datos y mostrarla en los campos de texto
        DbEmpleados dbEmpleados = new DbEmpleados(VerEmpleados.this);
        empleados = dbEmpleados.verEmpleados(id);

        if (empleados != null) {
            // Mostrar información del empleado en los campos de texto
            txtDni.setText(empleados.getDNI());
            txtNombre.setText(empleados.getNombre());
            txtApellidos.setText(empleados.getApellidos());
            txtTelefono.setText(empleados.getTelefono());
            txtDepartamento.setText(empleados.getDepartamento());
            txtLocalidad.setText(empleados.getLocalidad());
            txtDireccion.setText(empleados.getDireccion());
            txtEmail.setText(empleados.getEmail());
            txtHorario.setText(empleados.getHorario());

            Switch swtEdicion = findViewById(R.id.swtEdición);

            // Deshabilitar la edición de los campos de texto
            txtDni.setEnabled(false);;
            txtNombre.setEnabled(false);;
            txtApellidos.setEnabled(false);;
            txtTelefono.setEnabled(false);;
            txtDepartamento.setEnabled(false);;
            txtLocalidad.setEnabled(false);;
            txtDireccion.setEnabled(false);;
            txtEmail.setEnabled(false);;
            txtHorario.setEnabled(false);;
        }
           // Configurar el Switch para habilitar o deshabilitar la edición de los campos de texto
        swtEdicion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Habilitar la edición de los campos de texto
                    txtDni.setEnabled(true);
                    txtNombre.setEnabled(true);
                    txtApellidos.setEnabled(true);
                    txtTelefono.setEnabled(true);
                    txtDepartamento.setEnabled(true);
                    txtLocalidad.setEnabled(true);
                    txtDireccion.setEnabled(true);
                    txtEmail.setEnabled(true);
                    txtHorario.setEnabled(true);

                    // Configurar los campos de texto para permitir la entrada de texto
                    txtDni.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtNombre.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtApellidos.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtTelefono.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtDepartamento.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtLocalidad.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtDireccion.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtEmail.setInputType(InputType.TYPE_CLASS_TEXT);
                    txtHorario.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    // Deshabilitar la edición de los campos de texto
                    txtDni.setEnabled(false);
                    txtNombre.setEnabled(false);
                    txtApellidos.setEnabled(false);
                    txtTelefono.setEnabled(false);
                    txtDepartamento.setEnabled(false);
                    txtLocalidad.setEnabled(false);
                    txtDireccion.setEnabled(false);
                    txtEmail.setEnabled(false);
                    txtHorario.setEnabled(false);

                    // Obtener la información del empleado desde los campos de texto y actualizarla en la base de datos
                    DbEmpleados dbEmpleados = new DbEmpleados(VerEmpleados.this);
                    empleados.setDNI(txtDni.getText().toString().trim());
                    empleados.setNombre(txtNombre.getText().toString().trim());
                    empleados.setApellidos(txtApellidos.getText().toString().trim());
                    empleados.setTelefono(txtTelefono.getText().toString().trim());
                    empleados.setDepartamento(txtDepartamento.getText().toString().trim());
                    empleados.setLocalidad(txtLocalidad.getText().toString().trim());
                    empleados.setDireccion(txtDireccion.getText().toString().trim());
                    empleados.setEmail(txtEmail.getText().toString().trim());
                    empleados.setHorario(txtHorario.getText().toString().trim());
                    boolean actualizado = dbEmpleados.actualizarEmpleado(empleados);
                    if (actualizado) {
                        Toast.makeText(VerEmpleados.this, "Cambios guardados", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(VerEmpleados.this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
