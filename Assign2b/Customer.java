package Assignments.Assign2b;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Customer extends Data implements IUser {
    private static int id=1;
    private HashMap<Integer  ,Item> myCart;
    private final Queue<String> history ;
    private double wallet;
    public Customer(String name) {
        super(id,name);
        myCart = new HashMap<>();
        wallet = 1000d;
        id++;
        history = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "id : "+getId()+ " , Customer name : "+getName();
    }
    @Override
    public void printRewards() {
        System.out.println("Current rewards : "+getRewards());
    }
    @Override
    public void printItems() {
        for(String ele : history)
            System.out.println(ele);

    }

    @Override
    public void printOptions() {
        System.out.println( "Press 2 to checkout cart\n" +
                "Press 3 for Reward won\n" +
                "Press 4 to print the recent orders\n" +
                "Press 5 to Exit");
    }


    private int countItems() {
        int count = 0;
        for(Integer ele : myCart.keySet())
            count+=myCart.get(ele).getQuantity();
        return count;
    }


    public void select(Restaurant restaurant) {
        restaurant.printItems();
        System.out.println("CHOOSE ITEM BY ID");
        int id = sc.nextInt();
        System.out.println("ENTER QUANTITY");
        int quantity = sc.nextInt();
        if(quantity>restaurant.getItemList().get(id).getQuantity()){
            System.out.println("INSUFFICIENT QUANTITY !! CAN NOT ADD TO CART");
            return;
        }
        Item newItem = restaurant.getItemList().get(id).clone(quantity);
        if(myCart.containsKey(newItem.getId()))
            myCart.get(newItem.getId()).setQuantity(myCart.get(newItem.getId()).getQuantity()+newItem.getQuantity());
        else
            myCart.put(newItem.getId() , newItem);
        System.out.println("ITEM SUCCESSFULLY ADDED!!");
       restaurant.setQuantity(id,quantity);
    }

    public void checkOut(Restaurant restaurant) {
        AmountCalculator amountCalculator = new AmountCalculator(restaurant,this);
        double[] totalAmount=  amountCalculator.calculate();
        while (true){
            for(Integer ele:myCart.keySet())
                System.out.println(myCart.get(ele));
            System.out.println("Delivery Charge : "+totalAmount[0]);
            System.out.println("Total order value : "+(totalAmount[0]+totalAmount[1]));
            if(getRewards()+wallet>=totalAmount[0]+totalAmount[1]){
                System.out.println("Press 1 to checkout");
                int x = sc.nextInt();
                if(x==1){
                    break;
                }
            }
            else{
                System.out.println("INSUFFICIENT BALANCE!! PLEASE REMOVE ITEMS FROM YOUR CART ACCORDING TO ITEM ID.  :");
                int x = sc.nextInt();
                totalAmount = amountCalculator.itemRemover(x);
            }
        }
        double price = totalAmount[0]+totalAmount[1];
        System.out.println(countItems()+" items successfully brought for : Rs "+price);
        amountCalculator.updateReward(price);
        Zotato.addCompanyBalance(price/100);
        Zotato.addDeliveryCharges((int)totalAmount[0]);
        for(Integer ele : myCart.keySet()){
            if(history.size()==10)history.poll();
            history.add(myCart.get(ele).toString()+" , Total amount Rs: "+price+" ,Delivery Charges Rs: "+totalAmount[0]);
        }
        myCart = new HashMap<>();
    }



    public int addDeliveryCharges(){
        return 40;
    }

    public HashMap<Integer , Item> getMyCart() {
        return myCart;
    }
    public double customerDiscount(double price){
        return price;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
