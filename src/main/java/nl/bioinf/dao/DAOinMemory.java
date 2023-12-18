package nl.bioinf.dao;

import nl.bioinf.model.Role;
import nl.bioinf.model.User;

public class DAOinMemory implements DAO{
    @Override
    public User getUser(String username, String password) {
        return new User(username,"henk@example.com", Role.GUEST);
    }
}
