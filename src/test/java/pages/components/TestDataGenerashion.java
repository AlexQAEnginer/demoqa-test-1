package pages.components;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;


public class TestDataGenerashion {
    public static void main(String[] args) {
        System.out.println(randomString(10));
        String userTest = (randomString(10) + ".ru");
        System.out.println(userTest);
        System.out.println(randomInt(1,78));

        String[] names = {"a","b","c","d","e"};
        System.out.println(randomItem(names));
    }
    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min,max -1);
    }

    public static String randomString(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    public static String randomMail(){
        return randomString(10) + ".ru";
    }
    public static String randomItem(String[] values){
        int index = randomInt(1,values.length);
        return values[index];
    }
}
