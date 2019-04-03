package com.shantanu.example.secondjetpack;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MyViewModel extends AndroidViewModel {

    private LiveData<List<User>> userList;
    private UserRepository userRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userList = userRepository.getUserList();
    }

    public void insertUserVM(User user) {
        userRepository.insertUser(user);
    }

    public void deleteAllUsersVM() {
        userRepository.deleteAllUsers();
    }

    public LiveData<List<User>> getUsersVM() {
        return userList;
    }
}
