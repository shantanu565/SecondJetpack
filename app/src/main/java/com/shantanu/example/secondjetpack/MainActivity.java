package com.shantanu.example.secondjetpack;

import android.os.Bundle;
import android.view.View;

import com.shantanu.example.secondjetpack.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {
    private MyViewModel myViewModel;
    private ActivityMainBinding activityMainBinding;
    private CustomAdapter adapter;
    private List<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getUsersVM().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userModels) {
                adapter.setUserModelList(userModels);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new CustomAdapter();
        user = new ArrayList<>();
        adapter.setUserModelList(user);
        activityMainBinding.recyclerView.setAdapter(adapter);
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityMainBinding.addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.insertUserVM(new User("fullmetal", "abc@def.com", "7647354374537"));
            }
        });

        activityMainBinding.clearDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.deleteAllUsersVM();
                adapter.notifyDataSetChanged();
            }
        });

    }



}
