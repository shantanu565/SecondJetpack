package com.shantanu.example.secondjetpack;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private LiveData<List<User>> userList;
    private UserDao userDao;

    public UserRepository(Context context) {
        UserDatabase userDatabase = UserDatabase.getInstance(context.getApplicationContext());
        userDao = userDatabase.userDao();
        userList = userDao.getAllUsers();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public void insertUser(User user) {
        new InsertUserAT(userDao).execute(user);
    }

    public void deleteAllUsers() {
        new DeleteAllUsersAT(userDao).execute();
    }

    private static class InsertUserAT extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertUserAT(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... userModels) {
            userDao.insertUser(userModels[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAT extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        public DeleteAllUsersAT(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

}
