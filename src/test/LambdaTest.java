package test;

public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("lambda测试");
            }
        };
        r.run();
        Runnable r1 =  () -> System.out.println("lambda测试");
        r1.run();
    }
}
