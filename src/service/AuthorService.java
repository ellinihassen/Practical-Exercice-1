package service;

import dao.AuthorDao;

import exception.AuthorException;
import exception.UserException;
import model.Author;
import model.User;


import java.sql.SQLException;
import java.util.List;

public class AuthorService {
    private final AuthorDao authorDao = new AuthorDao();

    public List<Author> getAllAuthor() {
        try {
            return authorDao.getAllAuthor();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Author getAuthorById(Long id) {
        try {
            return authorDao.getAuthorById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(Author author) {
        try {
            authorDao.addAuthor(author);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            authorDao.deleteAuthorById(id);
        } catch (AuthorException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Author author) {
        try {
            authorDao.updateAuthor(author);
        } catch (AuthorException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Author searchAuthor(String name,String lastName) {
        try {
            return authorDao.findAuthorByName(name,lastName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
