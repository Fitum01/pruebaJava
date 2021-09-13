package com.example.pruebajava.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pruebajava.AdaptadorListView;
import com.example.pruebajava.Informes;
import com.example.pruebajava.Perfilado;
import com.example.pruebajava.R;
import com.example.pruebajava.SharedPrefManager;
import com.example.pruebajava.URLs;
import com.example.pruebajava.UltInf;
import com.example.pruebajava.User;
import com.example.pruebajava.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {

    Button btnPrueba;
    Integer idUsuario;
    User user;
    TextView textViewPrueba, tVResumenHome;
    private Informes[] arrayInformes = new Informes[5];
    Perfilado[] arrayPerfilado = new Perfilado[10];
    private ListView listViewInformes;
    private ArrayList <String> nombresLista;
    ProgressBar progressBar;
    UltInf[] arrayUltInf = new UltInf[10];

    //NavController navController;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = SharedPrefManager.getInstance(getActivity()).getUser();
        idUsuario = user.getIdUsuario();
        arrayInformes = SharedPrefManager.getInstance(getActivity()).getDatosInfEnLista();
        arrayPerfilado = SharedPrefManager.getInstance(getActivity()).getPerfilado();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
/*        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        textViewPrueba= root.findViewById(R.id.idttxtPrueba);
        btnPrueba = root.findViewById(R.id.btnPrueba);
        listViewInformes = root.findViewById(R.id.idListViewInformes);
        progressBar = root.findViewById(R.id.progressBarHome);

        tVResumenHome = root.findViewById(R.id.txtResumenHome);

        arrayInformes = SharedPrefManager.getInstance(getActivity()).getDatosInfEnLista();
        arrayPerfilado = SharedPrefManager.getInstance(getActivity()).getPerfilado();

        //El mensaje de entrar a la aplicacion

        String nombreUser = user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2();

        tVResumenHome.setText("Bienvenid@ " + nombreUser + ", revise los próximos informes a mandar según las fechas que le solicitaron sus médicos");

        //rellenamos la lista de nombres a mandar al listView
        nombresLista = new ArrayList<String>();
        for (int cont=0; cont<arrayInformes.length;cont++){
            if ((arrayInformes[cont].getIdInforme()) !=  -1) {
                nombresLista.add("Informe de tipo: " + arrayInformes[cont].getTipoInforme() + "\na fecha del " + arrayInformes[cont].getFechaAlta());
            }
        }
        //creamos el adaptador para la listView y se lo asignamos
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nombresLista);
        //listViewInformes.setAdapter(adapter);

        // le enchufamos el adaptador que hemos creado en lista_item.xml y AdaptadorListView.java
        AdaptadorListView adaptadorListView = new AdaptadorListView(getActivity(), R.layout.lista_item, nombresLista);
        listViewInformes.setAdapter(adaptadorListView);

        //listViewInformes.setAdapter(adapter);
        // le metemos el evento onClick de cada fila
        listViewInformes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //sacamos los valores
                String idTInforme = arrayInformes[position].getTipoInforme();
                String fechaAlta = arrayInformes[position].getFechaAlta();
                String temperatura = arrayInformes[position].getTemperatura();
                String saturacion = arrayInformes[position].getSaturacion();
                String dRespi = arrayInformes[position].getdRespi();
                String dCabeza = arrayInformes[position].getdCabeza();
                String dGarganta = arrayInformes[position].getdGarganta();
                String dMuscular = arrayInformes[position].getdMuscular();
                String peso = arrayInformes[position].getPeso();
                String tSistolica = arrayInformes[position].gettSistolica();
                String tDiastolica = arrayInformes[position].gettDiastolica();
                String tMedia = arrayInformes[position].gettMedia();
                String descripcion = arrayInformes[position].getDescripcion();
                String tos = arrayInformes[position].getTos();

                //comprobamos que se pulsa en un fila de listView con informe
                if ((arrayInformes[position].getIdInforme()) !=  -1) {
                    //creamos el bundle para pasarselo al otro fragment
                    Bundle consulta = new Bundle();
                    consulta.putString("idTInforme", idTInforme);
                    consulta.putString("temperatura", temperatura);
                    consulta.putString("saturacion", saturacion);
                    consulta.putString("fechaAlta", fechaAlta);
                    consulta.putString("tos", tos);
                    consulta.putString("dRespi", dRespi);
                    consulta.putString("dCabeza", dCabeza);
                    consulta.putString("dGarganta", dGarganta);
                    consulta.putString("dMuscular", dMuscular);
                    consulta.putString("peso", peso);
                    consulta.putString("tSistolica", tSistolica);
                    consulta.putString("tDiastolica", tDiastolica);
                    consulta.putString("tMedia", tMedia);
                    consulta.putString("descripcion", descripcion);

                    getParentFragmentManager().setFragmentResult("keyConsulta", consulta);

                    //aviso por pantalla al usuario
                    Toast.makeText(getActivity(), "Has pulsado: " + nombresLista.get(position), Toast.LENGTH_SHORT).show();

                    //flujo para dividir la navegacion
                    if (idTInforme.equals("COV")) {
                        //navegamos al fragment de Resumen de Covid
                        Navigation.findNavController(view).navigate(R.id.navigation_resumenInformeCOV);
                    }else {
                        //navegamos al fragment de Resumen de CHF
                        Navigation.findNavController(view).navigate(R.id.navigation_resumenInformeCHF);
                    }
                }
            }
        });

        UltimosInformes();

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayUltInf = SharedPrefManager.getInstance(getActivity()).getUltInf();
                Navigation.findNavController(root).navigate(R.id.navigation_proximosInformes);

            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //llamamos a listar informe para mostrar los datos en el listView
        listarInformes();

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
                        //Toast.makeText(getContext(), j.getString("message"), Toast.LENGTH_SHORT).show();
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
                            //SharedPrefManager.getInstance(getActivity()).guardarDatosInforme(informes);
                            SharedPrefManager.getInstance(getActivity()).guardarDatosInfEnLista(arrayInformes, i, informes);
                        }
                        //arrayInformes = SharedPrefManager.getInstance(getActivity()).getDatosInfEnLista();
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


    private void UltimosInformes(){

        final String idUsu = Integer.toString(idUsuario);
        String idTInforSig;
        ArrayList <UltInf> lUltimosInf = new ArrayList<UltInf>();
        final int[] verVeces = {-1};
        final int[] contador = {0};

        for (int i=0; i<arrayPerfilado.length; i++) {
            idTInforSig = arrayPerfilado[i].getIdTInforme();
            verVeces[0]++;

            // para mirar si hay mas tipos de Informe.
            if (idTInforSig != null) {
                String finalIdTInforSig = idTInforSig;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ULTINF,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);

                                try {
                                    //convertimos la respuesta al json object
                                    JSONObject obj = new JSONObject(response);

                                    //Si no hay error en la response
                                    if (!obj.getBoolean("error")) {
                                        //Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                        //sacamos los valores de la response
                                        JSONObject userJson = obj.getJSONObject("ultInf");

                                        //creamos el nuevo objeto user
                                        UltInf ultInf = new UltInf(
                                                userJson.getInt("idUsuario"),
                                                userJson.getString("fechaAlta"),
                                                userJson.getString("fechaInformeSig"),
                                                userJson.getString("idTInforme"),
                                                userJson.getString("descTI")
                                        );

                                        //lo almacenamos en las preferencias
                                        //SharedPrefManager.getInstance(getContext()).userLogin(user);
                                        contador[0]++;
                                        lUltimosInf.add(ultInf);

                                        //controlamos las veces que hemos llamado a la request
                                        if (verVeces[0] == contador[0]){
                                            //lo almacenamos en las preferencias
                                            SharedPrefManager.getInstance(getActivity()).guardarUltInf(contador[0], lUltimosInf);
                                        }

                                    } else {
                                        Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //sigue el flujo cuando acaba el tratamiento del JSON
                                //System.out.println(listaUltInf.get(0).getDescTI());
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
                        params.put("idTInforme", finalIdTInforSig);
                        return params;
                    }
                };

                VolleySingleton.getInstance(this.getContext()).addToRequestQueue(stringRequest);

            }else {
                //si lo que nos viene es un null
                break;
            }
        }
    }


}