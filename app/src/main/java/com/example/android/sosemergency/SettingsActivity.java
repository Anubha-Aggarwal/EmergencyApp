package com.example.android.sosemergency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
    public void addContact(View v)
    {
        Intent intent=new Intent(SettingsActivity.this,AddContactActivity.class);
        startActivity(intent);
    }
    public void viewContact(View v)
    {
        Intent intent=new Intent(SettingsActivity.this,ViewContact.class);
        startActivity(intent);
    }
    public void delete(View v)
    {
        Intent intent=new Intent(SettingsActivity.this,DeleteActivity.class);
        startActivity(intent);
    }
    public void message(View v)
    {
        Intent intent=new Intent(SettingsActivity.this,MessageActivity.class);
        startActivity(intent);
    }
}
