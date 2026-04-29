package com.example.spendtrack.data.repository;

import android.app.Application;

import com.example.spendtrack.data.dao.CategoryDao;
import com.example.spendtrack.data.database.AppDatabase;
import com.example.spendtrack.data.entity.Category;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private final CategoryDao categoryDao;
    private final ExecutorService executorService;

    public CategoryRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        this.categoryDao = database.categoryDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Category category) {
        executorService.execute(() -> categoryDao.insert(category));
    }

    public void update(Category category) {
        executorService.execute(() -> categoryDao.update(category));
    }

    public void delete(Category category) {
        executorService.execute(() -> categoryDao.delete(category));
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }
}
