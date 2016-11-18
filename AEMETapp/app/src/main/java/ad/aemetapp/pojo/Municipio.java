package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by jmpuentenueva on 14/11/2016.
 */
@Parcel
@Root (name = "municipio")
public class Municipio {
    @Element (name = "cpro")
    String cpro;
    @Element (name = "cmun")
    String cmun;
    @Element
    String dc;
    @Element (name = "nombre")
    String nombre;

    public Municipio() {

    }

    public Municipio(String nombre){
        this.nombre = nombre;
    }

    public Municipio(String cpro, String cmun, String dc, String nombre) {
        this.cpro = cpro;
        this.cmun = cmun;
        this.nombre = nombre;
    }

    public String getCpro() {
        return cpro;
    }

    public void setCpro(String cpro) {
        this.cpro = cpro;
    }

    public String getCmun() {
        return cmun;
    }

    public void setCmun(String cmun) {
        this.cmun = cmun;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
