package com.example.pruebajava;

public class Informes {
    private int idInforme, idUsuario;
    private String temperatura, saturacion, idTInforme, fechaAlta, fechaInformeSig, tos, dRespi, dCabeza, dGarganta, dMuscular, peso, tSistolica, tDiastolica, tMedia, descripcion;

    public Informes(int idInforme, String temperatura, String saturacion, String idTInforme, String fechaAlta, String fechaInformeSig, String tos, int idUsuario, String dRespi, String dCabeza, String dGarganta, String dMuscular, String peso, String tSistolica, String tDiastolica, String tMedia, String descripcion) {
        this.idInforme = idInforme;
        this.temperatura = temperatura;
        this.saturacion = saturacion;
        this.idTInforme = idTInforme;
        this.fechaAlta = fechaAlta;
        this.fechaInformeSig = fechaInformeSig;
        this.tos = tos;
        this.idUsuario = idUsuario;
        this.dRespi = dRespi;
        this.dCabeza = dCabeza;
        this.dGarganta = dGarganta;
        this.dMuscular = dMuscular;
        this.peso = peso;
        this.tSistolica = tSistolica;
        this.tDiastolica = tDiastolica;
        this.tMedia = tMedia;
        this.descripcion = descripcion;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public String getTemperatura() { return temperatura; }

    public void setTemperatura(String temperatura) { this.temperatura = temperatura; }

    public String getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(String saturacion) {
        this.saturacion = saturacion;
    }

    public String getTipoInforme() { return idTInforme;  }

    public void setTipoInforme(String idTInforme) {
        this.idTInforme = idTInforme;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaInformeSig() {
        return fechaInformeSig;
    }

    public void setFechaInformeSig(String fechaInformeSig) {
        this.fechaInformeSig = fechaInformeSig;
    }

    public String getTos() { return tos; }

    public void setTos(String tos) { this.tos = tos; }

    public String getdRespi() {
        return dRespi;
    }

    public void setdRespi(String dRespi) {
        this.dRespi = dRespi;
    }

    public String getdCabeza() {
        return dCabeza;
    }

    public void setdCabeza(String dCabeza) {
        this.dCabeza = dCabeza;
    }

    public String getdGarganta() {
        return dGarganta;
    }

    public void setdGarganta(String dGarganta) {
        this.dGarganta = dGarganta;
    }

    public String getdMuscular() {
        return dMuscular;
    }

    public void setdMuscular(String dMuscular) {
        this.dMuscular = dMuscular;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String gettSistolica() {
        return tSistolica;
    }

    public void settSistolica(String tSistolica) {
        this.tSistolica = tSistolica;
    }

    public String gettDiastolica() {
        return tDiastolica;
    }

    public void settDiastolica(String tDiastolica) {
        this.tDiastolica = tDiastolica;
    }

    public String gettMedia() {
        return tMedia;
    }

    public void settMedia(String tMedia) {
        this.tMedia = tMedia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
