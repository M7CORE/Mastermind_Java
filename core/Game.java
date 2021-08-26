/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import static constants.Constants.MAX_ATTEMPTS;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author mfch9
 */
public class Game implements IGame{
    
    
    //Member Variables
    private int attempt;
    private Codebreaker codebreaker;
    private Codemaker codemaker;
    
    //Custom Constructor for Game
    public Game (){
        System.out.println("generated the secret code!");
        codemaker = new Codemaker();
        codebreaker = new Codebreaker();
        attempt = 0;
//        play();
    }
    
    //Getters and setter for member variables
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public void setCodebreaker(Codebreaker codebreaker) {
        this.codebreaker = codebreaker;
    }

    public void setCodemaker(Codemaker codemaker) {
        this.codemaker = codemaker;
    }
    
    public int getAttempt() {
        return attempt;
    }

    public Codebreaker getCodebreaker() {
        return codebreaker;
    }
    
    public Codemaker getCodemaker() {
        return codemaker;
    }
    
    
    
    //Implement IGame interface method
    @Override
    public void play(){
        int i;
        int j;
        
        System.out.println("\nAttempt 1");
        ArrayList<Color> callCodebreaker = codebreaker.getCodebreakerAttempt();
        codemaker.checkAttemptedCode(callCodebreaker);
        ArrayList<Color> callCodemaker = codemaker.getCodemakerResponse();
        
        //Loop through attempts.
        for (i = 0; i < MAX_ATTEMPTS - 1; ++i){
            if((codemaker.getCodemakerResponse().size() == 4) && (codemaker.getCodemakerResponse().get(0).equals(Color.RED)) &&
               (codemaker.getCodemakerResponse().get(1).equals(Color.RED)) &&
               (codemaker.getCodemakerResponse().get(2).equals(Color.RED)) &&
               (codemaker.getCodemakerResponse().get(3).equals(Color.RED)))
                break;
            else{
                System.out.println("\nAttempt " + (i + 2));
                ArrayList<Color> callCodebreaker2 = codebreaker.getCodebreakerAttempt();
                codemaker.checkAttemptedCode(callCodebreaker);
                ArrayList<Color> callCodemaker2 = codemaker.getCodemakerResponse();
            }
        }
        if ((i >= 9))
            System.out.println("You have run out of attempts.");
    }   
}
