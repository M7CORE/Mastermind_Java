/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import static constants.Constants.MAX_ATTEMPTS;
import static constants.Constants.MAX_PEGS;
import core.Codemaker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mfch9
 */
public class CodemakerUi {
    
    //Member variables
    JPanel codemakerResponse;
    JPanel secretCode;
    Codemaker codemaker;
    JLabel[] secretLabels;
    JLabel[][] responseLabels;
    ImageIcon question;
    boolean checkClicked;
    JButton check;
    
    //Getters and setters for check and checkClicked
    public void setCheckClicked(boolean checkClicked) {
        this.checkClicked = checkClicked;
    }

    public void setCheck(JButton check) {
        this.check = check;
    }

    public boolean isCheckClicked() {
        return checkClicked;
    }

    public JButton getCheck() {
        return check;
    }
    
    //Getters for JPanels
    public JPanel getCodemakerResponse() {
        return codemakerResponse;
    }

    public JPanel getSecretCode() {
        return secretCode;
    }
   
    //Custom constructor
    public CodemakerUi(Codemaker codemaker){
        this.codemaker = codemaker;
        initComponents();
    }
    
    public void initComponents(){
        
        //Initialize member variables
        codemakerResponse = new JPanel();
        secretCode = new JPanel();
        secretLabels = new JLabel[MAX_PEGS];
        responseLabels = new JLabel[MAX_ATTEMPTS][MAX_PEGS];
        
       String fileName = "../images/question.jpg";
//       check if it works or the image location needs to be moved.
        question = new ImageIcon(getClass().getResource(fileName));
       
        
        //Set size and border for codemakerResponse.
        codemakerResponse.setMinimumSize(new Dimension(150,200));
        codemakerResponse.setPreferredSize(new Dimension (100,300));
        codemakerResponse.setBorder(BorderFactory.createTitledBorder("Response"));
        codemakerResponse.setLayout(new GridLayout(10, 4));
        
        //Set size and border for secretCode.
        secretCode.setMinimumSize(new Dimension(300,75));
        secretCode.setPreferredSize(new Dimension(600,75));
        secretCode.setBorder(BorderFactory.createTitledBorder("Secret Code"));
        secretCode.setLayout(new FlowLayout());
        
       //Create 4 JLabel for secret code and add to secret code.
        for (int i = 0; i < 4; ++i){
            JLabel jL = new JLabel();
            ImageIcon iI = new ImageIcon();
            iI = imageResize(question);
            jL.setIcon(iI);
            secretCode.add(jL);
        }
        
        //Add check button next to the secret code and add to JPanel.
        check = new JButton("Check");
        //Action listener for JButton check.
        check.addActionListener(new buttonClicked());
        secretCode.add(check);
        
        //Create JLabel for response and add to codemakerResponse.
        for (int x = 0; x < MAX_ATTEMPTS; ++x){
            for (int y = 0; y < MAX_PEGS; ++y){
                JLabel label = new JLabel();                
                label.setBorder(BorderFactory.createEtchedBorder());
                responseLabels[x][y] = label;
                codemakerResponse.add(label);
            }   
        }
    }
    
    //method to resize image.
    private ImageIcon imageResize(ImageIcon icon){
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
    
    //method to display secret code once reached max attempt or guessed
    public void displaySecretCode(){
        secretCode.removeAll();
        JLabel secCode = new JLabel("The secret code was");
        secretCode.add(secCode);
        List<Color> list = new ArrayList(codemaker.getSecretCode());
        for(int i = 0; i < list.size(); i++){
            Color color = new Color(list.get(i).getRGB());
            RoundButton button = new RoundButton();
            button.setBackground(color);
            secretCode.add(button);
        }
        secretCode.revalidate();
        secretCode.repaint();
    }
    
    //method to display codemaker response on the side.
    public void displayCodemakerResponse(int row){
        ArrayList<Color> al = new ArrayList<Color>(codemaker.getCodemakerResponse());
        for (int i = 0; i < al.size(); i++){
            Color color = new Color(al.get(i).getRGB());
            if (al.get(i).equals(null) == false){
//            if (color.equals(null) == false){
                responseLabels[row][i].setOpaque(true);
                responseLabels[row][i].setBackground(color);
            }
        }
        al.clear();
    }
    
    //action listener for check button
    private class buttonClicked implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            checkClicked = true;
        }
    }
    
    
   
}
