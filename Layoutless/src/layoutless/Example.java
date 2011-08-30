package layoutless;

import javax.swing.*;
import java.awt.*;

public class Example extends JFrame {
    private Layoutless layoutless;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel iLabel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static void main(String[] args) {
	JFrame frame = new Example();
	frame.setVisible(true);
    }
    public Example() {
	setLayout(new BorderLayout());
	setSize(400, 300);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {	
	jLabel1 = new javax.swing.JLabel();
	jLabel2 = new javax.swing.JLabel();
	jLabel3 = new javax.swing.JLabel();
	jTextField1 = new javax.swing.JTextField();
	jTextField2 = new javax.swing.JTextField();
	jPasswordField1 = new javax.swing.JPasswordField();
	jButton1 = new javax.swing.JButton();
	jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	jLabel1.setText("jLabel1");
	jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	jLabel2.setText("jLabel2");
	jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	jLabel3.setText("jLabel3");
	jTextField1.setText("jTextField1");
	jTextField2.setText("jTextField2");
	jPasswordField1.setText("jPasswordField1");
	jButton1.setText("jButton1");
	ImageIcon icon = new ImageIcon(getClass().getResource("Example.png"));
	iLabel = new javax.swing.JLabel();
	iLabel.setIcon(icon);
	layoutless = new Layoutless();
	int labelsWidth=150;
	layoutless
		.item(new ComponentBox()
		    .component(iLabel)
		    .width(253)
		    .height(256)
		    .x(0)
		    .y(0)
		    )
		.item(new ComponentBox()
		    .component(jLabel1)
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(jTextField1)
		    .width(layoutless.width().minus(labelsWidth).minus(16))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(jLabel2)
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
		    .component(jLabel3)
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(jPasswordField1)
		    .width(layoutless.width().minus(labelsWidth).minus(16))
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
		;
	this.add(layoutless, BorderLayout.CENTER);
    }
}
