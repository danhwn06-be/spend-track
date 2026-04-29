package com.example.spendtrack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.spendtrack.data.entity.Category;
import com.example.spendtrack.data.repository.CategoryRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends AndroidViewModel {
    private final CategoryRepository repository;
    private final MutableLiveData<List<Category>> allCategories;
    private final ExecutorService executorService;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new CategoryRepository(application);
        allCategories = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();

        loadAllCategories();
    }

    private void loadAllCategories() {
        executorService.execute(() -> {
            List<Category> categories = repository.getAllCategories();
            allCategories.postValue(categories);
        });
    }

    public void insert(Category category) {
        repository.insert(category);
        loadAllCategories();
    }

    public void update(Category category) {
        repository.update(category);
        loadAllCategories();
    }

    public void delete(Category category) {
        repository.delete(category);
        loadAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }
}
