package acdat.jroldan.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreacionJsonActivity extends Activity implements View.OnClickListener {
    public static final String WEB = "http://www.alejandrosuarez.es/feed/";
    //public static final String WEB = "http://192.168.2.110/acceso/alejandro.rss";
    public static final String RESULTADO_JSON = "resultado.json";
    public static final String RESULTADO_GSON = "resultado_gson.json";
    public static final String TEMPORAL = "alejandro.xml";
    ArrayList<Noticia> noticias;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_json);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == boton)
            descarga(WEB, TEMPORAL);
    }
    //obtener el rss y escribir los ficheros
    private void descarga(String web, String temporal) {

    }
}
