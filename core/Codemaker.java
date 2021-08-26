/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import constants.Constants;
import static constants.Constants.MAX_PEGS;
import static constants.Constants.codeColors;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.List;

/**
 *
 * @author mfch9
 */
public class Codemaker implements ICodemaker {
   
    //member variables
    private Set<Color> secretCode;
    private ArrayList<Color> codemakerResponse;
    private boolean codeGuessed;
    
    //Getters and setters for member variables
    public void setSecretCode(Set<Color> secretCode) {
        this.secretCode = secretCode;
    }

    public void setCodemakerResponse(ArrayList<Color> codemakerResponse) {
        this.codemakerResponse = codemakerResponse;
    }

    public Set<Color> getSecretCode() {
        return secretCode;
    }

    public ArrayList<Color> getCodemakerResponse() {
        return codemakerResponse;
    }
    
    public boolean isCodeGuessed(){
        return codeGuessed;
    }
    
    public void setCodeGuessed(boolean codeGuessed){
        this.codeGuessed = codeGuessed;
    }
    
    //Custom constructor
    public Codemaker(){

        codeGuessed = false;
        secretCode = new HashSet();
        codemakerResponse = new ArrayList();
        generateSecretCode();
    }
    
    //Implement ICodemaker methods
    @Override
    public void generateSecretCode(){
        
        Random r = new Random();
        int index;
        Color selectedColor;
        
        //Choose index for code. Generate code and save in secretCode.
        while(secretCode.size() < MAX_PEGS){
            index = r.nextInt(Constants.COLORS);
            selectedColor = new Color(codeColors.get(index).getRGB());
            secretCode.add(selectedColor);
        }
       
        //Print generated code.
        for (Color colors: secretCode){
            System.out.println(colors);
        }  
    }    
    
    @Override
    public void checkAttemptedCode(ArrayList<Color> attempt){ 

        int redPegs = 0;
        int whitePegs = 0;
        Set<Color> checkedPegs = new HashSet();
        ArrayList<Color> consoleCode = new ArrayList();
        codemakerResponse.clear();
        int i;
        
        //Set consoleCode to the secretCode generated.
        consoleCode.addAll(secretCode);
   
        System.out.println("Codemaker is checking the attempt...");
        
        //Check for correct answer.
        if (consoleCode.equals(attempt)){
            redPegs = 4;
            whitePegs = 0;
            System.out.println("You guessed the right code!");
            codeGuessed = true;
        }
        
        //Check for correct color and correct color and position.
        else{
                    
            //Checks for correct color and position.
            //If the code is correct, redPeg++ and add the color to checkedPegs.
            for (i = 0; i < MAX_PEGS; ++i){
                if (consoleCode.get(i).equals(attempt.get(i)) == true){
                    redPegs++;
                    checkedPegs.add(consoleCode.get(i));
                }  
           }
            
            //Check for right color. Add whitePeg if color is present.
            for(i = 0; i < MAX_PEGS; ++i){
                if ((consoleCode.get(i).equals(attempt.get(i)) != true) 
                    && (consoleCode.contains(attempt.get(i)) == true) && 
                    (checkedPegs.contains(attempt.get(i)) != true)){
                    whitePegs++;
                    checkedPegs.add(attempt.get(i));
                }
            }
        }
       
        //For each redPeg add the color RED to codemakerResponse and print message.
        for (i = 0; i < redPegs; ++i){
            codemakerResponse.add(Color.RED);
            System.out.println("Correct color in correct place.");
        }  
        
        //For each whitePeg  add the color WHITE to codemakerResponse and print message.
        for(i = 0; i < whitePegs; ++i){
            codemakerResponse.add(Color.WHITE);
            System.out.println("Correct color in wrong place.");
        }
        System.out.println();
        
        //Print the codemakerResponse for this attempt.
        System.out.println("Color response from codemaker:");
        for(Color pegOut : getCodemakerResponse())
            System.out.println(pegOut);
        
        //Print peg totals.
        System.out.println("Red pegs: " + redPegs + " White pegs:" + whitePegs);
        System.out.println();
    }      
}
