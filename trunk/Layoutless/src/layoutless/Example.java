package layoutless;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import layoutless.controls.*;

public class Example extends JFrame {
    private Layoutless layoutless;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static void main(String[] args) {
	JFrame frame = new Example();
	frame.setVisible(true);
    }
    public Example() {
	setLayout(new BorderLayout());
	setSize(400, 200);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {	
	iconLabel= new javax.swing.JLabel();
	jTextField1 = new javax.swing.JTextField();
	jTextField2 = new javax.swing.JTextField();
	jPasswordField1 = new javax.swing.JPasswordField();
	jButton1 = new javax.swing.JButton();
	jButton2 = new javax.swing.JButton();
	jTextField1.setText("jTextField1");
	jTextField2.setText("jTextField2");
	jPasswordField1.setText("jPasswordField1");
	jButton1.setText("jButton1");
	jButton2.setText("...");
	iconLabel.setIcon(new ImageIcon(getClass().getResource("Example.png")));
	layoutless = new Layoutless();
	int labelsWidth=150;
	layoutless
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("File")
			.alignTextLeft(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(jTextField1)
		    .width(layoutless.width().minus(labelsWidth).minus(16).minus(50))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(jButton2)
		    .width(49)
		    .height(21)
		    .x(layoutless.width().minus(58))
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("Name")
			.alignTextLeft(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*1)
		    )
		.item(new ComponentBox()
		    .component(jTextField2)
		    .width(layoutless.width().minus(labelsWidth).minus(16))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*1)
		    )
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("Password")
			.alignTextLeft(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(jPasswordField1)
		    .width(150)
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(jButton1)
		    .width(90)
		    .height(27)
		    .x(labelsWidth+8)
		    .y(layoutless.height().minus(40))
		    )
		.item(new ComponentBox()
		    .component(iconLabel)
		    .width(128)
		    .height(128)
		    .x(0)
		    .y(layoutless.height().minus(140))
		    )		
		;
	this.add(layoutless, BorderLayout.CENTER);
    }
}
