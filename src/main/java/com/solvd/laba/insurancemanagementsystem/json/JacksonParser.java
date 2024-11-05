package com.solvd.laba.insurancemanagementsystem.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.laba.insurancemanagementsystem.model.InsuranceManagementSystem;

import java.io.File;
import java.io.IOException;

public class JacksonParser {

    public static void write(InsuranceManagementSystem lists) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("src/main/resources/insurance_management_system.json"), lists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InsuranceManagementSystem read(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InsuranceManagementSystem system = new InsuranceManagementSystem();
        try {
            system = objectMapper.readValue(file, InsuranceManagementSystem.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return system;
    }
}
