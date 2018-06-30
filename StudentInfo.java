
package mysqlcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class StudentInfo{
    private final String dbClassName = "com.mysql.jdbc.Driver";
    private final String CONNECTION ="jdbc:mysql://127.0.0.1/java";
    private Connection c = null;
    private Statement st = null;
    private ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    
    public void inputInfo(String name,String subject,long roll, long phone,int num){
         try {
            Class.forName(dbClassName);
            Properties p = new Properties();
            p.put("user","root");
            p.put("password","");
            c = DriverManager.getConnection(CONNECTION,p);
            st = c.createStatement();
            switch(num){
                case 1: 
                    System.out.println("\nSaving data:-");
                    st.executeUpdate("INSERT INTO `studentInfo` (roll,name,subject,phone)"+
                       "VALUES('"+roll+"','"+name+"','"+subject+"','"+phone+"')");
                    break;

                case 2://printing everything from the database
                    rs = st.executeQuery("SELECT * from studentInfo");
                    System.out.print("\nPrinting from database:-\n");
                    while(rs.next()){
                        int id = rs.getInt("roll");
                        String names = rs.getString("name");
                        String subjects = rs.getString("subject");
                        int phone_no = rs.getInt("phone");
                        System.out.println(id+"\t"+names+"\t  "+subjects+"  \t"+phone_no);
                    }
                    break;
                    
                case 3://update the database
                    System.out.print("\nUpdating the table:-");
                    st.executeUpdate("UPDATE studentInfo SET subject='science' WHERE roll in (45)");
                    System.out.print("\nUpdation completed");
                    break;
            }
            
            
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Exception: "+e);
        }
        catch(SQLException e){
            System.out.println("Can not connect to the database...: "+e);
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
        finally{
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    System.out.println("Connection can not be ade free: "+e);
                }
            }
        }
    }
}
