package com.example.pruebajava.altaInformeCA;

import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.pruebajava.R;

public class AltaInformeFragment extends Fragment {

    String idTInforme;
    private SeekBar seekBarPeso, seekBarTDiastolica, seekBarTSistolica, seekBarTMedia;
    private Float aFloat;
    private String tMedia = "90", tDiastolica = "80", tSistolica = "120", tPeso = "70";
    private TextView txtPeso, txtTDiastolica, txtTMedia, txtTSistolica;
    private Button btnCont;

    private AltaInformeViewModel mViewModel;

    public static AltaInformeFragment newInstance() {
        return new AltaInformeFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // para recoger el valor que le mando del boton de alta de los informes
        getParentFragmentManager().setFragmentResultListener("key",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                idTInforme = result.getString("idTInforme");
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.alta_informe_fragment, container, false);

        seekBarPeso=root.findViewById(R.id.idSeekbarPeso);
        seekBarTDiastolica=root.findViewById(R.id.idSeekbarTDiastolica);
        seekBarTSistolica=root.findViewById(R.id.idSeekbarTSistolica);
        seekBarTMedia=root.findViewById(R.id.idSeekbarTMedia);
        txtPeso=root.findViewById(R.id.idTxtSeekbarPeso);
        txtTDiastolica=root.findViewById(R.id.idTxtSeekbarTDiastolica);
        txtTSistolica=root.findViewById(R.id.idTxtSeekbarTSistolica);
        txtTMedia=root.findViewById(R.id.idTxtSeekbarTMedia);
        btnCont=root.findViewById(R.id.idBtnContAlta);

        //control de la barra del peso
        seekBarPeso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                aFloat=(float) (progress * 0.1);
                //sTemp= Float.toString(aFloat);
                //txtTemp.setText(sTemp);
                txtPeso.setText(String.valueOf(aFloat) + " Kg");
                tPeso = Float.toString(aFloat);

                // para limitar valores +100 o menos de 0
                // [no se si haria falta... si fuera % si y concatenaria primero en un pasa intermedio antes del set]

                //if(txtTemp.getText().toString().equals("100")) txtTemp.setText("100");
                //if(txtTemp.getText().toString().equals("0")) txtTemp.setText("0");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //control de la barra de tension de TDiastolica
        seekBarTDiastolica.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtTDiastolica.setText("Diastolica: " + String.valueOf(progress) + " mmHg");
                tDiastolica =  String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //control de la barra de tension de TSistolica
        seekBarTSistolica.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtTSistolica.setText("Sitolica: " + String.valueOf(progress) + " mmHg");
                tSistolica =  String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //control de la barra de tension de TMedia
        seekBarTMedia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtTMedia.setText("Media: " + String.valueOf(progress) + " mmHg");
                tMedia =  String.valueOf(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                String t1=txtPeso.getText().toString();
                String t2=txtTSistolica.getText().toString();
                String t3=txtTDiastolica.getText().toString();
                String t4=txtTMedia.getText().toString();
*/
                String t1 = tPeso;
                String t2 = tSistolica;
                String t3 = tDiastolica;
                String t4 = tMedia;

                Bundle alta = new Bundle();
                alta.putString("idTInforme", idTInforme);
                alta.putString("peso", t1);
                alta.putString("sistolica", t2);
                alta.putString("diastolica", t3);
                alta.putString("media", t4);
                getParentFragmentManager().setFragmentResult("keyAlta", alta);
                Navigation.findNavController(view).navigate(R.id.navigation_altaInforme2);
            }
        });

    }
}