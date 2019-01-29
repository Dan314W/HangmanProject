// Chapter 10 Question 21

package hangmanproject;

import hangmanproject.HangmanGame;
import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hangman extends JPanel{

    public static int failedGuesses = 0;    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int h = getHeight();
        int w = getWidth();
        g.setColor(Color.BLACK);
        g.drawLine((int)(0.75*w),(int)(0.9*h),(int)(0.95*w),(int)(0.9*h));
        g.drawLine((int)(0.9*w),(int)(0.1*h),(int)(0.9*w),(int)(0.9*h));
        g.drawLine((int)(0.3*w),(int)(0.1*h),(int)(0.95*w),(int)(0.1*h));
        g.drawLine((int)(0.5*w),(int)(0.1*h),(int)(0.5*w),(int)(0.2*h));
        
        if(failedGuesses == 0) return;
        g.drawOval((int)(0.4*w), (int)(0.2*h), (int)(0.2*w), (int)(0.2*w));
        
        if(failedGuesses == 1) return;
        g.drawLine((int)(0.5*w),(int)(0.2*h)+(int)(0.2*w),(int)(0.5*w),
                (int)(0.6*h));
        
        if(failedGuesses == 2) return;
        g.drawLine((int)(0.5*w),(int)(0.45*h),(int)(0.3*w),(int)(0.3*h));
        
        if(failedGuesses == 3) return;
        g.drawLine((int)(0.5*w),(int)(0.45*h),(int)(0.7*w),(int)(0.3*h));
        
        if(failedGuesses == 4) return;
        g.drawLine((int)(0.5*w),(int)(0.6*h),(int)(0.3*w),(int)(0.75*h));
        
        if(failedGuesses == 5) return;
        g.drawLine((int)(0.5*w),(int)(0.6*h),(int)(0.7*w),(int)(0.75*h));
    }
    
    public static void main(String[] args){
        JFrame window = new JFrame("Test Window");
        window.setBounds(100, 100, 400, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel canvas = new Hangman();
        canvas.setBackground(Color.WHITE);
        window.getContentPane().add(canvas);
        window.setVisible(true);
            
        boolean done = false;

        Scanner input = new Scanner(System.in);
        /*
        System.out.println("Please select a difficulty setting: ");
        System.out.println("1) Easy = 9, 2) Medium = 6, 3) Hard = 3");
        System.out.print("Enter a number (1-3): ");
        int difficulty = input.nextInt();
        */
        int maxFailedGuesses=0;
        int difficulty = 2;
        switch(difficulty){
            case 1: maxFailedGuesses = 9; break;
            case 2: maxFailedGuesses = 6; break;
            case 3: maxFailedGuesses = 3; break;
            default: System.out.println("Error!"); done = true; break;
        }
    
        //This section reads a word from the WordList.txt file
        Scanner inputFile = null;
        try{
            inputFile = new Scanner(new 
                File("C:\\Users\\MJNeff18579\\Documents\\NetBeansProjects\\HangmanProject\\src\\hangmanproject\\WordList.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("***  Can't find WordList.txt ***");
            System.exit(1);
        }
        
        ArrayList words = new ArrayList(); 
        while (inputFile.hasNextLine()){
            words.add(inputFile.nextLine());
        }
        inputFile.close();
    
        String word = (String)words.get((int)(Math.random() * words.size()));
        word = word.trim();
        HangmanGame game = new HangmanGame(word);
        int count = 0;


        while (!done){
            System.out.println("Tried: " + game.getTried());
            System.out.println("Failed guesses: " + failedGuesses);
            System.out.println(game.getGuessed());
            System.out.println();

            System.out.print("Enter next letter (or Quit): ");
            String s = input.next();
            if ("quit".equalsIgnoreCase(s))
                break;
            if (s.length() != 1){
                System.out.println("Invalid input");
            }
            else{
                int result = game.tryLetter(s.charAt(0));
                switch(result){
                    case 0:
                        System.out.println("Already tried");
                        break;
                    case -1:
                        System.out.println("Sorry, not there");
                        failedGuesses++;
                        window.repaint();
                        break;
                    case 1:
                        System.out.println("Yes!");
                        break;
                }
            }

            count++;

            if (game.getGuessed().indexOf('-') == -1){
                System.out.println("You guessed " + word + " in " + count + 
                        " tries.");
                done = true;
            }
            else if (failedGuesses >= maxFailedGuesses){
                System.out.println("You have guessed incorrectly " + 
                        failedGuesses + " times.");
                System.out.println("The correct word was: " + word + 
                        ". Better luck next time!");
                done = true;

            }
        }
    }
}
