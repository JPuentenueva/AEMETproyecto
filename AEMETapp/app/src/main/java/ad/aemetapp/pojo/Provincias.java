package ad.aemetapp.pojo;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by jmpuentenueva on 15/11/2016.
 */
@Parcel
public class Provincias {
    List<Provincia> provincias;

    public Provincias() {
    }

    public Provincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }
}
