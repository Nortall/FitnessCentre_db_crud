package org.example;

import java.util.Random;
import java.util.Set;

public class Helper {

    public static Integer generateUniqueId(Set<Integer> usedIds) {
        Random random = new Random();
        Integer id;
        do {
            id = random.nextInt(1000000) + 1;
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }


    public static String generatePhoneNumber() {
        Random random = new Random();
        return "+8-950-" + String.format("%04d", random.nextInt(10000));
    }
}
