package acdat.jroldan.json;

import java.util.ArrayList;

/**
 * Created by usuario on 30/01/18.
 */

class Person {

    private ArrayList<Contacto> contactos;
    private ArrayList<Telefono> telefonos;

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
