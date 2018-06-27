package mysqlcon;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
public class MySQlCon {
    private static final String dbClassName = "com.mysql.jdbc.Driver";
    private static final String CONNECTION ="jdbc:mysql://127.0.0.1/java";
    private String name;
    private String subject;
    private long roll,phone;
    Scanner sc = new Scanner(System.in);
    
    void studentInfo(){
        System.out.print("\nEnter the name:-");
        name = sc.nextLine();
        System.out.print("\nEnter the roll:-");
        roll = Integer.parseInt(sc.nextLine());
        System.out.print("\nEnter the subject:-");
        subject = sc.nextLine();
        System.out.print("\nEnter the phone no:-");
        phone = sc.nextLong();
        
    }
    
    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        int num;
        Scanner sc = new Scanner(System.in);
        MySQlCon obj =new MySQlCon();
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user","root");
        p.put("password","");
        Connection c = DriverManager.getConnection(CONNECTION,p);
        Statement st = c.createStatement();
        do{
            System.out.print("\n1)Enter data\n2)Display data\n3)Update\n4)Exit");
            System.out.print("\nEnter your option:-");
            num = sc.nextInt();

            switch(num){
                case 1:obj.studentInfo();
                    //Inserting data into database
                    st.executeUpdate("INSERT INTO `studentInfo` (roll,name,subject,phone)"+
                           "VALUES('"+obj.roll+"','"+obj.name+"','"+obj.subject+"','"+obj.phone+"')");
                    break;

                case 2://printing everything from the database
                    ResultSet rs = st.executeQuery("SELECT * from studentInfo");
                    System.out.print("\nPrinting from database:-\n");
                    System.out.println("id  name    subject    phone");
                    while(rs.next()){
                        int id = rs.getInt("roll");
                        String name = rs.getString("name");
                        String subject = rs.getString("subject");
                        int phone = rs.getInt("phone");
                        System.out.println(id+"   "+name+"    "+subject+"    "+phone);
                    }
                    break;
                    
                case 3://update the database
                    System.out.print("\nUpdating the table:-");
                    
                    st.executeUpdate("UPDATE studentInfo SET subject='english' WHERE roll in (45)");
                    System.out.print("\nUpdation completed");
                    break;
            }
        }while(num!=4);
        c.close();
    }
    
}
