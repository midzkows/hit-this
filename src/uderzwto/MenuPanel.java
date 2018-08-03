/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uderzwto;


import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

 /**
 *@author Małgorzata Idźkowska
 * Klasa z kartą panelu MenuPanel.
 */

public class MenuPanel extends JPanel {
    JLabel Imie, Stats, Poziom, Wyjscie, Start, Obraz;
    MenuPanel(){
        super();
        GridLayout GlownyRozklad = new GridLayout();
        setLayout(GlownyRozklad);
        JPanel Pomocniczy = new JPanel();
        JPanel Obrazkowy = new JPanel();

        GridLayout gl = new GridLayout(5,2);
        GridLayout g2 = new GridLayout();
        Pomocniczy.setLayout(gl);
        Obrazkowy.setLayout(g2);

        Stats = new JLabel("Wciśnij I, aby zobaczyć statystyki.");
        Poziom = new JLabel("Wciśnij P, aby ustawić poziom trudności.");
        Wyjscie = new JLabel("Wciśnij Q, aby wyjść z gry.");
        Start = new JLabel("Wciśnij S, aby rozpocząć grę.");
        Imie = new JLabel();
        
        Stats.setFont(new Font("Serif", Font.PLAIN, 25));
        Poziom.setFont(new Font("Serif", Font.PLAIN, 25));
        Wyjscie.setFont(new Font("Serif", Font.PLAIN, 25));
        Start.setFont(new Font("Serif", Font.PLAIN, 25));
        Imie.setFont(new Font("Serif", Font.PLAIN, 30));
        Obraz = new JLabel(new ImageIcon("obraz/obrazMenu.png"));
        Obraz.setVerticalAlignment(JLabel.CENTER);

        Obrazkowy.add(Obraz);
        Pomocniczy.add(Imie);
        Pomocniczy.add(Stats);
        Pomocniczy.add(Poziom);
        Pomocniczy.add(Wyjscie);
        Pomocniczy.add(Start);
        add(Pomocniczy);
        add(Obrazkowy);
    }

 /**
 * Metoda umożliwiająca wyświetlenie imienia gracza
 * na karcie MenuPanel.
 *   @param imie imie użytkownika
 */
    void setUsersName(String imie){
        Imie.setText("Gracz: " + imie);
        Imie.setFont(new Font("Serif", Font.PLAIN, 50));
    }
}