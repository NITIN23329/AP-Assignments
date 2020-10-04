package Assignments.Assign3;
import java.util.ArrayList;

public class Team<T extends Player> {
    ArrayList<T> list;

    public Team(int size) {
        this.list = new ArrayList<>(size);
    }
    public void add(T obj){
        list.add(obj);
    }
    public T get(int index){
        return list.get(index);
    }
    public void remove(T obj){
        for(T ele : list)
            if(ele.equals(obj)){
                list.remove(ele);
                break;
            }
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        for (T ele : list)
            str.append("Player").append(ele.getId()).append("  ");
        return str.toString();

    }
    public int size(){
        return list.size();
    }
}
