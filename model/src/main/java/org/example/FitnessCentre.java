package org.example;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FitnessCentre {
    private Integer id;
    private String name;
    private List<Halls> halls;
    private List<Coaches> coaches;
    private List<Services> services;
    private List<Customers> customers;
    private List<Subscription> subscriptions;
}

