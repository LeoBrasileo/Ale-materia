package com.ort.casievaluacion;

public class Personas
{
    private String nombre;
    private String appelido;
    private String email;
    private String pass;

    public Personas(String nombre, String appelido, String email, String pass) {
        this.nombre = nombre;
        this.appelido = appelido;
        this.email = email;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppelido() {
        return appelido;
    }

    public void setAppelido(String appelido) {
        this.appelido = appelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
