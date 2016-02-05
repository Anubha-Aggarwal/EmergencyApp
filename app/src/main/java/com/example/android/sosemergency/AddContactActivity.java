package com.example.android.sosemergency;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddContactActivity extends Activity {
    public static int Action_request = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }
    public void getContact(View v)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, Action_request);
    }
    @Override
    protected void onActivityResult(int requestCode, int result, Intent data) {
        if ((requestCode == Action_request) && result == RESULT_OK) {
            Uri contactUri = data.getData();
            //Log.i("uri", "answer" + contactUri);
            String[] p = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
            Cursor c = getContentResolver().query(contactUri, p, null, null, null);
            c.moveToFirst();
            int column = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nano=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int picno=c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);
            String Name=c.getString(nano);
            String conno = c.getString(column);
            String path=c.getString(picno);
            Log.i("image", "" + path);
            c.close();
            EditText name = (EditText) findViewById(R.id.Name);
            EditText no = (EditText) findViewById(R.id.PhoneNo);
           // ImageView photo=(ImageView)findViewById(R.id.pic);
            //photo.setImageBitmap(BitmapFactory.decodeFile(path));
            no.setText(conno);
            name.setText(Name);
        }
    }
        public void saveContact(View v)
        {
            EditText name = (EditText) findViewById(R.id.Name);
            EditText no = (EditText) findViewById(R.id.PhoneNo);
            String ConName=name.getText().toString();
            String ConNo=no.getText().toString();
            ContactClass a=new ContactClass(ConName,ConNo);
            DatabaseClass db=new DatabaseClass(this);
            db.addContact(a,this);
            name.setText("");
            no.setText("");
            Toast.makeText(this,"Contact Saved",Toast.LENGTH_LONG).show();

        }

}
