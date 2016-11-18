package ad.aemetapp.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ad.aemetapp.R;
import ad.aemetapp.interfaces.IOnClickProvincia;
import ad.aemetapp.pojo.Provincia;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Provincia} and makes a call to the
 * specified {@link IOnClickProvincia}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProvinciasAdapter extends RecyclerView.Adapter<ProvinciasAdapter.ViewHolder> {

    private final List<Provincia> mValues;
    private final IOnClickProvincia mListener;

    public ProvinciasAdapter(List<Provincia> items, IOnClickProvincia listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_provincia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mProvinciaView.setText(holder.mItem.getNombre());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickProvincia(holder.mItem);
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
        public final TextView mProvinciaView;
        public Provincia mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mProvinciaView = (TextView) view.findViewById(R.id.nombreProvincia);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
