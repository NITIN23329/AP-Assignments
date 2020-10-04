package Assignments.Assign3;

import java.util.ArrayList;

public class Detective extends Player {
    public Detective(int id) {
        super(id,800d);
    }
    @Override
    public  Player specialPower(Player userVote , ArrayList<Player> list){
        //return Player if mafia is selected else return null;
        if(userVote == null){
            while (true){
                int index = generate(list.size());
                Player p = list.get(index-1);
                if(!p.getClass().equals(this.getClass())) {
                    userVote = p;
                    break;
                }
            }
        }
        if(userVote.getClass().equals(Mafia.class))return userVote;
        return null;
    }
}
