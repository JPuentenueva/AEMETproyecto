package ad.aemetapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import ad.aemetapp.fragments.DetalleDiaFragment;
import ad.aemetapp.pojo.Dia;
import ad.aemetapp.pojo.EstadoCielo;
import ad.aemetapp.pojo.Raiz;

public class DetalleDiaScrollingActivity extends AppCompatActivity {
    ImageView imgEstadoCielo;
    Dia diaActual;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_MEDIA_SHARED);
                i.setData(Uri.parse("Dia: "+diaActual.getFecha()+"\n" +
                        "estado del cielo: " + diaActual.getEstado_cielo().get(0).getDescripcion()));
            }
        });
        imgEstadoCielo = (ImageView) findViewById(R.id.imageViewEstadoCielo);
        fecha = (TextView) findViewById(R.id.text_view_fecha);
        descripcionEstadoCielo = (TextView) findViewById(R.id.text_view_descripcionEstadoCielo);
        tempMaxima = (TextView) findViewById(R.id.text_view_tempMaxima);
        tempMinima = (TextView) findViewById(R.id.text_view_tempMinima);
        sensacionTermMax = (TextView) findViewById(R.id.text_view_sensMaxima);
        sensacionTermMin = (TextView) findViewById(R.id.text_view_sensMinima);
        precipitacionDia = (TextView) findViewById(R.id.text_view_precip_dia);
        precipitacionNoche = (TextView) findViewById(R.id.text_view_precip_noche);
        cotaDeNieveDia = (TextView) findViewById(R.id.text_view_cotaNieve_dia);
        cotaDeNieveNoche = (TextView) findViewById(R.id.text_view_cotaNieve_noche);
        cardViewCotaNieve = (CardView) findViewById(R.id.cardViewCotaDeNieve);

        diaActual = Parcels.unwrap(getIntent().getExtras().getParcelable("dia"));

        actualizaVistaDetalle(diaActual);
    }

    private void actualizaVistaDetalle(Dia dia) {
        //Seteo de Estado del cielo
        boolean estadoAsignado = false;
        for(EstadoCielo est : dia.getEstado_cielo()){
            if (est.getNumImg() != null
                    && !estadoAsignado) {
                Picasso.with(this)
                        .load("http://www.aemet.es/imagenes_gcd/_iconos_municipios/" + est.getNumImg() + ".png")
                        .resize(80, 80)
                        .into(imgEstadoCielo);
                estadoAsignado = true;
            } else {

            }
        }

        //Seteo de fecha
        /*Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime();*/
        fecha.setText(dia.getFecha());

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
            cotaDeNieveNoche.setText(diaActual.getCota_nieve_prov().get("00-12")+"m");
        } else {
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveNoche.setText("0m");
        }

        if (dia.getCota_nieve_prov().get("12-24") != null){
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveDia.setText(diaActual.getCota_nieve_prov().get("12-24")+"m");
        } else {
            cardViewCotaNieve.setVisibility(View.VISIBLE);
            cotaDeNieveDia.setText("0m");
        }
    }
}
