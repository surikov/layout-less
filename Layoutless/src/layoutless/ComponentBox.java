package layoutless;

import javax.swing.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import java.awt.*;

/**
 * 
 * @author User
 */
public class ComponentBox extends JPanel {
    public NumericProperty<ComponentBox> width;
    public NumericProperty<ComponentBox> height;
    public NumericProperty<ComponentBox> x;
    public NumericProperty<ComponentBox> y;
    
    public ComponentBox() {
	width = new NumericProperty<ComponentBox>(this);
	width.property.value(100).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	height = new NumericProperty<ComponentBox>(this);
	height.property.value(100).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	x = new NumericProperty<ComponentBox>(this);
	x.property.value(0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	y = new NumericProperty<ComponentBox>(this);
	y.property.value(0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	setOpaque(false);
	setLayout(new BorderLayout());
    }
    private void adjust() {
	if (width == null || height == null || x == null || y == null) {
	    return;
	}
	if (width.property == null || height.property == null || x.property == null || y.property == null) {
	    return;
	}
	if (width.property.value() > 1 && height.property.value() > 1) {
	    Dimension d = new Dimension(width.property.value().intValue(), height.property.value().intValue());
	    this.setSize(d);
	    this.setPreferredSize(d);
	}
	this.setLocation(x.property.value().intValue(), y.property.value().intValue());
	this.validate();
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox component(JComponent it) {
	this.removeAll();
	this.add(it, BorderLayout.CENTER);
	adjust();
	return this;
    }
    /**
     * 
     */
    public void unbind() {
	this.x.property.unbind();
	this.y.property.unbind();
	this.width.property.unbind();
	this.height.property.unbind();
    }
}