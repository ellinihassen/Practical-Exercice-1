package dao;

import exception.CategoryException;
import model.Author;
import model.Category;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {


    public void addCategory(Category category) {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into category(name) values(?)");
            st.setString(1, category.getName());
            st.executeUpdate();
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void updateCategory(Category category) throws CategoryException {
        Connection cnx = SingletonConnection.getConnection();
        Category oldCategory = getCategoryById(category.getId());
        if (oldCategory == null) {
            throw new CategoryException("Category Not Found");
        }
        if (category.getName() != null) {
            oldCategory.setName(category.getName());
        }

        try {
            PreparedStatement st = cnx.prepareStatement("update category set name=? where id=?");
            st.setString(1, oldCategory.getName());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteCategoryById(Long id) throws CategoryException {
        Connection cnx = SingletonConnection.getConnection();
        Category category = getCategoryById(id);
        if (category == null) {
            throw new CategoryException("Category Not Found");
        }
        try {
            PreparedStatement st = cnx.prepareStatement("delete from category where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Category> getAllCategory() {
        Connection cnx = SingletonConnection.getConnection();
        List<Category> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong(1));
                category.setName(rs.getString(2));
                liste.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Category getCategoryById(Long id) {
        Connection cnx = SingletonConnection.getConnection();
        Category category = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from category where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getLong(1));
                category.setName(rs.getString(2));

            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return category;
    }


    public Category findCategoryByName(String catName) {
        for (Category category : getAllCategory()) {
            if (category.getName().equals(catName)) {
                return category;
            }
        }
        return null;
    }
}
