package acdat.jroldan.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 25/01/18.
 */

public class Contacto {


    @SerializedName("name")
    @Expose
    private String nombre;
    @SerializedName("address")
    @Expose
    private String direccion;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private Telefono telefono;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public Telefono getTelefono() { return telefono; }
    public void setTelefono(Telefono t) {
        this.telefono = t;
    }
    public String toString() {
        return nombre;
    }
}
