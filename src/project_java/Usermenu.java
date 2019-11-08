package project_java;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Usermenu{
	Image img = new ImageIcon(this.getClass().getResource("/img/eskrim.jpg")).getImage();

	
	JFrame Userframe;
	Container c;
	JMenuBar menubar;
	JMenu account_menu, transaction_menu;
	JMenuItem logoff_item, exit_item, buy_item;
	JLabel gambar; 
	
	public void Usermenu() {
		Userframe = new JFrame(); 
		Userframe.setSize(1200, 700);
		Userframe.setLocationRelativeTo(null);
		Userframe.setTitle("Main Form");
		
		c = Userframe.getContentPane();
		
		//------------------------menubar----------------------------
		menubar = new JMenuBar();
			
			//----------------------menu Account----------------------
			account_menu = new JMenu("Account");
		
				logoff_item = new JMenuItem("Logoff");
				exit_item = new JMenuItem("Exit"); 
				
				account_menu.add(logoff_item);
				account_menu.add(exit_item);
			//-------------------------end account menu--------------------	

			//----------------------menu transaction-------------------------
			transaction_menu = new JMenu("Transaction");
			
			buy_item = new JMenuItem("Buy Product"); 
			
			transaction_menu.add(buy_item);
			//-------------------------end transaction menu--------------------

		menubar.add(account_menu);
		menubar.add(transaction_menu);
		
		Userframe.setJMenuBar(menubar);
		
		//-------------------gambar-----------------
		gambar = new JLabel("New label");
		gambar.setIcon(new ImageIcon(img));
		gambar.setBounds(0, 0, 1200,700);
		Userframe.getContentPane().add(gambar);
		
		Userframe.add(gambar);
		//----------------------end gambar---------------------

		Userframe.setVisible(true);			
	}
}