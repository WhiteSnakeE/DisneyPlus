package com.example.moviesandserialswebsite.typeReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TypeReaderMarvel {
    private final File marvelTXT =
            new File("F:\\Users\\Vlad\\IdeaProjects\\MoviesAndSerialsWebSite\\src\\main\\resources\\moviesTXT\\marvel.txt");
    @Autowired
    FindTextLine findTextLine;

//    public String findFirstType(){return findTextLine.find(marvelTXT,1);}
//    public String findSecondType(){
//        return findTextLine.find(marvelTXT,2);
//    }
    public String findThirdType(){
        return findTextLine.find(marvelTXT,3);
    }
    public String findFourthType(){
        return findTextLine.find(marvelTXT,4);
    }
    public String findFifthType(){
        return findTextLine.find(marvelTXT,5);
    }
    public String findSixthType(){return findTextLine.find(marvelTXT,6);}
}
