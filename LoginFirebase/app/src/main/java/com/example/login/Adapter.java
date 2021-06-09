package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<User> users;

    public Adapter(Context ctx, List<User> users){
        this.inflater=LayoutInflater.from(ctx);
        this.users=users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.user_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtID.setText(users.get(position).getId());
        holder.txtName.setText(users.get(position).getName());
        holder.txtAge.setText(users.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID, txtName, txtAge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID=itemView.findViewById(R.id.txtID);
            txtName=itemView.findViewById(R.id.txtName);
            txtAge=itemView.findViewById(R.id.txtAge);
        }
    }
}
