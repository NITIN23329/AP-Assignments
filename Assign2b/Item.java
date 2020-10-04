package Assignments.Assign2b;

public class Item {
    private final int id;
    private  String itemName;
    private int price;
    private int quantity;
    private String category;
    private int offer;

    public Item(int id , String itemName, int price, int quantity, String catogree, int offer) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        category = catogree;
        this.offer = offer;
        this.id = id;
    }
    public Item clone(int quantity){
        return new Item(this.id, this.itemName, this.price,quantity, this.category , this.offer);

    }

    @Override
    public String toString() {
        return
                "item id : " + id +
                        ", name : " + itemName +
                        ", price : " + price +
                        ", quantity : " + quantity +
                        ", type : " + category +
                        ", offer : " + offer +" % off";
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public void setOffer(int offer) {
        this.offer = offer;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getPrice() {
        return price;
    }

    public int getOffer() {
        return offer;
    }

    public int getId() {
        return id;
    }

}
