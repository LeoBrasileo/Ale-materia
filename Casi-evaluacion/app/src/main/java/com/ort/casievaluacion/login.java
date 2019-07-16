package com.ort.casievaluacion;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private Button ingresar;
    private Button registrar;
    private EditText editMail;
    private EditText contrasena;
    private String email;
    private String pass;
    private CheckBox checkBox;
    private Personas persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        ingresar = findViewById(R.id.btn1);
        editMail = findViewById(R.id.editMail);
        contrasena = findViewById(R.id.editPass);
        checkBox = findViewById(R.id.checkBox1);

        Personas persona = new Personas("Leonel","Braginski","leobraginski@gmail.com","oliver");

        cargarpreferencias();

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(editMail.getText().toString(), contrasena.getText().toString());
            }
        });


    }

    private void cargarpreferencias() {
        SharedPreferences sharedPreferences = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        editMail.setText(sharedPreferences.getString("user", ""));
        contrasena.setText(sharedPreferences.getString("pass", ""));

        if (!editMail.equals("") && !contrasena.equals("")) {
            signIn(editMail.getText().toString(), contrasena.getText().toString());
        }
    }

    private void signIn(final String email, final String password)
    {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un DNI", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Se debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if(persona.getEmail().equals(email) && persona.getPass().equals(password))
        {
            Intent intent =
                    new Intent(login.this,MainActivity.class);
            Toast.makeText(login.this,"Logueado correctamente",Toast.LENGTH_LONG).show();
            if (checkBox.isChecked())
            {
                SharedPreferences sharedPreferences = getSharedPreferences
                        ("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user", persona.getEmail());
                editor.putString("pass", persona.getPass().toString());
                editor.commit();
                startActivity(intent);
            }
        }
    }

}
