package appPack;

public class TestFor {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println("Ali"+i);
        }
        long end = System.currentTimeMillis();
        long bettween = end - start;
        System.out.println(bettween);
    }

}
