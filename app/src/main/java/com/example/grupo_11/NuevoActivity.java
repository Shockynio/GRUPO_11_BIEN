package com.example.grupo_11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grupo_11.db.DbEmpleados;

public class NuevoActivity extends AppCompatActivity {

    EditText txtDni, txtNombre, txtApellidos, txtTelefono, txtLocalidad, txtDireccion, txtEmail, txtHorario;
    Spinner spnDepartamento;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        // Asignación de vistas a las variables correspondientes
        txtDni = findViewById(R.id.txtDni);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtLocalidad = findViewById(R.id.txtLocalidad);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEmail = findViewById(R.id.txtEmail);
        txtHorario = findViewById(R.id.txtHorario);
        spnDepartamento = findViewById(R.id.spnDepartamento);
        btnGuarda = findViewById(R.id.btnGuarda);

        // Creación de un adaptador para el spinner de departamentos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departamentos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDepartamento.setAdapter(adapter);

        // Acción a realizar al hacer clic en el botón "Guardar"
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificación de que los campos son válidos antes de guardar el registro
                if (!isValidFields()) {
                    Toast.makeText(NuevoActivity.this,"Por favor, rellene correctamente todos los campos", Toast.LENGTH_LONG).show();
                    return;
                }

                // Obtención del departamento seleccionado en el spinner
                String departamento = spnDepartamento.getSelectedItem().toString();

                // Guardado del registro en la base de datos
                DbEmpleados dbEmpleados = new DbEmpleados(NuevoActivity.this);
                long id= dbEmpleados.insertarEmpleado(txtDni.getText().toString(), txtNombre.getText().toString(),
                        txtApellidos.getText().toString(), Integer.parseInt(txtTelefono.getText().toString()),
                        departamento, txtLocalidad.getText().toString(), txtDireccion.getText().toString(),
                        txtEmail.getText().toString(), txtHorario.getText().toString());

                // Mensaje de confirmación o error
                if (id> 0) {
                    Toast.makeText(NuevoActivity.this,"REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevoActivity.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Acción a realizar al perder el foco en el campo de texto del DNI
        txtDni.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String dni = txtDni.getText().toString().trim();
                    if (!isValidDNI(dni)) {
                        txtDni.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(NuevoActivity.this, "DNI inválido", Toast.LENGTH_SHORT).show();
                    } else {
                        txtDni.getBackground().setColorFilter(null);
                    }
                }
            }
        });


     txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                String nombre = txtNombre.getText().toString().trim();
                if (!isValidName(nombre)) {
                    txtNombre.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(NuevoActivity.this, "Nombre inválido", Toast.LENGTH_SHORT).show();
                } else {
                    txtNombre.getBackground().setColorFilter(null);
                    }
                  }
              }
         });

        txtTelefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String telefono = txtTelefono.getText().toString().trim();
                    if (!isValidSpanishMobileNumber(telefono)) {
                        txtTelefono.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(NuevoActivity.this, "Número de teléfono inválido", Toast.LENGTH_SHORT).show();
                    } else {
                        txtTelefono.getBackground().setColorFilter(null);
                    }
                }
            }
        });

        txtApellidos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String apellidos = txtApellidos.getText().toString().trim();
                    if (!isValidSurname(apellidos)) {
                        txtApellidos.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(NuevoActivity.this, "Apellidos inválidos", Toast.LENGTH_SHORT).show();
                    } else {
                        txtApellidos.getBackground().setColorFilter(null);
                    }
                }
            }
        });

        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String email = txtEmail.getText().toString().trim();
                    if (!isValidEmail(email)) {
                        txtEmail.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(NuevoActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                    } else {
                        txtEmail.getBackground().setColorFilter(null);
                    }
                }
            }
        });
    }




    private boolean isValidFields() {
        // Se asume que todos los campos son válidos por defecto
        boolean isValid = true;

        // Validación del campo DNI
        if (!isValidDNI(txtDni.getText().toString().trim())) {
            txtDni.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
            isValid = false; // Si el DNI no es válido, se marca como inválido el formulario
        } else {
            txtDni.getBackground().setColorFilter(null); // Se quita el color rojo si el campo es válido
        }

        // Validación del campo Teléfono
        if (!isValidSpanishMobileNumber(txtTelefono.getText().toString().trim())) {
            txtTelefono.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
            isValid = false;
        } else {
            txtTelefono.getBackground().setColorFilter(null);
        }

        // Validación del campo Nombre
        if (!isValidName(txtNombre.getText().toString().trim())) {
            txtNombre.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
            isValid = false;
        } else {
            txtNombre.getBackground().setColorFilter(null);
        }

        // Validación del campo Apellidos
        if (!isValidSurname(txtApellidos.getText().toString().trim())) {
            txtApellidos.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
            isValid = false;
        } else {
            txtApellidos.getBackground().setColorFilter(null);
        }

        // Validación del campo Email
        if (!isValidEmail(txtEmail.getText().toString().trim())) {
            txtEmail.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
            isValid = false;
        } else {
            txtEmail.getBackground().setColorFilter(null);
        }

        // Devuelve si el formulario es válido o no
        return isValid;
    }


    // LÓGICA MÉTODO DNI:

    // En este método, utilizamos una expresión regular para que coincida con el formato de un número DNI válido: 8 dígitos seguidos de 1 letra.
    // Si la cadena de entrada no coincide con este formato, el método devuelve falso inmediatamente.
    // A continuación, extraemos la letra y los dígitos de la cadena de entrada, y analizamos los dígitos como entero.
    // Luego usamos un conjunto fijo de letras para calcular la letra esperada para el número DNI dado, utilizando un algoritmo simple basado en el módulo del número dividido por 23.
    // Si la letra esperada no coincide con la letra real en la cadena de entrada, el método devuelve falso.
    // De lo contrario, el método devuelve verdadero, lo que indica que la cadena de entrada es un número DNI español válido.

    private boolean isValidDNI(String dni) {
                boolean valid = false;
                String dniRegex = "\\d{8}[A-HJ-NP-TV-Z]";
                if (dni.matches(dniRegex)) {
                    int dniNumber = Integer.parseInt(dni.substring(0, 8));
                    String dniLetter = dni.substring(8);
                    String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
                    int index = dniNumber % 23;
                    char letter = letters.charAt(index);
                    if (dniLetter.charAt(0) == letter) {
                        valid = true;
                    }
                }
                return valid;
            }

    // Comprobar que el número de teléfono comienza con un prefijo válido de España
    private boolean isValidSpanishMobileNumber(String mobileNumber) {
        String mobileRegex = "^[6|7|9]\\d{8}$";
        return mobileNumber.matches(mobileRegex);
    }

    private boolean isValidName(String name) {
        String nameRegex = "^[A-Za-zÁ-Úá-úñÑ ]{2,}$";
        return name.matches(nameRegex);
    }

    private boolean isValidSurname(String surname) {
        String surnameRegex = "^[A-Za-zÁ-Úá-úñÑ ]{2,}$";
        return surname.matches(surnameRegex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return email.matches(emailRegex);
    }



    private void limpiar(){
        txtDni.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtLocalidad.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtHorario.setText("");
        spnDepartamento.setSelection(0);
    }
}
