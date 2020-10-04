package Assignments.Assign2b;

import java.util.HashMap;

public class AmountCalculator {
    private final Restaurant restaurant;
    private final Customer customer;

    public AmountCalculator(Restaurant restaurant, Customer customer) {
        this.restaurant = restaurant;
        this.customer = customer;
    }
    public double[] calculate(){
        HashMap<Integer,Item> myCart = customer.getMyCart();
        double price = 0d;
        for(Integer item : myCart.keySet()){
            price+=( myCart.get(item).getPrice() - (myCart.get(item).getOffer()/100d)*(double)myCart.get(item).getPrice())*myCart.get(item).getQuantity();

        }
        price = restaurant.restaurantDiscount(price);
        price = customer.customerDiscount(price);
        return new double[]{customer.addDeliveryCharges(), price};
    }
    public double[] itemRemover(int x) {
        customer.getMyCart().remove(x);
        return calculate();

    }
    public void updateReward(double price){
        double subReward;
        if(customer.getRewards()-price>=0){
            subReward =customer.getRewards() -  price;
        }
        else{
            double total = price- customer.getRewards();
            subReward = 0d;
            customer.setWallet(customer.getWallet()-total);
        }
        int newRewards = restaurant.calculateRewards(price);
        subReward+=newRewards;
        customer.setRewards(subReward);
        restaurant.setRewards(restaurant.getRewards()+newRewards);

    }
}
