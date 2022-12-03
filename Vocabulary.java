package sample;
/* Author: Sahar Hazan
 * Date: 16.12.2022
 * Maman 13 - Question 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Vocabulary {

    private ArrayList<String> words = new ArrayList<String>();

    public void fileToArrayList (){
        try{
            Scanner input = new Scanner(new File("words.txt"));
            while(input.hasNext())
                words.add(input.next());
            input.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public String getRandomizeWord(){
        Random rand = new Random();
        String randomWord = words.get(rand.nextInt(words.size()-1));
        return randomWord;
    }  
}
