/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import javax.swing.JOptionPane;
import core.Game;
import userInterface.MasterMindUi;

/**
 *
 * @author mfch9
 */
public class MasterMind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to MasterMind!");
        JOptionPane.showMessageDialog(null, "Let's Play MasterMind!");
        Game myGame; 
        myGame = new Game();
        
        //Call UI
        MasterMindUi userInterface = new MasterMindUi(myGame);
        
    }

    
}
