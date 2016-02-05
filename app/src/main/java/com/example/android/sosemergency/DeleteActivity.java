package com.example.android.sosemergency;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DeleteActivity extends Activity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        recyclerView= (RecyclerView)findViewById(R.id.delete_recycler_view);
        DatabaseClass db=new DatabaseClass(this);
        List<ContactClass> l =new ArrayList<>();
        l=db.viewContacts();
        adapter=new DeleteListAdapter(l,this);
        recyclerView.setAdapter(adapter);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

}
