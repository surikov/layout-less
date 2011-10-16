package layoutless.controls;
import java.awt.event.*;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
public class SimpleIcon extends JLabel {
    private It<Icon> icon;
    public SimpleIcon() {
	super();
	icon = new It<Icon>().afterChange(new Task() {
	    @Override public void doTask() {
		if (icon != null) {
		    setIcon(icon.value());
		}
	    }
	});
	this.addComponentListener(new ComponentListener() {
	    @Override public void componentResized(ComponentEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
		//adjust();
	    }
	    @Override public void componentMoved(ComponentEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	    @Override public void componentShown(ComponentEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	    @Override public void componentHidden(ComponentEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	});
    }
    private void adjust() {
	if (icon.value() == null) {
	    return;
	}
	int cw = getSize().width;
	int ch = getSize().height;
	int iw = icon.value().getIconWidth();
	int ih = icon.value().getIconHeight();
	if (cw >= 0 && ch >= 0 && iw >= 0 && ih >= 0) {
	    System.out.println("" + cw + "x" + ch+"/" + iw + "x" + ih);
	}
    }
    public SimpleIcon icon(Icon it) {
	icon.value(it);
	return this;
    }
    public SimpleIcon icon(It<Icon> it) {
	icon.bind(it);
	return this;
    }
    public It<Icon> icon() {
	return icon;
    }
}
