package service;

import dao.AuthorDao;
import dao.CategoryDao;
import exception.AuthorException;
import exception.CategoryException;
import model.Author;
import model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();

    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    public Category getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    public void save(Category category) {
        categoryDao.addCategory(category);
    }

    public void delete(Long id) {
        try {
            categoryDao.deleteCategoryById(id);
        } catch (CategoryException e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        try {
            categoryDao.updateCategory(category);
        } catch (CategoryException e) {
            e.printStackTrace();
        }
    }

    public Category searchCategoreis(String catName) {
        if (categoryDao.findCategoryByName(catName) != null) {
            return categoryDao.findCategoryByName(catName);

        }
        return null;
    }
    public void afficheCategories() {
        for (Category category : getAllCategory()) {
            System.out.println(category);
        }
    }

}
