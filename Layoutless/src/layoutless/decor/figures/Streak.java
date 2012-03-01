package layoutless.decor.figures;

import layoutless.decor.*;
import tee.binding.properties.*;
import java.awt.*;
import java.awt.geom.*;

public class Streak extends Figure {
    public NumericProperty<Streak> startX;
    public NumericProperty<Streak> startY;
    public NumericProperty<Streak> endX;
    public NumericProperty<Streak> endY;
    public NumericProperty<Streak> width;
    public NumericProperty<Streak> color;
    public Streak() {
	startX = new NumericProperty<Streak>(this);
	startX.property.value(0);
	startY = new NumericProperty<Streak>(this);
	startY.property.value(0);
	endX = new NumericProperty<Streak>(this);
	endX.property.value(0);
	endY = new NumericProperty<Streak>(this);
	endY.property.value(0);
	width = new NumericProperty<Streak>(this);
	width.property.value(3);
	color = new NumericProperty<Streak>(this);
	color.property.value(0xffcc9966);
    }
    @Override
    public void paint(Graphics2D g2) {
	int cap = BasicStroke.CAP_ROUND;
	int join = BasicStroke.JOIN_ROUND;
	g2.setStroke(new BasicStroke(width.property.value().intValue(), cap, join));
	g2.setPaint(new Color(color.property.value().intValue(), true));
	g2.draw(new Line2D.Double(startX.property.value(), startY.property.value(), endX.property.value(), endY.property.value()));
    }
}
