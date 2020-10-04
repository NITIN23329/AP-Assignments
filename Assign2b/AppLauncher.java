package Assignments.Assign2b;

import java.util.ArrayList;
import java.util.Scanner;

public class AppLauncher extends Zotato {
    static Zotato myApp  = new Zotato();
    public static void main(String[] args) {

        boolean isExit = false;
        while (!isExit){
            myApp.printMenu();
            int x = sc.nextInt();
            switch (x){
                case 1:
                    restrauOwner();
                    break;
                case 2:
                    customer();
                    break;
                case 3:
                    userDetails();
                    break;
                case 4:
                    companyAccountDetails();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("Wrong input!!");
            }
        }
    }

    private static void companyAccountDetails() {
        System.out.println("Total Company balance : Rs. "+myApp.getCompanyBalance());
        System.out.println("Total Delivery Charges Collected : Rs. "+myApp.getDeliveryCharges());
    }

    private static void userDetails() {
        System.out.println("Enter 1 for Customer details");
        System.out.println("Enter 2 for Restaurant details");
        int x = sc.nextInt();
        if(x==2) {
            restrauDetails2();
            System.out.println("Choose Restaurant");
            int y = sc.nextInt();
            System.out.println( myApp.getRestaurants().get(y-1).toString());
        }
        else{
            customerDetails2();
            System.out.println("Choose Customer");
            int y = sc.nextInt();
            System.out.println( (myApp.getCustomers().get(y-1)).toString()+" , wallet amount left : "+ ((Customer) myApp.getCustomers().get(y-1)).getWallet());
        }

    }

    private static void customerDetails2() {
        for(IUser ele : myApp.getCustomers())
            System.out.println("id : "+((Customer)ele).getId()+" , Customer name : "+((Customer)ele).getName());
    }

    private static void customerDetails() {
        for(IUser ele : myApp.getCustomers())
            System.out.println(((Customer)ele));
    }

    private static void customer() {
        customerDetails();
        System.out.println("Choose Customer : ");
        int x = sc.nextInt();
        Customer cus =  (Customer)myApp.getCustomers().get(x-1);
        boolean isExit = false;
        int c=0;
        Restaurant res = null;
        while (!isExit) {
            System.out.println("Welcome " + cus.getName());
            if(c==0) {
                System.out.println("Press 1 to Select Restaurant");
            }
            else System.out.println("Press 1 to Search Item ");
           cus.printOptions();
            int y = sc.nextInt();
            switch (y) {
                case 1:
                    if(c==0){
                        restrauDetails();
                        System.out.println("Choose restaurant : ");
                        int z = sc.nextInt();
                        res = (Restaurant)myApp.getRestaurants().get(z-1);
                        c=1;
                    }
                    cus.select(res);
                    break;
                case 2:
                    cus.checkOut(res);
                    break;
                case 3:
                    cus.printRewards();
                    break;
                case 4:
                    cus.printItems();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("Wrong input !!");
            }
        }
    }

    private static void restrauOwner() {
        restrauDetails();
        System.out.println("Choose Restaurant :");
        int x = sc.nextInt();
        Restaurant res = (Restaurant)myApp.getRestaurants().get(x-1);
        boolean isExit = false;
        while (!isExit){
           res.printOptions();
            if( !(res.getClass()==Restaurant.class))
                System.out.println("Press 4 to Discount on bill value");

            int y = sc.nextInt();
            switch (y) {
                case 1:
                    res.addItem();
                    break;
                case 2:
                    res.editItem();
                    break;
                case 3:
                    res.printRewards();
                    break;
                case 4:
                    if(res.getClass()==Restaurant.class)break;
                    res.discountOnBillValue();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("Wrong input !!");
            }

        }
    }
    private static void restrauDetails(){
        for(IUser ele : myApp.getRestaurants() )
            System.out.println(ele);
    }
    private static void restrauDetails2(){
        for(IUser ele : myApp.getRestaurants() )
            System.out.println("id : "+((Restaurant) ele).getId()+" , Restaurant name : "+((Restaurant) ele).getName());
    }
}
class Zotato{
    static Scanner sc = new Scanner(System.in);
    private final ArrayList<IUser> restaurants ;
    private final ArrayList<IUser> customers ;
    private  static double companyBalance;
    private static   int deliveryCharges;
    public Zotato() {
        restaurants = new ArrayList<>();
        customers = new ArrayList<>();

        IUser r1 = new Authentic("Shah");
        IUser r2 = new Restaurant("Ravi's");
        IUser r3 = new Authentic("The Chinese");
        IUser r4 = new FastFood("Wang's");
        IUser r5 = new Restaurant("Paradise");

        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r4);
        restaurants.add(r5);

        IUser c1 = new Elite("Ram" );
        IUser c2 = new Elite("Sam");
        IUser c3 = new Special("Tim");
        IUser c4 = new Customer("Kim" );
        IUser c5 = new Customer("Jim");

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        customers.add(c5);

        companyBalance=0d;
        deliveryCharges=0;
    }
    public void printMenu() {
        System.out.println("Welcome to Zotato : \n" +
                "Press 1 to Enter as Restaurant Owner\n" +
                "Press 2 to  Enter as Customer\n" +
                "Press 3 to Check User Details\n" +
                "Press 4 to Company Account details\n" +
                "Press 5 to  Exit");
    }

    public ArrayList<IUser> getRestaurants() {
        return restaurants;
    }

    public ArrayList<IUser> getCustomers() {
        return customers;
    }
    public static void addCompanyBalance(double x){
        companyBalance+=x;
    }
    public static void addDeliveryCharges(int x){
        deliveryCharges+=x;
    }

    public double getCompanyBalance() {
        return companyBalance;
    }

    public int getDeliveryCharges() {
        return deliveryCharges;
    }
}