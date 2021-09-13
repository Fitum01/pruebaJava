package com.example.pruebajava.resumenInformes;

import androidx.fragment.app.FragmentResultListener;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pruebajava.R;

public class ResumenInformeCOV extends Fragment {

    private TextView et0, et1, et2, et3, et4, et5, et6, et7, et8;
    String s0, s1, s2, s3, s4, s5, s6, s7, s8, s9;

    private ResumenInformeCOVViewModel mViewModel;

    public static ResumenInformeCOV newInstance() {
        return new ResumenInformeCOV();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // para recoger el valor que le mando al pulsar en el listView
        getParentFragmentManager().setFragmentResultListener("keyConsulta", this, new FragmentResultListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                s0 = result.getString("idTInforme");
                s1 = result.getString("temperatura");
                s2 = result.getString("saturacion");
                s3 = result.getString("tos");
                s4 = result.getString("dRespi");
                s5 = result.getString("dCabeza");
                s6 = result.getString("dGarganta");
                s7 = result.getString("dMuscular");
                s8 = result.getString("fechaAlta");
                s9 = result.getString("descripcion");

                //mostramos los datos segun su tipo de informe

                if (s0.equals("COV")){
                    et0.setText(s9);
                    et1.setText(s1 + " grados");
                    et2.setText(s2 + " %");
                    et3.setText(s3);
                    et4.setText(s4);
                    et5.setText(s5);
                    et6.setText(s6);
                    et7.setText(s7);
                    et8.setText(s8);
                }
            }
        });

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.resumen_informe_cov_fragment, container, false);

        et0 = root.findViewById(R.id.txtTipoInformeRICOV);
        et1 = root.findViewById(R.id.txtTemperaturaRICOV);
        et2 = root.findViewById(R.id.txtSaturacionRICOV);
        et3 = root.findViewById(R.id.txtTosRICOV);
        et4 = root.findViewById(R.id.txtDRespiRICOV);
        et5 = root.findViewById(R.id.txtDCabezaRICOV);
        et6 = root.findViewById(R.id.txtDGargantaRICOV);
        et7 = root.findViewById(R.id.txtDMuscularRICOV);
        et8 = root.findViewById(R.id.txtFechaAltaRICOV);

        return root;
    }



}