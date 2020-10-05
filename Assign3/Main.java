package Assignments.Assign3;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main extends DataBase {
    static DataBase data;
    public static void main(String[] args) {
        System.out.println("Welcome to Mafia");
        int noOfPlayers ;
        while (true){
            System.out.println("Enter number of Players");
            noOfPlayers = integerInput();
            if(noOfPlayers<6) System.out.println("Total Players must be at least 6");
            else break;
        }
        data = new DataBase(noOfPlayers);
        boolean isOk=false;
        Player user =null;
        while (!isOk){
            System.out.println("Choose your character : ");
            System.out.println("1 for Mafia\n2 for Detective\n3 for Healer\n4 for Commoner\n5 to assign randomly ");
            int x = integerInput();
            int random;
            switch (x){
                case 1:
                    random = generate(mafias.size());
                    isOk=true;
                    user=mafias.get(random-1);
                   break;
                case 2:
                    random = generate(detectives.size());
                    user=detectives.get(random-1);
                    isOk=true;
                    break;
                case 3:
                    random = generate(healers.size());
                    user=healers.get(random-1);
                    isOk=true;
                    break;
                case 4:
                    random = generate(commoners.size());
                    user=commoners.get(random-1);
                    isOk=true;
                    break;
                case 5:
                    random=generate(currentList.size());
                    user = currentList.get(random-1);
                    isOk=true;
                    break;
                default:
                    System.out.println("Enter number 1 to 5 only");
            }
        }
        data.setUser(user);

        System.out.println("You are "+user.toString());
        if(user.getClass().equals(Commoner.class))
            System.out.println("You are Commoner ");
        else if(user.getClass().equals(Detective.class))
            System.out.println("You are Detective. All detectives are : "+detectives);
        else if(user.getClass().equals(Mafia.class))
            System.out.println("You are Mafia. All Mafias are : "+mafias);
        else System.out.println("You are Healer. All Healers are : "+healers);

        int roundCounter=1;
        while (mafias.size()<(currentPlayers.size()-mafias.size()) && mafias.size()!=0){
            System.out.println("Round "+roundCounter);
            System.out.print(currentPlayers.size()+" Players are remaining : ");
            for(Integer ele : currentPlayers.keySet())
                System.out.print(currentPlayers.get(ele));
            System.out.println("are alive");
            Player choosedMafia=null;
            Player choosedDetective=null;
            Player choosedHealer=null;
            Player died = null;

                if ( user!=null && user.getClass().equals(Mafia.class)) {
                    while (true) {
                        System.out.println("Choose a player to kill");
                        int x = integerInput();
                        if (!currentPlayers.containsKey(x))
                            System.out.println("Choose only from alive players");
                        else if (currentPlayers.get(x).getClass().equals(Mafia.class))
                            System.out.println("You can not chose a mafia to kill");
                        else {
                            choosedMafia = currentPlayers.get(x);
                            break;
                        }
                    }
                } else {
                    if(mafias.size()>0)
                        choosedMafia = mafias.get(0).specialPower(null, currentList);
                    System.out.println("Mafias have chosen their target");
                }

                if ( user!=null && user.getClass().equals(Detective.class)) {
                    while (true) {
                        System.out.println("Choose a player to test");
                        int x = integerInput();
                        if (!currentPlayers.containsKey(x))
                            System.out.println("Choose only from alive players");
                        else if (currentPlayers.get(x).getClass().equals(Detective.class))
                            System.out.println("You can not test a detective");
                        else {
                            choosedDetective = currentPlayers.get(x);
                            break;
                        }
                    }
                } else {
                    if(detectives.size()>0) {
                        choosedDetective = detectives.get(0).specialPower(null, currentList);
                    }
                    System.out.println("Detectives have chosen someone to test");

                }

            boolean isVoting =true;
            if(choosedDetective!=null) {
                if (!choosedDetective.getClass().equals(Mafia.class)) {
                    if(user!=null && user.getClass().equals(Detective.class))
                        System.out.println(choosedDetective.toString() + " is not a Mafia");
                } else {
                    isVoting = false;
                    if(user!=null && user.getClass().equals(Detective.class))
                        System.out.println(choosedDetective.toString() + " is a mafia");
                    currentPlayers.remove(choosedDetective.getId());
                    currentList.remove(choosedDetective);
                    mafias.remove((Mafia) choosedDetective);
                    if (user!=null && user.equals(choosedDetective))
                        user = null;

                }
            }

                if ( user!=null && user.getClass().equals(Healer.class)) {
                    while (true) {
                        System.out.println("Choose a player to heal");
                        int x = integerInput();
                        if (!currentPlayers.containsKey(x))
                            System.out.println("Choose only from alive Players");
                        else {
                            choosedHealer = currentPlayers.get(x);
                            break;
                        }
                    }
                } else {
                    if(healers.size()>0) {
                        choosedHealer = healers.get(0).specialPower(null, currentList);
                    }
                    System.out.println("Healers have chosen someone to heal");
                }

                if(choosedMafia!=null) {
                    double totalMafiaHp = 0d;
                    int x = 0;
                    for (int i = 0; i < mafias.size(); i++) {
                        totalMafiaHp += mafias.get(i).getHp();
                        if (mafias.get(i).getHp() > 0) x++;
                    }
                    Mafia masterMafia = new Mafia(0, totalMafiaHp);
                    if (masterMafia.compareTo(choosedMafia) >= 0) {
                        choosedMafia.reduceHP(choosedMafia.getHp());
                    } else {
                        choosedMafia.reduceHP(masterMafia.getHp());
                    }

                    double finalHpTOBeDivided = choosedMafia.getHp();
                    double minPowerReqd = finalHpTOBeDivided / x;

                    while (x > 0 && finalHpTOBeDivided>0) {
                       double newFinalHpToBeDivided=0d;

                        for (int i = 0; i < mafias.size(); i++) {
                            if (mafias.get(i).getHp() < minPowerReqd) {
                                finalHpTOBeDivided -= mafias.get(i).getHp();
                                newFinalHpToBeDivided+=minPowerReqd-mafias.get(i).getHp();
                                mafias.get(i).reduceHP(mafias.get(i).getHp());
                            }
                        }
                        for (int i = 0; i < mafias.size(); i++) {
                            if (mafias.get(i).getHp() >= minPowerReqd) {
                                mafias.get(i).reduceHP(finalHpTOBeDivided / x);
                            }
                        }
                        x=0;
                        for (int i = 0; i < mafias.size(); i++) {
                            if (mafias.get(i).getHp() > 0) x++;
                        }
                        finalHpTOBeDivided=newFinalHpToBeDivided;
                        minPowerReqd=finalHpTOBeDivided/x;
                    }
                }

            if(choosedHealer!=null)
                choosedHealer.increaseHP(500);

            if(choosedMafia!=null && choosedMafia.getHp()==0){
                currentPlayers.remove(choosedMafia.getId());
                currentList.remove(choosedMafia);
                if(choosedMafia.getClass().equals(Detective.class))
                    detectives.remove((Detective)choosedMafia);
                else if(choosedMafia.getClass().equals(Commoner.class))
                    commoners.remove((Commoner)choosedMafia);
                else if(choosedMafia.getClass().equals(Healer.class))
                    healers.remove((Healer) choosedMafia);
                if (user!=null && user.equals(choosedMafia))
                    user = null;
                died=choosedMafia;
            }

            System.out.println("*******END OF ACTION*******");
            if(died!=null)
                    System.out.println(died.toString()+" has died");
            else System.out.println("No one died");
            Player votedOut = choosedDetective;
            if(isVoting) {
                Player voting = null;
                if (user != null) {

                    while (true) {
                        System.out.println("Select a person to voted out : ");
                        int x = integerInput();
                        if (!currentPlayers.containsKey(x)) System.out.println("Choose only from alive player");
                        else {
                            voting = currentPlayers.get(x);
                            break;
                        }
                    }
                }
                votedOut = currentList.get(0).voting(currentList, voting);

            }

            System.out.println(votedOut.toString() + " has been voted out");
            currentPlayers.remove(votedOut.getId());
            currentList.remove(votedOut);
            if (votedOut.getClass().equals(Detective.class))
                detectives.remove((Detective) votedOut);
            else if (votedOut.getClass().equals(Commoner.class))
                commoners.remove((Commoner) votedOut);
            else if (votedOut.getClass().equals(Healer.class))
                healers.remove((Healer) votedOut);
            else if (votedOut.getClass().equals(Mafia.class))
                mafias.remove((Mafia) votedOut);
            if (user!=null && user.equals(votedOut))
                user = null;
            System.out.println("*******END OF ROUND "+roundCounter++ +" *********");
        }

        System.out.println("******GAME OVER*******");
        if(mafias.size()==0) System.out.println("Mafias have lost the game");
        else System.out.println("Mafias have won the game");
        System.out.print("All Mafias are : ");
        for(Player ele : allMafias) {
            System.out.print(ele.toString());
            if(ele.equals(userFirst)) System.out.print("(user)  ");
        }
        System.out.println();
        System.out.print("All Detectives are : ");
        for(Player ele : allDetectives) {
            System.out.print(ele.toString());
            if(ele.equals(userFirst)) System.out.print("(user)  ");
        }
        System.out.println();
        System.out.print("All Healers are : ");
        for(Player ele : allHealers) {
            System.out.print(ele.toString());
            if(ele.equals(userFirst)) System.out.print("(user)  ");
        }
        System.out.println();
        System.out.print("All Commoners are : ");
        for(Player ele : allCommoners) {
            System.out.print(ele.toString());
            if(ele.equals(userFirst)) System.out.print("(user)  ");
        }
        System.out.println();

    }
    private static int integerInput() throws NoSuchElementException{
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Enter a number");
                return sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid integer only ");
                sc.nextLine();
            }catch (NoSuchElementException e){
                throw new NoSuchElementException("You can not skip entering a number");
            }
        }
    }
}
