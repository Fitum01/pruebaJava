package com.example.pruebajava.altaInformeCOV;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentResultListener;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pruebajava.Informes;
import com.example.pruebajava.R;
import com.example.pruebajava.SharedPrefManager;
import com.example.pruebajava.URLs;
import com.example.pruebajava.User;
import com.example.pruebajava.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AltaInfCOVID2 extends Fragment {

    String tipoInformeCOV, formattedDate, formattedDate2, saturacionCOV, temperaturaCOV, dRespiCOV, dCabezaCOV, dGargantaCOV, dMuscularCOV, tosCOV;
    TextView tvFechaAlta, tVdSaturacion, tVdTemperatura, tVdRespi, tVdCabeza, tVdGarganta, tVdMuscular, tVtos;
    Button btnAltaInformeCOV;
    Integer idUsuario;
    User user;
    ProgressBar progressBar;
    private Informes[] arrayInformes = new Informes[5];

    private AltaInfCOVID2ViewModel mViewModel;

    public static AltaInfCOVID2 newInstance() {
        return new AltaInfCOVID2();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // para recoger el valor que le mando del boton ">>"
        getParentFragmentManager().setFragmentResultListener("keyAltaCOV",this, new FragmentResultListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                temperaturaCOV = result.getString("temperatura");
                saturacionCOV = result.getString("saturacion");
                tosCOV = result.getString("tos");
                dRespiCOV = result.getString("dRespi");
                dCabezaCOV = result.getString("dCabeza");
                dGargantaCOV = result.getString("dGarganta");
                dMuscularCOV = result.getString("dMuscular");
                tipoInformeCOV = result.getString("idTInforme");

                //sacamos la hora del sistema en el formato que necesitamos para meterla a la BBDD
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
                formattedDate = df.format(c.getTime());

                //calculamos la fecha para el siguiente informe de tipo COV
                //codigo para transformar la feha y sumarle unos dias
                Date fecha1, fecha2 = new Date();
                String string = formattedDate;

                try {
                    fecha1=new SimpleDateFormat("dd/MM/yyyy").parse(string);
                    fecha2 =sumarRestarDiasFecha(fecha1, 1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/YYYY");
                formattedDate2 = df2.format(fecha2.getTime());

                //mostramos por pantalla los datos para confirmar el Alta
                tvFechaAlta.setText(formattedDate);
                tVdSaturacion.setText(saturacionCOV);
                tVtos.setText(tosCOV);
                tVdTemperatura.setText(temperaturaCOV);
                tVdRespi.setText(dRespiCOV);
                tVdCabeza.setText(dCabezaCOV);
                tVdGarganta.setText(dGargantaCOV);
                tVdMuscular.setText(dMuscularCOV);
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.alta_inf_covid2_fragment, container, false);

        tvFechaAlta=root.findViewById(R.id.txtFechaAlta2);
        tVdSaturacion=root.findViewById(R.id.txtSaturacion2);
        tVtos=root.findViewById(R.id.txtTos2);
        tVdTemperatura=root.findViewById(R.id.txtTemperatura2);
        tVdRespi=root.findViewById(R.id.txtDRespi2);
        tVdCabeza=root.findViewById(R.id.txtDCabeza2);
        tVdGarganta=root.findViewById(R.id.txtDGarganta2);
        tVdMuscular=root.findViewById(R.id.txtDMuscular2);
        btnAltaInformeCOV=root.findViewById(R.id.idBtnConfAltaCOV);
        progressBar = root.findViewById(R.id.progressBarAltaInfCOV2);

        //rescatamos al usuario logado y sus valores
        user = SharedPrefManager.getInstance(root.getContext()).getUser();
        idUsuario = user.getIdUsuario();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAltaInformeCOV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarInformeCOV();
                listarInformes();


                //creamos el Dialog de aviso de envio
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Aviso")
                        .setMessage("El Informe ha sido enviado")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        arrayInformes = SharedPrefManager.getInstance(getActivity()).getDatosInfEnLista();
                                        Navigation.findNavController(view).navigate(R.id.navigation_home);
                                    }
                                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    // Suma los días recibidos a la fecha

    public Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    // funcion para registrar un informe de tipo = CHF [cardiovascular]
    private void registrarInformeCOV() {

        final String temperatura = temperaturaCOV.trim();
        final String saturacion = saturacionCOV.trim();
        final String idTInforme = tipoInformeCOV.trim();
        final String fechaAlta = formattedDate.trim();
        final String fechaInformeSig = formattedDate2.trim();
        final String tos = tosCOV.trim();
        final String idUsu = Integer.toString(idUsuario);
        final String dRespi = dRespiCOV.trim();
        final String dCabeza = dCabezaCOV.trim();
        final String dGarganta = dGargantaCOV.trim();
        final String dMuscular = dMuscularCOV.trim();
        final String peso = "";
        final String tSistolica = "";
        final String tDiastolica = "";
        final String tMedia = "";
        final String descripcion = "";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_INSERTINF, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // transformamos la respuesta a un objeto JSON
                    JSONObject obj = new JSONObject(response);

                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //recogemos el informe de la respuesta
                        JSONObject informeJson = obj.getJSONObject("informe");

                        //creamos un nuevo objeto Informe
                        Informes informes = new Informes(
                                informeJson.getInt("idInforme"),
                                informeJson.getString("temperatura"),
                                informeJson.getString("saturacion"),
                                informeJson.getString("idTInforme"),
                                informeJson.getString("fechaAlta"),
                                informeJson.getString("fechaInformeSig"),
                                informeJson.getString("tos"),
                                informeJson.getInt("idUsuario"),
                                informeJson.getString("dRespi"),
                                informeJson.getString("dCabeza"),
                                informeJson.getString("dGarganta"),
                                informeJson.getString("dMuscular"),
                                informeJson.getString("peso"),
                                informeJson.getString("tSistolica"),
                                informeJson.getString("tDiastolica"),
                                informeJson.getString("tMedia"),
                                informeJson.getString("descripcion")
                        );

                        //guardamos el objeto en las shares preferences
                        SharedPrefManager.getInstance(getActivity()).guardarDatosInforme(informes);

                        // continuamos flujo ¿?


                    } else {
                        Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("temperatura", temperatura);
                params.put("saturacion", saturacion);
                params.put("idTInforme", idTInforme);
                params.put("fechaAlta", fechaAlta);
                params.put("fechaInformeSig", fechaInformeSig);
                params.put("tos", tos);
                params.put("idUsuario", idUsu);
                params.put("dRespi", dRespi);
                params.put("dCabeza", dCabeza);
                params.put("dGarganta", dGarganta);
                params.put("dMuscular", dMuscular);
                params.put("peso", peso);
                params.put("tSistolica", tSistolica);
                params.put("tDiastolica", tDiastolica);
                params.put("tMedia", tMedia);
                params.put("descripcion", descripcion);
                return params;
            }
        };
        VolleySingleton.getInstance(this.getContext()).addToRequestQueue(stringRequest);
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
                    if (!j.getBoolean("error")) {
                        //Toast.makeText(getContext(), j.getString("message"), Toast.LENGTH_SHORT).show();

                        //recogemos el informe de la respuesta
                        JSONArray obj = j.getJSONArray("informe");
                        for (int i = 0; i < obj.length(); i++){
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