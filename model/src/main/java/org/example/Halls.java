package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Halls {
    private Integer id;
    private String name;
    private Integer square;

    public static List<Halls> generateHalls(int countOfHalls) {
        List<Halls> hallsList = new ArrayList<>();
        Set<Integer> usedIds = new HashSet<>();


        Random random = new Random();

        for (int i = 0; i < countOfHalls; i++) {
            String name = "HallName_" + i;
            Integer hall_id = Helper.generateUniqueId(usedIds);
            Integer square = random.nextInt(1000) + 100;

            Halls hall = new Halls(hall_id, name, square);
            hallsList.add(hall);
        }

        return hallsList;
    }

}
