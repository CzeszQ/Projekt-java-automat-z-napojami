import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel panel1;
    private JButton kawaButton;
    private JButton zeroButton;
    private JLabel wodalabel;
    private JLabel kawalabel;
    private JLabel zerolabel;
    private JLabel herbatalabel;
    private JButton wodaButton;
    private JButton herbataButton;

    int width = 1150, height = 1150;

    public Menu(){
        super("Menu");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);

        //obrazy
        ImageIcon wodaIcon = new ImageIcon(getClass().getResource("woda_menu2.jpg"));
        wodaButton.setIcon(wodaIcon);

        ImageIcon herbataIcon = new ImageIcon(getClass().getResource("herbata_menu.jpg"));
        herbataButton.setIcon(herbataIcon);

        ImageIcon kawaIcon = new ImageIcon(getClass().getResource("kawa_menu.png"));
        kawaButton.setIcon(kawaIcon);

        ImageIcon zeroIcon = new ImageIcon(getClass().getResource("zero_menu.jpg"));
        zeroButton.setIcon(zeroIcon);


        //listenery do przyciskow
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
