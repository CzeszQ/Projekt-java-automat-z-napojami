import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Herbata extends JFrame {
    private JPanel panel1;
    private JButton backMenu;
    private DefaultListModel<String> listModel;
    private JList<String> herbataList;
    private JButton wybierzButton;
    private JLabel saldoLabel;
    private DatabaseManager dbManager;
    private double selectedNapojCena = 0.0;
    private String selectedNapojNazwa = "";
    private int selectedNapojId = 0;
    private SaldoContainer saldoContainer;


    int width = 1150, height = 1000;

    public Herbata(double saldo){
        super("Herbata");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.saldoContainer = new SaldoContainer(saldo);

        // Inicjalizacja listModel
        listModel = new DefaultListModel<>();

        // Przypisanie listModel do kawaList
        herbataList.setModel(listModel);

        saldoLabel.setText("Saldo: " + saldo + " zł");

        backMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu(saldo);
                menu.setVisible(true);
            }
        });

        wybierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedNapoj = herbataList.getSelectedValue();
                if (selectedNapoj != null) {
                    // Przechowuj wybrany napój i jego cenę do dalszego przetwarzania
                    String[] napojDetails = selectedNapoj.split(" - ");
                    selectedNapojId = Integer.parseInt(napojDetails[0]);
                    selectedNapojNazwa = napojDetails[1].trim(); // Usuwa białe znaki z początku i końca
                    selectedNapojCena = Double.parseDouble(napojDetails[2].replace(" zł", ""));

                    if (saldoContainer.getSaldo() >= selectedNapojCena) {
                        try {
                            // Zapisz transakcję do bazy danych
                            dbManager.recordTransaction(selectedNapojId, selectedNapojNazwa, selectedNapojCena, "tea");

                            saldoContainer.setSaldo(saldoContainer.getSaldo() - selectedNapojCena);
                            JOptionPane.showMessageDialog(null, "Dziękujemy za zakup! Twoja reszta to: " + saldoContainer.getSaldo() + " zł.");

                            dispose(); // Zamknij okno po zakupie
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Błąd podczas zapisu transakcji.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie masz wystarczających pieniędzy.");
                    }
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
            ResultSet rs = dbManager.gettea();
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
