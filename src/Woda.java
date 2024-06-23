import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Woda extends JFrame {


    private JPanel panel1;
    private JButton backMenu;
    private DefaultListModel<String> listModel;
    private JList<String> wodaList;
    private JLabel saldoLabel;
    private JButton wybierzButton;
    private DatabaseManager dbManager;
    private double selectedNapojCena = 0.0;
    private String selectedNapojNazwa = "";

    int width = 1150, height = 1000;

    public Woda() {
        super("Woda");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);

        // Inicjalizacja listModel
        listModel = new DefaultListModel<>();

        // Przypisanie listModel do kawaList
        wodaList.setModel(listModel);

        backMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        wybierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedNapoj = wodaList.getSelectedValue();
                if (selectedNapoj != null) {
                    JOptionPane.showMessageDialog(null, "Wybrałeś: " + selectedNapoj);
                    dispose();
                    // Przechowuj wybrany napój i jego cenę do dalszego przetwarzania
                    String[] napojDetails = selectedNapoj.split(" - ");
                    selectedNapojNazwa = napojDetails[1];
                    selectedNapojCena = Double.parseDouble(napojDetails[2].replace(" zł", ""));
                    // Możesz przekazać te informacje do następnego formularza lub użyć ich w inny sposób
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę wybrać kawę.");
                }
            }
        });

        // Połączenie z bazą danych i aktualizacja listy
        try {
            dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/drinks", "root", "");
            updateWodaList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void updateWodaList() {
        try {
            ResultSet rs = dbManager.getwater();
            listModel.clear();
            while (rs.next()) {
                String napoj = rs.getInt("id") + " - " + rs.getString("name") + " - " + rs.getDouble("price") + " zł - " + rs.getDouble("volume") + " L";
                listModel.addElement(napoj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}