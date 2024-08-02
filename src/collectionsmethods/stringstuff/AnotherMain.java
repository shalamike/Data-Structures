package collectionsmethods.stringstuff;

import java.util.Objects;

public class AnotherMain {
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private String b = "abc";

    public static void main(String[] args){
        AnotherMain b = new AnotherMain();
        Main a = new Main();
        System.out.println(Objects.equals(b.getB(), a.getA()));
    }

}
