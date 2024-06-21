import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame {
    private JPanel panel1;
    private JButton WelcomeButton;
    private JPanel labelpanel;
    private JLabel welcomeLabel;


    int width = 1150, height = 1000;



    public Welcome(){
        super();
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("napoje_welcome_form.png"));
        welcomeLabel.setIcon(imageIcon);






        WelcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                Zaplac zaplata = new Zaplac();
                zaplata.setVisible(true);
            }
        });
    }



}
