package com.example.moviesandserialswebsite.typeReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
@Component
public class TypeReaderDisney {
    private final File disneyTXT =
            new File("F:\\Users\\Vlad\\IdeaProjects\\MoviesAndSerialsWebSite\\src\\main\\resources\\moviesTXT\\disney.txt");
    @Autowired
    FindTextLine findTextLine;

    public String findFirstType(){return findTextLine.find(disneyTXT,1);}
    public String findSecondType(){
        return findTextLine.find(disneyTXT,2);
    }
    public String findThirdType(){
        return findTextLine.find(disneyTXT,3);
    }
    public String findFourthType(){
        return findTextLine.find(disneyTXT,4);
    }
    public String findFifthType(){
        return findTextLine.find(disneyTXT,5);
    }
}
