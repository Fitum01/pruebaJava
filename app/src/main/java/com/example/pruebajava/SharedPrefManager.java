package com.example.pruebajava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_DNI = "keydni";
    private static final String KEY_NOMBRE = "keynombre";
    private static final String KEY_APELLIDO1 = "keyapellido1";
    private static final String KEY_APELLIDO2 = "keyapellido2";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENERO = "keygenero";
    private static final String KEY_IDUSUARIO = "keyidUsuario";
    private static final String KEY_CONTACTO = "keycontacto";

    private static SharedPrefManager mInstance;
    private static Context ctx;

    private static final String SHARED_PREF_INFORME = "volleyregisterinforme";
    private static final String KEY_IDINFORME = "keyidInforme";
    private static final String KEY_TEMPERATURA = "keytemperatura";
    private static final String KEY_SATURACION = "keysaturacion";
    private static final String KEY_IDTINFORME = "keyidtinforme";
    private static final String KEY_FECHAALTA = "keyfechaalta";
    private static final String KEY_FECHAINFORMESIG = "keyfechaInformeSig";
    private static final String KEY_TOS = "keytos";
    private static final String KEY_IDUSER = "keyiduser";
    private static final String KEY_DRESPI = "keydRespi";
    private static final String KEY_DCABEZA = "keydCabeza";
    private static final String KEY_DGARGANTA = "keydGarganta";
    private static final String KEY_DMUSCULAR = "keydMuscular";
    private static final String KEY_PESO = "keypeso";
    private static final String KEY_TSISTOLICA = "keytSistolica";
    private static final String KEY_TDIASTOLICA = "keytDiastolica";
    private static final String KEY_TMEDIA = "keytMedia";
    private static final String KEY_DESCRIPCION = "keydescripcion";

    private static final String SHARED_PREF_INFORMELIST = "volleyregisterinformelist";
    private static final String KEY_IDINFORMELIST = "keyidInformelist";
    private static final String KEY_TEMPERATURALIST = "keytemperaturalist";
    private static final String KEY_SATURACIONLIST = "keysaturacionlist";
    private static final String KEY_IDTINFORMELSIT = "keyidtinformelist";
    private static final String KEY_FECHAALTALIST = "keyfechaaltalist";
    private static final String KEY_FECHAINFORMESIGLIST = "keyfechaInformeSiglist";
    private static final String KEY_TOSLIST = "keytoslist";
    private static final String KEY_IDUSERLIST = "keyiduserlist";
    private static final String KEY_DRESPILIST = "keydRespilist";
    private static final String KEY_DCABEZALIST = "keydCabezalist";
    private static final String KEY_DGARGANTALIST = "keydGargantalist";
    private static final String KEY_DMUSCULARLIST = "keydMuscularlist";
    private static final String KEY_PESOLIST = "keypesolist";
    private static final String KEY_TSISTOLICALIST = "keytSistolicalist";
    private static final String KEY_TDIASTOLICALIST = "keytDiastolicalist";
    private static final String KEY_TMEDIALIST = "keytMedialist";
    private static final String KEY_DESCRIPCIONLIST = "keydescripcionlist";

    private static final String SHARED_PREF_DATOSPERFIL = "volleyregisterdatosperfil";
    private static final String KEY_IDUSUARIOPERFIL = "keyidUsuarioperfil";
    private static final String KEY_NOMBREPERFIL = "keynombreperfil";
    private static final String KEY_APELLIDO1PERFIL = "keyapellido1perfil";
    private static final String KEY_APELLIDO2PERFIL = "keyapellido2perfil";
    private static final String KEY_DNIPERFIL = "keydniperfil";
    private static final String KEY_CONTACTOPERFIL = "keycontactoperfil";
    private static final String KEY_EMAILPERFIL = "keyemailperfil";
    private static final String KEY_GENEROPERFIL = "keygeneroperfil";
    private static final String KEY_IDPSPERFIL = "keyIdPSperfil";
    private static final String KEY_NOMBREPSPERFIL = "keynombrePSperfil";
    private static final String KEY_APELLIDO1PSPERFIL = "keyapellido1PSperfil";
    private static final String KEY_APELLIDO2PSPERFIL = "keyapellido2PSperfil";
    private static final String KEY_CONTACTOPSPERFIL = "keycontactoPSperfil";
    private static final String KEY_IDTIPOSPSPERFIL = "keyIdTipoPSperfil";
    private static final String KEY_DESCPSPERFIL = "keydescPSperfil";

    private static final String SHARED_PREF_PERFILADO = "volleyregisterperfilado";
    private static final String KEY_IDUSUARIOPERFILADO = "keyidUsuarioperfilado";
    private static final String KEY_IDTINFORMEPERFILADO = "keyidtipoinformeperfilado";
    private static final String KEY_IDDESCTIPERFILADO = "keyiddesctiperfilado";

    private static final String SHARED_PREF_ULTINF = "volleyregisterultinf";
    private static final String KEY_IDUSUARIOULTINF = "keyidUsuarioultinf";
    private static final String KEY_FECHAALTAULTINF = "keyfechaAltaultinf";
    private static final String KEY_FECHAULTINF = "keyfechaultinf";
    private static final String KEY_IDTINFORMEULTINF = "keyidTinformeultinf";
    private static final String KEY_DESCTIULTINF = "keydescTIultinf";


    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //Este metodo almacenara el usuario en las sharedPreferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_IDUSUARIO, user.getIdUsuario());
        editor.putString(KEY_DNI, user.getDni());
        editor.putString(KEY_NOMBRE, user.getNombre());
        editor.putString(KEY_APELLIDO1, user.getApellido1());
        editor.putString(KEY_APELLIDO2, user.getApellido2());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENERO, user.getGenero());
        editor.putString(KEY_CONTACTO, user.getContacto());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DNI, null) != null;
    }

    //Este metodo devolvera el usuario logado
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_IDUSUARIO, -1),
                sharedPreferences.getString(KEY_DNI, null),
                sharedPreferences.getString(KEY_NOMBRE, null),
                sharedPreferences.getString(KEY_APELLIDO1, null),
                sharedPreferences.getString(KEY_APELLIDO2, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENERO, null),
                sharedPreferences.getString(KEY_CONTACTO, null)
        );
    }

    //Este metodo hara un logout
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        SharedPreferences sharedPreferences2 = ctx.getSharedPreferences(SHARED_PREF_INFORMELIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.clear();
        editor2.apply();
        SharedPreferences sharedPreferences3 = ctx.getSharedPreferences(SHARED_PREF_INFORME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor3.clear();
        editor3.apply();
        SharedPreferences sharedPreferences4 = ctx.getSharedPreferences(SHARED_PREF_DATOSPERFIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        editor4.clear();
        editor4.apply();
        SharedPreferences sharedPreferences5 = ctx.getSharedPreferences(SHARED_PREF_PERFILADO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();
        editor5.clear();
        editor5.apply();
        SharedPreferences sharedPreferences6 = ctx.getSharedPreferences(SHARED_PREF_ULTINF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedPreferences6.edit();
        editor6.clear();
        editor6.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }


    //este metodo guarda los valores del informe en las shared preferences
    public void guardarDatosInforme (Informes informes){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_INFORME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_IDINFORME, informes.getIdInforme());
        editor.putString(KEY_TEMPERATURA, informes.getTemperatura());
        editor.putString(KEY_SATURACION, informes.getSaturacion());
        editor.putString(KEY_IDTINFORME, informes.getTipoInforme());
        editor.putString(KEY_FECHAALTA, informes.getFechaAlta());
        editor.putString(KEY_FECHAINFORMESIG, informes.getFechaInformeSig());
        editor.putString(KEY_TOS, informes.getTos());
        editor.putInt(KEY_IDUSER, informes.getIdUsuario());
        editor.putString(KEY_DRESPI, informes.getdRespi());
        editor.putString(KEY_DCABEZA, informes.getdCabeza());
        editor.putString(KEY_DGARGANTA, informes.getdGarganta());
        editor.putString(KEY_DMUSCULAR, informes.getdMuscular());
        editor.putString(KEY_PESO, informes.getPeso());
        editor.putString(KEY_TSISTOLICA, informes.gettSistolica());
        editor.putString(KEY_TDIASTOLICA, informes.gettDiastolica());
        editor.putString(KEY_TMEDIA, informes.gettMedia());
        editor.putString(KEY_DESCRIPCION, informes.getDescripcion());
        editor.apply();
    }

    //este metodo devuelve el informe
    public Informes getInforme(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_INFORME, Context.MODE_PRIVATE);
        return new Informes(
                sharedPreferences.getInt(KEY_IDINFORME,-1),
                sharedPreferences.getString(KEY_TEMPERATURA, null),
                sharedPreferences.getString(KEY_SATURACION, null),
                sharedPreferences.getString(KEY_IDTINFORME, null),
                sharedPreferences.getString(KEY_FECHAALTA, null),
                sharedPreferences.getString(KEY_FECHAINFORMESIG, null),
                sharedPreferences.getString(KEY_TOS, null),
                sharedPreferences.getInt(KEY_IDUSER, -1),
                sharedPreferences.getString(KEY_DRESPI, null),
                sharedPreferences.getString(KEY_DCABEZA, null),
                sharedPreferences.getString(KEY_DGARGANTA, null),
                sharedPreferences.getString(KEY_DMUSCULAR, null),
                sharedPreferences.getString(KEY_PESO, null),
                sharedPreferences.getString(KEY_TSISTOLICA, null),
                sharedPreferences.getString(KEY_TDIASTOLICA, null),
                sharedPreferences.getString(KEY_TMEDIA, null),
                sharedPreferences.getString(KEY_DESCRIPCION, null)
        );
    }

    public void guardarDatosInfEnLista(Informes[] arrayInformes, int i, Informes informes){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_INFORMELIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        arrayInformes[i] = informes;
        editor.putInt(KEY_IDINFORMELIST+i, arrayInformes[i].getIdInforme());
        editor.putString(KEY_TEMPERATURALIST+i, arrayInformes[i].getTemperatura());
        editor.putString(KEY_SATURACIONLIST+i, arrayInformes[i].getSaturacion());
        editor.putString(KEY_IDTINFORMELSIT+i, arrayInformes[i].getTipoInforme());
        editor.putString(KEY_FECHAALTALIST+i, arrayInformes[i].getFechaAlta());
        editor.putString(KEY_FECHAINFORMESIGLIST+i, arrayInformes[i].getFechaInformeSig());
        editor.putString(KEY_TOSLIST+i, arrayInformes[i].getTos());
        editor.putInt(KEY_IDUSERLIST+i, arrayInformes[i].getIdUsuario());
        editor.putString(KEY_DRESPILIST+i, arrayInformes[i].getdRespi());
        editor.putString(KEY_DCABEZALIST+i, arrayInformes[i].getdCabeza());
        editor.putString(KEY_DGARGANTALIST+i, arrayInformes[i].getdGarganta());
        editor.putString(KEY_DMUSCULARLIST+i, arrayInformes[i].getdMuscular());
        editor.putString(KEY_PESOLIST+i, arrayInformes[i].getPeso());
        editor.putString(KEY_TSISTOLICALIST+i, arrayInformes[i].gettSistolica());
        editor.putString(KEY_TDIASTOLICALIST+i, arrayInformes[i].gettDiastolica());
        editor.putString(KEY_TMEDIALIST+i, arrayInformes[i].gettMedia());
        editor.putString(KEY_DESCRIPCIONLIST+i, arrayInformes[i].getDescripcion());
        editor.apply();
    }

    public Informes[] getDatosInfEnLista(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_INFORMELIST, Context.MODE_PRIVATE);
        Informes[] arr = new Informes[5];
        for (int j = 0; j<5; j++) {
            arr[j] =new Informes(
                    sharedPreferences.getInt(KEY_IDINFORMELIST+j, -1),
                    sharedPreferences.getString(KEY_TEMPERATURALIST+j, null),
                    sharedPreferences.getString(KEY_SATURACIONLIST+j, null),
                    sharedPreferences.getString(KEY_IDTINFORMELSIT+j, null),
                    sharedPreferences.getString(KEY_FECHAALTALIST+j, null),
                    sharedPreferences.getString(KEY_FECHAINFORMESIGLIST+j, null),
                    sharedPreferences.getString(KEY_TOSLIST+j, null),
                    sharedPreferences.getInt(KEY_IDUSERLIST+j, -1),
                    sharedPreferences.getString(KEY_DRESPILIST+j, null),
                    sharedPreferences.getString(KEY_DCABEZALIST+j, null),
                    sharedPreferences.getString(KEY_DGARGANTALIST+j, null),
                    sharedPreferences.getString(KEY_DMUSCULARLIST+j, null),
                    sharedPreferences.getString(KEY_PESOLIST+j, null),
                    sharedPreferences.getString(KEY_TSISTOLICALIST+j, null),
                    sharedPreferences.getString(KEY_TDIASTOLICALIST+j, null),
                    sharedPreferences.getString(KEY_TMEDIALIST+j, null),
                    sharedPreferences.getString(KEY_DESCRIPCIONLIST+j, null)
            );
        }
        return arr;
    }

    public void guardarDatosPerfil(Perfil[] arrayPerfil, int i, Perfil perfil){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_DATOSPERFIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        arrayPerfil[i] = perfil;
        editor.putInt(KEY_IDUSUARIOPERFIL+i, arrayPerfil[i].getIdUsuario());
        editor.putString(KEY_NOMBREPERFIL+i, arrayPerfil[i].getNombre());
        editor.putString(KEY_APELLIDO1PERFIL+i, arrayPerfil[i].getApellido1());
        editor.putString(KEY_APELLIDO2PERFIL+i, arrayPerfil[i].getApellido2());
        editor.putString(KEY_DNIPERFIL+i, arrayPerfil[i].getDni());
        editor.putString(KEY_CONTACTOPERFIL+i, arrayPerfil[i].getContacto());
        editor.putString(KEY_EMAILPERFIL+i, arrayPerfil[i].getEmail());
        editor.putString(KEY_GENEROPERFIL+i, arrayPerfil[i].getGenero());
        editor.putInt(KEY_IDPSPERFIL+i, arrayPerfil[i].getIdPS());
        editor.putString(KEY_NOMBREPSPERFIL+i, arrayPerfil[i].getNombrePS());
        editor.putString(KEY_APELLIDO1PSPERFIL+i, arrayPerfil[i].getApellido1PS());
        editor.putString(KEY_APELLIDO2PSPERFIL+i, arrayPerfil[i].getApellido2PS());
        editor.putString(KEY_CONTACTOPSPERFIL+i, arrayPerfil[i].getContactoPS());
        editor.putString(KEY_IDTIPOSPSPERFIL+i, arrayPerfil[i].getIdtipoPS());
        editor.putString(KEY_DESCPSPERFIL+i, arrayPerfil[i].getDescripcionPS());
        editor.apply();
    }

    public Perfil[] getDatosPerfil(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_DATOSPERFIL, Context.MODE_PRIVATE);
        Perfil[] arr = new Perfil[5];
        for (int j = 0; j<5; j++) {
            arr[j] =new Perfil(
                    sharedPreferences.getInt(KEY_IDUSUARIOPERFIL+j, -1),
                    sharedPreferences.getString(KEY_NOMBREPERFIL+j, null),
                    sharedPreferences.getString(KEY_APELLIDO1PERFIL+j, null),
                    sharedPreferences.getString(KEY_APELLIDO2PERFIL+j, null),
                    sharedPreferences.getString(KEY_DNIPERFIL+j, null),
                    sharedPreferences.getString(KEY_CONTACTOPERFIL+j, null),
                    sharedPreferences.getString(KEY_EMAILPERFIL+j, null),
                    sharedPreferences.getString(KEY_GENEROPERFIL+j, null),
                    sharedPreferences.getInt(KEY_IDPSPERFIL+j, -1),
                    sharedPreferences.getString(KEY_NOMBREPSPERFIL+j, null),
                    sharedPreferences.getString(KEY_APELLIDO1PSPERFIL+j, null),
                    sharedPreferences.getString(KEY_APELLIDO2PSPERFIL+j, null),
                    sharedPreferences.getString(KEY_CONTACTOPSPERFIL+j, null),
                    sharedPreferences.getString(KEY_IDTIPOSPSPERFIL+j, null),
                    sharedPreferences.getString(KEY_DESCPSPERFIL+j, null)
            );
        }
        return arr;
    }

    public void guardarPerfilado(Perfilado[] arrayPerfilado, int i, Perfilado perfilado){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_PERFILADO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        arrayPerfilado[i] = perfilado;
        editor.putInt(KEY_IDUSUARIOPERFILADO+i, arrayPerfilado[i].getIdUsuario());
        editor.putString(KEY_IDTINFORMEPERFILADO+i, arrayPerfilado[i].getIdTInforme());
        editor.putString(KEY_IDDESCTIPERFILADO+i, arrayPerfilado[i].getDescTI());
        editor.apply();
    }

    public Perfilado[] getPerfilado(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_PERFILADO, Context.MODE_PRIVATE);
        Perfilado[] arr = new Perfilado[10];
        for (int j = 0; j<10; j++) {
            arr[j] =new Perfilado(
                    sharedPreferences.getInt(KEY_IDUSUARIOPERFILADO+j, -1),
                    sharedPreferences.getString(KEY_IDTINFORMEPERFILADO+j, null),
                    sharedPreferences.getString(KEY_IDDESCTIPERFILADO+j, null)
            );
        }
        return arr;
    }

    public void guardarUltInf(int i, ArrayList<UltInf> listaUltInf){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_ULTINF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        UltInf[] arr = new UltInf[i];
        for (int j=0; j<listaUltInf.size(); j++){
            arr[j] = listaUltInf.get(j);
            editor.putInt(KEY_IDUSUARIOULTINF+j, listaUltInf.get(j).getIdUsuario());
            editor.putString(KEY_FECHAALTAULTINF+j, listaUltInf.get(j).getFechaAlta());
            editor.putString(KEY_FECHAULTINF+j, listaUltInf.get(j).getFechaInformeSig());
            editor.putString(KEY_IDTINFORMEULTINF+j, listaUltInf.get(j).getIdTInforme());
            editor.putString(KEY_DESCTIULTINF+j, listaUltInf.get(j).getDescTI());
            editor.apply();
        }

    }

    public UltInf[] getUltInf(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_ULTINF, Context.MODE_PRIVATE);
        UltInf[] arr = new UltInf[10];
        for (int j = 0; j<10; j++) {
            arr[j] =new UltInf(
                    sharedPreferences.getInt(KEY_IDUSUARIOULTINF+j, -1),
                    sharedPreferences.getString(KEY_FECHAALTAULTINF+j, null),
                    sharedPreferences.getString(KEY_FECHAULTINF+j, null),
                    sharedPreferences.getString(KEY_IDTINFORMEULTINF+j, null),
                    sharedPreferences.getString(KEY_DESCTIULTINF+j, null)
            );
        }

        return arr;
    }

    public void borrarListaInformes(){
        SharedPreferences sharedPreferences2 = ctx.getSharedPreferences(SHARED_PREF_INFORMELIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.clear();
        editor2.apply();
    }

    public void borrarInforme(){
        SharedPreferences sharedPreferences3 = ctx.getSharedPreferences(SHARED_PREF_INFORME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor3.clear();
        editor3.apply();
    }

    public void borrarDatosPerfil(){
        SharedPreferences sharedPreferences4 = ctx.getSharedPreferences(SHARED_PREF_DATOSPERFIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        editor4.clear();
        editor4.apply();
    }

    public void borrarPerfilado(){
        SharedPreferences sharedPreferences5 = ctx.getSharedPreferences(SHARED_PREF_PERFILADO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();
        editor5.clear();
        editor5.apply();
    }
}
