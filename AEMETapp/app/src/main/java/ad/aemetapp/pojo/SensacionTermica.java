package ad.aemetapp.pojo;

import org.parceler.Parcel;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jose on 13/11/2016.
 */
@Parcel
@Root (strict = false)
public class SensacionTermica {
    @Element
    String maxima;

    @Element
    String minima;

    public SensacionTermica() {
    }

    public SensacionTermica(String maxima, String minima) {
        this.maxima = maxima;
        this.minima = minima;
    }

    public String getMaxima() {
        return maxima;
    }

    public void setMaxima(String maxima) {
        this.maxima = maxima;
    }

    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

}
