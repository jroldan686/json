package acdat.jroldan.json;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class PrimitivaActivity extends Activity implements View.OnClickListener{
    public static final String TAG = "MyTag";
    public static final String WEB = "http://192.168.3.57/acceso/primitivamal.json";
    //public static final String WEB = "https://portadaalta.mobi/acceso/primitiva.json";
    Button mBtnDescargar;
    TextView mTxvDatos;
    RequestQueue mRequestQueue;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_primitiva );
        mBtnDescargar = (Button) findViewById(R.id.btnDescargar);
        mBtnDescargar.setOnClickListener(this);
        mTxvDatos = (TextView) findViewById(R.id.txvDatos);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        progressDialog = new ProgressDialog(this);
    }
    @Override
    public void onClick(View view) {
        if (view == mBtnDescargar) {
            progressDialog.show();
            descarga();
        }
    }
    private void descarga() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, WEB, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {
                            mTxvDatos.setText(Analisis.analizarPrimitiva(response));
                        } catch (JSONException e) {
                            Toast.makeText(PrimitivaActivity.this,
                                    "Error en el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(PrimitivaActivity.this,
                                "Error en la descarga: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Set the tag on the request.
        jsObjRequest.setTag( TAG );
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}
