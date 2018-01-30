package acdat.jroldan.json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPrimitiva, btnListaContactos;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrimitiva = (Button)findViewById(R.id.btnPrimitiva);
        btnPrimitiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, PrimitivaActivity.class);
                startActivity(i);
            }
        });

        btnListaContactos = (Button)findViewById(R.id.btnListaContactos);
        btnListaContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, ListaContactosActivity.class);
                startActivity(i);
            }
        });
    }
}
