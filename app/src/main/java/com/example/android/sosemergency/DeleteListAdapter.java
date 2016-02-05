package com.example.android.sosemergency;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by User on 09-07-2015.
 */
public class DeleteListAdapter extends RecyclerView.Adapter<DeleteListAdapter.ViewHolder> implements View.OnClickListener {
    List<ContactClass> data;
    Context context;
    DeleteListAdapter(List<ContactClass> l, Context c)
    {
        data=l;
        context=c;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlistlayout,parent,false);
        v.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        TextView Ename;
        TextView Ephone;
        Ename=(TextView)v.findViewById(R.id.EsecName);
        Ephone=(TextView)v.findViewById(R.id.EsecPhoneNo);
        String Name=Ename.getText().toString();
        String Phone=Ephone.getText().toString();
        ContactClass a=new ContactClass(Name,Phone);
        Toast t=Toast.makeText(context,"Deleted", Toast.LENGTH_LONG);
        t.show();
        int pos=v.getVerticalScrollbarPosition();
        DatabaseClass db=new DatabaseClass(context);
        db.delete(a);
        //Ename.setText("");
       // Ephone.setText("");
        //TextView TeName= (TextView)v.findViewById(R.id.secName);
        //TextView TePhone=(TextView)v.findViewById(R.id.secPhone);
        //TeName.setText("");
        //TePhone.setText("");
        data.remove(pos);
        notifyDataSetChanged();
        //notifyItemRemoved(pos);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameText;
        TextView TePhone;
        TextView Ename;
        TextView Ephone;
        public ViewHolder(View itemView) {
            super(itemView);
            userNameText= (TextView)itemView.findViewById(R.id.secName);
            TePhone=(TextView)itemView.findViewById(R.id.secPhone);
            Ename=(TextView)itemView.findViewById(R.id.EsecName);
            Ephone=(TextView)itemView.findViewById(R.id.EsecPhoneNo);
        }
    }

}
