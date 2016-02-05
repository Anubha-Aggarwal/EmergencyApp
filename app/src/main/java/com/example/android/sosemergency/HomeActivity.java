package com.example.android.sosemergency;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;


public class HomeActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
    GoogleApiClient mgoogleclient;
    Location location;
    double longi;
    double lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title);
        googleApiClientBuild();
    }

 /*   public void SendMessage(View view) {
/*        SharedPreferences sh = getSharedPreferences("com.example.android.sosemergency", MODE_PRIVATE);
        // String def="R.string.title";
        String text = sh.getString("messageKey", "");
        StringBuffer bf = new StringBuffer();
        bf.append(text);
        bf.append("http://maps.google.com?q=");
        bf.append(lat);
        bf.append(",");
        bf.append(longi);
        DatabaseClass db = new DatabaseClass(this);
        List<ContactClass> l = db.viewContacts();
        int pos = 0;
        while (l.get(pos)!= null) {
           String phone=(l.get(pos).getPhoneNo());
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phone, null, bf.toString(), null, null);
//groovylanguage
        }*/


            //PackageManager pm=getPackageManager();
            //try {
    /*            Uri uri=Uri.parse("smsto:"+"9024789367");
                Intent waIntent = new Intent(Intent.ACTION_SENDTO,uri);
               // waIntent.setType("text/plain");
                //String text = "Help Me "+"http://maps.google.com/?q="+lat+","+longi;

              //  PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                //Check if package exists or not. If not then code
                //in catch block will be called
                waIntent.setPackage("com.whatsapp");

               // waIntent.putExtra(Intent.EXTRA_TEXT, text);
        waIntent.setType("text/plain");
        waIntent.putExtra(Intent.EXTRA_TEXT,"Hii");
               // startActivity(Intent.createChooser(waIntent, "Share with"));

            //} catch (PackageManager.NameNotFoundException e) {
              //  Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                //        .show();
            //}
            startActivity(waIntent);
        }*/
    public void SendMessage(View v)
    {
        SharedPreferences sh=getSharedPreferences("com.example.android.sosemergency",MODE_PRIVATE);
        String text=sh.getString("message", "Help Me");
        String data=text+"http://maps.google.com/?q="+lat+","+longi;
        DatabaseClass db=new DatabaseClass(this);
        List<ContactClass>l=db.viewContacts();
        int pos=0;
        SmsManager manager=SmsManager.getDefault();
        int limit=l.size();
        while(((pos<limit)&&l.get(pos)!=null))
        {
           String phone= l.get(pos).getPhoneNo();
            manager.sendTextMessage(phone,null,data,null,null);
            pos=pos+1;
        }
        Toast.makeText(this,"MessageActivity Sent",Toast.LENGTH_LONG).show();
    }
    public void showMap(View v)
    {
        Intent intent=new Intent(HomeActivity.this,MapsActivity.class);
        startActivity(intent);
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
            Intent intent =new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected synchronized void googleApiClientBuild()
    {
        mgoogleclient=new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
    }
    @Override
    protected  void onStart()
    {
        super.onStart();
        mgoogleclient.connect();
    }
    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(mgoogleclient);
        if (location != null) {
            lat = location.getLatitude();
            longi = location.getLongitude();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
