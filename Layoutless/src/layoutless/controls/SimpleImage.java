package layoutless.controls;

import java.awt.event.*;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import java.awt.*;

public class SimpleImage extends JComponent {

    private It<Image> image;
    private int iconWidth;
    private int iconHeight;

    public SimpleImage() {
	super();
	image = new It<Image>()//.afterChange(new Task() {

	    //@Override public void doTask() {
		/* if (icon != null) {
		 setIcon(icon.value());
		 } */
//	    }
//	})
;
	/*this.addComponentListener(new ComponentListener() {

	    @Override public void componentResized(ComponentEvent e) {
		//adjust();
	    }

	    @Override public void componentMoved(ComponentEvent e) {
	    }

	    @Override public void componentShown(ComponentEvent e) {
	    }

	    @Override public void componentHidden(ComponentEvent e) {
	    }
	});*/
    }

    public void paint(Graphics g) {
	if (image != null) {
	    if (image.value() != null) {
		int cw = getSize().width;
		int ch = getSize().height;
		readImageSize();
		//System.out.println("repaint "+cw+"x"+ch+" / "+iconWidth+"x"+iconHeight);
		//ImageIcon ii=(ImageIcon)icon.value();
		//g.fillOval(0, 0, iconWidth, iconHeight);
		g.drawImage(image.value(), 0, 0, cw,ch,null);
	    }
	}
    }

    /*private void adjust() {
	if (icon.value() == null) {
	    return;
	}
	int cw = getSize().width;
	int ch = getSize().height;
	if (cw >= 0 && ch >= 0 && iconWidth >= 0 && iconHeight >= 0) {
	    System.out.println("" + cw + "x" + ch + "/" + iconWidth + "x" + iconHeight);
	}
    }*/
    private void readImageSize() {
	if (image != null) {
	    if (image.value() != null) {
		iconWidth = image.value().getWidth(null);
		iconHeight = image.value().getHeight(null);
	    }
	}
    }

    public SimpleImage image(Image it) {
	image.value(it);
	readImageSize();
	return this;
    }

    public SimpleImage image(It<Image> it) {
	image.bind(it);
	readImageSize();
	return this;
    }

    public It<Image> image() {
	return image;
    }
}
