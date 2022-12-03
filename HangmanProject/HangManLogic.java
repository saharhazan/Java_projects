package sample;
/* Author: Sahar Hazan
 * Date: 16.12.2022
 * Maman 13 - Question 1
 */

import java.util.ArrayList;

public class HangManLogic {

     String guessWord;
     private ArrayList<String> guesses = new ArrayList<String>();
     String check = "";
     int lives = 6;
     boolean currGuess; 


    // Getters and Setters 
    public String getWord() {return guessWord;}
    public void setWord(String str) {this.guessWord = str;}
    public int getLives(){return lives;}
    public void setLives(int lives){this.lives= lives;}
    public String getCheck(){return check;}

    // Restart
    public void restart(){
        guesses.clear();
        guessWord = "";
        check = "";
        lives = 6;
        currGuess = false;
    }
    
    public String printLines(String str){ 
        for (int i = 0; i < str.length(); i++) {
            check += "_";
        }
        return check;
    }

    // get input from user and check if it's true
    public void checkGuess(String guess){
        currGuess = false;
        guesses.add(guess);
        char letter = guesses.get(guesses.size()-1).charAt(0);
        for (int i = 0; i < guessWord.length(); i++) {
           if(letter == guessWord.charAt(i)){
                currGuess = true;
                check = check.substring(0,i) + guessWord.charAt(i) + check.substring(i+1, check.length());
                //guesses.add(currentGuess);
           }
        }
        if(!currGuess){
            lives--;
        }
        if(isWin()){
            System.out.println("Nice! the word is : " + check);
        };
    }

    public boolean isWin(){return guessWord.equals(check);}
    
}
