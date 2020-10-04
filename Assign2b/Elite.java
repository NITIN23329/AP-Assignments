package Assignments.Assign2b;

public class Elite extends Customer{
    public Elite(String name) {
        super(name);
    }

    @Override
    public int addDeliveryCharges() {
        return 0;
    }
    @Override
    public double customerDiscount(double price) {
        if(price>200)
            price-=50;
        return price;
    }
    @Override
    public String toString() {
        return super.toString()+ " , type : Elite ";
    }
}
