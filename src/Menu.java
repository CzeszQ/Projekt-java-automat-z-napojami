import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel panel1;
    private JButton wodaButton;
    private JButton kawaButton;
    private JButton herbataButton;
    private JButton zerobutton;
    private JLabel wodalabel;
    private JLabel kawalabel;
    private JLabel herbatalabel;

    int width = 1150, height = 1000;

    public Menu(){
        super("Menu");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);


        ImageIcon wodaIcon = new ImageIcon(getClass().getResource("woda_menu.jpg"));
        wodaButton.setIcon(wodaIcon);

        ImageIcon herbataIcon = new ImageIcon(getClass().getResource("herbata menu.png"));
        herbataButton.setIcon(herbataIcon);

        wodaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Woda menu = new Woda();
                menu.setVisible(true);
            }
        });
        herbataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Herbata herbata = new Herbata();
                herbata.setVisible(true);
            }
        });
    }

}
