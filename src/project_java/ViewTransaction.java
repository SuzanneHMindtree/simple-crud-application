package project_java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewTransaction {
	Koneksi konek = new Koneksi();
	
	Vector<String> header = new Vector<>();
	
	Vector<Object> transaction;
	
	Vector<String> header2 = new Vector<>();
	
	Vector<Object> Product;
	
	JPanel atas, bawah;
	
	JFrame viewtrans;
	JScrollPane scroll, scroll2;
	JTable table, table2;

	DefaultTableModel dtm;
	DefaultTableModel dtm2;
	
	ResultSetMetaData rsm;
	ResultSetMetaData rsm2;
	
	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	
	String TransactionID;
	
	
	public void view(){
		viewtrans = new JFrame();
		viewtrans.setSize(1000, 600);
		viewtrans.setLocationRelativeTo(null);
		viewtrans.setLayout(new GridLayout(2, 1));
		viewtrans.setTitle("View Transaction");
		
		header = new Vector<>();
		header.add("Transaction ID");
		header.add("User ID");
		header.add("User Email");
		header.add("Transaction Date");
		dtm = new DefaultTableModel(header, 0);
		
		//-------------------------------------table atas---------------------------------
		try {
			konek.koneksi();
			String sql = "Select TransactionID, ht.UserID, UserEmail, TransactionDate from headertransaction ht, user u where u.UserID = ht.UserID";
			ResultSet rs=konek.stmt.executeQuery(sql);
			rsm = rs.getMetaData();
			while(rs.next()){
				transaction = new Vector<>();
				for(int i = 1; i<= rsm.getColumnCount(); i++){
					transaction.add(rs.getObject(i));
					
				}
					dtm.addRow(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable(dtm);
		
		scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(0, 350));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TransactionID = table.getValueAt(table.getSelectedRow(), 0).toString();
				//select all data
				try {
					dtm2.setRowCount(0);
					konek.koneksi();
					String sql = "Select p.ProductID, ProductName, Qty from detailtransaction dt, product p where p.ProductID = dt.ProductID and TransactionID = '"+TransactionID+"'";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm2 = rs.getMetaData();
					while(rs.next()){
						Product = new Vector<>();
						for(int i = 1; i<= rsm2.getColumnCount(); i++){
							Product.add(rs.getObject(i));
							
						}
							dtm2.addRow(Product);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		//------------------------------------table bawah-----------------------------------
		header2 = new Vector<>();
		header2.add("Product ID");
		header2.add("Product Name");
		header2.add("Quantity");
		dtm2 = new DefaultTableModel(header2, 0);
		
	
		
		
		table2 = new JTable(dtm2);
		
		
		scrollpane2 = new JScrollPane(table2);
		scrollpane2.setPreferredSize(new Dimension(0, 350));
		
		
		
		
				
		viewtrans.add(scrollpane);
		viewtrans.add(scrollpane2);
					
		viewtrans.setVisible(true);
		
	}
	
	



}
