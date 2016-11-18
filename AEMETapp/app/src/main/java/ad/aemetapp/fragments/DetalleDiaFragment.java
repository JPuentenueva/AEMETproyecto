package ad.aemetapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.Calendar;

import ad.aemetapp.R;
import ad.aemetapp.pojo.Dia;
import ad.aemetapp.pojo.EstadoCielo;
import ad.aemetapp.pojo.Raiz;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleDiaFragment extends Fragment {
    ImageView imgEstadoCielo;
    TextView fecha;
    TextView descripcionEstadoCielo;
    TextView tempMaxima;
    TextView tempMinima;
    TextView sensacionTermMax;
    TextView sensacionTermMin;
    TextView precipitacionDia;
    TextView precipitacionNoche;
    TextView cotaDeNieveDia;
    TextView cotaDeNieveNoche;
    CardView cardViewCotaNieve;
    View viewLocal;
    Dia diaActual;

    public DetalleDiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewLocal = inflater.inflate(R.layout.fragment_detalle_dia, container, false);

        diaActual = Parcels.unwrap(getArguments().getParcelable("datos_municipio"));

        imgEstadoCielo = (ImageView) viewLocal.findViewById(R.id.imageViewEstadoCielo);
        fecha = (TextView) viewLocal.findViewById(R.id.text_view_fecha);
        descripcionEstadoCielo = (TextView) viewLocal.findViewById(R.id.text_view_descripcionEstadoCielo);
        tempMaxima = (TextView) viewLocal.findViewById(R.id.text_view_tempMaxima);
        tempMinima = (TextView) viewLocal.findViewById(R.id.text_view_tempMinima);
        sensacionTermMax = (TextView) viewLocal.findViewById(R.id.text_view_sensMaxima);
        sensacionTermMin = (TextView) viewLocal.findViewById(R.id.text_view_sensMinima);
        precipitacionDia = (TextView) viewLocal.findViewById(R.id.text_view_precip_dia);
        precipitacionNoche = (TextView) viewLocal.findViewById(R.id.text_view_precip_noche);
        cotaDeNieveDia = (TextView) viewLocal.findViewById(R.id.text_view_cotaNieve_dia);
        cotaDeNieveNoche = (TextView) viewLocal.findViewById(R.id.text_view_cotaNieve_noche);
        cardViewCotaNieve = (CardView) viewLocal.findViewById(R.id.cardViewCotaDeNieve);

        actualizaVistaDetalle(diaActual);

        return viewLocal;

    }

    public void actualizaVistaDetalle(Dia dia) {
        //Seteo de Estado del cielo
        boolean estadoAsignado = false;
        for(EstadoCielo est : dia.getEstado_cielo()){
            if (est.getNumImg() != null && est.getPeriodo().equals("00-24") && !estadoAsignado){
                Picasso.with(viewLocal.getContext())
                        .load("http://www.aemet.es/imagenes_gcd/_iconos_municipios/"+est.getNumImg()+".png")
                        .resize(80,80)
                        .into(imgEstadoCielo);
                descripcionEstadoCielo.setText(est.getDescripcion());
                estadoAsignado = true;
            } else if(est.getNumImg() != null && est.getPeriodo().equals("12-00")) {
                Picasso.with(viewLocal.getContext())
                        .load("http://www.aemet.es/imagenes_gcd/_iconos_municipios/"+est.getNumImg()+".png")
                        .resize(80,80)
                        .into(imgEstadoCielo);
                descripcionEstadoCielo.setText(est.getDescripcion());
                estadoAsignado = true;
            } else {
                Picasso.with(viewLocal.getContext())
                        .load("http://www.aemet.es/imagenes_gcd/_iconos_municipios/"+est.getNumImg()+".png")
                        .resize(80,80)
                        .into(imgEstadoCielo);
                descripcionEstadoCielo.setText(est.getDescripcion());
                estadoAsignado = true;
            }
        }

        //Seteo de fecha
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(dia.getFecha());
        fecha.setText(fechaActual.DAY_OF_MONTH + "/" + fechaActual.MONTH + "/" + fechaActual.YEAR);

        //Seteo de temperaturas y sensacion térmica
        tempMaxima.setText(dia.getTemperatura().getMaxima()+"º");
        tempMinima.setText(dia.getTemperatura().getMinima()+"º");
        sensacionTermMax.setText(dia.getSens_termica().getMaxima()+"º");
        sensacionTermMin.setText(dia.getSens_termica().getMinima()+"º");

        //Seteo de probabilidad de precipitación de dia y noche
        if (dia.getProb_precipitacion().get("12-24") == null){
            precipitacionDia.setText("0%");
        } else {
            precipitacionDia.setText(dia.getProb_precipitacion().get("12-24")+"%");
        }
        if (dia.getProb_precipitacion().get("00-12") == null){
            precipitacionNoche.setText("0%");
        } else {
            precipitacionNoche.setText(dia.getProb_precipitacion().get("00-12")+"%");
        }

        if (dia.getCota_nieve_prov().get("00-12") == null
                && dia.getCota_nieve_prov().get("12-24") == null){
            cardViewCotaNieve.setVisibility(View.INVISIBLE);
        }

        //Seteo de la Cota de Nieve
        if (dia.getCota_nieve_prov().get("00-12") != null){
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveNoche.setText(diaActual.getCota_nieve_prov().get("00-12")+"%");
        } else {
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveNoche.setText("0%");
        }

        if (dia.getCota_nieve_prov().get("12-24") != null){
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveNoche.setText(diaActual.getCota_nieve_prov().get("12-24")+"%");
        } else {
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveNoche.setText("0%");
        }
    }
}
