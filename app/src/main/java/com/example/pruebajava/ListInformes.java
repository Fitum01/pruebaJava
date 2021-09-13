package com.example.pruebajava;

public class ListInformes {

    private Informes[] lInformes = new Informes[5];

    public ListInformes(Informes[] informes){
        for (int i = 0; i < 5; i++){
            lInformes[i] = informes[i];
        }
    }

    public Informes[] getlInformes(){
        return lInformes;
    }

    public void setlInformes(Informes[] lInformes){
        this.lInformes = lInformes;
    }
}
