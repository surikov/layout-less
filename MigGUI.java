package layoutless;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.*;

public class MigGUI extends JFrame {
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static void main(String[] args) {
	JFrame frame = new MigGUI();
	frame.setVisible(true);
    }
    public MigGUI() {
	
	setSize(400, 200);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {
	MigLayout layout = new MigLayout("fillx", "60[150]5[]0", "0[]7[]7[]push[]0");
	setLayout(layout);
	
	jLabel1 = new javax.swing.JLabel();
	jLabel2 = new javax.swing.JLabel();
	jLabel3 = new javax.swing.JLabel();
	iconLabel = new javax.swing.JLabel();
	jTextField1 = new javax.swing.JTextField();
	jTextField2 = new javax.swing.JTextField();
	jPasswordField1 = new javax.swing.JPasswordField();
	jButton1 = new javax.swing.JButton();
	jButton2 = new javax.swing.JButton();
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
	jButton2.setText("...");
	iconLabel.setIcon(new ImageIcon(getClass().getResource("Example.png")));
	this.add(jLabel1);
	this.add(jTextField1, "growx, split" );
	this.add(jButton2, "wrap");
	this.add(jLabel2);
	this.add(jTextField2, "wrap");
	this.add(jLabel3);
	this.add(jPasswordField1, "wrap");
	this.add(jButton1, "skip");
    }
}
