package com.example.sj.volleytest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    TextView mTextView;
    ImageView iv;

    private ProgressDialog pDialog;
    private static String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;
    private Button btnPost, btnGetJson, btnJsonParsing, btnGetJsonArray, btnJsonArrayParsing;
    private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";
    // json array response url
    private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";
    RequestQueue rq;



    ImageRequest ir = new ImageRequest("http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif", new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
            iv.setImageBitmap(response);

        }
    }, 0, 0, null, null);

    StringRequest postReq = new StringRequest(Request.Method.POST, "http://httpbin.org/post", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mTextView.setText(response); // We set the response data in the TextView
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error ["+error+"]");
        }
    }) ;


    StringRequest GetJsonReq = new StringRequest(Request.Method.GET, "http://api.androidhive.info/volley/person_object.json", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mTextView.setText(response); // We set the response data in the TextView
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error ["+error+"]");
        }
    }) ;

    StringRequest GetJsonArrayReq = new StringRequest(Request.Method.GET, "http://api.androidhive.info/volley/person_array.json", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            mTextView.setText(response); // We set the response data in the TextView
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error ["+error+"]");
        }
    }) ;

    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
            urlJsonObj, null, new Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            Log.d(TAG, response.toString());

            try {
                // Parsing json object response
                // response will be a json object
                String name = response.getString("name");
                String email = response.getString("email");
                JSONObject phone = response.getJSONObject("phone");
                String home = phone.getString("home");
                String mobile = phone.getString("mobile");

                jsonResponse = "";
                jsonResponse += "Name: " + name + "\n\n";
                jsonResponse += "Email: " + email + "\n\n";
                jsonResponse += "Home: " + home + "\n\n";
                jsonResponse += "Mobile: " + mobile + "\n\n";

                mTextView.setText(jsonResponse);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
            hidepDialog();
        }
    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),
                    error.getMessage(), Toast.LENGTH_SHORT).show();
            // hide the progress dialog
            hidepDialog();
        }
    });


    JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());

                    try {
                        // Parsing json array response
                        // loop through each json object
                        jsonResponse = "";
                        for (int i = 0; i < response.length(); i++) {

                            JSONObject person = (JSONObject) response
                                    .get(i);

                            String name = person.getString("name");
                            String email = person.getString("email");
                            JSONObject phone = person
                                    .getJSONObject("phone");
                            String home = phone.getString("home");
                            String mobile = phone.getString("mobile");

                            jsonResponse += "Name: " + name + "\n\n";
                            jsonResponse += "Email: " + email + "\n\n";
                            jsonResponse += "Home: " + home + "\n\n";
                            jsonResponse += "Mobile: " + mobile + "\n\n\n";

                        }

                        mTextView.setText(jsonResponse);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                    hidepDialog();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),
                    error.getMessage(), Toast.LENGTH_SHORT).show();
            hidepDialog();
        }
    });



    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void PostRequest() {
        rq.add(postReq);
    }
    private void JsonRequest() {
        rq.add(GetJsonReq);
    }
    private void JsonParsing() {
        rq.add(jsonObjReq);
    }
    private void JsonArrayRequest() {
        rq.add(GetJsonArrayReq);
    }
    private void JsonArrayParsing() {
        rq.add(req);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetView();
        rq = Volley.newRequestQueue(this);
        GetImageRequest();

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostRequest();
            }
        });
        btnGetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonRequest();
            }
        });
        btnJsonParsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonParsing();

            }
        });
        btnGetJsonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayRequest();
            }
        });
        btnJsonArrayParsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayParsing();
            }
        });



        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    private void SetView() {
        mTextView = (TextView) findViewById(R.id.Text);
        iv = (ImageView) findViewById(R.id.imageView);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnGetJson = (Button) findViewById(R.id.btnGetJson);
        btnJsonParsing = (Button) findViewById(R.id.btnJsonParsing);
        btnGetJsonArray = (Button) findViewById(R.id.btnGetJsonArray);
        btnJsonArrayParsing = (Button) findViewById(R.id.btnJsonArrayParsing);

    }

    private void GetImageRequest() {
        rq.add(ir);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
