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

public class ResumenInformeCHF extends Fragment {

    private TextView et0, et1, et2, et3;
    String s0, s1, s2, s3, s4, s5, s6;

    private ResumenInformeCHViewModel mViewModel;

    public static ResumenInformeCHF newInstance() {
        return new ResumenInformeCHF();
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
                s1 = result.getString("peso");
                s2 = result.getString("tSistolica");
                s3 = result.getString("tDiastolica");
                s4 = result.getString("tMedia");
                s5 = result.getString("fechaAlta");
                s6 = result.getString("descripcion");

                //mostramos los datos segun su tipo de informe

                if (s0.equals("CHF")){
                    et0.setText(s6);
                    et1.setText(s1 + " Kg");
                    et2.setText(s2 + " / " + s3 + "\n" + "( " + s4 + " )");
                    et3.setText(s5);
                }
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.resumen_informe_chf_fragment, container, false);

        et0 = root.findViewById(R.id.txtTipoInformeRI);
        et1 = root.findViewById(R.id.txtPesoRI);
        et2 = root.findViewById(R.id.txtTensionRI);
        et3 = root.findViewById(R.id.txtFechaAltaRI);

        return root;
    }



}