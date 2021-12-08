package bazamysql_4t_06122021;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Last update 08 gru 2021r.
 * @author Andrzej Gac <andrzej.gac@zsmeie.torun.pl>
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/jdbcswing.html#adding_listeners
 */
public class BazaMySql_4T_06122021 extends JFrame{

    public BazaMySql_4T_06122021() throws HeadlessException {
        setTitle("---------Aplikacja bazo-danowa---------");
        setSize(new Dimension(Config.sizeX, Config.sizeY));
        Container c =getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(0, 102, 102));
        c.add(new UserInterface());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        BazaMySql_4T_06122021 obj = new BazaMySql_4T_06122021();  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BazaMySql_4T_06122021.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
