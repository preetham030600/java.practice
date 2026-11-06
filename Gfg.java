class GettersandSetters{
    int a;
    int b;
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
    boolean compare(GettersandSetters obj){
        return this.a==obj.a;
    }
    
}
public class Gfg {
    public static void main(String[] args) {
        GettersandSetters obj1=new GettersandSetters();
        obj1.setA(10);
        GettersandSetters obj2=new GettersandSetters();
        obj2.setA(10);
        GettersandSetters obj3=new GettersandSetters();
        obj3.setA(20);
        System.out.println(obj1.compare(obj2));
        System.out.println(obj1.compare(obj3));
}
}

