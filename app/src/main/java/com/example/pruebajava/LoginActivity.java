package com.example.pruebajava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {

    EditText etName, etPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        progressBar = findViewById(R.id.progressBar);
        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etUserPassword);



        //llamamos al metodo de userLogin
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    userLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void userLogin() throws Exception {
        //primero cogemos los valores
        final String dni = etName.getText().toString();
        final String password = etPassword.getText().toString();
        final String passEncriptado;
        final String  key ="claveTFG";

        //validamos las entradas de los campos
        if (TextUtils.isEmpty(dni)) {
            etName.setError("Por favor, introduzca su DNI");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Por favor, introduzca su Password o clave");
            etPassword.requestFocus();
            return;
        }

        // pasamos a encriptar el password antes de mandarlo a la BBDD
        passEncriptado = encriptar(password.trim(),key.trim());

        //Si va bien preparamos la request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            //convertimos la respuesta al json object
                            JSONObject obj = new JSONObject(response);


                            //Si no hay error en la response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //sacamos los valores de la response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creamos el nuevo objeto user
                                User user = new User(
                                        userJson.getInt("idUsuario"),
                                        userJson.getString("dni"),
                                        userJson.getString("nombre"),
                                        userJson.getString("apellido1"),
                                        userJson.getString("apellido2"),
                                        userJson.getString("email"),
                                        userJson.getString("genero"),
                                        userJson.getString("contacto")
                                );

                                //lo almacenamos en las preferencias
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //empezamos la profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("dni", dni);
                params.put("password", passEncriptado);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
/*
    private String desencriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey (password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte [] datosDecodificados = Base64.decode(datos, Base64.DEFAULT);
        byte [] datosDesencriptadosBytes = cipher.doFinal(datosDecodificados);
        String datosDesencriptadosString =new String(datosDesencriptadosBytes);
        return datosDesencriptadosString.trim();
    }
*/
    // metodo para encriptar la clave en base al algoritmo AES
    private String encriptar (String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey (password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte [] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return  datosEncriptadosString.trim();
    }

    private SecretKeySpec generateKey (String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte [] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec (key, "AES");
        return secretKey;
    }
}