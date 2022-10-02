package com.example.moviesandserialswebsite;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        File disneyTXT = new File("F:\\Users\\Vlad\\IdeaProjects\\MoviesAndSerialsWebSite\\src\\main\\resources\\moviesTXT\\disney.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(disneyTXT));
            String line = "";
            int a = 1;
            while((line = reader.readLine()) != null) {
                if (a == 2) System.out.println(line);
                a++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
