package com.example.sj.asynctask;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity implements DataSend {

    @Override
    public void sendData(String str) {
        // TODO Auto-generated method stub
        TextView tv1 = (TextView)findViewById(R.id.TextView1);
        tv1.setText(str);



    }

    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute("80", "90", "100", "110");


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


    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        DataSend dataSend;
        Activity mActivity;
        public MyAsyncTask(Activity activity) {
            mActivity = activity;

            //dataSend = (DataSend)activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String sum = "";

            if(params != null){
                for(String s : params){
                    sum += s;
                }
            }
            return sum;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result != null){

                TextView tv1 = (TextView)mActivity.findViewById(R.id.TextView1);
                tv1.setText(result);



                //dataSend.sendData(result);
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }



}
