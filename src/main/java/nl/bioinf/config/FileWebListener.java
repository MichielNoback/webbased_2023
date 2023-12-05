package nl.bioinf.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FileWebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("FileWebListener.contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //ServletContextListener.super.contextDestroyed(sce);
    }
}
