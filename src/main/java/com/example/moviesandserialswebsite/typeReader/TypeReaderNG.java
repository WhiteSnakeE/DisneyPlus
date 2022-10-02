package com.example.moviesandserialswebsite.typeReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TypeReaderNG {
    private final File nationalGeographicTXT =
            new File("F:\\Users\\Vlad\\IdeaProjects\\MoviesAndSerialsWebSite\\src\\main\\resources\\moviesTXT\\national-geographic.txt");
    @Autowired
    FindTextLine findTextLine;

//    public String findFirstType(){return findTextLine.find(nationalGeographicTXT,1);}
//    public String findSecondType(){
//        return findTextLine.find(nationalGeographicTXT,2);
//    }
    public String findThirdType(){
        return findTextLine.find(nationalGeographicTXT,3);
    }
    public String findFourthType(){
        return findTextLine.find(nationalGeographicTXT,4);
    }
    public String findFifthType(){
        return findTextLine.find(nationalGeographicTXT,5);
    }
}
