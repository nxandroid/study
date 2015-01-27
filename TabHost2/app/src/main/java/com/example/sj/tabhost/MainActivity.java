package com.example.sj.tabhost;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        TabSpec spec1 = tabHost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("tab 1");

        TabSpec spec2 = tabHost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("tab 2");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);

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
