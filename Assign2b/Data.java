package Assignments.Assign2b;

import java.util.Scanner;

public class Data {
    private final int id;
    private final String name;
    private double rewards;
    public static Scanner sc = Zotato.sc;
    public Data(int id, String name) {
        this.id = id;
        this.name = name;
        this.rewards = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRewards() {
        return rewards;
    }

    public void setRewards(double rewards) {
        this.rewards = rewards;
    }
}
