package service;


import dao.AdresseDao;
import exception.AdresseException;
import exception.AuthorException;
import model.Adresse;
import model.Author;

import java.sql.SQLException;

public class AdresseService {

    private final AdresseDao adresseDao= new AdresseDao();

    public void save(Adresse adresse)  {
        try {
            adresseDao.addAdresse(adresse);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete(Long id)  {
        try {
            adresseDao.deleteAdresseById(id);
        } catch (AdresseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update(Adresse adresse)  {
        try {
            adresseDao.updateAdresse(adresse);
        } catch (AdresseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
