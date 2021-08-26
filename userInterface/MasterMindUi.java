/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import core.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author mfch9
 */
public class MasterMindUi{
    
    //Member variables
    Game game;
    CodebreakerUi codebreakerUi;
    CodemakerUi codemakerUi;
    JFrame frame;
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenu helpMenu;
    JMenuItem newGameMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem aboutMenuItem;
    JMenuItem rulesMenuItem;
    
    //Custom constructor
    public MasterMindUi(Game game){
        this.game = game;
        initComponents();
        play();
    }
    
    private void initComponents(){
        
        //Initialize all components.
        BorderLayout layout = new BorderLayout();
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        helpMenu = new JMenu("Help");
        newGameMenuItem = new JMenuItem("New Game");
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");
        rulesMenuItem = new JMenuItem("Games Rules");
         
        //Initialize codebreaker and codemaker UI.
        codebreakerUi = new CodebreakerUi(game.getCodebreaker());
        codemakerUi = new CodemakerUi(game.getCodemaker());
        
        //Instantiate and set JFrame.
        frame = new JFrame("Mastermind");
        frame.setLayout(layout);
        frame.setSize(new Dimension(350, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up gameMenu.
        gameMenu.add(newGameMenuItem);
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new helpListener());
        menuBar.add(gameMenu);
        
        //Set up helpMenu.
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(new aboutListener());
        helpMenu.add(rulesMenuItem);
        rulesMenuItem.addActionListener(new rulesListener());
        
        //Set up menuBar
        menuBar.add(helpMenu);
        
        //Add menuBar and JPanels to the frame.
        frame.setJMenuBar(menuBar);
        frame.add(codebreakerUi.getCodebreakerAttempt(),layout.CENTER);
        frame.add(codebreakerUi.getCodebreakerColors(), layout.SOUTH);
        frame.add(codemakerUi.getCodemakerResponse(), layout.EAST);
        frame.add(codemakerUi.getSecretCode(), layout.NORTH);
        frame.setVisible(true);   
    }

    //Inner class for the help menu.
    private static class helpListener implements ActionListener {

        @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
               int choice = JOptionPane.showConfirmDialog(null, "Confirm to exit Mastermind?", "Exit?", YES_NO_OPTION);
               if (choice == 0)
                   System.exit(0);
            }
    }
    
    //Inner class for the about menu.
    private static class aboutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Mastermind version 1.0\nMaria Corella\nSummer 2019");
        }
    }
    
    //Inner class for the rules menu.
    private static class rulesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Step 1: The codemaker selects a"
                    + " four color secret code, in any order, no duplicate colors.\n\n"
                    + "Step 2: The codebreaker places a guess in the bottom row, "
                    + "no duplicate colors.\n\nStep 3: The codemaker gives feedback "
                    + "next to each guess row with four pegs\n- Each red peg means "
                    + "that one of the guessed colors is correct, and is in the right location."
                    + "\n- Each white peg means that one of the guessed colors is correct, "
                    + "but is in the wrong location.\n\nStep 4: Repeat with the next row,"
                    + " unless the secret code was guessed on the first turn\n\n"
                    + "Step 5: Continue until the secret code is guessed or there are no more "
                    + "guesses left, there are 10 attempts");
        }
    }
    
    //play method
    private void play(){
        for(int i = 9; i >= 0; --i){
            codemakerUi.check.setEnabled(false);
            codemakerUi.checkClicked = false;
            JOptionPane.showMessageDialog(null, "Enter guess.");
            while(codebreakerUi.codebreaker.codebreakerAttempt.size() != 4)
            {
                System.out.println();
                continue;
            }       
            codemakerUi.check.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Click the check button.");
            while(codemakerUi.isCheckClicked() == false){
                System.out.println("");
                continue;
            }
            JOptionPane.showMessageDialog(null, "Codemaker is checking the guess.");
            ArrayList<Color> codebreakerList = new ArrayList<Color>(codebreakerUi.codebreaker.getCodebreakerAttempt());
            codemakerUi.codemaker.checkAttemptedCode(codebreakerList);
            ArrayList<Color> codemakerList = new ArrayList<Color>(codemakerUi.codemaker.getCodemakerResponse());
            codemakerUi.displayCodemakerResponse(i);
            codebreakerUi.codebreaker.removeAll();
            if (codemakerUi.codemaker.isCodeGuessed() == true){
                JOptionPane.showMessageDialog(null, "You have guessed the code.");
                codemakerUi.displaySecretCode();
                break;
            } 
        }
        if (codemakerUi.codemaker.isCodeGuessed() == false){
            JOptionPane.showMessageDialog(null, "You have run out of attempts");
            codemakerUi.displaySecretCode();
        }
    }
}
