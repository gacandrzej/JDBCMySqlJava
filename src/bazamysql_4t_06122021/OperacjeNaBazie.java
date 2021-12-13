package bazamysql_4t_06122021;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
* zadanie domowe termin długi
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
*/
/**
 * Last update 08 gru 2021r.
 * @author Andrzej Gac <andrzej.gac@zsmeie.torun.pl>
 */
public class OperacjeNaBazie {
    Connection conn;
    ResultSet rs;
    Statement stmt;
    String lp = "";
    String nazwa = "";
    String producent = "";
    String data_sprzedazy = "";
    String cena = "";
    String waga = "";
    public OperacjeNaBazie() {
        
    }
    
    public void selectAll(){
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
            }            
        } catch (SQLException ex) {
            Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OperacjeNaBazie.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        System.out.println("Wyniki:");
        System.out.println(""+lp+" " +nazwa+" "+producent);
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
}
