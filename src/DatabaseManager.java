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
    public ResultSet getTransactions() throws Exception {
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM transactions";
        return stmt.executeQuery(query);
    }

    //zapisywanie trasakcji do bazy danych
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
    //dodawanie nowego napoju do bazy
    public void addProduct(String name, double price, double volume, String category) throws SQLException {
        String query = "INSERT INTO products (name, price, volume, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setDouble(3, volume);
            pstmt.setString(4, category);
            pstmt.executeUpdate();
        }
    }

    public void updateProduct(String name, double price, double volume, Integer id) throws SQLException {
        String query;
        if (id != null) {
            query = "UPDATE products SET price = ?, volume = ? WHERE id = ?";
        } else {
            query = "UPDATE products SET price = ?, volume = ? WHERE name = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, price);
            pstmt.setDouble(2, volume);
            if (id != null) {
                pstmt.setInt(3, id);
            } else {
                pstmt.setString(3, name);
            }
            pstmt.executeUpdate();
        }
    }



    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produkt został pomyślnie usunięty.");
            } else {
                System.out.println("Nie znaleziono produktu do usunięcia.");
            }
        }
    }








    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }



}


