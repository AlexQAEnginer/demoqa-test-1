package pages.components;

import java.security.SecureRandom;


public class TestDataGenerashion {
    public static void main(String[] args) {
        System.out.println(randomString(10));
        String userTest = (randomString(10) + ".ru");
        System.out.println(userTest);
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
}
