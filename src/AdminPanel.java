import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel extends JFrame {

    private JPanel panel1;
    private JTable transakcjeTable;
    private JComboBox<String> usunComboBox;
    private JComboBox<String> kategoriaDodajComboBox;
    private JLabel historiaLabel;
    private JTextField nazwaDodajTextField;
    private JTextField cenaDodajTextField;
    private JScrollPane trasakcjeTableScrollPane;
    private JLabel editCenaLabel;
    private JLabel editPojemnoscLabel;
    private JLabel dodajNazwalabel;
    private JLabel dodajcenalabel;
    private JLabel dodajpojemnosclabel;
    private JLabel dodacKategoriaLabel;
    private JLabel editidlabel;
    private JButton usunButton;
    private JButton edytujButton;
    private JButton dodajButton;
    private JTextField pojemnoscEditTextField;
    private JTextField IdEditTextField;
    private JTextField pojemnoscDodajTextField;
    private JTextField cenaEditTextField;
    private JLabel id_napoju;
    private JTextField idNapojuTextField;
    private JButton exitButton;

    private DatabaseManager dbManager;

    int width = 1150, height = 1000;

    public AdminPanel() {
        super();
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/drinks", "root", "");
            updateTransactionsTable();
            populateUsunComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //dodaj combobox
        kategoriaDodajComboBox.addItem("tea");
        kategoriaDodajComboBox.addItem("water");
        kategoriaDodajComboBox.addItem("zero_drink");
        kategoriaDodajComboBox.addItem("coffee");


        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nazwaDodajTextField.getText().trim();
                    String cenaText = cenaDodajTextField.getText().trim();
                    String pojemnoscText = pojemnoscDodajTextField.getText().trim();
                    String category = (String) kategoriaDodajComboBox.getSelectedItem(); // Pobieramy wybraną kategorię

                    // Sprawdzamy, czy nazwa produktu zawiera cyfry
                    if (containsDigit(name)) {
                        JOptionPane.showMessageDialog(null, "Nazwa produktu nie może zawierać cyfr.", "Błąd", JOptionPane.WARNING_MESSAGE);
                        return; // Przerwij operację jeśli nazwa zawiera cyfry
                    }

                    // Sprawdzamy, czy cena składa się wyłącznie z cyfr
                    if (!isNumeric(cenaText)) {
                        JOptionPane.showMessageDialog(null, "Cena powinna być liczbą.", "Błąd", JOptionPane.WARNING_MESSAGE);
                        return; // Przerwij operację jeśli cena nie jest liczbą
                    }

                    // Sprawdzamy, czy pojemność składa się wyłącznie z cyfr
                    if (!isNumeric(pojemnoscText)) {
                        JOptionPane.showMessageDialog(null, "Pojemność powinna być liczbą.", "Błąd", JOptionPane.WARNING_MESSAGE);
                        return; // Przerwij operację jeśli pojemność nie jest liczbą
                    }

                    double price = Double.parseDouble(cenaText);
                    double volume = Double.parseDouble(pojemnoscText);

                    // Dodajemy produkt do bazy danych
                    dbManager.addProduct(name, price, volume, category);
                    JOptionPane.showMessageDialog(null, "Produkt dodany pomyślnie");
                    updateTransactionsTable();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas parsowania danych numerycznych.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas dodawania produktu: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
            private boolean containsDigit(String text) {
                for (char c : text.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }

            // Funkcja sprawdzająca, czy tekst jest liczbą
            private boolean isNumeric(String text) {
                if (text == null || text.isEmpty()) {
                    return false;
                }
                for (char c : text.toCharArray()) {
                    if (!Character.isDigit(c) && c != '.') {
                        return false;
                    }
                }
                return true;
            }


        });



        edytujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = IdEditTextField.getText().trim();
                String cenaText = cenaEditTextField.getText().trim();
                String pojemnoscText = pojemnoscEditTextField.getText().trim();
                String idText = idNapojuTextField.getText().trim();

                // Sprawdzamy, czy nazwa produktu zawiera cyfry
                if (containsDigit(name)) {
                    JOptionPane.showMessageDialog(null, "Nazwa produktu nie może zawierać cyfr.", "Błąd", JOptionPane.WARNING_MESSAGE);
                    return; // Przerwij operację jeśli nazwa zawiera cyfry
                }

                // Sprawdzamy, czy cena składa się wyłącznie z cyfr
                if (!isNumeric(cenaText)) {
                    JOptionPane.showMessageDialog(null, "Cena powinna być liczbą.", "Błąd", JOptionPane.WARNING_MESSAGE);
                    return; // Przerwij operację jeśli cena nie jest liczbą
                }

                // Sprawdzamy, czy pojemność składa się wyłącznie z cyfr
                if (!isNumeric(pojemnoscText)) {
                    JOptionPane.showMessageDialog(null, "Pojemność powinna być liczbą.", "Błąd", JOptionPane.WARNING_MESSAGE);
                    return; // Przerwij operację jeśli pojemność nie jest liczbą
                }

                // Inicjalizujemy zmienną na ID produktu, jeśli podano
                Integer productId = null;
                if (!idText.isEmpty()) {
                    try {
                        productId = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Podano niepoprawny format ID produktu.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return; // Przerwij operację w przypadku niepoprawnego formatu ID
                    }
                }

                try {
                    double price = Double.parseDouble(cenaText);
                    double volume = Double.parseDouble(pojemnoscText);

                    // Edytujemy produkt w bazie danych
                    dbManager.updateProduct(name, price, volume, productId); // Wywołujemy metodę updateProduct z nazwą produktu i opcjonalnym id

                    JOptionPane.showMessageDialog(null, "Produkt zaktualizowany pomyślnie");
                    updateTransactionsTable();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas parsowania danych numerycznych.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas aktualizacji produktu: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
            private boolean containsDigit(String text) {
                for (char c : text.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }

            // Funkcja sprawdzająca, czy tekst jest liczbą
            private boolean isNumeric(String text) {
                if (text == null || text.isEmpty()) {
                    return false;
                }
                for (char c : text.toCharArray()) {
                    if (!Character.isDigit(c) && c != '.') {
                        return false;
                    }
                }
                return true;
            }
        });



        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = usunComboBox.getSelectedItem().toString();
                if (selectedProduct != null && !selectedProduct.isEmpty()) {
                    try {
                        int productId = Integer.parseInt(selectedProduct.split(" - ")[0]);
                        dbManager.deleteProduct(productId);
                        JOptionPane.showMessageDialog(null, "Produkt został pomyślnie usunięty.");
                        refreshComboBox();
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Błąd przetwarzania id produktu: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Błąd podczas usuwania produktu: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz produkt do usunięcia.", "Błąd", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


    }
    private void refreshComboBox() {
        usunComboBox.removeAllItems();
        try {
            // Pobierz wszystkie produkty do ComboBox z bazy danych
            addProductsToComboBox("coffee");
            addProductsToComboBox("tea");
            addProductsToComboBox("water");
            addProductsToComboBox("zero_drink");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd podczas pobierania produktów: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTransactionsTable() {
        try {
            ResultSet rs = dbManager.getTransactions();
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Product ID", "Name", "Price", "Category", "Quantity", "Transaction Date"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("transaction_date")
                });
            }
            transakcjeTable.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void populateUsunComboBox() throws Exception {
        try {
            // Clear the existing items
            usunComboBox.removeAllItems();

            // Add products from each category
            addProductsToComboBox("coffee");
            addProductsToComboBox("tea");
            addProductsToComboBox("water");
            addProductsToComboBox("zero_drink");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd podczas pobierania produktów: " + ex.getMessage());
        }
    }

    private void addProductsToComboBox(String category) throws SQLException {
        ResultSet rs = null;
        try {
            if (category.equals("coffee")) {
                rs = dbManager.getCoffees();
            } else if (category.equals("tea")) {
                rs = dbManager.gettea();
            } else if (category.equals("water")) {
                rs = dbManager.getwater();
            } else if (category.equals("zero_drink")) {
                rs = dbManager.getZeroDrinks();
            }

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                double volume = rs.getDouble("volume");

                String fullRecord = id + " - " + name + " - " + price + " zł - " + volume + " L";
                usunComboBox.addItem(fullRecord);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas zamykania ResultSet: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}




