package Assignments.Assign3;

import java.util.ArrayList;

public class Mafia extends Player implements Comparable<Player>{
    public Mafia(int id) {
       this(id,2500d);
    }
    public Mafia(int id,double hp){
        super(id,hp);
    }

    @Override
   public  Player specialPower(Player userVote, ArrayList<Player> list){
        if(userVote == null){
            int index = generate(list.size());
            Player p = list.get(index-1);
            if(!p.getClass().equals(this.getClass()))
                return p;
            else return specialPower(null,list);
        }
        return userVote;
   }

    @Override
    public int compareTo(Player o) {
        return Double.compare(this.getHp(),o.getHp());

    }
}
