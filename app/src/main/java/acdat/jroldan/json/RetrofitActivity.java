package acdat.jroldan.json;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    EditText nombreUsuario;
    Button botonDescarga;
    RecyclerView rvRepos;
    private RepositoryAdapter adapter;
    ApiService apiService;
    ArrayList<Repos> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        nombreUsuario = (EditText) findViewById(R.id.edtUserName);
        botonDescarga = (Button) findViewById(R.id.btnDownload);
        rvRepos = (RecyclerView) findViewById(R.id.rcvRepositories);
        adapter = new RepositoryAdapter();
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        rvRepos.addOnItemTouchListener(new RecyclerTouchListener(this, rvRepos,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Toast.makeText(getApplicationContext(), "position " + position + " was clicked!",
                                Toast.LENGTH_SHORT).show();
                        //Uri uri = Uri.parse(adapter.get(position).getUrl());
                        Uri uri = Uri.parse(adapter.get(position).getHtmlUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        if (intent.resolveActivity(getPackageManager()) != null)
                            startActivity(intent);
                        else
                            Toast.makeText(getApplicationContext(), "No hay un navegador",
                                    Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(RetrofitActivity.this,
                                "Long press on position: " + position,
                                Toast.LENGTH_LONG).show();
                    }
                }));

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        Repos client = retrofit.create(Repos.class);

        // Fetch a list of the Github repositories.
        Call<List<Repos>> call = client.reposForUser("fs-opensource");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        if(view == botonDescarga) {
            String username = nombreUsuario.getText().toString();
            if(username.isEmpty())
                showMessage("Debe dar un nombre");
            else {
                Call<ArrayList<Repos>> call = apiService.listRepos(username);
                call.enqueue(new Callback<ArrayList<Repos>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Repos>> call, Response<ArrayList<Repos>> response) {
                        //int statusCode = response.code();
                        //User user = response.body();
                        //quitar cuadro de progreso
                        if(response.isSuccessful()) {
                            repos = response.body();
                            adapter.set(repos);
                            showMessage("Repositorios actualizados ok");
                        }
                        else {
                            StringBuilder message = new StringBuilder();
                            message.append("Error en la descarga: " + response.code());
                            if(response.body() != null)
                                message.append(response.body());
                            if(response.errorBody() != null)
                                message.append(response.errorBody());
                            showMessage(message.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Repos>> call, Throwable t) {
                        //quitar cuadro de progreso
                        if(t != null)
                            showMessage("Fallo en la comunicación: " + t.getMessage());
                    }
                });
            }
        }
    }
}
