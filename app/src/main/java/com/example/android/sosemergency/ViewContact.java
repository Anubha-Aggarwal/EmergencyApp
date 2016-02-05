package com.example.android.sosemergency;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ViewContact extends Activity {
    RecyclerView recycle;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        DatabaseClass db=new DatabaseClass(this);
        List<ContactClass> l=new ArrayList<ContactClass>();
        l=db.viewContacts();
        recycle=(RecyclerView)findViewById(R.id.my_recycler_view);
        manager=new LinearLayoutManager(this);
        adapter=new ViewListAdapter(l);
        recycle.setLayoutManager(manager);
        recycle.setAdapter(adapter);
    }


}
