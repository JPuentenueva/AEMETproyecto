package ad.aemetapp.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ad.aemetapp.R;
import ad.aemetapp.interfaces.IOnClickMunicipio;
import ad.aemetapp.pojo.Municipio;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Municipio} and makes a call to the
 * specified {@link IOnClickMunicipio}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MunicipiosAdapter extends RecyclerView.Adapter<MunicipiosAdapter.ViewHolder> {

    private final List<Municipio> mValues;
    private final IOnClickMunicipio mListener;

    public MunicipiosAdapter(List<Municipio> items, IOnClickMunicipio listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_municipio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mMunicipioView.setText(holder.mItem.getNombre());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickMunicipio(holder.mItem);
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
        public final TextView mMunicipioView;
        public Municipio mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMunicipioView = (TextView) view.findViewById(R.id.nombreMunicipio);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
