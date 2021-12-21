package bazamysql_4t_06122021;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/*
* zadanie domowe termin długi 15 styczeń 2022r.max do 03 luty 2022r.
* 0) rozmieszczenie elementów, ręcznie c.setLayout(new GroupGridLayout());
* 1) okno logowania do aplikacji z autoryzacją w bazie
* 2) czy w netbeansach możemy łączyć się po ssl lub tls?
* 3) sygnalizacja że jesteśmy połączeni
* 4) sposób wyświetlenia danych: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
To będzie na lekcjach:
* 1) wyszukiwanie danych
* 2) modyfikacja danych
* 3) usuwanie danych
* 4) dodawanie danych
* 5) wyświetlenie zawartości bazy
*/
/**
 * Last update 21 gru 2021r.
 * @author Andrzej Gac <andrzej.gac@zsmeie.torun.pl>
 */
public class OperacjeNaBazie {
    String[] kolumny = { "lp","nazwa", "producent", "data_sprzedazy", "cena", "waga" };
         DefaultTableModel model ;
    
    Connection conn;
    ResultSet rs;
    Statement stmt;
    PreparedStatement ptmt;
    String lp = "";
    String nazwa = "";
    String producent = "";
    String data_sprzedazy = "";
    String cena = "";
    String waga = "";
    public OperacjeNaBazie() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(kolumny);
    }
    
    public DefaultTableModel selectAll(){
        
        System.out.println("---------test selectAll");
        try {       
            conn = setConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from towary;");
            while (rs.next() && !rs.isAfterLast()) {
                lp=rs.getString("lp");
                nazwa=rs.getString("nazwa");
                producent=rs.getString("producent");
                data_sprzedazy=rs.getString("data_sprzedazy");
                cena=rs.getString("cena");
                waga=rs.getString("waga");     
                model.addRow(new Object[]{lp, nazwa,producent, data_sprzedazy,cena,waga});
            }            
        } catch (SQLException ex) {
            Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
        }finally{          
            closeAll(rs, ptmt, stmt, conn);
        } 
        System.out.println("Wyniki:");
        System.out.println(""+lp+" " +nazwa+" "+producent+" "
                +data_sprzedazy+" "+cena+" "+waga);
        return model;
    }
    
    public boolean addRowToDatabase(){
        
        try {
            conn = setConnection();
            ptmt = (PreparedStatement) conn.prepareStatement(
                    "insert into towary("
                            + "nazwa,"
                            + "producent,"
                            + "data_sprzedazy,"
                            + "cena,"
                            + "waga) "
                            + "values (?,?,?,?,?);");
            ptmt.setString(1,  "klawiatura" );
            ptmt.setString(2,  "Logitech" );
            ptmt.setString(3,  "2021-12-13" );
            ptmt.setString(4,  "99.99" );
            ptmt.setString(5,   "0.45");
                        
            System.out.println("sql:"+ptmt);
            ptmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeAll(rs,ptmt,stmt,conn);         
        }
        return true;
    }
    public boolean findInDatabase(){
        ArrayList<String> wynik = new ArrayList<>();
        try {
            conn = setConnection();
            ptmt = conn.prepareStatement("select * from towary"
                    + " where nazwa=? and producent=? ");
            ptmt.setString(1, "klawiatura");
            ptmt.setString(2, "Logitech");
            
            System.out.println("sql:"+ptmt);
            rs = ptmt.executeQuery();
            while(rs.next()) {
                        wynik.add(rs.getString("lp"));
                        wynik.add(rs.getString("nazwa"));
                        wynik.add(rs.getString("producent"));
                        wynik.add(rs.getString("data_sprzedazy"));
                        wynik.add(rs.getString("cena"));
                        wynik.add(rs.getString("waga"));
          }
            System.out.println("wyniki szukania: "+wynik);
        } catch (SQLException ex) {
            Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeAll(rs, ptmt, stmt, conn);
        }
        return true;
    }
    
    public int deleteFromTable(){
        int codeInt=-1;
        try {
            conn = setConnection();
            ptmt =  conn.prepareStatement("delete from towary where nazwa=? and producent=? and lp=?;");
            ptmt.setString(1, "klawiatura");
            ptmt.setString(2, "Logitech");
            ptmt.setString(3, "72");
            System.out.println("sql=" + ptmt);
           codeInt = ptmt.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll(rs, ptmt, stmt, conn);
        }
        return codeInt;
    }
    
     public Connection setConnection() throws SQLException {
     
    conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sklep2?zeroDateTimeBehavior=CONVERT_TO_NULL", 
            "andrzej", 
            "123");
   return conn;
    }
     public Connection getConnection() throws SQLException {
     
    if(conn!=null && !conn.isClosed()) {
                return conn;
    }
    return conn;
    }

    private void closeAll(ResultSet rs, PreparedStatement ptmt, Statement stmt, Connection conn) {
       if (rs != null) 
      
                try {
                    rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
       }
           if (stmt != null)
               
               try {
                   stmt.close();
       } catch (SQLException ex) {
           Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
       }
           
            if (ptmt != null) 
      
                try {
                    ptmt.close();
       } catch (SQLException ex) {
           Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
       }
         
            if (conn != null) 
      
                try {
                    conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
       };
           
    }
}
