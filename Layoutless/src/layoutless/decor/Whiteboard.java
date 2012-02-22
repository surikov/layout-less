package layoutless.decor;

import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class Whiteboard extends JPanel {
    
    private Window window;
    private Vector<Figure> figures = new Vector<Figure>();
    private WindowAdapter windowAdapter = new WindowAdapter() {
        
        public void windowClosed(WindowEvent e) {
            window.removeWindowListener(this);
            //System.out.println(e+" / "+window.hashCode());
            clear();
        }
    };
    
    private void clear() {
    }
    
    public Whiteboard(Window win) {
        super();
        window = win;
        this.setOpaque(false);
    }

    public Whiteboard figure(Figure f) {
        figures.add(f);
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < figures.size(); i++) {
            figures.get(figures.size() - i - 1).paint(g2);
        }
    }
}
