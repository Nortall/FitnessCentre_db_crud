package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coaches {

    private Integer id;
    private String name;
    private String surname;
    private Integer experience;
    private String specialization;
    private String phone_number;



    public static List<Coaches> generateCoaches(int countOfCoaches) {
        List<Coaches> coachesList = new ArrayList<>();
        Set<Integer> usedId = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < countOfCoaches; i++) {
            Integer coach_id = Helper.generateUniqueId(usedId);
            String name = "CoachName_" + i;
            String surname = "CoachSurname_" + i;
            Integer experience = random.nextInt(30) + 1; // Генерация опыта от 1 до 30 лет
            String specialization = generateSpecialization();
            String phone_number = Helper.generatePhoneNumber();

            Coaches coach = new Coaches(coach_id, name, surname, experience, specialization, phone_number);
            coachesList.add(coach);
        }

        return coachesList;
    }
    private static String generateSpecialization() {
        String[] specializations = {"Фитнесс", "Йога", "Плавание", "Бокс", "Фигурное катание", "Бодибилдинг", "Смешанные единоборства"};
        Random random = new Random();
        return specializations[random.nextInt(specializations.length)];
    }

}

