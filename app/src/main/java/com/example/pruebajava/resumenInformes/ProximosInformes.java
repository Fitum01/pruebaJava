package com.example.pruebajava.resumenInformes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pruebajava.R;
import com.example.pruebajava.SharedPrefManager;
import com.example.pruebajava.UltInf;


public class ProximosInformes extends Fragment {

    TextView tvListaInfSig;
    String lista, textoFinal ="", idTInforSig;
    UltInf[] arrayUltInf = new UltInf[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.proximos_informes_fragment, container, false);

        tvListaInfSig = root.findViewById(R.id.idtxtInformesSig);

        arrayUltInf = SharedPrefManager.getInstance(getActivity()).getUltInf();

        //recorremos todos el array por si tiene informes de varios tipos

        for (int i=0; i<arrayUltInf.length; i++) {
            idTInforSig = arrayUltInf[i].getIdTInforme();
            // mirar si hay mas tipos de informes
            if (idTInforSig != null) {
                lista = (arrayUltInf[i].getDescTI() + " el dia: " + arrayUltInf[i].getFechaInformeSig() + "\n\n");
                textoFinal = textoFinal + lista;
            }else {
                break;
            }
        }

        tvListaInfSig.setText(textoFinal);
        return root;
    }
}