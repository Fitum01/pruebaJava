package com.example.pruebajava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    int idUsuario;
    User user;
    Informes[] arrayInformes = new Informes[5];
    Perfil[] arrayPerfil = new Perfil[5];
    Perfilado[] arrayPerfilado = new Perfilado[10];
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){

            progressBar = findViewById(R.id.progressBarMain);
            //rescatamos al usuario logado y sus valores
            user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
            idUsuario = user.getIdUsuario();
            //arrayInformes = SharedPrefManager.getInstance(getApplicationContext()).getDatosInfEnLista();
            listarInformes();
            //startActivity(new Intent(this, Menu.class));
            finish();

        }
        else{
            Intent  intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void listarInformes() {

        final String idUsu = Integer.toString(idUsuario);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LISTAINF, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {

                    // transformamos la respuesta a un objeto JSON
                    JSONObject j = new JSONObject(response);
                    //comprobamos que no venga vacia la consulta
                    if (!j.getString("informe").equals("0")) {
                        if (!j.getBoolean("error")) {
                            //Toast.makeText(getApplicationContext(), j.getString("message"), Toast.LENGTH_SHORT).show();

                            //recogemos el informe de la respuesta
                            JSONArray obj = j.getJSONArray("informe");
                            for (int i = 0; i < obj.length(); i++) {
                                Informes informes = new Informes(
                                        obj.getJSONObject(i).getInt("idInforme"),
                                        obj.getJSONObject(i).getString("temperatura"),
                                        obj.getJSONObject(i).getString("saturacion"),
                                        obj.getJSONObject(i).getString("idTInforme"),
                                        obj.getJSONObject(i).getString("fechaAlta"),
                                        obj.getJSONObject(i).getString("fechaInformeSig"),
                                        obj.getJSONObject(i).getString("tos"),
                                        obj.getJSONObject(i).getInt("idUsuario"),
                                        obj.getJSONObject(i).getString("dRespi"),
                                        obj.getJSONObject(i).getString("dCabeza"),
                                        obj.getJSONObject(i).getString("dGarganta"),
                                        obj.getJSONObject(i).getString("dMuscular"),
                                        obj.getJSONObject(i).getString("peso"),
                                        obj.getJSONObject(i).getString("tSistolica"),
                                        obj.getJSONObject(i).getString("tDiastolica"),
                                        obj.getJSONObject(i).getString("tMedia"),
                                        obj.getJSONObject(i).getString("descripcion")
                                );
                                //guardamos el objeto en las shares preferences
                                SharedPrefManager.getInstance(getApplicationContext()).guardarDatosInfEnLista(arrayInformes, i, informes);
                            }

                            finish();
                            ObtenerDatosPerfil();
                            ObtenerDatosPerfilado();
                            //startActivity(new Intent(getApplicationContext(), Menu.class));

                        } else {
                            Toast.makeText(getApplicationContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                    //flujo de cuando viene sin datos la consulta de la select
                    finish();
                    ObtenerDatosPerfil();
                    ObtenerDatosPerfilado();
                    //startActivity(new Intent(getApplicationContext(), Menu.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", idUsu);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void ObtenerDatosPerfil() {

        final String idUsu = Integer.toString(idUsuario);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_DATOSPERFIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    // transformamos la respuesta a un objeto JSON
                    JSONObject j = new JSONObject(response);
                    //comprobamos que no venga vacia la consulta
                    if (!j.getString("perfil").equals("0")) {
                        if (!j.getBoolean("error")) {
                            //Toast.makeText(getContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
                            //recogemos el informe de la respuesta
                            JSONArray obj = j.getJSONArray("perfil");
                            for (int i = 0; i < obj.length(); i++) {
                                Perfil perfil = new Perfil(
                                        obj.getJSONObject(i).getInt("idUsuario"),
                                        obj.getJSONObject(i).getString("nombre"),
                                        obj.getJSONObject(i).getString("apellido1"),
                                        obj.getJSONObject(i).getString("apellido2"),
                                        obj.getJSONObject(i).getString("dni"),
                                        obj.getJSONObject(i).getString("contacto"),
                                        obj.getJSONObject(i).getString("email"),
                                        obj.getJSONObject(i).getString("genero"),
                                        obj.getJSONObject(i).getInt("idPS"),
                                        obj.getJSONObject(i).getString("nombrePS"),
                                        obj.getJSONObject(i).getString("apellido1PS"),
                                        obj.getJSONObject(i).getString("apellido2PS"),
                                        obj.getJSONObject(i).getString("contactoPS"),
                                        obj.getJSONObject(i).getString("idTipoPS"),
                                        obj.getJSONObject(i).getString("descripcion")
                                );
                                //guardamos el objeto en las shares preferences
                                SharedPrefManager.getInstance(getApplicationContext()).guardarDatosPerfil(arrayPerfil, i, perfil);
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", idUsu);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void ObtenerDatosPerfilado(){

        final String idUsu = Integer.toString(idUsuario);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_VERPERFILADO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    // transformamos la respuesta a un objeto JSON
                    JSONObject j = new JSONObject(response);
                    //comprobamos que no venga vacia la consulta
                    if (!j.getString("perfilado").equals("0")) {
                        if (!j.getBoolean("error")) {
                            //Toast.makeText(getContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
                            //recogemos el informe de la respuesta
                            JSONArray obj = j.getJSONArray("perfilado");
                            for (int i = 0; i < obj.length(); i++) {
                                Perfilado perfilado = new Perfilado(
                                        obj.getJSONObject(i).getInt("idUsuario"),
                                        obj.getJSONObject(i).getString("idTInforme"),
                                        obj.getJSONObject(i).getString("descTI")
                                );
                                //guardamos el objeto en las shares preferences
                                SharedPrefManager.getInstance(getApplicationContext()).guardarPerfilado(arrayPerfilado, i, perfilado);
                            }
                            finish();
                            startActivity(new Intent(getApplicationContext(), Menu.class));

                        } else {
                            Toast.makeText(getApplicationContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        finish();
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", idUsu);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



}



