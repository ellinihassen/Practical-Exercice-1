package dao;

import exception.AuthorException;
import model.Author;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {


    public void addAuthor(Author author) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into author(name,lastName) values(?,?)");
            st.setString(1, author.getName());
            st.setString(2, author.getLastName());
            st.executeUpdate();
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void updateAuthor(Author author) throws AuthorException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Author oldAuthor = getAuthorById(author.getId());
        if(oldAuthor==null){
            throw new AuthorException("Author Not Found To Be Updated");
        }
        if(author.getName()!=null){
            oldAuthor.setName(author.getName());
        }
        if(author.getLastName()!=null){
            oldAuthor.setLastName(author.getLastName());
        }

        try {
            PreparedStatement st=cnx.prepareStatement("update author set name=?,lastName=? where id=?");
            st.setString(1, oldAuthor.getName());
            st.setString(2, oldAuthor.getLastName());
            st.setLong(3, oldAuthor.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void deleteAuthorById(Long id) throws AuthorException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Author author = getAuthorById(id);
        if(author==null){
            throw new AuthorException("Author Not Found To Be Deleted");
        }
        try {
            PreparedStatement st=cnx.prepareStatement("delete from author where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public List<Author> getAllAuthor() throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        List<Author> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from author");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getLong(1));
                author.setName(rs.getString(2));
                author.setLastName(rs.getString(3));

                liste.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }


    public Author getAuthorById(Long id) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Author author=null;
        try {
            PreparedStatement ps=cnx.prepareStatement("select * from author where id=?");
            ps.setLong(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                author=new Author();
                author.setId(rs.getLong(1));
                author.setName(rs.getString(2));
                author.setLastName(rs.getString(3));

            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return author;
    }

    public Author findAuthorByName(String name, String lastName) throws SQLException {
        for (Author author : getAllAuthor()) {
            if(author.getName().equals(name) && author.getLastName().equals(lastName)) {
                return author;
            }
        }
        return null;
    }
}
