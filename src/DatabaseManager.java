import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String dbUrl, String user, String password) throws Exception {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }
    //selecty do wszystkich baz
    public ResultSet getCoffees() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM coffee";
        return stmt.executeQuery(query);
    }

    public ResultSet gettea() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM tea";
        return stmt.executeQuery(query);
    }

    public ResultSet getwater() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM water";
        return stmt.executeQuery(query);
    }

    public ResultSet getZeroDrinks() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM zero_drink";
        return stmt.executeQuery(query);
    }





    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }



}


