package Assignments.Assign2b;

public class Special extends Customer{
    public Special(String name) {
        super(name);
    }
    @Override
    public int addDeliveryCharges() {
       return 25;
    }

    @Override
    public double customerDiscount(double price) {
         if(price>200)
            price-=25;
         return price;
    }

    @Override
    public String toString() {
        return super.toString()+ " , type : Special ";
    }

}
