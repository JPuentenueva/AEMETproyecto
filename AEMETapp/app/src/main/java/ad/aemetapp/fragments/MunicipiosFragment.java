package ad.aemetapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import ad.aemetapp.R;
import ad.aemetapp.interfaces.IOnClickMunicipio;
import ad.aemetapp.pojo.Municipios;
import ad.aemetapp.pojo.Provincia;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IOnClickMunicipio}
 * interface.
 */
public class MunicipiosFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private IOnClickMunicipio mListener;
    TextView nombreProvincia;
    Provincia provincia;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MunicipiosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MunicipiosFragment newInstance(int columnCount) {
        MunicipiosFragment fragment = new MunicipiosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            Municipios listaMunicipios = Parcels.unwrap(getArguments().getParcelable("municipios"));
            if(getArguments().getString("provincia") == null){
                provincia = new Provincia("Búsqueda", listaMunicipios);
            } else {
                provincia = new Provincia(getArguments().getString("provincia"), listaMunicipios);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_municipio_list, container, false);
        nombreProvincia = (TextView) view.findViewById(R.id.provinciaActual);
        nombreProvincia.setText(provincia.getNombre());

        // Set the adapter
        //Al no tener unicamente un List en fragment_provincia_list hay que borrar el if
        Context context = view.getContext();
        //IMPORTANTE findViewById del Recycler
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listMunicipios);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new MunicipiosAdapter(provincia.getMunicipios().getMunicipios(), mListener));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnClickMunicipio) {
            mListener = (IOnClickMunicipio) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
