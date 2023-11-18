package org.example;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services {

    private Integer id;
    private String name;
    private Integer price;



    public static List<Services> generateServices(int countOfServices) {
        Random random = new Random();

        List<Services> servicesList = new ArrayList<>();
        Set<Integer> usedIds = new HashSet<>();

        for (int i = 0; i < countOfServices; i++) {
            String name = generateName();
            Integer service_id = Helper.generateUniqueId(usedIds);
            Integer price = random.nextInt(5000) + 300;

            Services service = new Services(service_id, name, price);
            servicesList.add(service);
        }

        return servicesList;
    }

    private static String generateName(){
        String[] names = {"Тренажерный зал", "Бассейн", "Массаж", "Групповые занятия", "Каток", "Релакс"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }


}
