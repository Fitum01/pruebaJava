package com.example.pruebajava;

public class UltInf {

    private int idUsuario;
    private String idTInforme, fechaAlta, fechaInformeSig, descTI;

    public UltInf(int idUsuario, String fechaAlta, String  fechaInformeSig, String idTInforme, String descTI) {
        this.idUsuario = idUsuario;
        this.fechaAlta = fechaAlta;
        this.fechaInformeSig = fechaInformeSig;
        this.idTInforme = idTInforme;
        this.descTI = descTI;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getIdTInforme() { return idTInforme;  }

    public void setIdTInforme(String idTInforme) {
        this.idTInforme = idTInforme;
    }

    public String getDescTI() {
        return descTI;
    }

    public void setDescTI(String descTI) {
        this.descTI = descTI;
    }
}
