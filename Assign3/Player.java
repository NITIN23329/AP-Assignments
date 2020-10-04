package Assignments.Assign3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Player extends RandomNumberGenerator {
    private final int id;
    private double hp;

    public Player(int id,double hp) {
        this.id = id;
        this.hp = hp;
    }

    public abstract Player specialPower(Player userVote , ArrayList<Player> list);

    public void reduceHP(double n){
        hp=Math.max(0,hp-n);
    }
    public void increaseHP(double n){
        hp+=n;
    }

    public  Player voting(ArrayList<Player> list ,Player userVote ) {
        Map<Player, Integer> votes = new HashMap<>();
        int noOfVotes = list.size();
        int maxVotes = 0;
        if (userVote !=null) {
            votes.put(userVote,1);
            maxVotes = 1;
            noOfVotes--;
        }
        for (int i = 0; i < noOfVotes; i++) {
            int x = generate(list.size());
            Player p = list.get(x-1);
            if(!votes.containsKey(p))votes.put(p,0);
            votes.put(p,votes.get(p)+1);
           maxVotes=Math.max(maxVotes,votes.get(p));
        }
        int noOfMax=0;
        Player votedOut=null;
        for (Player p : votes.keySet()){
            if(votes.get(p)==maxVotes){
                noOfMax++;
                votedOut=p;
            }
        }
        if(noOfMax>1)return voting(list,userVote);
        else return votedOut;
    }

    public int getId() {
        return id;
    }

    public double getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Player" +id+"  " ;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass()!=obj.getClass())
            return false;
        Player p = (Player)obj;
        return this.getId()==p.getId();

    }
}
