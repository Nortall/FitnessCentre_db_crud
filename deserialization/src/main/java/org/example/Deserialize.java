package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Deserialize {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("fitnessCentres.json");
        List<FitnessCentre> fitnessCentres = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, FitnessCentre.class));

        for (FitnessCentre fitnessCentre : fitnessCentres){
            System.out.println(fitnessCentre);
        }
    }
}
