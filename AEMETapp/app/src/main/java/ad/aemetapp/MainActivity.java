package ad.aemetapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ad.aemetapp.fragments.DiasFragment;
import ad.aemetapp.fragments.MunicipiosFragment;
import ad.aemetapp.fragments.ProvinciasFragment;
import ad.aemetapp.interfaces.IOnClickDia;
import ad.aemetapp.interfaces.IOnClickMunicipio;
import ad.aemetapp.interfaces.IOnClickProvincia;
import ad.aemetapp.pojo.Dia;
import ad.aemetapp.pojo.Municipio;
import ad.aemetapp.pojo.Municipios;
import ad.aemetapp.pojo.Provincia;
import ad.aemetapp.pojo.Provincias;
import ad.aemetapp.pojo.Raiz;

public class MainActivity extends AppCompatActivity implements IOnClickProvincia, IOnClickMunicipio, IOnClickDia{
    Map<String, Municipios> provincia_municipios;
    List<Municipio> listaCiudades;
    List<Provincia> provincias = new ArrayList<>();
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HiloLecturaProvincias thread = new HiloLecturaProvincias();
        thread.execute();

    }

    @Override
    public void onClickDia(Dia dia) {

    }

    @Override
    public void onClickProvincia(Provincia provincia) {
        HiloMunicipiosDeProvincia thread = new HiloMunicipiosDeProvincia();
        thread.execute(provincia.getNombre());
    }

    @Override
    public void onClickMunicipio(Municipio municipio) {
        HiloDiasDeMunicipio thread = new HiloDiasDeMunicipio();
        thread.execute(municipio);
    }

    class HiloLecturaProvincias extends AsyncTask<Void,Void,Void> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... params) {

            //Leo el csv y creo un map de las ciudades(key)-provincias(value)
            try (InputStream is = getAssets().open("csv/municipios.csv")){
                InputStreamReader isr = new InputStreamReader(is,"ISO-8859-1");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                List<Municipio> municipios_de_provincia = new ArrayList<>();
                String currentProv = null;
                provincia_municipios = new HashMap<>();

                while ((linea = br.readLine()) != null) {
                    String[] splitProvincia_municipio = linea.split(";");

                    if(!splitProvincia_municipio[0].equalsIgnoreCase(currentProv)){

                        if (currentProv != null) {
                            Provincia p = new Provincia(currentProv,new Municipios(municipios_de_provincia));
                            provincia_municipios.put(p.getNombre(), p.getMunicipios());

                            provincias.add(p);
                        }

                        currentProv = splitProvincia_municipio[0];
                        municipios_de_provincia = new ArrayList<>();
                    }

                    /* BLOQUE PARA OBTENER EL CODIGO DE MUNICIPIO
                    //Busco el municipio en la XML de municipios
                    Serializer serializer = new Persister();
                    String query = URLEncoder.encode(splitProvincia_municipio[1],"UTF-8");
                    //String query = splitProvincia_municipio[1];
                    String sentenciaCompleta = "http://www.salesianos-triana.com/dam/xml/municipios/?exacta=1&ciudad="+query;
                    URL url = new URL(sentenciaCompleta);

                    Municipios muns = serializer.read(Municipios.class,url.openStream());
                    */

                    Municipio currentMun = new Municipio(splitProvincia_municipio[1]);
                    //Asigno la lista cargada
                    municipios_de_provincia.add(currentMun);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            fragment = new ProvinciasFragment();

            Provincias provinciasFin = new Provincias(provincias);
            Bundle bundle = new Bundle();
            bundle.putParcelable("provincias", Parcels.wrap(provinciasFin));

            fragment.setArguments(bundle);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
        }
    }

    class HiloMunicipiosDeProvincia extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            String prov = params[0];
            Municipios m = provincia_municipios.get(prov);

            fragment = new MunicipiosFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("municipios",Parcels.wrap(m));
            bundle.putString("provincia",prov);
            fragment.setArguments(bundle);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    class HiloBusquedaMunicipios extends AsyncTask<String,Void,Void> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(String... params) {
            //Ahora saco la lista de municipios con su código usando Serializer
            Municipios municipios = null;
            Serializer serializer = new Persister();

            try (FileInputStream fis = openFileInput("http://www.salesianos-triana.com/dam/xml/municipios/?ciudad="+params[0])){

                municipios = serializer.read(Municipios.class, fis);

            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            } catch (Exception e) {
                    e.printStackTrace();
            }

            //TODO comprobar que la lista no es nula, y si lo es, lanzar mensaje de lista vacia

            listaCiudades = municipios.getMunicipios();

            return null;
        }
    }

    private class HiloDiasDeMunicipio extends AsyncTask<Municipio,Void,Void> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Municipio... params) {
            Municipio currentMun = params[0];
            Raiz datos_municipio = null;
            Serializer serializer = new Persister();
            String query = null;
            URL url = null;

            //Crea la sentencia en un URL
            try {
                query = URLEncoder.encode(currentMun.getNombre(),"UTF-8");
                String sentenciaCompleta = "http://www.salesianos-triana.com/dam/xml/municipios/?exacta=1&ciudad="+query;
                url = new URL(sentenciaCompleta);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //Obtiene el municipio con el código
            try (InputStream inputStream = url.openStream()) {
                currentMun = serializer.read(Municipios.class, inputStream).getMunicipios().get(0);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

            //Obtiene los datos del municipio
            try (FileInputStream fis = openFileInput("http://www.aemet.es/xml/municipios/localidad_"+currentMun.getCpro()+currentMun.getCmun()+".xml")) {

                datos_municipio = serializer.read(Raiz.class, fis);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent i = new Intent(MainActivity.this, DiasActivity.class);
            i.putExtra("datos_municipio", Parcels.wrap(datos_municipio));

            startActivity(i);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }
}
