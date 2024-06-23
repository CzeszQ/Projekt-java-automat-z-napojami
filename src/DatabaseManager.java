import java.sql.*;


public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String dbUrl, String user, String password) throws Exception {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }
    //selecty do wszystkich baz
    public ResultSet getCoffees() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM products where category='coffee'";
        return stmt.executeQuery(query);
    }

    public ResultSet gettea() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM products where category='tea'";
        return stmt.executeQuery(query);
    }

    public ResultSet getwater() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM products where category='water'";
        return stmt.executeQuery(query);
    }

    public ResultSet getZeroDrinks() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM products where category='zero_drink'";
        return stmt.executeQuery(query);
    }

    public void recordTransaction(int productId, String name, double price, String category) throws SQLException {
        String query = "INSERT INTO transactions (product_id, name, price, category, quantity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setString(4, category);
            pstmt.setInt(5, 1); // Assuming quantity is always 1 for this example
            pstmt.executeUpdate();
        }
    }






    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }



}


