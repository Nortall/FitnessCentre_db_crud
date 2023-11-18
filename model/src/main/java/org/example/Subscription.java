package org.example;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private Integer id;

    private String type;

    private Integer price;



    private static String generateType(){
        Random random = new Random();
        String[] types = {"Стандартный", "Утренний", "Вечерний", "Расширенный",};
        return types[random.nextInt(types.length)];
    }

    public static List<Subscription> generateSubscription(int countOfSubscription){
        Random random = new Random();
        List<Subscription> subscriptions = new ArrayList<>();
        Set<Integer> usedId = new HashSet<>();
        for(int i = 0; i < countOfSubscription; i++){
            Integer subscription_id = Helper.generateUniqueId(usedId);
            String type = generateType();
            Integer price = random.nextInt(5000) + 800;

            Subscription subscription = new Subscription(subscription_id, type, price);
            subscriptions.add(subscription);
        }
        return subscriptions;
    }


}

