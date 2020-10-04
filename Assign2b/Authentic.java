package Assignments.Assign2b;

public class Authentic extends Restaurant{
    public Authentic(String name) {
        super(name);
    }
    @Override
    public int calculateRewards(double price) {
        return ((int)(price/200))*25;
    }

    @Override
    public String toString() {
        return super.toString()+" ,type : Authenticate";
    }

    @Override
    public double restaurantDiscount(double price) {
        price -= (getDiscount()/100d)*price;
        if(price>100)price-=50;
        return price;
    }
}
