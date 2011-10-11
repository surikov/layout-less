package layoutless.demo;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import layoutless.ComponentBox;
import layoutless.Layoutless;
import layoutless.controls.*;
import tee.binding.*;

public class Example extends JFrame {
    private Layoutless layoutless;
    //private javax.swing.JButton jButton1;
    //private javax.swing.JButton jButton2;
    private javax.swing.JLabel iconLabel;
    //private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static void main(String[] args) {
	JFrame frame = new Example();
	frame.setVisible(true);
    }
    public Example() {
	setLayout(new BorderLayout());
	setSize(500, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {
	iconLabel= new javax.swing.JLabel();
	jTextField1 = new javax.swing.JTextField();
	jTextField2 = new javax.swing.JTextField();
	//jPasswordField1 = new javax.swing.JPasswordField();
	//jButton1 = new javax.swing.JButton();
	//jButton2 = new javax.swing.JButton();
	jTextField1.setText("jTextField1");
	jTextField2.setText("jTextField2");
	//jPasswordField1.setText("jPasswordField1");
	//jButton1.setText("jButton1");
	//jButton2.setText("...");
	//iconLabel.setIcon(new ImageIcon(getClass().getResource("Example.png")));
	iconLabel.setIcon(new ImageIcon("keys.png"));
	layoutless = new Layoutless();
	int labelsWidth=150;
	final SimpleTextField fle=new SimpleTextField();
	final SimplePasswordField psw=new SimplePasswordField();
	layoutless
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("File")
			.normalAlignment(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(fle
			.text("123")
			)
		    .width(layoutless.width().minus(labelsWidth).minus(16).minus(50))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*0)
		    )
		/*.item(new ComponentBox()
		    .component(jButton2)
		    .width(49)
		    .height(21)
		    .x(layoutless.width().minus(58))
		    .y(8+25*0)
		    )*/
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("...")
			//.icon(new ImageIcon("ok.png"))
			.task(new Task(){@Override public void doTask() {
				System.out.println("...");
				}
			    })
			.normalAlignment(true))
		    .width(49)
		    .height(21)
		    .x(layoutless.width().minus(58))
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("Name")
			.normalAlignment(false))
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
			.normalAlignment(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(psw.text("111"))
		    .width(150)
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("jb1")
			.icon(new ImageIcon("ok.png"))
			.task(new Task(){@Override public void doTask() {
				System.out.println(
					//fle.text().value()
					psw.text().value()
					);
				}
			    })
			.normalAlignment(true))
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
