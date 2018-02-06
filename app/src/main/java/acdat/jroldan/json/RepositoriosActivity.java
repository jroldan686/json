package acdat.jroldan.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RepositoriosActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombreUsuario;
    Button botonDescarga;
    RecyclerView rvRepos;
    private RepositoryAdapter adapter;
    private ArrayList<Repo> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios);
        nombreUsuario = (EditText) findViewById(R.id.editText);
        botonDescarga = (Button) findViewById(R.id.button);
        botonDescarga.setOnClickListener(this);
        rvRepos = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RepositoryAdapter(this);
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        //manage click
    }

    @Override
    public void onClick(View view) {

    }
}
