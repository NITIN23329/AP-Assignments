package Assignments.Assign2b;

import java.util.HashMap;

public  class Restaurant extends Data implements IUser{
    private static  int restrauID = 1;
    private int itemID = 1;
    private final HashMap<Integer , Item> itemList;
    private int discount;
    public Restaurant(String name) {
        super(restrauID,name);
        restrauID++;
        itemList = new HashMap<>();
        discount = 0;
    }
    @Override
    public String toString() {
        return "Restaurant id : " + this.getId() +
                ", Restaurant name : " + this.getName()
                ;
    }


    @Override
    public void printRewards() {
        System.out.println("Current rewards : " + getRewards());

    }
    @Override
    public void printItems(){

        for(Integer ele : itemList.keySet())
            System.out.println(itemList.get(ele));
    }

    @Override
    public void printOptions() {
        System.out.println("Welcome "+this.getName());
        System.out.println("Press 1 to Add item\n" +
                "Press 2 to Edit item\n" +
                "Press 3 to Print Rewards\n"+
                "Press 5 to  Exit");
    }

    public void addItem() {
        String name,type;
        int price,quantity,offer;
        sc.nextLine();
        System.out.println("FOOD NAME :");
        name = sc.nextLine();
        System.out.println("ITEM PRICE : ");
        price = sc.nextInt();
        System.out.println("ITEM QUANTITY : ");
        quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("ITEM TYPE");
        type = sc.nextLine();
        System.out.println("OFFER ON ITEM");
        offer = sc.nextInt();
        Item item = new Item(itemID,name,price,quantity,type,offer);
        itemList.put(itemID , item);
        System.out.println(item);
        itemID++;
    }

    public void editItem() {
        System.out.println("CHOOSE ITEMS BY CODE : ");
        printItems();
        int id = sc.nextInt();
        Item update = itemList.get(id);
        System.out.println("CHOOSE AND ATTRIBUTE TO EDIT");
        System.out.println("1)Name \n2)Price \n3)Quantity \n4)Category \n5)Offer");
        int choice = sc.nextInt();
        System.out.print("ENTER NEW ");
        switch (choice){
            case 1 :
                System.out.println("NAME : ");
                String name = sc.nextLine();
                update.setItemName(name);
                break;
            case 2:
                System.out.println("PRICE : ");
                int price = sc.nextInt();
                update.setPrice(price);
                break;
            case 3:
                System.out.println("QUANTITY : ");
                int quantity = sc.nextInt();
                update.setQuantity(quantity);
                break;
            case 4:
                System.out.println("CATEGORY : ");
                String type = sc.nextLine();
                update.setCategory(type);
                break;
            case 5:
                System.out.println("OFFER : ");
                int discount = sc.nextInt();
                update.setOffer(discount);
                break;
            default:
                System.out.println("ENTER VALID INPUT");

        }
        System.out.println(itemList.get(id));
    }
    public void discountOnBillValue() {
        System.out.println("Enter Discount % on overall bill value");
        discount=sc.nextInt();
    }


    public int calculateRewards(double price){
        return ((int)(price/100))*5;
    }
    public HashMap<Integer,Item> getItemList() {
        return itemList;
    }
    public void setQuantity(int id , int sub){
        itemList.get(id).setQuantity(itemList.get(id).getQuantity()-sub);
    }

    public double restaurantDiscount(double price) {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
