package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.Viewholder> {

    private  DBHelper DB  ;
    private Context context;
    private ArrayList<FriendsModel> friendsModelArrayList;

    // Constructor

    public FriendsAdapter(Context context, ArrayList<FriendsModel> friendsModelArrayList) {
        this.context = context;
        this.friendsModelArrayList = friendsModelArrayList;
        DB = new DBHelper(context.getApplicationContext());
    }

    @NonNull
    @Override
    public FriendsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.Viewholder holder, int position) {
        FriendsModel model = friendsModelArrayList.get(position);
        holder.firstNameTv.setText(model.getFirst_Name());
        holder.lastNameTv.setText(model.getLast_Name());
        holder.mobileNoTv.setText(model.getMobile_No());
        holder.emailTv.setText(model.getEmail_Id());
        holder.model=model;
        holder.pos=position;

    }



    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return friendsModelArrayList.size();
    }

    public FriendsModel getItem(int position) {
        return friendsModelArrayList.get(position);
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
         FriendsModel model;
         int pos;
        private TextView firstNameTv,lastNameTv,mobileNoTv,emailTv;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            firstNameTv=  itemView.findViewById(R.id.idTVFirstname);
            lastNameTv= itemView.findViewById(R.id.idTVLastname);
            mobileNoTv= itemView.findViewById(R.id.idTVMobileNo);
            emailTv= itemView.findViewById(R.id.idTVEmail);

            itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo", "onClick: "+ model.getContact_id());
                    Boolean delete = DB.deleteFriend(model.getContact_id());
                    if (delete) {
                        Toast.makeText(context.getApplicationContext(), "Contact Delete successfully", Toast.LENGTH_SHORT).show();
                         friendsModelArrayList.remove(pos);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context.getApplicationContext(), "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
