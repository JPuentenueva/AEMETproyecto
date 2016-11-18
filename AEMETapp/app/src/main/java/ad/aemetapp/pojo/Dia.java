package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (strict = false)
public class Dia {
    @Attribute
    Date fecha;

    @ElementMap(entry="prob_precipitacion", key="periodo", attribute=true, inline=true, required = false)
    Map<String,String> prob_precipitacion;

    @ElementMap(entry="cota_nieve_prov", key="periodo", attribute=true, inline=true, required = false)
    Map<String,String> cota_nieve_prov;

    @ElementList (inline = true, required = false)
    List<EstadoCielo> estado_cielo;

    @Element
    Temperatura temperatura;

    @Element
    SensacionTermica sens_termica;

    public Dia() {
    }

    public Dia(Date fecha, Map<String, String> prob_precipitacion, Map<String, String> cota_nieve_prov, List<EstadoCielo> estado_cielo, Temperatura temperatura, SensacionTermica sens_termica) {
        this.fecha = fecha;
        this.prob_precipitacion = prob_precipitacion;
        this.cota_nieve_prov = cota_nieve_prov;
        this.estado_cielo = estado_cielo;
        this.temperatura = temperatura;
        this.sens_termica = sens_termica;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Map<String, String> getProb_precipitacion() {
        return prob_precipitacion;
    }

    public void setProb_precipitacion(Map<String, String> prob_precipitacion) {
        this.prob_precipitacion = prob_precipitacion;
    }

    public Map<String, String> getCota_nieve_prov() {
        return cota_nieve_prov;
    }

    public void setCota_nieve_prov(Map<String, String> cota_nieve_prov) {
        this.cota_nieve_prov = cota_nieve_prov;
    }

    public List<EstadoCielo> getEstado_cielo() {
        return estado_cielo;
    }

    public void setEstado_cielo(List<EstadoCielo> estado_cielo) {
        this.estado_cielo = estado_cielo;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public SensacionTermica getSens_termica() {
        return sens_termica;
    }

    public void setSens_termica(SensacionTermica sens_termica) {
        this.sens_termica = sens_termica;
    }
}
