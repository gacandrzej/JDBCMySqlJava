package bazamysql_4t_06122021;

import javax.swing.JPanel;

/**
 * Zsmeie W Toruniu, last update 15 gru 2021r.
 * @author Andrzej Gac <andrzej.gac@zsmeie.torun.pl>
 */
public class UserInterface extends JPanel{

    public UserInterface() {
        OperacjeNaBazie o = new OperacjeNaBazie();
        o.selectAll();
        //o.addRowToDatabase();
        o.findInDatabase();
    }
    
}
