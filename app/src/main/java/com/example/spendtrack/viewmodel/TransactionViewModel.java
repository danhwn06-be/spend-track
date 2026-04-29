package com.example.spendtrack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.spendtrack.data.entity.Transaction;
import com.example.spendtrack.data.repository.TransactionRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepository repository;
    private final MutableLiveData<List<Transaction>> allTransactions;
    private final MutableLiveData<List<Transaction>> transactionsByCategory;
    private final ExecutorService executorService;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = new MutableLiveData<>();
        transactionsByCategory = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();

        loadAllTransactions();
    }

    private void loadAllTransactions() {
        executorService.execute(() -> {
            List<Transaction> transactions = repository.getAllTransactions();
            allTransactions.postValue(transactions);
        });
    }
    public void insert(Transaction transaction) {
        repository.insert(transaction);
        loadAllTransactions();
    }

    public void update(Transaction transaction) {
        repository.update(transaction);
        loadAllTransactions();
    }

    public void delete(Transaction transaction) {
        repository.delete(transaction);
        loadAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<Transaction>> getTransactionsByCategory(int categoryId) {
        executorService.execute(() -> {
            List<Transaction> transactions = repository.getTransactionsByCategory(categoryId);
            transactionsByCategory.postValue(transactions);
        });
        return transactionsByCategory;
    }
}
