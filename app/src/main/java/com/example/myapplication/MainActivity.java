package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper DB=new DBHelper(MainActivity.this);
    private RecyclerView friendsRV;

    // Arraylist for storing data
    private ArrayList<FriendsModel> friendsModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Friend List");
        friendsRV = findViewById(R.id.idRVFriends);
        FloatingActionButton fab = findViewById(R.id.fab);
        friendsModelArrayList = new ArrayList<>();
        DB = new DBHelper(this);
        Cursor cursor=DB.selectAllFriend();
        if(cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                do{
                    friendsModelArrayList.add(new FriendsModel( cursor.getString(0).toString(),
                            cursor.getString(1).toString(),
                            cursor.getString(2).toString(),
                            cursor.getString(3).toString(),
                            cursor.getString(4).toString()
                            ));
                }while (cursor.moveToNext());

            }

        }

        FriendsAdapter courseAdapter = new FriendsAdapter(this, friendsModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        friendsRV.setLayoutManager(linearLayoutManager);
        friendsRV.setAdapter(courseAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), NewFriends.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        friendsModelArrayList = new ArrayList<>();
        Cursor cursor = DB.selectAllFriend();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    friendsModelArrayList.add(new FriendsModel(cursor.getString(0).toString(),
                            cursor.getString(1).toString(),
                            cursor.getString(2).toString(),
                            cursor.getString(3).toString(),
                            cursor.getString(4).toString()
                    ));
                } while (cursor.moveToNext());

            }
        }
        FriendsAdapter courseAdapter = new FriendsAdapter(this, friendsModelArrayList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        friendsRV.setLayoutManager(linearLayoutManager);
        friendsRV.setAdapter(courseAdapter);
    }
}