package bazamysql_4t_06122021;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Zsmeie W Toruniu, last update 21 gru 2021r.
 * @author Andrzej Gac <andrzej.gac@zsmeie.torun.pl>
 */
public class UserInterface extends JPanel{
    JButton jButton;
JScrollPane jScrollPane;
JTable jTable;
String[] kolumny = { "lp","nazwa", "producent", "data_sprzedazy", "cena", "waga" };
DefaultTableModel model ;
OperacjeNaBazie o;

    public UserInterface() {
        o = new OperacjeNaBazie();
        jButton = new JButton("connect");
     
      model = new DefaultTableModel();
      jTable = new JTable(model);
      
        jTable.setCellEditor(null);
      //  model.addColumn(kolumny);
        model.setColumnIdentifiers(kolumny); 
           String lp = "1111";
    String nazwa = "aaa";
    String producent = "bbb";
    String data_sprzedazy = "2021-12-21";
    String cena = "23";
    String waga = "5";
          
      
       //jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setFillsViewportHeight(true);
        jTable.setPreferredScrollableViewportSize(new Dimension(960,512));

        jScrollPane = new JScrollPane(jTable);
        this.add(jScrollPane);
       jButton.setBounds(100, 50, 150, 50);
        add(jButton);
       
        
        setBounds(10, 10, Config.sizePanelX, Config.sizePanelY);
        setSize(new Dimension(Config.sizePanelX, Config.sizePanelY));
        setBackground(Color.red);
        
        o.selectAll();
        o.addRowToDatabase();
        
        switch(o.deleteFromTable()){
            case 0: System.out.println("------------------------------> nie znaleziono rekordu do usunięcia");break;
            case 1: System.out.println("poprawnie usunięto rekord danych");break;
                default:System.out.println("------------------------------> inny bląd");;
        }
        o.findInDatabase();
        jButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              fillTable();
              //model.addRow(new Object[]{lp, nazwa,producent, data_sprzedazy,cena,waga});
          }

          
        });
        
    }
    private void fillTable() {
              jTable.setModel(o.selectAll());
          }
}
