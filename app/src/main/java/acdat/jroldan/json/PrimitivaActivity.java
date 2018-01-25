package acdat.jroldan.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;

public class PrimitivaActivity extends Activity implements View.OnClickListener{
    public static final String TAG = "MyTag";
    public static final String WEB = "http://192.168.1.20/acceso/primitiva.json";
    //public static final String WEB = "https://portadaalta.mobi/acceso/primitiva.json";
    Button mButton;
    TextView mTextView;
    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_primitiva );
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textView);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
    }
    @Override
    public void onClick(View view) {
        if (view == mButton)
            descarga();
    }
    private void descarga() {


/*
        // Set the tag on the request.
        jsObjRequest.setTag(TAG);
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
*/
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}
