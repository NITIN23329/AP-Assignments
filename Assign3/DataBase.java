package Assignments.Assign3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase extends RandomNumberGenerator{
    public static Team<Mafia> mafias;
    public static Team<Detective> detectives;
    public static Team<Healer> healers;
    public static Team<Commoner> commoners;
    public static Map<Integer,Player> currentPlayers;
    public static ArrayList<Player> currentList;

    public static ArrayList<Mafia> allMafias;
    public static ArrayList<Detective> allDetectives;
    public static ArrayList<Healer> allHealers;
    public static ArrayList<Commoner> allCommoners;

    public static Player userFirst;

    public DataBase() {
    }

    public DataBase(int size) {
        mafias=new Team<>(size/5);
        detectives=new Team<>(size/5);
        healers = new Team<>(Math.max(1,size/10));
        commoners = new Team<>(size-mafias.size()-detectives.size()- healers.size());
        currentPlayers=new HashMap<>();
        currentList =new ArrayList<>();
        allMafias=new ArrayList<>();
        allDetectives=new ArrayList<>();
        allHealers=new ArrayList<>();
        allCommoners=new ArrayList<>();
        assignCharacters(size);

    }

    private void assignCharacters(int size) {
        for (int i=0;i<size/5;){
            int x = generate(size);
            if (!currentPlayers.containsKey(x)) {
                Mafia p = new Mafia(x);
                currentPlayers.put(x,p);
                mafias.add(p);
                currentList.add(p);
                allMafias.add(p);
                i++;
            }
        }
        for (int i=0;i<size/5;){
            int x = generate(size);
            if (!currentPlayers.containsKey(x)) {
                Detective p = new Detective(x);
                currentPlayers.put(x,p);
                detectives.add(p);
                currentList.add(p);
                allDetectives.add(p);
                i++;
            }
        }
        for (int i=0;i<Math.max(1,size/10);){
            int x = generate(size);
            if (!currentPlayers.containsKey(x)) {
                Healer p = new Healer(x);
                currentPlayers.put(x,p);
                healers.add(p);
                currentList.add(p);
                allHealers.add(p);
                i++;
            }
        }

        for(int x=1;x<=size;x++){
            if(!currentPlayers.containsKey(x)){
                Commoner p = new Commoner(x);
                currentPlayers.put(x,p);
                commoners.add(p);
                currentList.add(p);
                allCommoners.add(p);
            }
        }
    }

    public  void setUser(Player user) {
       userFirst = user;
    }
}
