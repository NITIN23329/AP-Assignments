package Assignments.Assign2b;

public class FastFood extends Restaurant{
    public FastFood(String name) {
        super(name);
    }


    @Override
    public int calculateRewards(double price) {
        return ((int)(price/150))*10;
    }

    @Override
    public String toString() {
        return super.toString()+" ,type : Fast Food";
    }
    @Override
    public double restaurantDiscount(double price) {
        price -= (getDiscount()/100d)*price;
        return price;
    }

}
