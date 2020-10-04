package Assignments.Assign3;

import java.util.ArrayList;

public class Commoner extends Player{
    public Commoner(int id) {
        super(id,1000d);
    }
    @Override
    public  Player specialPower(Player userVote , ArrayList<Player> list){
        //no special powers
       return null;
    }

}
