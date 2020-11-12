package dao;
import exception.BookException;
import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {


    private static AuthorDao authorDao = new AuthorDao();
    private static CategoryDao categoryDao = new CategoryDao();

    public void addBook(Book book) {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into book (title,price,releasedate,description,photoname,productinstock,id_author,id_category)" +
                    " values(?,?,?,?,?,?,?,?)");
            st.setString(1,book.getTitle());
            st.setDouble(2,book.getPrice());
            st.setDate(3, java.sql.Date.valueOf( book.getReleaseDate() ));
            st.setString(4,book.getDescription());
            st.setString(5,book.getPhotoName());
            st.setInt(6,book.getProductInStock());
            st.setLong(7,book.getAuthor().getId());
            st.setLong(8,book.getCategory().getId());
            st.executeUpdate();
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public Book getBookById(Long id) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Book book = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from book where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setId(rs.getLong(1));
                book.setTitle(rs.getString(2));
                book.setPrice(rs.getDouble(3));
                book.setReleaseDate(rs.getDate(4).toLocalDate());
                book.setDescription(rs.getString(5));
                book.setPhotoName(rs.getString(6));
                book.setProductInStock(rs.getInt(7));
                Long author_id= rs.getLong(8);
                book.setAuthor(authorDao.getAuthorById(author_id));
                Long category_id= rs.getLong(9);
                book.setCategory(categoryDao.getCategoryById(category_id));

            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return book;
    }

    public void updateBook(Book book) throws BookException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Book oldBook = getBookById(book.getId());
        if(oldBook==null){
            throw new BookException("Book Not Found");
        }
        if(book.getTitle()!=null){
            oldBook.setTitle(book.getTitle());
        }
        if(book.getPrice()!=null){
            oldBook.setPrice(book.getPrice());
        }
        if(book.getReleaseDate()!=null){
            oldBook.setReleaseDate(book.getReleaseDate());
        }
        if(book.getDescription()!=null){
            oldBook.setDescription(book.getDescription());
        }
        if(book.getPhotoName()!=null){
            oldBook.setPhotoName(book.getPhotoName());
        }
        if(book.getProductInStock()!=null){
            oldBook.setProductInStock(book.getProductInStock());
        }
       if(book.getCategory()!=null && book.getCategory().getId()!=null){
           oldBook.setCategory(book.getCategory());
       }
       if(book.getAuthor()!= null && book.getAuthor().getId() != null){
           oldBook.setAuthor(book.getAuthor());
       }

        try {
            PreparedStatement st=cnx.prepareStatement("update category set title=?,price=?,releasedate=?,description=?" +
                    "photoname=?,productinstock=?,author_id=?,category_id=? where id=?");
           st.setString(1, oldBook.getTitle());
           st.setDouble(2,oldBook.getPrice());
           st.setDate(3, java.sql.Date.valueOf( oldBook.getReleaseDate() ));
           st.setString(4,oldBook.getDescription());
           st.setString(5,oldBook.getPhotoName());
           st.setInt(6,oldBook.getProductInStock());
           st.setLong(7,oldBook.getAuthor().getId());
           st.setLong(8,oldBook.getCategory().getId());
           st.setLong(9,oldBook.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        List<Book> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from book");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book = new Book();
                book.setId(rs.getLong(1));
                book.setTitle(rs.getString(2));
                book.setPrice(rs.getDouble(3));
                book.setReleaseDate(rs.getDate(4).toLocalDate());
                book.setDescription(rs.getString(5));
                book.setPhotoName(rs.getString(6));
                book.setProductInStock(rs.getInt(7));
                Long author_id= rs.getLong(8);
                book.setAuthor(authorDao.getAuthorById(author_id));
                Long category_id= rs.getLong(9);
                book.setCategory(categoryDao.getCategoryById(category_id));
                liste.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public void deleteBookById(Long id) throws BookException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Book book = getBookById(id);
        if(book==null){
            throw new BookException("Book Not Found");
        }
        try {
            PreparedStatement st=cnx.prepareStatement("delete from book where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
