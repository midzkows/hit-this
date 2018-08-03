package uderzwto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;



/**
 *
 * @author Małgorzata
 * Klasa z kartą panelu Rozgrywka.
 */
public class Rozgrywka extends JPanel implements ActionListener {
    boolean leftActive = false;
    boolean rightActive = false;
    boolean downActive = false;
    boolean upActive = false;
    Timer timer = new Timer(1000, this);
    ImageIcon Gora = new ImageIcon("obraz/up.png"); 
    ImageIcon Dol = new ImageIcon("obraz/down.png"); 
    ImageIcon Kamien = new ImageIcon("obraz/kamien.png");
    ImageIcon Lewo = new ImageIcon("obraz/left.png"); 
    ImageIcon Prawo = new ImageIcon("obraz/right.png"); 
    ImageIcon Ggora = new ImageIcon("obraz/uup.png"); 
    ImageIcon Ddol = new ImageIcon("obraz/ddown.png"); 
    ImageIcon Llewo = new ImageIcon("obraz/lleft.png"); 
    ImageIcon Pprawo = new ImageIcon("obraz/rright.png");
    JLabel Gong = new JLabel(new ImageIcon("obraz/obrazGry.png"));
    JLabel Mlot = new JLabel(new ImageIcon("obraz/mlot.png"));
    JLabel Napis = new JLabel("Po zniknięciu napisu powtórz sekwencję");
    JLabel Napis2 = new JLabel(" i zatwierdź (ENTER).");
    String imie;
    int poziom;
    JLabel Imie = new JLabel();
    JLabel Poziom = new JLabel();
    int[] losy;
    float poprawnosc = 0;
    int[] wpisyUsera;
    boolean tura = false;
    JLabel Pop = new JLabel();
    int liczba = 0;
    int timerek = -2;

    Rozgrywka(){
        super();
        setLayout(null);
        add(Gong);
        add(Mlot);
        add(Imie);
        add(Mlot);
        add(Napis);
        add(Napis2);
        add(Poziom);
        Napis.setBounds(0, 50, 600, 100);
        Napis2.setBounds(0, 100, 500, 100);
        Pop.setFont(new Font("Serif", Font.PLAIN, 30));
        Pop.setForeground(Color.BLUE);
        Pop.setBounds(0, 50, 600, 100);
        Napis2.setFont(new Font("Serif", Font.PLAIN, 30));
        Napis.setFont(new Font("Serif", Font.PLAIN, 30));
        Napis.setForeground(Color.BLUE);
        Napis2.setForeground(Color.BLUE);
        Gong.setBounds(700, 20, 200, 650);
        Mlot.setBounds(700, 600, 230, 100);
        Imie.setBounds(0, 0, 400, 60);
        Poziom.setBounds(500, 0, 700, 40);
    }
 /**
 * Miganie strzałek zrealizowane w metodzie paint odbywa się 
 * poprzez sprawdzanie flag.
 */ 
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g);
        Image image_to_draw = upActive ? Ggora.getImage() : Gora.getImage();
        g2d.drawImage(image_to_draw, 200, 300, null);
        image_to_draw = downActive ? Ddol.getImage() : Dol.getImage();
        g2d.drawImage(image_to_draw, 200, 500, null);
        image_to_draw = rightActive ? Pprawo.getImage() : Prawo.getImage();
        g2d.drawImage(image_to_draw, 300, 400, null);
        image_to_draw = leftActive ? Llewo.getImage() : Lewo.getImage();
        g2d.drawImage(image_to_draw, 100, 400, null);
        g.setFont(new Font("Serif", Font.PLAIN, 40));
        rysujSile(g, poprawnosc);
        g2d.dispose();
        repaint();
}
    void startBlinking()
    {
        timer.setRepeats(true);
        timer.start();
    }

    void setUsersInfo(int lvl, String imie){
        poziom = lvl;
        Imie.setText("GRACZ: " + imie);
        Poziom.setText("Poziom: " + lvl);
        Imie.setFont(new Font("Serif", Font.PLAIN, 50));
        Poziom.setFont(new Font("Serif", Font.PLAIN, 50));
    }
    
 /**
 * Sekwencja zostaje wylosowana.
 * Rozróżnienie długości sekwencji na podstawie
 * wybranego poziomu.
 * @return sekwencja
 */
    int[] prepareGame(){

        Random randomGenerator = new Random();
     // 0 is up, 1 is down, 2 is left, 3 is right
        switch(poziom){
            case 1:
               this.liczba = 4;
               break;
            case 2:
              this.liczba = 8;
               break;
            case 3:
               this.liczba = 12;
               break;
        }
        losy = new int[liczba];
        for (int i = 0; i < liczba; i++)
        {
            losy[i] = randomGenerator.nextInt(4);
        }
        return losy;       
    }
    
 /**
 * Odpytywanie elementów sekwencji (na której pozycji) i ustawiane wybranych
 * flag tak, aby odpowiednia strzałka została podświetlona.
 * @param liczba długość sekwencji
 * @param timerek poszczególna wartość w sekwencji
 */
    
    void ustawFlagi(int liczba, int timerek){
        switch(losy[timerek/2]){
            case 0:
                upActive = true;
                downActive = false;
                leftActive = false;
                rightActive = false;
                break;
            case 1:
                upActive = false;
                downActive = true;
                leftActive = false;
                rightActive = false;
                break;
            case 2:
                upActive = false;
                downActive = false;
                leftActive = true;
                rightActive = false;
                break;
            case 3:
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = true;
                break;
        }
        switch(losy[timerek/2]){
            case 0:
                upActive = true;
                downActive = false;
                leftActive = false;
                rightActive = false;
                break;
            case 1:
                upActive = false;
                downActive = true;
                leftActive = false;
                rightActive = false;
                break;
            case 2:
                upActive = false;
                downActive = false;
                leftActive = true;
                rightActive = false;
                break;
            case 3:
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = true;
                break;
        }
        switch(losy[timerek/2]){
            case 0:
                upActive = true;
                downActive = false;
                leftActive = false;
                rightActive = false;
                break;
            case 1:
                upActive = false;
                downActive = true;
                leftActive = false;
                rightActive = false;
                break;
            case 2:
                upActive = false;
                downActive = false;
                leftActive = true;
                rightActive = false;
                break;
            case 3:
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = true;
                break;
        }
        switch(losy[timerek/2]){
            case 0:
                upActive = true;
                downActive = false;
                leftActive = false;
                rightActive = false;
                break;
            case 1:
                upActive = false;
                downActive = true;
                leftActive = false;
                rightActive = false;
                break;
            case 2:
                upActive = false;
                downActive = false;
                leftActive = true;
                rightActive = false;
                break;
            case 3:
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = true;
                break;
        }
        if (liczba >= 8 ){
            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }

            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }

            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }                                               
            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }
        }
                    
   
       if (liczba >= 12){
           switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }         
            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }                           
            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }
                                                            
            switch(losy[timerek/2]){
                case 0:
                    upActive = true;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 1:
                    upActive = false;
                    downActive = true;
                    leftActive = false;
                    rightActive = false;
                    break;
                case 2:
                    upActive = false;
                    downActive = false;
                    leftActive = true;
                    rightActive = false;
                    break;
                case 3:
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = true;
                    break;
            }
        }
    }
 /**
 * Wołanie metody ustawFlagi na zmianę z gaszeniem strzałek.
 * Po wyświetleniu sekwencji tworzona jest tablica, która przechowa
 * to, co użytkownik wpisze.
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        timerek++;
            if (timerek == 0){
                ustawFlagi(liczba, timerek);
            }
            if (timerek == 1){
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = false;            
            }
            if (timerek == 2){
                ustawFlagi(liczba, timerek);
            }
            if (timerek == 3){
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = false;            
            }
            if (timerek == 4){
                ustawFlagi(liczba, timerek);
            }
            if (timerek == 5){
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = false;            
            }
            if (timerek == 6){
                ustawFlagi(liczba, timerek);
            }
            if (timerek == 7){
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = false;            
            }
            if (liczba > 4){
                if (timerek == 8) {
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 9){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 10) {
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 11){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 12){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 13){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 14){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 15){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
            }
            if (liczba == 12){
                if (timerek == 16){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 17){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 18){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 19){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 20){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 21){
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
                if (timerek == 22){
                    ustawFlagi(liczba, timerek);
                }
                if (timerek == 23) {
                    upActive = false;
                    downActive = false;
                    leftActive = false;
                    rightActive = false;            
                }
            }
            if (timerek/2 > liczba)
            {
                upActive = false;
                downActive = false;
                leftActive = false;
                rightActive = false;
                if (timerek/2 == liczba+1){
                    Napis.setVisible(false);
                    Napis2.setVisible(false);
                    wpisyUsera = new int[liczba];
                    tura = true;
                }   
            }
        repaint();
    }
    
    
 /**
 * Seria metod służących do wywołania zapisu tego,
 * co użytkownik wpisał.
 * @param licznik ogarnicza długość sekwencji.
 */
    void wcisnietoGore(int licznik){
        if (licznik < liczba)
            wpisyUsera[licznik] = 0;
    }
    
    void wcisnietoDol(int licznik){
        if (licznik < liczba)
            wpisyUsera[licznik] = 1;
    }
        
    void wcisnietoLewo(int licznik){
        if (licznik < liczba)
            wpisyUsera[licznik] = 2;
    }
            
    void wcisnietoPrawo(int licznik){
        if (licznik < liczba)
            wpisyUsera[licznik] = 3;
    }
    
/**
 * Porównanie strzałek wylosowanych i wpisanych przez użytkownika,
 * obliczenie poprawności.
 * @return poprawnosc stosunek poprawnie powtórzonych strzałek
 * do długości sekwencji.
 */
    float weryfikacja(){
        try{

            for (int i = 0; i < liczba; i++){
                if (losy[i] == wpisyUsera[i])
                    poprawnosc = poprawnosc + 1;
                }
            poprawnosc = poprawnosc/(float)liczba;
            System.out.println(poprawnosc); 
            Pop.setText("Twoja poprawność to " + poprawnosc);
            add(Pop);
        }
        catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }
        return poprawnosc;
    }
    
    
/**
 * Określenie wysokości, na której ma pojawić się element
 * na wyświetlonym stanowisku gry.
 * @param correct uzyskana poprawność
 */
     private void rysujSile(Graphics g, float correct)
    {
        Graphics2D g2d = (Graphics2D)g;
        if (correct == 0.0)
            g2d.drawImage(Kamien.getImage(), 760, 420, null);
        else if (correct > 0.0 && correct <= 0.25)
            g2d.drawImage(Kamien.getImage(), 760, 380, null);
        else if (correct > 0.25 && correct < 0.50)
            g2d.drawImage(Kamien.getImage(), 760, 340, null);
        else if (correct == 0.50)
            g2d.drawImage(Kamien.getImage(), 760, 310, null);
        else if (correct > 0.50 && correct <= 0.75)
            g2d.drawImage(Kamien.getImage(), 760, 280, null);
        else if (correct > 0.75 && correct <= 0.99)
            g2d.drawImage(Kamien.getImage(), 760, 250, null);
        else if (correct== 1.0)
            g2d.drawImage(Kamien.getImage(), 760, 200, null);
    }
}