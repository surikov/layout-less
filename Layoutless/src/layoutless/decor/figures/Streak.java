package layoutless.decor.figures;

import layoutless.decor.*;

import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Streak extends Figure {

    private Numeric startX;

    public Streak() {
        startX = new Numeric().value(0);
    }

    @Override
    public void paint(Graphics2D g2) {
        float width = 17.0f;
        int cap = BasicStroke.CAP_ROUND;
        int join = BasicStroke.JOIN_ROUND;
        g2.setStroke(new BasicStroke(width, cap, join));
        g2.setPaint(Color.red);
        g2.draw(new Line2D.Double(0, 0, 130, 250));
    }

    public Streak startX() {
        return this;
    }
}
