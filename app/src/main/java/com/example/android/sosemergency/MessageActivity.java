package com.example.android.sosemergency;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MessageActivity extends Activity {
    EditText data;
    public  String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        data=(EditText)findViewById(R.id.emessage);
        try
        {
            SharedPreferences sh=getSharedPreferences("com.example.android.sosemergency",MODE_PRIVATE);
           // String def="R.string.title";
             text=sh.getString("messageKey","");
            if(text.equals(""))
            {
                data.setHint("");
            }
            else
                data.setHint(text);

        }
        catch(Exception e)
        {
            Toast.makeText(this,"ErrorOcurred",Toast.LENGTH_LONG).show();
        }
    }
    public void saveMessage(View v)
    {
        SharedPreferences sh=getSharedPreferences("com.example.android.sosemergency",MODE_PRIVATE);
        SharedPreferences.Editor editor=sh.edit();
        String message= data.getText().toString();
        editor.putString("messageKey",message);
        editor.commit();
        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
    }

}
