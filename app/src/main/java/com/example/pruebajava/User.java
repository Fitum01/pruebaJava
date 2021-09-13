package com.example.pruebajava;

public class User {
    private int idUsuario;
    private String nombre, email, genero, apellido1, apellido2, dni, contacto;

    public User(int idUsuario, String dni, String nombre, String apellido1, String apellido2, String email, String genero, String contacto) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.genero = genero;
        this.contacto = contacto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() { return genero; }

    public void setGendero(String genero) {
        this.genero = genero;
    }

    public String getApellido1() { return apellido1; }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() { return apellido2; }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() { return dni; }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContacto() { return contacto; }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

}
