import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Demo {

    public static void main(String[] args) {
        Test1 t17 = new Test1("17w1w","17w2w");
        Test1 t18 = new Test1("18w1w","18w2w");
        Test t1 = new Test("q1q","q2q","q3q","q4q","q5q","q6q",t17,t18);

        Test t2 = new Test();
        t2.setQ7(new Test1());

    }
}

class Test {
    String q1;
    String q2;
    String q3;
    String q4;
    String q5;
    String q6;
    Test1 q7;
    Test1 q8;

    public Test() {
    }

    public Test(String q1, String q2, String q3, String q4, String q5, String q6, Test1 q7, Test1 q8) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public String getQ6() {
        return q6;
    }

    public void setQ6(String q6) {
        this.q6 = q6;
    }

    public Test1 getQ7() {
        return q7;
    }

    public void setQ7(Test1 q7) {
        this.q7 = q7;
    }

    public Test1 getQ8() {
        return q8;
    }

    public void setQ8(Test1 q8) {
        this.q8 = q8;
    }
}

class Test1 {
    String w1;
    String w2;

    public Test1() {
    }

    public Test1(String w1, String w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }
}
