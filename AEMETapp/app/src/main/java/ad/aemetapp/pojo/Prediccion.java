package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (strict = false)
public class Prediccion {
    @ElementList
    List<Dia> dia;

    public Prediccion() {
    }

    public Prediccion(List<Dia> dia) {
        this.dia = dia;
    }

    public List<Dia> getDias() {
        return dia;
    }

    public void setDia(List<Dia> dia) {
        this.dia = dia;
    }
}
