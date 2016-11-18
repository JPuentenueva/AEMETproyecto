package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by jmpuentenueva on 15/11/2016.
 */
@Parcel
public class Provincia {
    String nombre;
    Municipios municipio;

    public Provincia() {
    }

    public Provincia(String nombre, Municipios municipios) {
        this.nombre = nombre;
        this.municipio = municipios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Municipios getMunicipios() {
        return municipio;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipio = municipios;
    }
}
