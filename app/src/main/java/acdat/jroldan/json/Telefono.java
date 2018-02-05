package acdat.jroldan.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 25/01/18.
 */

public class Telefono {

    @SerializedName("home")
    @Expose
    private String casa;
    @SerializedName("mobile")
    @Expose
    private String movil;
    @SerializedName("work")
    @Expose
    private String trabajo;

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }
}
