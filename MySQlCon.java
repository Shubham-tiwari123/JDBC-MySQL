package mysqlcon;

import java.util.Scanner;

public class MySQlCon {
    public static void main(String[] args){
        int num;
        String name,companyname,companyaddress;
        String subject;
        long roll,phone,pincode;
        MySQlCon m = new MySQlCon();
        StudentInfo s = new StudentInfo();
        CompanyInfo c = new CompanyInfo();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("\nMain menu:-\n1)Enter student data\n2)Enter company data\n3)Exit");
            System.out.print("\nEnter your option:-");
            num = sc.nextInt();
            switch(num){
                case 1:
                    System.out.print("\nStudent:-");
                    System.out.print("\n1)Enter data\n2)Display data\n3)Update\n4)Exit");
                    System.out.print("\nEnter your option:-");
                    num = sc.nextInt();
                    switch(num){
                        case 1:
                            System.out.print("\nEnter the name:-");
                            name = sc.nextLine();
                            System.out.print("\nEnter the roll:-");
                            roll = Integer.parseInt(sc.nextLine());
                            System.out.print("\nEnter the subject:-");
                            subject = sc.nextLine();
                            System.out.print("\nEnter the phone no:-");
                            phone = sc.nextLong();
                            s.inputInfo(name, subject, roll, phone, 1);
                            break;

                        case 2:
                            s.inputInfo(null, null, 0, 0, 2);
                            break;

                        case 3:
                            s.inputInfo(null, null, 0, 0, 3);
                            break;
                    }
                    break;

                case 2:
                    System.out.print("\nCompany:-");
                    System.out.print("\n1)Enter data\n2)Display data\n3)Update\n4)Exit");
                    System.out.print("\nEnter your option:-");
                    num = sc.nextInt();
                    switch(num){
                        case 1:
                            System.out.print("\nEnter the company name:-");
                            companyname = sc.nextLine();
                            System.out.print("\nEnter the company address:-");
                            companyaddress = sc.nextLine();
                            System.out.print("\nEnter the pincode:-");
                            pincode = sc.nextLong();
                            System.out.print("\nEnter the phone no:-");
                            phone = sc.nextLong();
                            c.inputInfo(companyname, companyaddress, pincode, phone, 1);
                        case 2:
                            c.inputInfo(null, null, 0, 0, 2);
                            break;
                        case 3:
                            c.inputInfo(null, null, 0, 0, 3);
                    }
            }
        }while(num!=3);
    }
    
}
