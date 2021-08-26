/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import static constants.Constants.COLORS;
import static constants.Constants.MAX_ATTEMPTS;
import static constants.Constants.MAX_PEGS;
import static constants.Constants.codeColors;
import core.Codebreaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author mfch9
 */
public class CodebreakerUi {
    
    //Member variables
    public JPanel codebreakerAttempt;
    public JPanel codebreakerColors;
    public Codebreaker codebreaker;
    public RoundButton[] buttons;
    public RoundButton[][] attempts;
    public Color colorSelected;
    
    //Getter and setters for JPanel member variables.
    public JPanel getCodebreakerAttempt() {
        return codebreakerAttempt;
    }

    public void setCodebreakerAttempt(JPanel codebreakerAttempt) {
        this.codebreakerAttempt = codebreakerAttempt;
    }

    public JPanel getCodebreakerColors() {
        return codebreakerColors;
    }

    public void setCodebreakerColors(JPanel codebreakerColors) {
        this.codebreakerColors = codebreakerColors;
    }
    
    //Custom constructor
    public CodebreakerUi(Codebreaker codebreaker){
        this.codebreaker = codebreaker;
        initComponents();
    }
    
    public void initComponents(){
        
        //Instantiation of member variables
        codebreakerAttempt = new JPanel();
        codebreakerColors = new JPanel();
        buttons = new RoundButton[COLORS];
        attempts = new RoundButton[MAX_ATTEMPTS][MAX_PEGS];
        Dimension panelDimension = new Dimension(200,300);
        Dimension minDimension = new Dimension(150,200);
        GridLayout gridLayout = new GridLayout(10,4);
        int i;
        int j;
        int index = 0;
        
        //Set size for codebreakerAttempt
        codebreakerAttempt.setMinimumSize(minDimension);
        codebreakerAttempt.setPreferredSize(panelDimension);
        
        //Set size for codebreakerColors
        codebreakerColors.setMinimumSize(new Dimension (600, 200));
        codebreakerColors.setPreferredSize(new Dimension(300, 75));
        
        
        //Titled border for each method.
        codebreakerAttempt.setBorder(BorderFactory.createTitledBorder("Attempts"));
        codebreakerColors.setBorder(BorderFactory.createTitledBorder("Colors"));
        
        //Layout for codebreaker attempt.
        codebreakerAttempt.setLayout(gridLayout);
        
        //Loop to create a round button and disable if not on last row.
        //Add round button to the codebreaker attempt JPanel.
        for(i = 0; i < MAX_ATTEMPTS; ++i){
            for(j = 0; j < MAX_PEGS; ++j){
                RoundButton roundButton = new RoundButton();
                attempts[i][j] = roundButton;
                roundButton.putClientProperty("row", i);
                //Action listener for color button selected.
                roundButton.addActionListener(new AttemptListener());
                if (i != 9)
                    attempts[i][j].setEnabled(false);
                codebreakerAttempt.add(roundButton);
            }    
        }
        
        //Loop to create a button for each color. Set the color of the button.
        //Add the button to codebreakerColors JPanel.
        for (RoundButton button: buttons){
            button = new RoundButton();
            Color color = codeColors.get(index);
            button.setBackground(color);
            button.putClientProperty("color", color);
            //action listener for selected color location.
            button.addActionListener(new ColorListener());
 
            //add tool tip text to color button.
            if (color.equals(Color.RED))
                button.setToolTipText("Red");
            else if(color.equals(Color.BLUE))
                button.setToolTipText("Blue");
            else if(color.equals(Color.WHITE))
                button.setToolTipText("White");
            else if(color.equals(Color.BLACK))
                button.setToolTipText("Black");
            else if(color.equals(Color.YELLOW))
                button.setToolTipText("Yellow");
            else if(color.equals(Color.ORANGE))
                button.setToolTipText("Orange");
            else if(color.equals(Color.PINK))
                button.setToolTipText("Pink");
            else if(color.equals(Color.GREEN))
                button.setToolTipText("Green");
            
            codebreakerColors.add(button);
            index++;
        }
    
    }
    
    //Private class for actionListener for colorSelected.
    private class ColorListener implements ActionListener{
        
        @Override
            public void actionPerformed(ActionEvent ae){
               
                // explicit type casting
                RoundButton button = (RoundButton)ae.getSource();
                
                // explicit type casting
              colorSelected = (Color)button.getClientProperty("color");
            }    
    }
    
    //private class for actionListener for place of colorSelected.
    private class AttemptListener implements ActionListener{
    
        @Override
            public void actionPerformed(ActionEvent ae){
              RoundButton roundButton = (RoundButton)ae.getSource();
              
              if(codebreaker.codebreakerAttempt.contains(colorSelected) == false){
                  roundButton.setBackground(colorSelected);
                  codebreaker.codebreakerAttempt.add(colorSelected);   
              }
              
              if (codebreaker.codebreakerAttempt.size() == 4){
                  int onRow = (int)(roundButton.getClientProperty("row"));
                  enableDisableButtons(onRow);
              }
            }
    }
    
    //Method to disable previous button row and enable next button row.
    private void enableDisableButtons(int onRow){
     
        for(int i = 0; i < 4; i++){
            attempts[onRow][i].setEnabled(false);
        }
        
        for(int j = 0; j < 4; j++){
            if (onRow != 0)
                attempts[onRow - 1][j].setEnabled(true);
        }
    } 
}
