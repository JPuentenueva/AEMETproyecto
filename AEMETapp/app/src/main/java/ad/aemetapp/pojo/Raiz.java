package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (name = "root", strict = false)
public class Raiz {
    @Element (name = "elaborado")
    String elaborado;
    @Element (name = "nombre")
    String nombre;
    @Element (name = "provincia")
    String provincia;
    @Element (name = "prediccion")
    Prediccion prediccion;

    public Raiz() {

    }

    public Raiz(String elaborado, String nombre, String provincia, Prediccion prediccion) {
        this.elaborado = elaborado;
        this.nombre = nombre;
        this.provincia = provincia;
        this.prediccion = prediccion;
    }

    public String getElaborado() {
        return elaborado;
    }

    public void setElaborado(String elaborado) {
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
