package Assignments.Assign3;

import java.util.ArrayList;

public class Healer extends Player{
    public Healer(int id) {
        super(id,800d);
    }
    @Override
    public  Player specialPower(Player userVote , ArrayList<Player> list){
        if(userVote == null){
            int index = generate(list.size());
            userVote= list.get(index-1);
        }
        return userVote;
    }

}
