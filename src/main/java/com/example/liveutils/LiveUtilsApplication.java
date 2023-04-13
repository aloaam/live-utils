package com.example.liveutils;

import csvreader.CsvReader;
import csvreader.models.LaLigaRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class LiveUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveUtilsApplication.class, args);

        InputStream inputStream = ClassLoader.getSystemResourceAsStream("es.1.csv");
        CsvReader csvReader = new CsvReader();
        List<LaLigaRecord> objects = csvReader.read(inputStream);

    }

}
