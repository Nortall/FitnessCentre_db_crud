package org.example;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    private Integer id;
    private String name;
    private String surname;
    private String phone_number;

    public static List<Customers> generateCustomers(int countOfCustomers) {
        List<Customers> customersList = new ArrayList<>();
        Set<Integer> usedId = new HashSet<>();

        for (int i = 0; i < countOfCustomers; i++) {
            Integer customer_id = Helper.generateUniqueId(usedId);
            String name = "CustomerName_" + i;
            String surname = "CustomerSurname_" + i;
            String phone_number = Helper.generatePhoneNumber();

            Customers customer = new Customers(customer_id,name, surname, phone_number);
            customersList.add(customer);
        }

        return customersList;
    }


}

