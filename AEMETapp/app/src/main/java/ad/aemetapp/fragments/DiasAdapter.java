package ad.aemetapp.fragments;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ad.aemetapp.R;
import ad.aemetapp.interfaces.IOnClickDia;
import ad.aemetapp.pojo.Dia;
import ad.aemetapp.pojo.EstadoCielo;

import java.util.Calendar;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Dia} and makes a call to the
 * specified {@link IOnClickDia}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder> {

    private final List<Dia> mValues;
    private final IOnClickDia mListener;
    Context ctx;

    public DiasAdapter(List<Dia> items, IOnClickDia listener, Context ctx) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        //Seteo la imagen del estado del cielo
        boolean estadoAsignado = false;
        for(EstadoCielo est : holder.mItem.getEstado_cielo()) {
            if (est.getNumImg() != null
                    && !estadoAsignado) {
                Picasso.with(ctx)
                        .load("http://www.aemet.es/imagenes_gcd/_iconos_municipios/" + est.getNumImg() + ".png")
                        .resize(80, 80)
                        .into(holder.mEstadoPrevioView);
                estadoAsignado = true;
            } else {

            }
        }

        Calendar fechaDeHoy = Calendar.getInstance();
        String [] fechaPorPartes = holder.mItem.getFecha().split("-");
        //Filtro para determinar si el dia es Hoy
        if(fechaDeHoy.DAY_OF_MONTH == Integer.parseInt(fechaPorPartes[2])
                && fechaDeHoy.MONTH == Integer.parseInt(fechaPorPartes[1])
                && fechaDeHoy.YEAR == Integer.parseInt(fechaPorPartes[0])){
            holder.mFechaView.setText("Hoy");
        } else {
            holder.mFechaView.setText(fechaPorPartes[2] + "/" + fechaPorPartes[1] + "/" + fechaPorPartes[0]);
        }

        //Filtro para probabilidad de precipitación
        if(holder.mItem.getProb_precipitacion().get("00-24") == null){
            if(holder.mItem.getProb_precipitacion().get("12-24") == null){
                if(holder.mItem.getProb_precipitacion().get("00-12") != null) {
                    holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("00-12") + "%");
                }else{
                    holder.mPrecipitacionView.setText("0%");
                }
            }else{
                if(holder.mItem.getProb_precipitacion().get("12-24") != null) {
                    holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("12-24") + "%");
                }else{
                    holder.mPrecipitacionView.setText("0%");
                }
            }
        }else {
            holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("00-24") + "%");
        }

        //Set de las temperaturas
        holder.mTempMaxView.setText(holder.mItem.getTemperatura().getMaxima()+"º");
        holder.mTempMinView.setText(holder.mItem.getTemperatura().getMinima()+"º");


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickDia(holder.mItem);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mEstadoPrevioView;
        public final TextView mFechaView;
        public final TextView mPrecipitacionView;
        public final TextView mTempMaxView;
        public final TextView mTempMinView;
        public Dia mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mEstadoPrevioView = (ImageView) view.findViewById(R.id.imageViewEstadoPrevio);
            mFechaView = (TextView) view.findViewById(R.id.textViewFecha);
            mPrecipitacionView = (TextView) view.findViewById(R.id.textViewProb_precip);
            mTempMaxView = (TextView) view.findViewById(R.id.textViewThermax);
            mTempMinView = (TextView) view.findViewById(R.id.textViewThermin);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
