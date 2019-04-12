package com.shantanu.example.secondjetpack;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shantanu.example.secondjetpack.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private MyViewModel myViewModel;
    private ActivityMainBinding activityMainBinding;
    private CustomAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        userList = new ArrayList<>();
        adapter = new CustomAdapter(this, userList);
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setUserModelList(userList);
        activityMainBinding.recyclerView.setAdapter(adapter);

        myViewModel.getUsersVM().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userModels) {
                adapter.setUserModelList(userModels);
                adapter.notifyDataSetChanged();
            }
        });

        activityMainBinding.addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.insertUserVM(new User("Ram mohan", "rammohana@gmail.com", "7647354374537"));
                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
            }
        });

        activityMainBinding.clearDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.deleteAllUsersVM();
            }
        });
    }
}
