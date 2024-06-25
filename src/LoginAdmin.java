import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdmin extends JFrame {
    private JPanel panel1;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;

    int width = 800, height = 600;
    private Connection connection;

    public LoginAdmin() {
        super("Login");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginTextField.getText().trim();
                String password = new String(passwordTextField.getPassword()).trim();

                if (validateLogin(username, password)) {
                    AdminPanel adminPanel = new AdminPanel();
                    adminPanel.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowy login lub hasło.", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Połączenie z bazą danych
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/drinks", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd połączenia z bazą danych: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Jeżeli istnieje taki użytkownik, zwróć true
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd podczas sprawdzania danych logowania: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        new LoginAdmin();
    }
}
