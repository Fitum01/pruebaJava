package com.example.pruebajava.altaInformeCOV;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.pruebajava.R;

public class AltaInfCOVID extends Fragment {

    String idTInforme;
    private SeekBar seekBarTemperatura, seekBarSaturacion;
    private Float aFloat;
    private TextView txtTemperatura, txtSaturacion;
    private String tTemp = "36.5", tSat = "98", tos="SI", dRespi="SI", dGarganta="SI", dCabeza="SI", dMuscular ="SI";
    private Button btnCont;
    private RadioGroup radioGroupTos, radioGroupDGarganta, radioGroupDCabeza, radioGroupDMuscular, radioGroupDRespi;

    private AltaInfCOVIDViewModel mViewModel;

    public static AltaInfCOVID newInstance() {
        return new AltaInfCOVID();
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

        View root =  inflater.inflate(R.layout.alta_inf_covid_fragment, container, false);


        txtSaturacion = root.findViewById(R.id.idTxtSeekbarSaturacion);
        seekBarSaturacion = root.findViewById(R.id.idSeekbarSaturacion);
        txtTemperatura = root.findViewById(R.id.idTxtSeekbarTemperatura);
        seekBarTemperatura = root.findViewById(R.id.idSeekbarTemperatura);
        btnCont = root.findViewById(R.id.idBtnContAltaCOV);
        radioGroupTos = root.findViewById(R.id.rbTos);
        radioGroupDCabeza = root.findViewById(R.id.rbDCabeza);
        radioGroupDGarganta = root.findViewById(R.id.rbDGarganta);
        radioGroupDMuscular = root.findViewById(R.id.rbDMuscular);
        radioGroupDRespi = root.findViewById(R.id.rbDRespi);

        /*
        //capturamos los valores de los radio button
        tos = ((RadioButton) root.findViewById(radioGroupTos.getCheckedRadioButtonId())).getText().toString();
        dRespi = ((RadioButton) root.findViewById(radioGroupDRespi.getCheckedRadioButtonId())).getText().toString();
        dGarganta = ((RadioButton) root.findViewById(radioGroupDGarganta.getCheckedRadioButtonId())).getText().toString();
        dCabeza = ((RadioButton) root.findViewById(radioGroupDCabeza.getCheckedRadioButtonId())).getText().toString();
        dMuscular = ((RadioButton) root.findViewById(radioGroupDMuscular.getCheckedRadioButtonId())).getText().toString();
         */

        //capturamos los posibles cambios en los radio buttom
        // el de la tos
        radioGroupTos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbTosSI:
                        tos = ((RadioButton) root.findViewById(radioGroupTos.getCheckedRadioButtonId())).getText().toString();
                        break;
                    case R.id.rbTosNO:
                        tos = ((RadioButton) root.findViewById(radioGroupTos.getCheckedRadioButtonId())).getText().toString();
                        break;
                }
            }
        });

        // el del dolor de cabeza
        radioGroupDCabeza.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbDCabezaSI:
                        dCabeza = ((RadioButton) root.findViewById(radioGroupDCabeza.getCheckedRadioButtonId())).getText().toString();
                        break;
                    case R.id.rbDCabezaNO:
                        dCabeza = ((RadioButton) root.findViewById(radioGroupDCabeza.getCheckedRadioButtonId())).getText().toString();
                        break;
                }
            }
        });

        // el del dolor al respirar
        radioGroupDRespi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbDRespiSI:
                        dRespi = ((RadioButton) root.findViewById(radioGroupDRespi.getCheckedRadioButtonId())).getText().toString();
                        break;
                    case R.id.rbDRespiNO:
                        dRespi = ((RadioButton) root.findViewById(radioGroupDRespi.getCheckedRadioButtonId())).getText().toString();
                        break;
                }
            }
        });

        // el del dolor muscular
        radioGroupDMuscular.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbDMuscularSI:
                        dMuscular = ((RadioButton) root.findViewById(radioGroupDMuscular.getCheckedRadioButtonId())).getText().toString();
                        break;
                    case R.id.rbDMuscularNO:
                        dMuscular = ((RadioButton) root.findViewById(radioGroupDMuscular.getCheckedRadioButtonId())).getText().toString();
                        break;
                }
            }
        });

        // el del dolor de garganta
        radioGroupDGarganta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.rbDGargantaSI:
                        dGarganta = ((RadioButton) root.findViewById(radioGroupDGarganta.getCheckedRadioButtonId())).getText().toString();
                        break;
                    case R.id.rbDGargantaNO:
                        dGarganta = ((RadioButton) root.findViewById(radioGroupDGarganta.getCheckedRadioButtonId())).getText().toString();
                        break;
                }
            }
        });


        //control de la barra de saturacion de oxigeno
        seekBarSaturacion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtSaturacion.setText(String.valueOf(progress) + " %");
                tSat =  String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //control de la barra de la temperatura
        seekBarTemperatura.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                aFloat=(float) (progress * 0.1);
                txtTemperatura.setText(String.valueOf(aFloat) + " grados");
                tTemp = Float.toString(aFloat);
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

                String t1 = tTemp;
                String t2 = tSat;

                Bundle alta = new Bundle();
                alta.putString("idTInforme", idTInforme);
                alta.putString("temperatura", t1);
                alta.putString("saturacion", t2);
                alta.putString("tos", tos);
                alta.putString("dRespi", dRespi);
                alta.putString("dCabeza", dCabeza);
                alta.putString("dGarganta", dGarganta);
                alta.putString("dMuscular", dMuscular);

                getParentFragmentManager().setFragmentResult("keyAltaCOV", alta);
                Navigation.findNavController(view).navigate(R.id.navigation_altaInfCOVID2);
            }
        });
    }

}