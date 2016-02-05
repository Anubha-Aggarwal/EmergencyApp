package com.example.android.sosemergency;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 08-07-2015.
 */
public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.ViewHolder>  {
    List<ContactClass> data;
    ViewListAdapter(List<ContactClass> l)
    {
        data=l;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlistlayout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Ename.setText(data.get(position).getName());
        holder.Ephone.setText(data.get(position).getPhoneNo());
    }




    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView TeName;
        TextView TePhone;
        TextView Ename;
        TextView Ephone;
        public ViewHolder(View itemView) {
            super(itemView);
            TeName= (TextView)itemView.findViewById(R.id.secName);
            TePhone=(TextView)itemView.findViewById(R.id.secPhone);
            Ename=(TextView)itemView.findViewById(R.id.EsecName);
            Ephone=(TextView)itemView.findViewById(R.id.EsecPhoneNo);
        }
    }
}
