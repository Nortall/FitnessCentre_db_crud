package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Serialize {
    public static void main(String[] args) throws IOException {
        List<FitnessCentre> fitnessCentres = Generator.generateFitnessCentre(10);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("fitnessCentres.json"), fitnessCentres);
    }
}
