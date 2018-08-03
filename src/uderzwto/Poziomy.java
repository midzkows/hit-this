
package uderzwto;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Małgorzata
 * Klasa z kartą paneleu Poziomy.
 * 
 */
public class Poziomy extends JPanel{
    JLabel First, Second, Third, Start, Poziom;

    Poziomy(){
        super();
        Poziom = new JLabel();
        First = new JLabel("Poziom łatwy: wciśnij 1.");
        Second = new JLabel("Poziom średni: wciśnij 2.");
        Third = new JLabel("Poziom trudny: wciśnij 3.");
        Start = new JLabel("Naciśnij S, aby rozpocząć grę.");
        
        First.setFont(new Font("Serif", Font.PLAIN, 25));
        Second.setFont(new Font("Serif", Font.PLAIN, 25));
        Third.setFont(new Font("Serif", Font.PLAIN, 25));
        Start.setFont(new Font("Serif", Font.PLAIN, 25));
        Poziom.setFont(new Font("Serif", Font.PLAIN, 25));
        
        GridLayout GlownyRozklad = new GridLayout(4, 2);
        setLayout(GlownyRozklad);
        add(First);
        add(Second);
        add(Third);
        add(Poziom);
        add(Start);
    }
    
 /**
 * Metoda umożliwiająca wyświetlenie imienia gracza
 * na karcie Poziomy.
 * @param lvl ustawiony poziom trudności do wyświetlenia.
 */
    void setInfo(int lvl){
        if (lvl == 0)
            Poziom.setText("Wybrany poziom: ");
        else
            Poziom.setText("Wybrany poziom: " + lvl);
    }
}
