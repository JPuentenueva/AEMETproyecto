package ad.aemetapp.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ad.aemetapp.R;
import ad.aemetapp.interfaces.IOnClickDia;
import ad.aemetapp.pojo.Dia;

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

    public DiasAdapter(List<Dia> items, IOnClickDia listener) {
        mValues = items;
        mListener = listener;
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

        Calendar fechaDelDia = Calendar.getInstance();
        Calendar fechaDeHoy = Calendar.getInstance();
        fechaDelDia.setTime(holder.mItem.getFecha());

        //Filtro para determinar si el dia es Hoy
        if(fechaDeHoy.DAY_OF_MONTH == fechaDelDia.DAY_OF_MONTH
                && fechaDeHoy.MONTH == fechaDelDia.MONTH
                && fechaDeHoy.YEAR == fechaDelDia.YEAR){
            holder.mFechaView.setText("Hoy");
        } else {
            holder.mFechaView.setText(fechaDelDia.DAY_OF_MONTH + "/" + fechaDelDia.MONTH + "/" + fechaDelDia.YEAR);
        }

        //Filtro para probabilidad de precipitación
        if(holder.mItem.getProb_precipitacion().get("00-24") == null){
            if(holder.mItem.getProb_precipitacion().get("00-12") == null){
                holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("12-24")+"%");
            }else{
                holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("00-12")+"%");
            }
        }else {
            holder.mPrecipitacionView.setText(holder.mItem.getProb_precipitacion().get("00-24") + "%");
        }

        //Set de las temperaturas
        holder.mTempMaxView.setText(holder.mItem.getTemperatura().getMaxima());
        holder.mTempMaxView.setText(holder.mItem.getTemperatura().getMinima());


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
        public final TextView mFechaView;
        public final TextView mPrecipitacionView;
        public final TextView mTempMaxView;
        public final TextView mTempMinView;
        public Dia mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
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
