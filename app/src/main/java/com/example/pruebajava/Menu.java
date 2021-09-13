package com.example.pruebajava;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Menu extends AppCompatActivity {

    //BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_informes, R.id.navigation_perfil, R.id.navigation_salir)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        setBottomNavVisible();
        // para meter funcionalidad en el boton de salir del bottom navigation
        navView.getMenu().findItem(R.id.navigation_salir).setOnMenuItemClickListener(navigation_salir -> {
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            finish();
            return true;
        });
    }

    // funcion para ocultar el bottomNavMenu cuando vamos a alta Informe.
    private void setBottomNavVisible(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) ->{
                    switch (destination.getId()){
                        case R.id.navigation_altaInforme:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_altaInforme2:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_resumenInformeCHF:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_resumenInformeCOV:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_proximosInformes:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_altaInfCOVID:
                            OcultarBottonMenu();
                            break;
                        case R.id.navigation_altaInfCOVID2:
                            OcultarBottonMenu();
                            break;
                        default:
                            MostrarBottonMenu();
                            break;
                    }
                }
                );
    }

    private void MostrarBottonMenu(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
    }

    private void OcultarBottonMenu(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);
    }

    // funcion para volverme hacia atras con la flecha de las cabeceras
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp();
    }
}