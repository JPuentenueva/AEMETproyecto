package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (name = "root", strict = false)
public class Raiz {
    @Element
    Date elaborado;
    @Element
    String nombre;
    @Element
    String provincia;
    @Element
    Prediccion prediccion;

    public Raiz() {
    }

    public Raiz(Date elaborado, String nombre, String provincia, Prediccion prediccion) {
        this.elaborado = elaborado;
        this.nombre = nombre;
        this.provincia = provincia;
        this.prediccion = prediccion;
    }

    public Date getElaborado() {
        return elaborado;
    }

    public void setElaborado(Date elaborado) {
        this.elaborado = elaborado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Prediccion getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(Prediccion prediccion) {
        this.prediccion = prediccion;
    }
}
