package ad.aemetapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.parceler.Parcels;

import ad.aemetapp.fragments.DetalleDiaFragment;
import ad.aemetapp.fragments.DiasFragment;
import ad.aemetapp.interfaces.IOnClickDia;
import ad.aemetapp.pojo.Dia;
import ad.aemetapp.pojo.Municipio;
import ad.aemetapp.pojo.Raiz;

public class DiasActivity extends AppCompatActivity implements IOnClickDia {
    Raiz datos_municipio;
    LinearLayout linearLayoutTablet;
    DetalleDiaFragment fragmentDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias);
        datos_municipio = Parcels.unwrap(getIntent().getExtras().getParcelable("datos_municipio"));
        setTitle(datos_municipio.getNombre());

        linearLayoutTablet = (LinearLayout) findViewById(R.id.linear_layout_tablet);

        if(linearLayoutTablet!=null) {
            // Estoy en la tablet
            Bundle extras = new Bundle();
            extras.putParcelable("datos_municipio",Parcels.wrap(datos_municipio));
            fragmentDetalle.setArguments(extras);
            fragmentDetalle = (DetalleDiaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDetalleDia);

        } else {
            // estoy en un móvil de mano (handset)
            Fragment f = new DiasFragment();

            Bundle extras = new Bundle();
            extras.putParcelable("datos_municipio",Parcels.wrap(datos_municipio));

            f.setArguments(extras);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,f)
                    .commit();
        }
    }

    public void onClickDia(Dia dia) {
        if(linearLayoutTablet!=null) { // tablet
            fragmentDetalle.actualizaVistaDetalle(dia);
        } else { // móvil
            Intent i = new Intent(this, DetalleDiaActivity.class);
            i.putExtra("dia",Parcels.wrap(dia));
            startActivity(i);
        }
    }

}
