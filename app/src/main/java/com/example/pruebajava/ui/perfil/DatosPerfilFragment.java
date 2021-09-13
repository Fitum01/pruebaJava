package com.example.pruebajava.ui.perfil;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pruebajava.Perfil;
import com.example.pruebajava.R;
import com.example.pruebajava.SharedPrefManager;
import com.example.pruebajava.URLs;
import com.example.pruebajava.User;
import com.example.pruebajava.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatosPerfilFragment extends Fragment {
    User user;
    Integer idUsuario;

    private Perfil[] arrayPerfil = new Perfil[5];
    ProgressBar progressBar;

    private NotificationsViewModel notificationsViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //user = SharedPrefManager.getInstance(root.getContext()).getUser();
        user = SharedPrefManager.getInstance(getActivity()).getUser();
        idUsuario = user.getIdUsuario();
        //ObtenerDatosPerfil();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView etNombre, et0, et1, et2, et3, etListaPS;

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_datos_perfil, container, false);

        //final TextView textView = root.findViewById(R.id.text_notifications);
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
        //   public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});


        progressBar = root.findViewById(R.id.progressBarPerfil);

        et0 = root.findViewById(R.id.idtxt0PerfilDNI);
        et1 = root.findViewById(R.id.idtxt1PerfilSexo);
        et2 = root.findViewById(R.id.idtxt2PerfilContacto);
        et3 = root.findViewById(R.id.idtxt3PerfilEmail);
        etNombre = root.findViewById(R.id.txtNombrePerfil);
        etListaPS = root.findViewById(R.id.idtxtListaPS);


        arrayPerfil = SharedPrefManager.getInstance(getActivity()).getDatosPerfil();

        String dni = user.getDni();
        String nombreCompleto = user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2();
        String sexo = user.getGenero();
        String contacto = user.getContacto();
        String email = user.getEmail();

        String nombrePS = "", listaFinalPS = "", basura, salto = "\n";
        ArrayList<String> listaPS = new ArrayList<String>();

        //recorremos el array del perfilado para sacar los datos y los colocamos cada uno en un elemento del arrayList
        for (int i=0; i<arrayPerfil.length; i++){
            if (arrayPerfil[i].getNombre() != null) {
                nombrePS = arrayPerfil[i].getNombrePS() + " " + arrayPerfil[i].getApellido1PS() + " " + arrayPerfil[i].getApellido2PS() + " (" + arrayPerfil[i].getContactoPS() + " )";
                listaPS.add(arrayPerfil[i].getDescripcionPS() + " : ");
                listaPS.add(nombrePS);
                listaPS.add(salto);
            }
        }

        //recorremos el arrayList y lo metemos en un string para pintarlo despues
        for (int cont=0; cont<listaPS.size(); cont++){
            basura = listaPS.get(cont);
            listaFinalPS = listaFinalPS + "\n" + basura;
        }


        etNombre.setText(nombreCompleto);
        et0.setText(dni);
        et1.setText(sexo);
        et2.setText(contacto);
        et3.setText(email);
        etListaPS.setText(listaFinalPS);

        // para ponerle scroll al textview de la lista de personal sanitario
        etListaPS.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
                                SharedPrefManager.getInstance(getActivity()).guardarDatosPerfil(arrayPerfil, i, perfil);
                            }

                        } else {
                            Toast.makeText(getContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", idUsu);
                return params;
            }
        };
        VolleySingleton.getInstance(this.getContext()).addToRequestQueue(stringRequest);
    }

}