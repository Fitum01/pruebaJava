package com.example.pruebajava.ui.informes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pruebajava.Perfilado;
import com.example.pruebajava.R;
import com.example.pruebajava.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InformesFragment extends Fragment {

    Button btnNavCA, btnNavCV;
    TextView tvTxtCOV, tvTxtCHF;
    Perfilado[] arrayPerfilado = new Perfilado[10];
    Boolean opCHF, opCOV;
    String idTInforSig;

    private InformesViewModel informesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        informesViewModel =
                new ViewModelProvider(this).get(InformesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_informes, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        informesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnNavCA = root.findViewById(R.id.btnInformeCA);
        btnNavCV = root.findViewById(R.id.btnInformeCV);
        tvTxtCHF = root.findViewById(R.id.idtxtCHF);
        tvTxtCOV = root.findViewById(R.id.idtxtCOV);


        arrayPerfilado = SharedPrefManager.getInstance(getActivity()).getPerfilado();

        opCHF = false;
        opCOV = false;

        // para setear las opciones de visibilidad segun el perfil
        for (int i=0; i<arrayPerfilado.length; i++){
            idTInforSig = arrayPerfilado[i].getIdTInforme();

            // para mirar si hay mas tipos de informe
            if(idTInforSig != null) {
                switch (arrayPerfilado[i].getIdTInforme()) {
                    case "CHF":
                        opCHF = true;
                        break;
                    case "COV":
                        opCOV = true;
                        break;
                    default:
                        break;
                }
            }else {
                break;
            }
        }

        //esconder el texto y boton de lanzar informes COV
        if (!opCOV){
            escorderOpcionesCOV();
        }
        //esconder el texto y boton de lanzar informes CHF
        if (!opCHF){
            escorderOpcionesCHF();
        }


        return root;
    }

    private void escorderOpcionesCHF() {
        btnNavCA.setVisibility(View.INVISIBLE);
        tvTxtCHF.setVisibility(View.INVISIBLE);
    }

    private void escorderOpcionesCOV() {
        btnNavCV.setVisibility(View.INVISIBLE);
        tvTxtCOV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //NavController navController = NavHostFragment.findNavController(this);

        btnNavCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle resultado = new Bundle();
                resultado.putString("idTInforme", "CHF");
                getParentFragmentManager().setFragmentResult("key", resultado);
                Navigation.findNavController(view).navigate(R.id.navigation_altaInforme);
            }
        });

        btnNavCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle resultado = new Bundle();
                resultado.putString("idTInforme", "COV");
                getParentFragmentManager().setFragmentResult("key", resultado);
                Navigation.findNavController(view).navigate(R.id.navigation_altaInfCOVID);
            }
        });

    }


}