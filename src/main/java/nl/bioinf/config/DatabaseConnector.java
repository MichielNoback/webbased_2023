package nl.bioinf.config;

public class DatabaseConnector {
    //1: a single private static instance of the class
    private static DatabaseConnector databaseConnector;

    //2: a private constructor
    private DatabaseConnector(String url, String username, String password) {
        //do some database connection stuff
    }

    //3a: create the instance in a static block
    public static void createInstance(String url, String username, String password) {
        databaseConnector = new DatabaseConnector(url, username, password);
    }

    //3b: a public static method that returns the instance of the class
    public static DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            //databaseConnector = new DatabaseConnector(url, username, password);
            throw new IllegalStateException("DatabaseConnector not initialized");
        }
        return databaseConnector;
    }

}
