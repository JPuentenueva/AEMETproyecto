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
@Root (name = "municipios")
public class Municipios {
    @ElementList (name = "municipio", inline = true)
    List<Municipio> municipio;

    public Municipios() {
    }

    public Municipios(List<Municipio> municipio) {
        this.municipio = municipio;
    }

    public List<Municipio> getMunicipios() {
        return municipio;
    }

    public void setMunicipios(List<Municipio> municipio) {
        this.municipio = municipio;
    }
}
