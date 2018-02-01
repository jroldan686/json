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
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaContactosGsonActivity extends Activity implements View.OnClickListener {
    //public static final String WEB = "http://192.168.3.57/acceso/contacts.json";
    public static final String WEB = "https://www.portadaalta.mobi/acceso/contacts.json";
    Button boton;
    ListView lista;
    ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos_gson);
        boton = findViewById(R.id.btnDescargar);
        boton.setOnClickListener(this);
        lista = findViewById(R.id.lsvContactos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Toast.makeText(
                        ListaContactosGsonActivity.this,
                        "Móvil: " + ((Contacto)adapter.getItemAtPosition(position)).getTelefono().getMovil(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<Contacto>());
        lista.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(WEB);
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progreso.dismiss();

                try {
                    Gson gson = new GsonBuilder().create();
                    Person personas = gson.fromJson(String.valueOf(response), Person.class);
                    adapter.clear();
                    ArrayList<Contacto> contactos = personas.getContactos();
                    adapter.addAll(contactos);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error en Gson: " + statusCode + "\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progreso.dismiss();
                Toast.makeText(getApplicationContext(), "Error: " + statusCode + "\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
