package nl.bioinf.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabaseWebListener implements ServletContextListener {
@Override
    public void contextInitialized(ServletContextEvent sce) {
        //create database connection
        //store it in the servlet context
        System.out.println("DatabaseWebListener.contextInitialized");
        String url = "jdbc:mysql://localhost:3306/UserAdmin";
        DatabaseConnector.createInstance("url", "username", "password");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //close database connection pool
    }
}
