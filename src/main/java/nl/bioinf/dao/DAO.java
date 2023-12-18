package nl.bioinf.dao;

import nl.bioinf.model.User;

public interface DAO {
    User getUser(String username, String password);

}
