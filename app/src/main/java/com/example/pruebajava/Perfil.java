package com.example.pruebajava;

public class Perfil {

    private int idPS, idUsuario;
    private String nombre, apellido1, apellido2, dni, contacto, email, genero, idtipoPS, nombrePS, apellido1PS, apellido2PS, contactoPS, descripcionPS;

    public Perfil (int idUsuario, String nombre, String apellido1, String apellido2, String dni, String contacto, String email, String genero, int idPS, String nombrePS, String apellido1PS, String apellido2PS, String contactoPS, String idtipoPS, String descripcionPS){

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.contacto = contacto;
        this.email = email;
        this.genero = genero;
        this.idPS = idPS;
        this.nombrePS = nombrePS;
        this.apellido1PS = apellido1PS;
        this.apellido2PS = apellido2PS;
        this.contactoPS = contactoPS;
        this.idtipoPS = idtipoPS;
        this.descripcionPS = descripcionPS;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdPS() {
        return idPS;
    }

    public void setIdPS(int idPS) {
        this.idPS = idPS;
    }

    public String getNombrePS() {
        return nombrePS;
    }

    public void setNombrePS(String nombrePS) {
        this.nombrePS = nombrePS;
    }

    public String getApellido1PS() {
        return apellido1PS;
    }

    public void setApellido1PS(String apellido1PS) {
        this.apellido1PS = apellido1PS;
    }

    public String getApellido2PS() {
        return apellido2PS;
    }

    public void setApellido2PS(String apellido2PS) {
        this.apellido2PS = apellido2PS;
    }

    public String getContactoPS() {
        return contactoPS;
    }

    public void setContactoPS(String contactoPS) {
        this.contactoPS = contactoPS;
    }

    public String getIdtipoPS() {
        return idtipoPS;
    }

    public void setIdtipoPS(String idtipoPS) {
        this.idtipoPS = idtipoPS;
    }

    public String getDescripcionPS() {
        return descripcionPS;
    }

    public void setDescripcionPS(String descripcionPS) {
        this.descripcionPS = descripcionPS;
    }

}
