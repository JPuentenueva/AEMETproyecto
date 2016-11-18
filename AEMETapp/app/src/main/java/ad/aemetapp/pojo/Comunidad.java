package ad.aemetapp.pojo;

import org.parceler.Parcel;

/**
 * Created by jmpuentenueva on 15/11/2016.
 */
@Parcel
public class Comunidad {
    String nombre;
    Provincias provincias;

    public Comunidad() {
    }

    public Comunidad(Provincias provincias) {
        this.provincias = provincias;
    }

    public Comunidad(String nombre, Provincias provincias) {
        this.nombre = nombre;
        this.provincias = provincias;
    }

    public Provincias getProvincias() {
        return provincias;
    }

    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }
}
