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

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaContactosActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String WEB = "http://192.168.3.57/acceso/contactosmal.json";
    //public static final String WEB = "https://www.portadaalta.mobi/acceso/contactos.json";
    Button btnDescargar;
    ListView lista;
    ArrayList<Contacto> contactos;
    ArrayAdapter<Contacto> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        btnDescargar = (Button) findViewById(R.id.btnDescargar);
        btnDescargar.setOnClickListener(this);
        lista = (ListView) findViewById(R.id.lsvContactos);
        lista.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == btnDescargar)
            descarga( WEB );
    }
    //usar JsonHttpResponseHandler()
    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog. STYLE_SPINNER );
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progreso.dismiss();
                try {
                    contactos = Analisis.analizarContactos(response);
                    Toast.makeText(ListaContactosActivity.this, "Descarga con éxito", Toast.LENGTH_SHORT).show();
                    mostrar();
                } catch (JSONException e) {
                    Toast.makeText(ListaContactosActivity.this,
                            "Error en el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progreso.dismiss();
                Toast.makeText(ListaContactosActivity.this, "Error " + statusCode + ": " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void mostrar() {
        if (contactos != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<Contacto>(this, android.R.layout. simple_list_item_1 , contactos);
                lista.setAdapter(adapter);
            } else {
                adapter.clear();
                adapter.addAll(contactos);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast. LENGTH_SHORT ).show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Móvil: " + contactos.get(position).getTelefono().getMovil(), Toast. LENGTH_SHORT ).show();
    }
}
