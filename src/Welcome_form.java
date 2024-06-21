import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome_form extends JFrame {
    private JPanel panel1;
    private JTextField WITAMYWAUTOMACIEZTextField;
    private JButton WelcomeButton;


    int width = 800, height = 800;



    public Welcome_form(){
        super();
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);


        WelcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



}
