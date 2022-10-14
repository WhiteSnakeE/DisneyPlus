package com.example.moviesandserialswebsite.typeReader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
@Component
public class FindTextLine {
    public String find(File file, int lineCount){
        int count = 1;
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                if (count == lineCount) {
                    break;
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
