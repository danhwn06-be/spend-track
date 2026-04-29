package com.example.spendtrack.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.spendtrack.data.dao.CategoryDao;
import com.example.spendtrack.data.dao.TransactionDao;
import com.example.spendtrack.data.entity.Category;
import com.example.spendtrack.data.entity.Transaction;

@Database(entities = {Category.class, Transaction.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract TransactionDao transactionDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "spend_track_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
