/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static constants.Constants.COLORS;
import static constants.Constants.MAX_PEGS;
import static constants.Constants.codeColors;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mfch9
 */
public class Codebreaker implements ICodebreaker{
    
    //Member variable
    //private to public for size on mastermindui
    public ArrayList<Color> codebreakerAttempt;
    
    //Getter for member variable
    public ArrayList<Color> getCodebreakerAttempt() {

        return codebreakerAttempt;
    }

    //Setter for member variable
    public void setCodebreakerAttempt(ArrayList<Color> codebreakerAttempt) {
        this.codebreakerAttempt = codebreakerAttempt;
    }
    
    //Custom constructor for Codebreaker
    public Codebreaker() {
        codebreakerAttempt = new ArrayList();
    }
    
    //public method
    public void consoleAttempt(){
        
        Scanner scnr = new Scanner(System.in);
        removeAll(); //clear codebreakerAttempt.
        //Instantiate string with acceptable colors
        //arranged in same order as constants.
        String[] guessColor = {"BLUE", "BLACK", "ORANGE", "WHITE", 
            "YELLOW", "RED", "GREEN", "PINK"};
        int i;
        int index = 0;
        
        //Ask for color until 4 acceptable colors have been chosen.
        System.out.println("\n Enter your colors in left to right order\n" +
                "Use BLUE, BLACK, ORANGE, WHITE, YELLOW, RED, GREEN, PINK:");
        while (codebreakerAttempt.size() < MAX_PEGS){
            String userInput;
            boolean colorPresent = false;
            userInput = scnr.next();
            userInput = userInput.toUpperCase(); 
            
            //Check validity of color.
            for (i = 0; i < COLORS; ++i){    
                if (guessColor[i].equals(userInput)){
                    colorPresent = true;
                    index = i;
                } 
            }
            
            //If the color is valid, add to codebreakerAttempt. Print color.
            if (colorPresent == true){
                System.out.println("You entered: " + userInput);
                codebreakerAttempt.add(codeColors.get(index));
            }
            //If color is invalid. Print message. Loop will restart.
            else
                System.out.println("Invalid color choice, try again");
        }
        
        //Print codbreakersAttempt.
        System.out.println("\nCodebreaker's Attempt");
        for (Color color : codebreakerAttempt){
            System.out.println(color);
        }
        System.out.println();
    }
    
    //Public method removeAll attempts from the codebreakerAttempt
    public void removeAll(){
        System.out.println("\nCodebreaker's Attempt");
        for (Color color : codebreakerAttempt){
            System.out.println(color);
        }
        System.out.println();
        codebreakerAttempt.removeAll(codebreakerAttempt);
    }
    
    //Implement checkCode from ICodebreaker
    /**
     *
     * @param attempt
     */
    @Override
    public void checkCode (ArrayList<Color> attempt){
    }
    
}
