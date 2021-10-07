package ru.stqa.pft.addressbook.generators;

import java.util.Random;

public class RandomGenerator {
    public static int getRandomInteger(int min, int max){
        double dbl = (Math.random()*((max-min)+1)+min);
        return (int) dbl;
    }

    public static String getRandomString(int length)
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String getRandomName(int length){
        String rnd = getRandomString(length);
        return rnd.substring(0, 1).toUpperCase() + rnd.substring(1);
    }

}