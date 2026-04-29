package com.example.spendtrack.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.spendtrack.data.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    List<Transaction> getAllTransactions();

    @Query("SELECT * FROM transactions WHERE category_id = :categoryId ORDER BY date DESC")
    List<Transaction> getTransactionsByCategory(int categoryId);
}
