package com.marcelo.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private int selectedPosition = -1;

    private Context context;

    public UserAdapter(List<User> users, Context context){
        this.users = users;
        this.context = context;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent, false){
            return new  UserViewHolder(view);
        }
        @Override
                public  void onBindViewHolder(UserViewHolder holder, int position){
            User user = users.get(position);
            holder.idTV.setText(String.valueOf(user.getId()));
            holder.nombreTV.setText(user.getName());
            holder.correoTV.setText(user.getEmail());

            holder.IntemView.setOnClickListener(new View.OnClickListener()){
                public void onclick(View v){
                    setSelectedPosition(position);

                }
            });
        }

        @Override
                public int getItemContent(){
            return users.size();
        }
        public int getSelectedPosition(){
            return selectedPosition;
        }

        public void setSelectedPosition(int selectedPosition){
            this.selectedPosition = selectedPosition;

        }

        class UserViewHolder extends RecyclerView.ViewHolder{
            TextView idTV, nombreTV, correoTV;

            public  UserViewHolder(View itemView){
                super(itemView);
                idTV = itemView.findViewById(R.id.txtItemId);
                nombreTV = itemView.findViewById(R.id.txtNombre);
                correoTV = itemView.findViewById(R.id.txtCorreo);
            }

        }

    }


