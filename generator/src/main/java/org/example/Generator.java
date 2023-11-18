package org.example;

import java.util.*;

public class Generator {
    public static List<FitnessCentre> generateFitnessCentre(int countOfFitnessCentre){
        List<FitnessCentre> fitnessCentreList = new ArrayList<>();
        Set<Integer> usedIds = new HashSet<>();
        for(int i = 0; i < countOfFitnessCentre; i++){
            String name = "FitnessCentreName_" + i;
            Integer gym_id = Helper.generateUniqueId(usedIds);
            List<Halls> halls = Halls.generateHalls(3);
            List<Coaches> coachesList = Coaches.generateCoaches(3);
            List<Services> servicesList = Services.generateServices(3);
            List<Customers> customersList = Customers.generateCustomers(10);
            List<Subscription> subscriptionList = Subscription.generateSubscription(3);

            FitnessCentre fitnessCentre = new FitnessCentre(gym_id, name , halls, coachesList, servicesList, customersList, subscriptionList);
            fitnessCentreList.add(fitnessCentre);
            System.out.println(i);
        }
        return fitnessCentreList;
    }

}
