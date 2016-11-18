package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (strict = false)
public class EstadoCielo {
    @Attribute
    String periodo;

    @Attribute (required = false)
    String descripcion;

    @Text (required = false)
    String numImg;

    public EstadoCielo() {
    }

    public EstadoCielo(String periodo, String descripcion, String numImg) {
        this.periodo = periodo;
        this.descripcion = descripcion;
        this.numImg = numImg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNumImg() {
        return numImg;
    }

    public void setNumImg(String numImg) {
        this.numImg = numImg;
    }
}
