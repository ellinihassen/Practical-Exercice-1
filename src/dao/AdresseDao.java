package dao;

import com.mysql.jdbc.Statement;
import exception.AdresseException;
import exception.UserException;
import model.Adresse;
import model.Author;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdresseDao {

    public void addAdresse(Adresse adresse) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into adresse(zipCode,street,city,country) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, adresse.getZipCode());
            st.setString(2, adresse.getStreet());
            st.setString(3, adresse.getCity());
            st.setString(4, adresse.getCountry());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                adresse.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating adresse failed, no ID obtained.");
            }

            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteAdresseById(Long id) throws AdresseException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Adresse adresse = getAdresseById(id);
        if (adresse == null) {
            throw new AdresseException("Adresse Not Found To Be Deleted");
        }
        try {
            PreparedStatement st = cnx.prepareStatement("delete from adresse where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Adresse getAdresseById(Long id) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Adresse adresse = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from adresse where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                adresse = new Adresse();
                adresse.setId(rs.getLong(1));
                adresse.setZipCode(rs.getString(2));
                adresse.setStreet(rs.getString(3));
                adresse.setCity(rs.getString(4));
                adresse.setCountry(rs.getString(5));

            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return adresse;
    }

    public void updateAdresse(Adresse adresse) throws AdresseException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Adresse oldAdresse = getAdresseById(adresse.getId());
        if (oldAdresse == null) {
            throw new AdresseException("Adresse Not Found");
        }
        if (adresse.getZipCode() != null) {
            oldAdresse.setZipCode(adresse.getZipCode());
        }
        if (adresse.getStreet() != null) {
            oldAdresse.setStreet(adresse.getStreet());
        }
        if (adresse.getCity() != null) {
            oldAdresse.setCity(adresse.getCity());
        }
        if (adresse.getCountry() != null) {
            oldAdresse.setCountry(adresse.getCountry());
        }

        try {
            PreparedStatement st = cnx.prepareStatement("update adresse set zipcode=?,street=?,city=?,country=? where id=?");
            st.setString(1, oldAdresse.getZipCode());
            st.setString(2, oldAdresse.getStreet());
            st.setString(3, oldAdresse.getCity());
            st.setString(4, oldAdresse.getCountry());
            st.setLong(5, oldAdresse.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
