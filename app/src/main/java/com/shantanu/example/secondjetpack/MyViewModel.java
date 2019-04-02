package com.shantanu.example.secondjetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

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
