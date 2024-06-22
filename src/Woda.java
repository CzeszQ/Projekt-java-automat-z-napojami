import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Woda extends JFrame {


    private JPanel panel1;
    private JButton backMenu;


    int width = 1150, height = 1000;

    public Woda(){
        super("Woda");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);


        ImageIcon imageIcon = new ImageIcon(getClass().getResource(""));


        backMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }






}
