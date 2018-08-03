/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uderzwto;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Małgorzata
 * Karta z kartą panelu Wyniki.
 */
public class Wyniki extends JPanel {
    JLabel Wskazowka = new JLabel("Aby wrócić do Menu, wciśnij ESC.");
    JLabel[] Stats;

    
/**
 * W kontruktorze następuje przeczytanie pliku,
 * następnie imiona i wyniki są wyświetlane jako JLabel.
 */  
    Wyniki() {
        super();
        GridLayout GlownyRozklad = new GridLayout(10,10);
        setLayout(GlownyRozklad);
        Wskazowka.setFont(new Font("Serif", Font.PLAIN, 25));
        Wskazowka.setForeground(Color.BLUE);
        add(Wskazowka);
        try{
            BufferedReader br = new BufferedReader(new FileReader("results.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String all = sb.toString();
            String[] parts = all.split("(?<=,)");
            
            Stats = new JLabel[parts.length];
            for (int i = 0; i < parts.length; i++){
                Stats[i] = new JLabel(parts[i]);
                Stats[i].setFont(new Font("Serif", Font.PLAIN, 25));
                add(Stats[i]);
            }
           br.close();
        }
        catch (Exception e){
        System.out.println("Failure.");
        }
    } 
    
/**
 * Metoda, która umożliwia dodanie imienia i wyniku akutalnego gracza
 * do spisu statystyk.
 * @param imie imię gracza
 * @param wynik poprawność powtórzonej sekwencji
 */ 
    void addResult(String imie, float wynik){
        try {
            PrintWriter Out = new PrintWriter(new BufferedWriter(new FileWriter("results.txt", true)));
            Out.println("\n" + imie + " " + wynik + ",");
            Out.close();
        } catch (IOException e) {
            System.out.println("Failure.");
        }
    }
}
    
