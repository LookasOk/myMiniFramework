package utils.testData;

import java.util.Random;

public class RandomTdGenerator {

    public static String randomTd4() {
        Random r = new Random();
        return String.format("Test%04d", r.nextInt(9999));
    }
}
