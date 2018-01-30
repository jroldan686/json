package acdat.jroldan.json;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.util.ArrayList;

public class ListaContactosGsonActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String WEB = "http://192.168.1.20/acceso/contacts.json";
    //public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayList<Contact> contacts;
    ArrayAdapter<Contact> adapter;
    Person person;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);
        lista = (ListView) findViewById(R.id.listView);
        lista.setOnItemClickListener(this);
        contacts = new ArrayList<Contact>();
    }

    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(WEB, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }
        });
    }

    private void mostrar() {
        if (person != null) {
            contacts.clear();
            contacts.addAll(person.getContactos());
            if (adapter == null) {
                adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contacts);
            }
        } else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Móvil: " + contacts.get(position).getTelefono().getMovil(),
                Toast.LENGTH_SHORT).show();
    }
}
