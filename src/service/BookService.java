package service;


import dao.BookDao;
import exception.AuthorException;
import exception.BookException;
import model.Book;
import model.Category;


import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final BookDao bookDao = new BookDao();

    public List<Book> getAllBooks() {
        try {
            return bookDao.getAllBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Book getBookById(Long id) {
        try {
            return bookDao.getBookById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(Book book) {
        bookDao.addBook(book);
    }

    public void delete(Long id) {
        try {
            bookDao.deleteBookById(id);
        } catch (BookException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Book book) {
        try {
            bookDao.updateBook(book);
        } catch (BookException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void afficheBooks() {
        for (Book book : getAllBooks()) {
            System.out.println(book);
        }
    }
}
