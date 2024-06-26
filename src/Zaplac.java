import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zaplac extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JCheckBox a5zlCheckBox;
    private JTextField textField2;
    private JCheckBox a2ZlCheckBox;
    private JTextField textField3;
    private JCheckBox a1ZlCheckBox;
    private JButton wrzucbutton;
    private double saldo = 0.0;

    int width = 1150, height = 1000;

    public double getSaldo() {
        return saldo;
    }

    public Zaplac() {

        super("Wrzucanie monet");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

            // Obsługa przycisku "Wrzuć"
        wrzucbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = 0.0;

                // Sprawdzanie i dodawanie monet
                if (a5zlCheckBox.isSelected()) {
                    int quantity = Integer.parseInt(textField1.getText());
                    amount += 5.0 * quantity;
                }
                if (a2ZlCheckBox.isSelected()) {
                    int quantity = Integer.parseInt(textField2.getText());
                    amount += 2.0 * quantity;
                }
                if (a1ZlCheckBox.isSelected()) {
                    int quantity = Integer.parseInt(textField3.getText());
                    amount += 1.0 * quantity;
                }

                  saldo += amount;
                dispose();
                double saldo = getSaldo();
                Menu menu = new Menu(saldo);
                menu.setVisible(true);
            }
        });



    }


    }


