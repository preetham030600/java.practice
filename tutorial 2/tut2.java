import java.io.File;
import java.util.*;
class Student{
    private String name;
    private int rollNo;
    private int age;
    public Student(String name,int rollNo,int age){
        this.name=name;
        this.rollNo=rollNo;
        this.age=age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRollNo() {
        return rollNo;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean hasSameAge(Student others){
        return this.age==others.getAge();
    }
    public boolean isTwin(Student others){
        boolean sameage=hasSameAge(others);
        boolean samerollno=(this.rollNo==others.getRollNo());
        return sameage && samerollno;
    }

}







public class tut2{
    public static void main(String args[]) throws Exception{
        Student[] students=new Student[500];
        int count =0;
        Scanner sc =new Scanner(new File("students.txt"));
        while(sc.hasNextLine()){
            String line =sc.nextLine();
            if(line.isBlank()) continue;
            String[] parts=line.split(",");
            String name=parts[0];
            int rollNum=Integer.parseInt(parts[1]);
            int age =Integer.parseInt(parts[2]);
            students[count]=new Student(name,rollNum,age);
            count++;


        }
        sc.close();
        System.out.println("Total entries ="+count);
        System.out.println("----------------Pairs Detected--------------");
        int pairs =0;
        for(int i=0;i<count;i++){
            for(int j=i+1;j<count;j++){
                if(students[i].isTwin(students[j])){
                    pairs++;
                    System.out.println("Pairs"+pairs+"Name :"+students[i].getName()+" RollNo:"+students[i].getRollNo()+" Age:"+students[i].getAge()+"   +  "+"Name :"+students[j].getName()+" RollNo:"+students[j].getRollNo()+" Age:"+students[j].getAge());
                }
            }
        }
        System.out.println("Total number of twins:"+pairs);

    }
}