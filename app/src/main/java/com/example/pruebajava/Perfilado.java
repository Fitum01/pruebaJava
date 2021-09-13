package com.example.pruebajava;

public class Perfilado {

    private int idUsuario;
    private String idTInforme, descTI;

    public Perfilado (int idUsuario, String idTInforme, String descTI){

        this.idUsuario = idUsuario;
        this.idTInforme = idTInforme;
        this.descTI = descTI;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdTInforme() {
        return idTInforme;
    }

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
