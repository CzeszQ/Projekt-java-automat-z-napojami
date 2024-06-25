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
        private double saldo;


        int width = 1200, height = 1200;

        public Menu(double saldo){
            super("Menu");
            this.saldo = saldo;
            this.setContentPane(this.panel1);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(width, height);
            this.setLocationRelativeTo(null);
            this.setVisible(true);

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
                    Woda menu = new Woda(saldo);
                    menu.setVisible(true);
                }
            });

            herbataButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Herbata herbata = new Herbata(saldo);
                    herbata.setVisible(true);
                }
            });

            kawaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Kawa kawa = new Kawa(saldo);
                    kawa.setVisible(true);
                }
            });

            zeroButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Zero napoje_zero = new Zero(saldo);
                    napoje_zero.setVisible(true);
                }
            });
        }

    }
