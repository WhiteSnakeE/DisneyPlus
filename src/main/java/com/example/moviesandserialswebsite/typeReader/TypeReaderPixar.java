package com.example.moviesandserialswebsite.typeReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TypeReaderPixar {
    private final File pixarTXT =
            new File("F:\\Users\\Vlad\\IdeaProjects\\MoviesAndSerialsWebSite\\src\\main\\resources\\moviesTXT\\pixar.txt");
    @Autowired
    FindTextLine findTextLine;

    public String findFirstType(){return findTextLine.find(pixarTXT,1);}
    public String findSecondType(){
        return findTextLine.find(pixarTXT,2);
    }
    public String findThirdType(){
        return findTextLine.find(pixarTXT,3);
    }
    public String findFourthType(){
        return findTextLine.find(pixarTXT,4);
    }
    public String findFifthType(){
        return findTextLine.find(pixarTXT,5);
    }
    public String findSixthType(){return findTextLine.find(pixarTXT,6);}
}
