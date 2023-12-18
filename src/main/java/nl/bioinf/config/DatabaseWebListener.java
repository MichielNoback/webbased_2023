package nl.bioinf.config;

import nl.bioinf.dao.DAO;
import nl.bioinf.dao.DAOinMemory;
import nl.bioinf.dao.DAOmySQL;
import nl.bioinf.noback.db_utils.DbCredentials;
import nl.bioinf.noback.db_utils.DbUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class DatabaseWebListener implements ServletContextListener {
    private static DAO dao;

    public static DAO getDao() {
        return dao;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    //what data layer are we going to use?
    String dataLayerType = (String)sce.getServletContext().getInitParameter("data_layer_type");
    //System.out.println("Data Layer Type:" + dataLayerType);

    DatabaseWebListener.dao = getDAO(dataLayerType);


    //create database connection
        //store it in the servlet context
//        System.out.println("DatabaseWebListener.contextInitialized");
//        String url = "jdbc:mysql://localhost:3306/UserAdmin";
//        DatabaseConnector.createInstance("url", "username", "password");
    }

    private DAO getDAO(String dataLayerType) {
        DAO dao = null;
        if (dataLayerType.equals("mysql")) {
            DbUser dbUser = null;
            try {
                dbUser = DbCredentials.getMySQLuser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            String user = dbUser.getUserName();
            String passWrd = dbUser.getDatabasePassword();
            String host = dbUser.getHost();
            String dbName = dbUser.getDatabaseName();

            System.out.println("DB user: " + user);
            System.out.println("host: " + host);
            System.out.println("dbName: " + dbName);
            String url = "jdbc:mysql://" + host + ":3306/" + dbName + "?useSSL=false";
            System.out.println("url = " + url);
            dao = new DAOmySQL(url, user, passWrd);

        } else if (dataLayerType.equals("in_memory")) {
            dao = new DAOinMemory();
        } else {
            throw new IllegalArgumentException("Unknown data layer type: " + dataLayerType);
        }
        return dao;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //close database connection pool
    }
}
