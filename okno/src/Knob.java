import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Knob extends JComponent {

    
    
    //Pozycja X oraz Y kliknięcia myszki
    int xKlik;
    int yKlik;
    
    
    
    
    ActionListener al;
    Color Kolor; // zmiana koloru gałki
    Ellipse2D.Double galka; // Granice obszaru gałki
    
    //wartości czasu
    int czas = 1000;
    int minimum = 10;
    int maximum = 10000;
    int step = 10;

    public Knob() {
        Kolor = Color.CYAN; // Początkowy kolor gałki

        addMouseListener(new MouseAdapter() {
            @Override
            // nasłuchiwanie kliknięcie i sprawdzenie czy jest w obszarze gałki
            public void mousePressed(MouseEvent e) {
                if (galka.contains(e.getPoint())) {
                	// jeśli kliknięcie jest w gałce to wykonaj funkcje
                    czas_i_kod(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            
            }
        });
        
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            // 
            public void mouseDragged(MouseEvent e) {
                if (galka.contains(e.getPoint())) {
                    czas_i_kod(e);
                }
            }
        });
       
    }

    private void czas_i_kod(MouseEvent e) {
        xKlik = e.getX();
        yKlik = e.getY();
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        
        
        // Płynna zmiana koloru w zależności od pozycji gałki
        float hue = (float) (Math.atan2((yKlik - y), (xKlik - x)) / (2 * Math.PI) + 1) % 1;
        Kolor = Color.getHSBColor(hue, 1.0f, 1.0f); 

        // Przeliczenie pozycji gałki na wartość liczbową
        double kat = Math.atan2((yKlik - y), (xKlik - x));
        double dodatnikat = (kat + 2 * Math.PI) % (2 * Math.PI);
        czas = (int) ((dodatnikat / (2 * Math.PI)) * (maximum - minimum)) + minimum;
        

        // Zaokrąglenie wartości
        czas = Math.round((int) czas / step) * step;

        // Ograniczenie wartości do zakresu
        czas = Math.min(maximum, Math.max(minimum, czas));
        if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"czas ms"+ czas));

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int r = Math.min(getWidth(), getHeight()) / 2;
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        
        // Rysowanie obszaru gałki
        galka = new Ellipse2D.Double(x - r, y - r, r * 2, r * 2);
        
        g.setColor(Kolor);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.GRAY);
        int rm = r / 5;

        double kat = Math.atan2((yKlik - y), (xKlik - x));
        int xm = x + (int) (Math.cos(kat) * 0.75 * r);
        int ym = y + (int) (Math.sin(kat) * 0.75 * r);

        g.fillOval(xm - rm, ym - rm, rm * 2, rm * 2);

        // Wyświetlanie wartości
        g.setColor(Color.BLACK);
        g.drawString("Czas: " + czas+" ms", 10, 20);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addActinListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	public void removeActinListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//zwracanie czasu
	public int getValue() {
		
		return czas;
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame okno = new JFrame();

            Knob k = new Knob();

            okno.add(k);

            okno.setSize(600, 400);
            okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            okno.setVisible(true);
        });
    }
}
