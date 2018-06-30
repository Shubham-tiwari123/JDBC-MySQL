
package mysqlcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;


public class CompanyInfo{
   private final String dbClassName = "com.mysql.jdbc.Driver";
    private final String CONNECTION ="jdbc:mysql://127.0.0.1/java";
    private Connection c = null;
    private Statement st = null;
    private ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    
    public void inputInfo(String companyname,String companyaddress,long pincode, long phone,int num){
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
                    st.executeUpdate("INSERT INTO `companyInfo` (pincode,companyname,companyaddress,phone)"+
                       "VALUES('"+pincode+"','"+companyname+"','"+companyaddress+"','"+phone+"')");
                    break;

                case 2://printing everything from the database
                    rs = st.executeQuery("SELECT * from companyInfo");
                    System.out.print("\nPrinting from database:-\n");
                    while(rs.next()){
                        int pin = rs.getInt("pincode");
                        String names = rs.getString("companyname");
                        String address = rs.getString("companyaddress");
                        int phone_no = rs.getInt("phone");
                        System.out.println(pin+"\t"+names+"\t  "+address+"  \t"+phone_no);
                    }
                    break;
                    
                case 3://update the database
                    System.out.print("\nUpdating the table:-");
                    st.executeUpdate("UPDATE companyInfo SET companyname='Army' WHERE pincode in (45567)");
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
