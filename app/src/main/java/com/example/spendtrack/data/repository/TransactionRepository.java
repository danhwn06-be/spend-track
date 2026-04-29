package com.example.spendtrack.data.repository;

import android.app.Application;

import com.example.spendtrack.data.dao.TransactionDao;
import com.example.spendtrack.data.database.AppDatabase;
import com.example.spendtrack.data.entity.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {

    private final TransactionDao transactionDao;
    private final ExecutorService executorService;

    public TransactionRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        this.transactionDao = database.transactionDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Transaction transaction) {
        executorService.execute(() -> transactionDao.insert(transaction));
    }

    public void update(Transaction transaction) {
        executorService.execute(() -> transactionDao.update(transaction));
    }

    public void delete(Transaction transaction) {
        executorService.execute(() -> transactionDao.delete(transaction));
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    public List<Transaction> getTransactionsByCategory(int categoryId) {
        return transactionDao.getTransactionsByCategory(categoryId);
    }
}