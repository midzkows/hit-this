package uderzwto;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *  * @author Małgorzata Idźkowska
 * Klasa zawierająca główny panel gry, na którym wyświetlane
 * są poszczególne etapy.
 */

public final class MainWindow extends JFrame {
    MenuPanel Menu = new MenuPanel();
    Rozgrywka Gra = new Rozgrywka(); 
    Wyniki Results = new Wyniki();
    Poziomy WybierzPoziom = new Poziomy();
    CardLayout lm;
    JPanel Glowny = new JPanel();
    boolean lvlShowing = false;
    boolean mainShowing = true;
    boolean gameShowing = false;
    boolean statsShowing = false;
    int lvl = 1;
    int[] los;
    int licznik = 0;
 
/**
 * Konstruktor głównego panelu, tu znajduje się KeyListener.
 * 
 */
    MainWindow(String nazwa){
        super(nazwa);
        lm = new CardLayout();
        Glowny.setFocusable(true);
        Glowny.setLayout(lm);
        Glowny.add(Menu, "MENU");
        Glowny.add(Results, "WYNIKI");
        Glowny.add(Gra, "GRA");
        Glowny.add(WybierzPoziom, "POZIOM");
        add(Glowny);
        setResizable(false);
        setSize(1024,768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gra.addComponentListener(new CardManager());
        WybierzPoziom.addComponentListener(new CardManager());
        String code = JOptionPane.showInputDialog(Glowny, 
        "Wprowadz imie:", 
        "Witaj uzytkowniku!",
        JOptionPane.WARNING_MESSAGE);
        if (code == null || code.equals("")){
            System.exit(0);
        }
        Menu.setUsersName(code);
        Player Gracz = new Player(code);
        
        Glowny.addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent ke){
            if (mainShowing){
                switch(ke.getKeyCode())
                {
                    case KeyEvent.VK_S:
                        mainShowing = false;
                        lm.show(Glowny, "GRA");
                        Gra.setUsersInfo(getLevel(), code);
                        gameShowing = true;
                        
                        break;
                    case KeyEvent.VK_I:
                        mainShowing = false;
                        lvlShowing = false;
                        gameShowing = false;
                        statsShowing = true;
                        lm.show(Glowny, "WYNIKI");
                        break;

                    case KeyEvent.VK_P:
                        mainShowing = false;
                        lvlShowing = true;
                        lm.show(Glowny, "POZIOM");
                        break;
                    case KeyEvent.VK_Q:
                        System.exit(0);
                        break;
                }
            }
            else if (statsShowing){
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
                        mainShowing = true;
                        lvlShowing = false;
                        gameShowing = false;
                        statsShowing = false;
                        lm.show(Glowny, "MENU");
                }   
            }
            else if (lvlShowing){
                switch(ke.getKeyCode()){
                    case KeyEvent.VK_1:   
                        setLevel(1);
                        WybierzPoziom.setInfo(1);
                        break;
                    case KeyEvent.VK_2:
                        setLevel(2);
                        WybierzPoziom.setInfo(2);
                        break;
                    case KeyEvent.VK_3:
                        setLevel(3);
                        WybierzPoziom.setInfo(3);
                        break;
                    case KeyEvent.VK_S:
                        Gra.setUsersInfo(getLevel(), code);
                        lm.show(Glowny, "GRA");
                        lvlShowing = false;
                        gameShowing = true;
                        break;
                }
            }
            else if (gameShowing){
                if (Gra.tura){
                    switch(ke.getKeyCode()){
                        case KeyEvent.VK_UP:
                            Gra.wcisnietoGore(licznik);
                            licznik++;
                            break;
                        case KeyEvent.VK_DOWN:
                            Gra.wcisnietoDol(licznik);
                            licznik++;
                            break;
                        case KeyEvent.VK_LEFT:
                            Gra.wcisnietoLewo(licznik);
                            licznik++;
                            break;
                        case KeyEvent.VK_RIGHT:
                            Gra.wcisnietoPrawo(licznik);
                            licznik++;
                            break;
                    }
                }
                if (ke.getKeyCode() == KeyEvent.VK_ENTER){
                    Gracz.wynik = Gra.weryfikacja();
                    Results.addResult(code, Gracz.wynik);
                    }
            }
        }
        });

        }
    
/**
 * Setter do ustawiania poziomu trudności gry.
 *   @param level poziom trudności
 */
    public void setLevel(int level){
        this.lvl = level;
    }
 
/**
 * Getter zwracający ustawiony poziom trudności.
 *   @return level poziom trudności
 */    
    public int getLevel(){
        return lvl;
    }

    public class CardManager implements ComponentListener
    {
        @Override
        public void componentResized(ComponentEvent e) {
        }

        @Override
        public void componentMoved(ComponentEvent e) {
        }

        @Override
        public void componentShown(ComponentEvent e) {
            Component compa = (Component) e.getSource();
            if (e.getSource() == Gra && compa.isVisible()){
                gameShowing = true;
                System.out.println("GRA");
                los = Gra.prepareGame();
                Gra.losy = los;
                Gra.startBlinking();
            };
                }
        @Override
        public void componentHidden(ComponentEvent e) {
            
        }
    }
}
