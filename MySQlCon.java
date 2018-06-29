package mysqlcon;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class DbCon{
    private final String dbClassName = "com.mysql.jdbc.Driver";
    private final String CONNECTION ="jdbc:mysql://127.0.0.1/java";
    private Connection c = null;
    private Statement st = null;
    private ResultSet rs = null;
    protected String tablename = null;
    protected String var1,var2 = null;
    protected String var3,var4 = null;
    public void DbActivity(String name,String subject,long roll, long phone,int num){
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
                    st.executeUpdate("INSERT INTO "+tablename+" ("+var1+","+var2+","+var3+","+var4+")"+
                       "VALUES('"+roll+"','"+name+"','"+subject+"','"+phone+"')");
                    break;

                case 2://printing everything from the database
                    rs = st.executeQuery("SELECT * from "+tablename);
                    System.out.print("\nPrinting from database:-\n");
                    while(rs.next()){
                        int id = rs.getInt(var1);
                        String names = rs.getString(var2);
                        String subjects = rs.getString(var3);
                        int phone_no = rs.getInt(var4);
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
class StudentInfo extends DbCon{
    private String name;
    private String subject;
    private long roll,phone;
    
    Scanner sc = new Scanner(System.in);
    public void inputInfo(){
        System.out.print("\nEnter the name:-");
        name = sc.nextLine();
        System.out.print("\nEnter the roll:-");
        roll = Integer.parseInt(sc.nextLine());
        System.out.print("\nEnter the subject:-");
        subject = sc.nextLine();
        System.out.print("\nEnter the phone no:-");
        phone = sc.nextLong();
        var1="roll";
        var2="name";
        var3="subject";
        var4="phone";
        super.tablename="studentInfo";
        super.DbActivity(name, subject, roll, phone,1);
        
    }
    public void displayData(){
        var1="roll";
        var2="name";
        var3="subject";
        var4="phone";
        super.tablename="studentInfo";
        super.DbActivity(null, null, 0, 0, 2);
    }
    public void updataData(){
        super.tablename="studentInfo";
        super.DbActivity(null, null, 0, 0, 3);
    }
}

class CompanyInfo extends DbCon{
    private String companyname;
    private String companyaddress;
    private long pincode;
    private long phone;
    
    Scanner sc = new Scanner(System.in);
    public void inputInfo(){
        System.out.print("\nEnter the company name:-");
        companyname = sc.nextLine();
        System.out.print("\nEnter the company address:-");
        companyaddress = sc.nextLine();
        System.out.print("\nEnter the pincode:-");
        pincode = sc.nextLong();
        System.out.print("\nEnter the phone no:-");
        phone = sc.nextLong();
        
        var1="pincode";
        var2="companyname";
        var3="companyaddress";
        var4="phone";
        super.tablename="companyInfo";
        super.DbActivity(companyname, companyaddress, pincode, phone,1);
    }
    public void displayData(){
        var1="pincode";
        var2="companyname";
        var3="companyaddress";
        var4="phone";
        super.tablename="companyInfo";
        super.DbActivity(null, null, 0, 0, 2);
    }
}


public class MySQlCon {
    private int num;
    Scanner sc = new Scanner(System.in);
    public void enterStudentInfo(){
        StudentInfo s = new StudentInfo();
        do{
            System.out.print("\n1)Enter data\n2)Display data\n3)Update\n4)Exit");
            System.out.print("\nEnter your option:-");
            num = sc.nextInt();
            switch(num){
                case 1:
                    s.inputInfo();
                    break;

                case 2:
                    s.displayData();
                    break;
                    
                case 3:
                    s.updataData();
                    break;
            }
        }while(num!=4);
    }
    public void enterCompanyInfo(){
        CompanyInfo obj = new CompanyInfo();
        do{
            System.out.print("\n1)Enter data\n2)Display data\n3)Exit");
            System.out.print("\nEnter your option:-");
            num = sc.nextInt();
            switch(num){
                case 1:
                    obj.inputInfo();
                    break;

                case 2:
                    obj.displayData();
                    break;
            }
        }while(num!=4);
    }
    
    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        int num;
        MySQlCon m = new MySQlCon();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("\n1)Enter student data\n2Enter company data\n3)Exit");
            System.out.print("\nEnter your option:-");
            num = sc.nextInt();
            switch(num){
                case 1:
                    m.enterStudentInfo();
                    break;

                case 2:
                    m.enterCompanyInfo();
                    break;
            }
        }while(num!=3);
    }
    
}
