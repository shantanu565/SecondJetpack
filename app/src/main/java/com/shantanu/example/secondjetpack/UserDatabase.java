package com.shantanu.example.secondjetpack;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase userDatabaseInstance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (userDatabaseInstance == null) {
            userDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_db")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration().build();
        }
        return userDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class LoadPreExistingDataInDBAT extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        public LoadPreExistingDataInDBAT(UserDatabase database) {
            userDao = database.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insertUser(new User("fullmetal", "abc@gmail.com","764734673547"));
            return null;
        }
    }
}
