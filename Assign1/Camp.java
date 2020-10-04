package Assignments.Assign1;

import java.util.ArrayList;

import java.util.Scanner;



class Patient{
    private final String name;
    private final int oxygenLevel;
    private final float bodyTemperature;
    private final int age;
    private final int id;
    private int recoveryDate;
    private static int idGenerator=0;
    private  String hospitalName;

    public Patient(String name, int oxygenLevel, float bodyTemperature, int age) {
        this.name = name;
        this.oxygenLevel = oxygenLevel;
        this.bodyTemperature = bodyTemperature;
        this.age = age;
        idGenerator++;
        this.id = idGenerator;
        recoveryDate = -1;
        hospitalName = "no-name";

    }

    public int getRecoveryDate() {
        return recoveryDate;
    }

    public String getName() {
        return name;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setRecoveryDate(int days){
        recoveryDate = days;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
class Hospital extends Camp{
    private final String name;
    private final int minOxygenLevel;
    private final float maxBodyTemperature;
    private int noOfBedsAvailable ;
    private final ArrayList<Patient> list;

    public Hospital(String name, int minOxygenLevel, float maxBodyTemperature, int noOfBedsAvailable) {
        this.name = name;
        this.minOxygenLevel = minOxygenLevel;
        this.maxBodyTemperature = maxBodyTemperature;
        this.noOfBedsAvailable = noOfBedsAvailable;
        list = new ArrayList<>();
        addPatients();
        setRecoveryDates();
    }

    private void addPatients() {
        for(Patient p : patients){
            if(p.getOxygenLevel()>=this.minOxygenLevel &&  !patientRemover.contains(p) ){
                p.setHospitalName(this.name);
                list.add(p);
                patientRemover.add(p);
                noOfBedsAvailable--;
            }
            if(noOfBedsAvailable==0)return;
        }
        for (Patient p : patients) {
            if (p.getBodyTemperature() <= this.maxBodyTemperature && !patientRemover.contains(p)) {
                p.setHospitalName(this.name);
                list.add(p);
                patientRemover.add(p);
                noOfBedsAvailable--;
            }
            if (noOfBedsAvailable == 0) return;
        }

    }

    private void setRecoveryDates() {
        for(Patient p : list){
            System.out.println("Recovery days for admitted patient ID "+p.getId()+" :- ");
            p.setRecoveryDate(sc.nextInt());
        }
    }

    public int getNoOfBedsAvailable() {
        return noOfBedsAvailable;
    }

    public String getName() {
        return name;
    }

    public int getMinOxygenLevel() {
        return minOxygenLevel;
    }

    public float getMaxBodyTemperature() {
        return maxBodyTemperature;
    }

    public ArrayList<Patient> getList() {
        return list;
    }



}

public class Camp {
    public static Scanner sc = new Scanner(System.in);
    public  static ArrayList<Patient> patients  = new ArrayList<>();
    public  static ArrayList<Patient> patientRemover = new ArrayList<>();
    private final ArrayList<Hospital> hospitals = new ArrayList<>() ;

     public static void main(String[] args){
         Camp camp = new Camp();
         System.out.println("Enter no of Patients :");
         int noOfPatients = sc.nextInt();
         sc.nextLine();
         while (noOfPatients-->0){
             String[] str = sc.nextLine().split(" ");
             Patient p = new Patient(str[0],Integer.parseInt(str[2]),Float.parseFloat(str[1]),Integer.parseInt(str[3]));
             Camp.patients.add(p);
         }
         while (findRemaining()!=0){
             System.out.println("Enter number 1 to 9 ");
             byte querry = sc.nextByte();
             sc.nextLine();
             switch (querry){
                 case 1 :
                     camp.addHospitals();
                     break;
                 case 2 :
                     camp.patientRemover();
                     break;
                 case 3 :
                     camp.hospitalRemover();
                     break;
                 case 4 :
                     System.out.println(findRemaining()+" patients");
                     break;
                 case 5 :
                     camp.countOpenHospitals();
                     break;
                 case 6 :
                     camp.hospitalDetails();
                     break;
                 case 7 :
                     camp.patientData();
                     break;
                 case 8 :
                     camp.displayPatients();
                     break;
                 case 9 :
                     camp.hospitalPatients();
                     break;
                 default:
                     System.out.println("Please enter Number 1 to 9 only");
             }

         }

     }

    private static int findRemaining() {
        return Camp.patients.size()- Camp.patientRemover.size();
    }

    private void addHospitals(){
        System.out.println("Enter Health Care Institute name :");
        String name = sc.nextLine();
        System.out.println("Temperature criteria :");
        float temp = sc.nextFloat();
        System.out.println("Oxygen level criteria :");
        int oxygen = sc.nextInt();
        System.out.println("No of available beds :");
        int beds = sc.nextInt();
        System.out.println(name+"\nTemperature should be <= "+temp+
                "\nOxygen levels should be >= "+oxygen+"\nNumber of Available beds : "+beds
                +"\nAdmission Status : OPEN ");
        Hospital h = new Hospital(name,oxygen,temp,beds);
        hospitals.add(h);
    }

    private void patientRemover(){
        patients.removeAll(patientRemover);
        System.out.println("Account ID removed of admitted patients : ");
        for(Patient p : patientRemover)
            System.out.println(p.getId());
        patientRemover = new ArrayList<>();

    }
    private void hospitalRemover(){
        ArrayList<Hospital> hospitalRemover = new ArrayList<>();
        System.out.println("Accounts removed of Institute whose admission is closed : ");
        for(Hospital h : hospitals)
            if(h.getNoOfBedsAvailable()==0){
                System.out.println(h.getName());
                hospitalRemover.add(h);
            }
        hospitals.removeAll(hospitalRemover);
    }
    private void countOpenHospitals() {
        int counter = 0;
        for( Hospital ho : hospitals)
            if(ho.getNoOfBedsAvailable()>0)
                counter++;
        System.out.println(counter+" institutes are admitting patients currently");
    }

    private void hospitalDetails() {
        System.out.println("Health Care Institute name : ");
        String str = sc.nextLine();
        for(Hospital hos : hospitals)
            if(hos.getName().equals(str)){
                System.out.println(hos.getName()+"\nTemperature should be <= "+hos.getMaxBodyTemperature()+
                        "\nOxygen levels should be >= "+hos.getMinOxygenLevel()+
                        "\nNumber of Available beds : "+hos.getNoOfBedsAvailable());
                if(hos.getNoOfBedsAvailable()>0)
                    System.out.println("Status : Open");
                else System.out.println("Status : Closed");
                break;
            }
    }

    private void patientData() {
        System.out.println("Patient ID : ");
        int x = sc.nextInt();
        Patient p = null;
        for(Patient pa : patients) {
            if (pa.getId() == x) {
                p = pa;
                break;
            }
        }
        boolean isFound = false;
        if(p==null){
            for(Hospital ho:hospitals){
                for(Patient po : ho.getList()){
                    if(po.getId()==x){
                        p=po;
                        isFound=true;
                        break;
                    }
                }
                if(isFound)break;
            }
        }
        if(p==null) System.out.println("No data found");
        else{
            System.out.println("Patient name : "+p.getName()+"\nPatient ID : "+
                    p.getId()+"\nTemperature : "+p.getBodyTemperature()+
                    "\nOxygen level : "+p.getOxygenLevel()+"\nAge : "+p.getAge());
            if(p.getHospitalName().equals("no-name")){
                System.out.println("Status : Not Admitted");
            }
            else{
                System.out.println("Status : Admitted , Health Care Institute : "+p.getHospitalName());
            }
        }
    }

    private void hospitalPatients() {
        System.out.println("Enter Health Care Institute name :");
        String str = sc.nextLine();
        ArrayList<Patient> data = new ArrayList<>();
        for(Hospital h : hospitals){
            if(h.getName().equals(str)){
                data=h.getList();
                break;
            }
        }
        for(Patient p : data)
            System.out.println("Name: "+p.getName()+" , Recovery date : "+p.getRecoveryDate());
    }

    private void displayPatients() {

        for(Patient p : patients){
            System.out.println("Name: "+p.getName()+" , ID No. "+p.getId());
        }

    }

}

/*
8
Ram 98.4 94 25
Sam 100.4 92 55
Jim 104 91 61
Tim 99 93 60
Kim 100 91 48
nitin 106 80 18
aaksh 109 85 19
zoe 97 70 12
 */